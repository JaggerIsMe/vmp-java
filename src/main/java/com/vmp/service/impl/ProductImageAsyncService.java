package com.vmp.service.impl;

import com.vmp.component.ProductImageTaskRegistry;
import com.vmp.utils.OKHttpUtils;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service("productImageAsyncService")
public class ProductImageAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(ProductImageAsyncService.class);

    @Resource
    private ProductImageTaskRegistry productImageTaskRegistry;

    /**
     * 异步下载产品图片。
     *
     * 注意：该方法必须由其他Spring Bean调用，
     * 不能在当前类中通过this.downloadIfAbsent调用。
     */
    @Async("productImageExecutor")
    public void downloadIfAbsent(String taskKey, String productImgUrl, Path targetPath) {
        Path tempPath = null;

        try {
            /*
             * 任务真正执行时再次检查。
             * 图片可能已经被前一个任务下载完成。
             */
            if (isUsableImage(targetPath)) {
                return;
            }

            if (!isSupportedImageUrl(productImgUrl)) {
                logger.warn("Shopify产品图片地址不合法，url:{}", productImgUrl);
                return;
            }

            Files.createDirectories(targetPath.getParent());

            /*
             * 临时文件和正式文件放在同一目录，
             * 为后续原子移动提供条件。
             */
            tempPath = targetPath.resolveSibling(targetPath.getFileName().toString() + "." + UUID.randomUUID() + ".part");

            OKHttpUtils.downloadFile(productImgUrl, tempPath.toFile());

            if (!isUsableImage(tempPath)) {
                logger.warn("下载的Shopify产品图片为空，url:{}",productImgUrl);
                return;
            }

            moveToTarget(tempPath, targetPath);

            logger.info("Shopify产品图片下载成功，path:{}",targetPath);
        } catch (Exception e) {
            /*
             * 异步下载失败只记录日志。
             * 当前HTTP请求已经返回默认图片，不能让异常影响请求。
             */
            logger.error("异步下载Shopify产品图片失败",e);
        } finally {
            if (tempPath != null) {
                try {
                    Files.deleteIfExists(tempPath);
                } catch (Exception e) {
                    logger.warn("清理Shopify产品图片临时文件失败，path:{}", tempPath, e);
                }
            }

            /*
             * 无论成功、失败、URL为空还是产品不存在，都必须释放任务标记。
             */
            productImageTaskRegistry.release(taskKey);
        }
    }

    /**
     * 限制为HTTPS地址
     */
    private boolean isSupportedImageUrl(String sourceUrl) {
        try {
            URI uri = URI.create(sourceUrl);

            return "https".equalsIgnoreCase(uri.getScheme()) && !StringTools.isEmpty(uri.getHost());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isUsableImage(Path imagePath) {
        if (imagePath == null) {
            return false;
        }

        try {
            return Files.isRegularFile(imagePath)
                    && Files.size(imagePath) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 优先原子移动，文件系统不支持时回退为普通替换移动。
     */
    private void moveToTarget(Path tempPath, Path targetPath) throws Exception {

        try {
            Files.move(tempPath, targetPath, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(tempPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}