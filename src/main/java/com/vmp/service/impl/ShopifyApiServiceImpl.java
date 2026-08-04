package com.vmp.service.impl;

import com.vmp.entity.apidto.*;
import com.vmp.entity.config.ShopifyConfig;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.service.ShopifyApiService;
import com.vmp.utils.DateUtil;
import com.vmp.utils.JsonUtils;
import com.vmp.utils.OKHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Shopify业务接口
 */
@Service("shopifyApiService")
public class ShopifyApiServiceImpl implements ShopifyApiService {

    private static final Logger logger = LoggerFactory.getLogger(ShopifyApiServiceImpl.class);

    @Resource
    private ShopifyConfig shopifyConfig;

    /**
     * 获取Access Token
     *
     * @param host
     * @return
     */
    @Override
    public String getAccessToken(String host) {
        String url = "https://" + host + ".myshopify.com/admin/oauth/access_token";
        String clientId = shopifyConfig.getClientId();
        String clientSecret = shopifyConfig.getClientSecret();

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);

        String respStr = OKHttpUtils.postRequest(url, params);
        ShopifyToken token = JsonUtils.convertJson2Obj(respStr, ShopifyToken.class);

        return token.getAccessToken();
    }

    /**
     * 获取店铺信息
     *
     * @param host
     * @return
     */
    @Override
    public ShopifyShopInfo getShopifyShopInfo(String host) {
        String url = "https://" + host + ".myshopify.com/admin/api/" + shopifyConfig.getApiVersion() + "/graphql.json";

        Map<String, String> header = new HashMap<>();
        header.put("X-Shopify-Access-Token", getAccessToken(host));

        String query =
                "query {" +
                        "  shop {" +
                        "    id" +
                        "    name" +
                        "    currencyCode" +
                        "  }" +
                        "}";

        Map<String, Object> variables = new HashMap<>();

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        String respStr = OKHttpUtils.postJsonRequest(url, body, header);
        ShopifyApiResult result = JsonUtils.convertJson2Obj(respStr, ShopifyApiResult.class);
        ShopifyDataResponse dataResponse = JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(result.getData()), ShopifyDataResponse.class);

        return JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(dataResponse.getShop()), ShopifyShopInfo.class);
    }

    /**
     * 获取店铺产品-变体
     *
     * @param host
     * @param first
     * @param after
     * @return
     */
    @Override
    public ShopifyProductPageInfo getShopifyProducts(String host, Integer first, String after) {
        String url = "https://" + host + ".myshopify.com/admin/api/" + shopifyConfig.getApiVersion() + "/graphql.json";

        Map<String, String> header = new HashMap<>();
        header.put("X-Shopify-Access-Token", getAccessToken(host));

        String query =
                "query ListAllProductVariants($first: Int!, $after: String) {" +
                        "  productVariants(first: $first, after: $after) {" +
                        "    nodes {" +
                        "      id" +
                        "      title" +
                        "      sku" +
                        "      barcode" +
                        "      price" +
                        "      createdAt" +
                        "      displayName" +
                        "      availableForSale" +
                        "      image {" +
                        "        url" +
                        "      }" +
                        "      inventoryItem {" +
                        "        id" +
                        "        inventoryLevels(first: 10) {" +
                        "          nodes {" +
                        "            id" +
                        "            location {" +
                        "              id" +
                        "              name" +
                        "            }" +
                        "            quantities(names: [\"available\"]) {" +
                        "              name" +
                        "              quantity" +
                        "            }" +
                        "          }" +
                        "        }" +
                        "      }" +
                        "      product {" +
                        "        id" +
                        "        title" +
                        "        handle" +
                        "        onlineStoreUrl" +
                        "        totalVariants" +
                        "        status" +
                        "        tags" +
                        "        createdAt" +
                        "        productType" +
                        "        vendor" +
                        "        category {" +
                        "          name" +
                        "        }" +
                        "        media(first: 1) {" +
                        "          nodes {" +
                        "            preview {" +
                        "              image {" +
                        "                url" +
                        "              }" +
                        "            }" +
                        "          }" +
                        "        }" +
                        "      }" +
                        "    }" +
                        "    pageInfo {" +
                        "      hasNextPage" +
                        "      endCursor" +
                        "    }" +
                        "  }" +
                        "}";

        Map<String, Object> variables = new HashMap<>();
        variables.put("first", first);
        variables.put("after", after);

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        String respStr = OKHttpUtils.postJsonRequest(url, body, header);
        ShopifyApiResult result = JsonUtils.convertJson2Obj(respStr, ShopifyApiResult.class);
        ShopifyDataResponse dataResponse = JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(result.getData()), ShopifyDataResponse.class);

        return JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(dataResponse.getProductVariants()), ShopifyProductPageInfo.class);
    }

    /**
     * 获取店铺订单
     *
     * @param host
     * @param first
     * @param after
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public ShopifyOrderPageInfo getShopifyOrders(String host, Integer first, String after, Date startDate, Date endDate) {
        String url = "https://" + host + ".myshopify.com/admin/api/" + shopifyConfig.getApiVersion() + "/graphql.json";

        Map<String, String> header = new HashMap<>();
        header.put("X-Shopify-Access-Token", getAccessToken(host));

        String query =
                "query ListOrders($query: String, $first: Int!, $after: String) {" +
                        "  orders(first: $first, after: $after, reverse: false, sortKey: CREATED_AT, query: $query) {" +
                        "    nodes {" +
                        "      id" +
                        "      name" +
                        "      number" +
                        "      createdAt" +
                        "      sourceName" +
                        "      displayFinancialStatus" +
                        "      displayFulfillmentStatus" +
                        "      taxesIncluded" +
                        "      totalPriceSet {" +
                        "        shopMoney {" +
                        "          amount" +
                        "          currencyCode" +
                        "        }" +
                        "      }" +
                        "      totalDiscountsSet {" +
                        "        shopMoney {" +
                        "          amount" +
                        "          currencyCode" +
                        "        }" +
                        "      }" +
                        "      totalRefundedSet {" +
                        "        shopMoney {" +
                        "          amount" +
                        "          currencyCode" +
                        "        }" +
                        "      }" +
                        "      discountCodes" +
                        "      email" +
                        "      displayAddress {" +
                        "        address1" +
                        "        address2" +
                        "        city" +
                        "        company" +
                        "        country" +
                        "        countryCodeV2" +
                        "        formattedArea" +
                        "        id" +
                        "        firstName" +
                        "        lastName" +
                        "        name" +
                        "        phone" +
                        "        province" +
                        "        provinceCode" +
                        "        zip" +
                        "      }" +
                        "      returnStatus" +
                        "      lineItems(first: 50) {" +
                        "        nodes {" +
                        "          id" +
                        "          product {" +
                        "            id" +
                        "          }" +
                        "          variant {" +
                        "            id" +
                        "          }" +
                        "          quantity" +
                        "          refundableQuantity" +
                        "          originalTotalSet {" +
                        "            shopMoney {" +
                        "              amount" +
                        "              currencyCode" +
                        "            }" +
                        "          }" +
                        "          originalUnitPriceSet {" +
                        "            shopMoney {" +
                        "              amount" +
                        "              currencyCode" +
                        "            }" +
                        "          }" +
                        "          discountedUnitPriceAfterAllDiscountsSet {" +
                        "            shopMoney {" +
                        "              amount" +
                        "              currencyCode" +
                        "            }" +
                        "          }" +
                        "          totalDiscountSet {" +
                        "            shopMoney {" +
                        "              amount" +
                        "              currencyCode" +
                        "            }" +
                        "          }" +
                        "        }" +
                        "      }" +
                        "      refunds {" +
                        "        id" +
                        "        refundLineItems(first: 50) {" +
                        "          nodes {" +
                        "            id" +
                        "            quantity" +
                        "            lineItem {" +
                        "              id" +
                        "              product {" +
                        "                id" +
                        "              }" +
                        "              variant {" +
                        "                id" +
                        "              }" +
                        "            }" +
                        "          }" +
                        "        }" +
                        "      }" +
                        "    }" +
                        "    pageInfo {" +
                        "      hasNextPage" +
                        "      endCursor" +
                        "    }" +
                        "  }" +
                        "}";

        Map<String, Object> variables = new HashMap<>();
        variables.put("first", first);
        variables.put("after", after);
        String queryFilter =
                "(created_at:>=" + DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()) + " AND created_at:<=" + DateUtil.format(endDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()) +
                        ") OR (processed_at:>=" + DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()) + " AND processed_at:<=" + DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()) +
                        ") OR (updated_at:>=" + DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()) + " AND updated_at:<=" + DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()) + ")";
        variables.put("query", queryFilter);

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        String respStr = OKHttpUtils.postJsonRequest(url, body, header);
        ShopifyApiResult result = JsonUtils.convertJson2Obj(respStr, ShopifyApiResult.class);
        ShopifyDataResponse dataResponse = JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(result.getData()), ShopifyDataResponse.class);

        return JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(dataResponse.getOrders()), ShopifyOrderPageInfo.class);
    }

    /**
     * 分析官网流量Sessions
     *
     * @param host
     * @param type
     * @param day
     * @return
     */
    @Override
    public ShopifyqlQueryResponse getShopifySessionsReport(String host, String type, Date day) {
        String url = "https://" + host + ".myshopify.com/admin/api/" + shopifyConfig.getApiVersion() + "/graphql.json";

        Map<String, String> header = new HashMap<>();
        header.put("X-Shopify-Access-Token", getAccessToken(host));

        String shopifyql =
                "FROM sessions " +
                        "SHOW online_store_visitors, sessions, sessions_with_cart_additions, sessions_that_reached_checkout " +
                        "WHERE landing_page_path IS NOT NULL " +
                        "AND human_or_bot_session = 'human' " +
                        "AND landing_page_type = '" + type + "' " +
                        "GROUP BY landing_page_type, landing_page_path WITH TOTALS " +
                        "SINCE " + DateUtil.format(day, DateTimePatternEnum.YYYY_MM_DD.getPattern()) + " UNTIL " + DateUtil.format(day, DateTimePatternEnum.YYYY_MM_DD.getPattern()) + " " +
                        "ORDER BY online_store_visitors DESC " +
                        "LIMIT 1000";
        String query =
                "query {" +
                        "  shopifyqlQuery(" +
                        "    query: \"" + shopifyql + "\") {" +
                        "    tableData {" +
                        "      columns {" +
                        "        name" +
                        "        dataType" +
                        "        displayName" +
                        "      }" +
                        "      rows" +
                        "    }" +
                        "    parseErrors" +
                        "  }" +
                        "}";

        Map<String, Object> variables = new HashMap<>();

        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        String respStr = OKHttpUtils.postJsonRequest(url, body, header);
        ShopifyApiResult result = JsonUtils.convertJson2Obj(respStr, ShopifyApiResult.class);
        ShopifyDataResponse dataResponse = JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(result.getData()), ShopifyDataResponse.class);

        return JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(dataResponse.getShopifyqlQuery()), ShopifyqlQueryResponse.class);
    }
}
