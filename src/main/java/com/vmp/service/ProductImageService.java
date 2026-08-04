package com.vmp.service;

import com.vmp.entity.vo.ProductImageVO;

public interface ProductImageService {

    /**
     * Shopify获取当前请求应该返回的父体产品图片。
     * <p>
     * 本地正式图片不存在时：
     * 1. 异步触发原图下载；
     * 2. 当前请求返回默认图片。
     */
    ProductImageVO resolveShopifyProductImage(String storeId, String productId);
}