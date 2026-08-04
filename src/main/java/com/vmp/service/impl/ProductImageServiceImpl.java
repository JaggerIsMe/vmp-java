package com.vmp.service.impl;

import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.po.ShopifyProduct;
import com.vmp.entity.vo.ProductImageVO;
import com.vmp.exception.BusinessException;
import com.vmp.service.ProductImageService;
import com.vmp.service.ShopifyProductService;
import com.vmp.component.ProductImageTaskRegistry;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {

    private static final Logger logger = LoggerFactory.getLogger(ProductImageServiceImpl.class);

    private static final String SHOPIFY_IMAGE_FOLDER = Constants.FILE_FOLDER_FILE + Constants.FILE_FOLDER_PRODUCT_IMG + Constants.FILE_FOLDER_PRODUCT_IMG_SHOPIFY;

    @Resource
    private AppConfig appConfig;

    @Resource
    private ProductImageAsyncService productImageAsyncService;

    @Resource
    private ProductImageTaskRegistry productImageTaskRegistry;

    @Resource
    private ShopifyProductService productService;

    /**
     * Shopify获取当前请求应该返回的父体产品图片。
     * <p>
     * 本地正式图片不存在时：
     * 1. 异步触发原图下载；
     * 2. 当前请求返回默认图片。
     */
    @Override
    public ProductImageVO resolveShopifyProductImage(String storeId, String productId) {
        String productImgId = StringTools.createShopifyProductImgId(storeId, productId);

        Path imageFolder = getImageFolder(SHOPIFY_IMAGE_FOLDER);

        Path targetPath = imageFolder.resolve(productImgId + Constants.AVATAR_SUFFIX).toAbsolutePath().normalize();

        /*
         * 防止storeId、productId被构造成路径穿越参数。
         */
        if (!targetPath.startsWith(imageFolder)) {
            throw new BusinessException("产品图片路径非法");
        }

        try {
            Files.createDirectories(imageFolder);
        } catch (Exception e) {
            /*
             * 创建缓存目录失败时仍尝试返回默认图片。
             */
            logger.error("创建Shopify产品图片目录失败，path:{}", imageFolder, e);
        }

        /*
         * 正式图片已存在，直接返回。
         */
        if (isUsableImage(targetPath)) {
            ProductImageVO imageVO = new ProductImageVO();
            imageVO.setImagePath(targetPath.toString());
            imageVO.setDefaultImage(false);
            imageVO.setAvailable(true);
            return imageVO;
        }

        /*
         * 使用产品图片ID作为单实例任务去重键。
         */
        String taskKey = productImgId;

        if (productImageTaskRegistry.acquire(taskKey)) {
            try {
                ShopifyProduct product = productService.getShopifyProductByStoreIdAndProductIdAndVariantId(storeId, productId, Constants.SHOPIFY_PARENT_PRODUCT);
                if (null == product) {
                    throw new BusinessException("产品不存在");
                }
                String productImgUrl = product.getProductImgUrl();
                if (StringTools.isEmpty(productImgUrl)) {
                    throw new BusinessException("当前Shopify产品没有可下载的图片地址");
                }
                productImageAsyncService.downloadIfAbsent(taskKey, productImgUrl, targetPath);
            } catch (TaskRejectedException e) {
                /*
                 * 线程池拒绝任务时，异步方法不会执行，
                 * 因此必须在这里释放任务标记。
                 */
                productImageTaskRegistry.release(taskKey);

                logger.warn("Shopify产品图片下载队列已满，taskKey:{}", taskKey, e);
            } catch (RuntimeException e) {
                /*
                 * 包括异步代理调用异常等情况。
                 */
                productImageTaskRegistry.release(taskKey);

                logger.error("提交Shopify产品图片下载任务失败，taskKey:{}", taskKey, e);
            }
        }

        /*
         * 无论当前任务由本请求提交，还是已有相同任务正在下载，
         * 当前请求都立即返回默认图片。
         */
        Path defaultImagePath = getDefaultImagePath();
        ProductImageVO imageVO = new ProductImageVO();
        imageVO.setImagePath(defaultImagePath.toString());
        imageVO.setDefaultImage(true);
        imageVO.setAvailable(isUsableImage(defaultImagePath));
        return imageVO;
    }

    private Path getImageFolder(String folder) {
        return Paths.get(appConfig.getProjectFolder() + folder).toAbsolutePath().normalize();
    }

    private Path getDefaultImagePath() {
        return Paths.get(appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.DEFAULT_IMG).toAbsolutePath().normalize();
    }

    private boolean isUsableImage(Path imagePath) {
        if (imagePath == null) {
            return false;
        }

        try {
            return Files.isRegularFile(imagePath) && Files.size(imagePath) > 0;
        } catch (Exception e) {
            return false;
        }
    }
}