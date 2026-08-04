package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.config.ShopifyConfig;
import com.vmp.entity.query.ShopifyOrderQuery;
import com.vmp.entity.query.ShopifyProductInventoryQuery;
import com.vmp.entity.query.ShopifyProductQuery;
import com.vmp.entity.vo.*;
import com.vmp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController("shopifyController")
@RequestMapping("/shopify")
public class ShopifyController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(ShopifyController.class);

    @Resource
    private ShopifyConfig shopifyConfig;

    @Resource
    private ShopifyApiService shopifyApiService;

    @Resource
    private ShopifyProductService shopifyProductService;

    @Resource
    private ShopifyOrderService shopifyOrderService;

    @Resource
    private ShopifySessionReportService shopifySessionReportService;

    @Resource
    private ShopifyProductInventoryService shopifyProductInventoryService;

    @Resource
    private ProductImageService productImageService;


    @RequestMapping("/getVantrueAccessToken")
    public ResponseVO getVantrueAccessToken() {
        return getSuccessResponseVO(shopifyApiService.getAccessToken(shopifyConfig.getVantrueHost()));
    }

    @RequestMapping("/getShopifyShopInfo")
    public ResponseVO getShopifyShopInfo(String host) {
        return getSuccessResponseVO(shopifyApiService.getShopifyShopInfo(host));
    }

    @RequestMapping("/getShopifyProducts")
    public ResponseVO getShopifyProducts(@RequestParam("host") String host, @RequestParam("first") Integer first, @RequestParam("after") String after) {
        return getSuccessResponseVO(shopifyApiService.getShopifyProducts(host, first, null));
    }

    @RequestMapping("/syncShopifyProducts")
    public ResponseVO syncShopifyProducts(@RequestParam("host") String host, @RequestParam("first") Integer first, @RequestParam("after") String after) {
        shopifyProductService.syncShopifyProducts(host, first, null);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/getShopifyOrders")
    public ResponseVO getShopifyOrders(@RequestParam("host") String host, @RequestParam("first") Integer first, @RequestParam("after") String after,
                                       @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                       @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return getSuccessResponseVO(shopifyApiService.getShopifyOrders(host, first, null, startDate, endDate));
    }

    @RequestMapping("/syncShopifyOrders")
    public ResponseVO syncShopifyOrders(@RequestParam("host") String host, @RequestParam("first") Integer first, @RequestParam("after") String after,
                                        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        shopifyOrderService.syncShopifyOrders(host, first, null, startDate, endDate);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/getShopifySessionsReport")
    public ResponseVO getShopifySessionsReport(@RequestParam("host") String host, @RequestParam("type") String type,
                                               @RequestParam("day") @DateTimeFormat(pattern = "yyyy-MM-dd") Date day) {
        return getSuccessResponseVO(shopifyApiService.getShopifySessionsReport(host, type, day));
    }

    @RequestMapping("/writeShopifySessionsReport")
    public ResponseVO writeShopifySessionsReport(@RequestParam("host") String host, @RequestParam("type") String type,
                                                 @RequestParam("day") @DateTimeFormat(pattern = "yyyy-MM-dd") Date day) {
        shopifySessionReportService.getShopifySessionsReport(host, type, day);
        return getSuccessResponseVO(null);
    }

    /**
     * 获取商品列表-父体
     *
     * @param query
     * @return
     */
    @GlobalInterceptor(checkParams = true)
    @RequestMapping("/loadShopifyParentProductList")
    public ResponseVO loadShopifyParentProductList(ShopifyProductQuery query) {
        PaginationResultVO resultVO = shopifyProductService.loadShopifyParentProductList(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, ShopifyProductVO.class));
    }

    /**
     * 获取指定商品下的变体列表-默认分页大小是10
     *
     * @param query
     * @return
     */
    @RequestMapping("/loadShopifyChildProductListUnderSpecificParent")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO loadShopifyChildProductListUnderSpecificParent(ShopifyProductQuery query) {
        PaginationResultVO resultVO = shopifyProductService.loadShopifyChildProductListUnderSpecificParent(query);
        return getSuccessResponseVO(convert2PaginationVO(resultVO, ShopifyProductVO.class));
    }

    /**
     * 获取指定Shopify产品库存详情
     *
     * @return
     */
    @RequestMapping("/getProductInventoryDetails/{storeId}/{productId}/{variantId}")
    public ResponseVO getProductInventoryDetails(@VerifyParam(required = true) @PathVariable("storeId") String storeId,
                                                 @VerifyParam(required = true) @PathVariable("productId") String productId,
                                                 @VerifyParam(required = true) @PathVariable("variantId") String variantId) {
        ShopifyProductInventoryQuery inventoryQuery = new ShopifyProductInventoryQuery();
        inventoryQuery.setStoreId(storeId);
        inventoryQuery.setProductId(productId);
        inventoryQuery.setVariantId(variantId);
        inventoryQuery.setOrderBy("warehouse_id desc");
        return getSuccessResponseVO(shopifyProductInventoryService.findListByParam(inventoryQuery));
    }

    /**
     * 修改指定商品信息(父体和子体)-分配品牌或人员
     *
     * @param newBrand
     * @param newPersonInCharge
     * @param param
     * @return
     */
    @RequestMapping("/updateBrandOrPerson4ShopifySpecificProduct")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO updateBrandOrPerson4ShopifySpecificProduct(@RequestParam(value = "newBrand", required = false) String newBrand,
                                                                 @RequestParam(value = "newPersonInCharge", required = false) String newPersonInCharge,
                                                                 ShopifyProductQuery param) {
        shopifyProductService.updateBrandOrPerson4ShopifySpecificProduct(newBrand, newPersonInCharge, param);
        return getSuccessResponseVO(null);
    }

    /**
     * 获取父体产品主图。
     * <p>
     * 本地图片不存在时，本次请求返回默认图片，
     * 同时后台异步下载原图片。
     */
    @RequestMapping("/getProductImg/{storeId}/{productId}")
    @GlobalInterceptor(checkParams = true)
    public void getProductImg(HttpServletResponse response, @VerifyParam(required = true) @PathVariable("storeId") String storeId,
                              @VerifyParam(required = true) @PathVariable("productId") String productId) {

        ProductImageVO productImage = productImageService.resolveShopifyProductImage(storeId, productId);

        /*
         * 正式图片和默认图片都不存在。
         */
        if (!Boolean.TRUE.equals(productImage.getAvailable())) {
            printNoDefaultImage(response);
            return;
        }

        if (Boolean.TRUE.equals(productImage.getDefaultImage())) {
            /*
             * 默认图片不能被浏览器长期缓存到产品图片URL下。
             * 否则即使后台下载完成，浏览器仍可能继续显示默认图片。
             */
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        } else {
            response.setHeader("Cache-Control", "public, max-age=3600");
        }

        response.setContentType("image/jpeg");
        readFile(response, productImage.getImagePath());
    }

    /**
     * 多条件查询获取订单列表
     *
     * @param query
     * @return
     */
    @RequestMapping("/loadShopifyOrderListByMultiQuery")
    @GlobalInterceptor(checkParams = true)
    public ResponseVO loadShopifyOrderListByMultiQuery(ShopifyOrderQuery query) {
        query.setOrderBy("created_at desc");
        return getSuccessResponseVO(convert2PaginationVO(shopifyOrderService.loadShopifyOrderListByMultiQuery(query), ShopifyOrderVO.class));
    }

    /**
     * 获取日销售表现
     *
     * @param query
     * @return
     */
    @GlobalInterceptor(checkParams = true)
    @RequestMapping("/getShopifySalesDailyPerformance")
    public ResponseVO getShopifySalesDailyPerformance(ShopifyOrderQuery query) {
        query.setOrderBy("created_at desc");
        return getSuccessResponseVO(shopifyOrderService.getShopifySalesDailyPerformance(query));
    }

}
