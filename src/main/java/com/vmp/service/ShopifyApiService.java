package com.vmp.service;

import com.vmp.entity.apidto.ShopifyOrderPageInfo;
import com.vmp.entity.apidto.ShopifyProductPageInfo;
import com.vmp.entity.apidto.ShopifyShopInfo;
import com.vmp.entity.apidto.ShopifyqlQueryResponse;

import java.util.Date;

public interface ShopifyApiService {

    /**
     * 获取Access Token
     *
     * @param host
     * @return
     */
    String getAccessToken(String host);

    /**
     * 获取店铺信息
     * @param host
     * @return
     */
    ShopifyShopInfo getShopifyShopInfo(String host);

    /**
     * 获取店铺产品-变体
     * @param host
     * @param first
     * @param after
     * @return
     */
    ShopifyProductPageInfo getShopifyProducts(String host, Integer first, String after);

    /**
     * 获取店铺订单
     * @param host
     * @param first
     * @param after
     * @param startDate
     * @param endDate
     * @return
     */
    ShopifyOrderPageInfo getShopifyOrders(String host, Integer first, String after, Date startDate, Date endDate);

    /**
     * 分析官网流量Sessions
     * @param host
     * @param type
     * @param day
     * @return
     */
    ShopifyqlQueryResponse getShopifySessionsReport(String host, String type, Date day);

}
