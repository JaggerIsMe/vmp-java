package com.vmp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.Resource;

import com.vmp.entity.apidto.ShopifyOrderApiDto;
import com.vmp.entity.apidto.ShopifyOrderPageInfo;
import com.vmp.entity.apidto.ShopifyShopInfo;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.po.ShopifyOrderItem;
import com.vmp.entity.po.ShopifyProduct;
import com.vmp.entity.po.ShopifySessionReport;
import com.vmp.entity.query.*;
import com.vmp.entity.vo.ShopifyOrderItemVO;
import com.vmp.entity.dashboardVo.ShopifySalesDailyPerformanceVo;
import com.vmp.exception.BusinessException;
import com.vmp.service.*;
import com.vmp.utils.CopyTools;
import com.vmp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.po.ShopifyOrder;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.mappers.ShopifyOrderMapper;
import com.vmp.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 官网订单表 业务接口实现
 */
@Service("shopifyOrderService")
public class ShopifyOrderServiceImpl implements ShopifyOrderService {

    private static final Logger logger = LoggerFactory.getLogger(ShopifyOrderServiceImpl.class);

    @Resource
    private ShopifyOrderMapper<ShopifyOrder, ShopifyOrderQuery> shopifyOrderMapper;

    @Resource
    private ShopifyApiService shopifyApiService;

    @Resource
    private ShopifyOrderItemService shopifyOrderItemService;

    @Resource
    private ShopifyProductService shopifyProductService;

    @Resource
    private ShopifySessionReportService shopifySessionReportService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<ShopifyOrder> findListByParam(ShopifyOrderQuery param) {
        return this.shopifyOrderMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(ShopifyOrderQuery param) {
        return this.shopifyOrderMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<ShopifyOrder> findListByPage(ShopifyOrderQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<ShopifyOrder> list = this.findListByParam(param);
        PaginationResultVO<ShopifyOrder> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(ShopifyOrder bean) {
        return this.shopifyOrderMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<ShopifyOrder> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.shopifyOrderMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<ShopifyOrder> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.shopifyOrderMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(ShopifyOrder bean, ShopifyOrderQuery param) {
        StringTools.checkParam(param);
        return this.shopifyOrderMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(ShopifyOrderQuery param) {
        StringTools.checkParam(param);
        return this.shopifyOrderMapper.deleteByParam(param);
    }

    /**
     * 根据StoreIdAndOrderId获取对象
     */
    @Override
    public ShopifyOrder getShopifyOrderByStoreIdAndOrderId(String storeId, String orderId) {
        return this.shopifyOrderMapper.selectByStoreIdAndOrderId(storeId, orderId);
    }

    /**
     * 根据StoreIdAndOrderId修改
     */
    @Override
    public Integer updateShopifyOrderByStoreIdAndOrderId(ShopifyOrder bean, String storeId, String orderId) {
        return this.shopifyOrderMapper.updateByStoreIdAndOrderId(bean, storeId, orderId);
    }

    /**
     * 根据StoreIdAndOrderId删除
     */
    @Override
    public Integer deleteShopifyOrderByStoreIdAndOrderId(String storeId, String orderId) {
        return this.shopifyOrderMapper.deleteByStoreIdAndOrderId(storeId, orderId);
    }

    /**
     * 同步Shopify订单数据-单页
     *
     * @param host
     * @param first
     * @param after
     * @param startDate
     * @param endDate
     * @param shopInfo
     * @return 返回当页的endCursor
     */
    @Transactional(rollbackFor = Exception.class)
    public String syncShopifyOrdersSinglePage(String host, Integer first, String after, Date startDate, Date endDate, ShopifyShopInfo shopInfo) {
        ShopifyOrderPageInfo orderPageInfo = shopifyApiService.getShopifyOrders(host, first, after, startDate, endDate);
        List<ShopifyOrderApiDto> nodes = orderPageInfo.getNodes();
        List<ShopifyOrderItem> orderItemList = new ArrayList<>();
        nodes.forEach(node -> {
            node.setStoreId(shopInfo.getId());
            node.setStoreName(shopInfo.getName());
            node.setCurrencyCode(shopInfo.getCurrencyCode());

            List<ShopifyOrderApiDto.LineItemNode> orderItemNodeList = node.getLineItems().getNodes();
            orderItemNodeList.forEach(orderItemNode -> {
                ShopifyOrderItem orderItem = new ShopifyOrderItem();
                orderItem.setOrderId(node.getOrderId());
                orderItem.setItemId(orderItemNode.getId());
                orderItem.setStoreId(shopInfo.getId());
                if (null != orderItemNode.getProduct()) {
                    orderItem.setProductId(orderItemNode.getProduct().getId());
                }
                if (null != orderItemNode.getVariant()) {
                    orderItem.setVariantId(orderItemNode.getVariant().getId());
                }
                orderItem.setQuantity(orderItemNode.getQuantity());
                orderItem.setRefundQuantity(orderItemNode.getQuantity() - orderItemNode.getRefundableQuantity());
                orderItem.setOriginalTotalPrice(orderItemNode.getOriginalTotalSet().getShopMoney().getAmount());
                orderItem.setOriginalUnitPrice(orderItemNode.getOriginalUnitPriceSet().getShopMoney().getAmount());
                orderItem.setDiscountedUnitPrice(orderItemNode.getDiscountedUnitPriceAfterAllDiscountsSet().getShopMoney().getAmount());
                orderItem.setTotalDiscount(orderItemNode.getTotalDiscountSet().getShopMoney().getAmount());
                orderItem.setCreatedAt(node.getCreatedAt());
                orderItemList.add(orderItem);
            });
        });

        List<ShopifyOrder> orders = CopyTools.copyList(nodes, ShopifyOrder.class);
        addOrUpdateBatch(orders);
        this.shopifyOrderItemService.addOrUpdateBatch(orderItemList);

        if (orderPageInfo.getPageInfo().getHasNextPage()) {
            return orderPageInfo.getPageInfo().getEndCursor();
        } else {
            return null;
        }
    }

    /**
     * 同步官网订单数据
     *
     * @param host
     * @param first
     * @param after
     * @param startDate
     * @param endDate
     */
    @Override
    public void syncShopifyOrders(String host, Integer first, String after, Date startDate, Date endDate) {
        ShopifyShopInfo shopInfo = shopifyApiService.getShopifyShopInfo(host);

        String cursor = after;
        do {
            cursor = syncShopifyOrdersSinglePage(host, first, cursor, startDate, endDate, shopInfo);
        } while (!StringTools.isEmpty(cursor));
    }

    // 根据多条件组装orderIdList
    private List<String> buildShopifyOrderQueryOrderIdList(ShopifyOrderQuery query) {
        // 店铺、品牌、负责人(即指定多产品)
        List<String> storeIdList = query.getStoreIdList();
        List<String> brandList = query.getBrandList();
        List<String> personInChargeList = query.getPersonInChargeList();
        if (null != storeIdList || null != brandList || null != personInChargeList) {
            ShopifyProductQuery productQuery = new ShopifyProductQuery();
            productQuery.setStoreIdList(storeIdList);
            productQuery.setBrandList(brandList);
            productQuery.setPersonInChargeList(personInChargeList);
            List<ShopifyProduct> productList = this.shopifyProductService.findListByParam(productQuery);

            // 指定日期范围
            ShopifyOrderItemQuery orderItemQueryByProducts = new ShopifyOrderItemQuery();
            orderItemQueryByProducts.setCreatedAtStart(query.getCreatedAtStart());
            orderItemQueryByProducts.setCreatedAtEnd(query.getCreatedAtEnd());

            List<String> orderIdListByProducts = new ArrayList<>();
            productList.forEach(product -> {
                orderItemQueryByProducts.setStoreId(product.getStoreId());
                orderItemQueryByProducts.setProductId(product.getProductId());
                orderItemQueryByProducts.setVariantId(product.getVariantId());
                List<ShopifyOrderItem> orderItemListByProducts = this.shopifyOrderItemService.findListByParam(orderItemQueryByProducts);
                orderItemListByProducts.forEach(orderItem -> orderIdListByProducts.add(orderItem.getOrderId()));
            });
            query.setOrderIdList(orderIdListByProducts);
        }

        // 指定单产品
        String storeId = query.getStoreId();
        String productId = query.getProductId();
        String variantId = query.getVariantId();
        if (!StringTools.isEmpty(storeId) && !StringTools.isEmpty(productId) && !StringTools.isEmpty(variantId)) {
            query.setStoreIdList(null);
            query.setBrandList(null);
            query.setPersonInChargeList(null);

            // 指定日期范围
            ShopifyOrderItemQuery orderItemQuery = new ShopifyOrderItemQuery();
            orderItemQuery.setCreatedAtStart(query.getCreatedAtStart());
            orderItemQuery.setCreatedAtEnd(query.getCreatedAtEnd());

            orderItemQuery.setStoreId(storeId);
            orderItemQuery.setProductId(productId);
            // 如果指定的是父级产品，则查找该父级的所有下级变体(variantId设为null)
            if (!Constants.SHOPIFY_PARENT_PRODUCT.equals(variantId)) {
                orderItemQuery.setVariantId(variantId);
            }

            List<ShopifyOrderItem> orderItemList = shopifyOrderItemService.findListByParam(orderItemQuery);
            List<String> orderIdListByItem = new ArrayList<>();
            orderItemList.forEach(orderItem -> orderIdListByItem.add(orderItem.getOrderId()));
            query.setOrderIdList(orderIdListByItem);
        }

        return query.getOrderIdList();
    }

    // 根据多条件组装ShopifyOrderItemQuery
    private ShopifyOrderItemQuery buildShopifyOrderItemQuery(ShopifyOrderQuery query) {
        ShopifyOrderItemQuery orderItemQuery = new ShopifyOrderItemQuery();
        orderItemQuery.setCreatedAtStart(query.getCreatedAtStart());
        orderItemQuery.setCreatedAtEnd(query.getCreatedAtEnd());

        List<String> orderItemQueryStoreIdList = new ArrayList<>();
        List<String> orderItemQueryProductIdList = new ArrayList<>();
        List<String> orderItemQueryVariantIdList = new ArrayList<>();

        // 店铺、品牌、负责人(即指定多产品)
        List<String> storeIdList = query.getStoreIdList();
        List<String> brandList = query.getBrandList();
        List<String> personInChargeList = query.getPersonInChargeList();
        if (null != storeIdList || null != brandList || null != personInChargeList) {
            ShopifyProductQuery productQuery = new ShopifyProductQuery();
            productQuery.setStoreIdList(storeIdList);
            productQuery.setBrandList(brandList);
            productQuery.setPersonInChargeList(personInChargeList);
            List<ShopifyProduct> productList = this.shopifyProductService.findListByParam(productQuery);

            productList.forEach(product -> {
                orderItemQueryStoreIdList.add(product.getStoreId());
                orderItemQueryProductIdList.add(product.getProductId());
                orderItemQueryVariantIdList.add(product.getVariantId());
            });
            orderItemQuery.setStoreIdList(new ArrayList<>(new LinkedHashSet<>(orderItemQueryStoreIdList)));
            orderItemQuery.setProductIdList(new ArrayList<>(new LinkedHashSet<>(orderItemQueryProductIdList)));
            orderItemQuery.setVariantIdList(new ArrayList<>(new LinkedHashSet<>(orderItemQueryVariantIdList)));
        }

        // 指定单产品
        String storeId = query.getStoreId();
        String productId = query.getProductId();
        String variantId = query.getVariantId();
        if (!StringTools.isEmpty(storeId) && !StringTools.isEmpty(productId) && !StringTools.isEmpty(variantId)) {
            query.setStoreIdList(null);
            query.setBrandList(null);
            query.setPersonInChargeList(null);

            orderItemQuery.setStoreId(storeId);
            orderItemQuery.setProductId(productId);
            // 如果指定的是父级产品，则查找该父级的所有下级变体(variantId设为null)
            if (!Constants.SHOPIFY_PARENT_PRODUCT.equals(variantId)) {
                orderItemQuery.setVariantId(variantId);
            }
        }

        // 指定国家
        List<String> orderIdList = new ArrayList<>();
        List<String> customerCountryCodeList = query.getCustomerCountryCodeList();
        if (null != customerCountryCodeList) {
            List<ShopifyOrder> orderList = findListByParam(query);
            orderList.forEach(order -> orderIdList.add(order.getOrderId()));
            orderItemQuery.setOrderIdList(orderIdList);
        }

        return orderItemQuery;
    }

    // 根据多条件组装ShopifySessionReportQuery(storeIdList 和 landingPagePathList)
    private ShopifySessionReportQuery buildShopifyProductLandingPagePathList(ShopifyOrderQuery query) {
        ShopifySessionReportQuery sessionReportQuery = new ShopifySessionReportQuery();
        List<String> sessionQueryStoreIdList = new ArrayList<>();
        List<String> landingPagePathList = new ArrayList<>();

        // 店铺、品牌、负责人(即指定多产品)
        List<String> storeIdList = query.getStoreIdList();
        List<String> brandList = query.getBrandList();
        List<String> personInChargeList = query.getPersonInChargeList();
        if (null != storeIdList || null != brandList || null != personInChargeList) {
            ShopifyProductQuery productQuery = new ShopifyProductQuery();
            productQuery.setStoreIdList(storeIdList);
            productQuery.setBrandList(brandList);
            productQuery.setPersonInChargeList(personInChargeList);
            List<ShopifyProduct> productList = this.shopifyProductService.findListByParam(productQuery);

            productList.forEach(product -> {
                sessionQueryStoreIdList.add(product.getStoreId());
                landingPagePathList.add("/products/" + product.getHandle());
            });

            // 组装ShopifySessionReportQuery
            sessionReportQuery.setStoreIdList(new ArrayList<>(new LinkedHashSet<>(sessionQueryStoreIdList)));
            sessionReportQuery.setLandingPagePathList(new ArrayList<>(new LinkedHashSet<>(landingPagePathList)));
        }

        // 指定单产品
        String storeId = query.getStoreId();
        String productId = query.getProductId();
        String variantId = query.getVariantId();
        if (!StringTools.isEmpty(storeId) && !StringTools.isEmpty(productId) && !StringTools.isEmpty(variantId)) {
            query.setStoreIdList(null);
            query.setBrandList(null);
            query.setPersonInChargeList(null);

            ShopifyProduct singleProduct = this.shopifyProductService.getShopifyProductByStoreIdAndProductIdAndVariantId(storeId, productId, variantId);
            sessionQueryStoreIdList.add(singleProduct.getStoreId());
            landingPagePathList.add("/products/" + singleProduct.getHandle());

            // 组装ShopifySessionReportQuery
            sessionReportQuery.setStoreIdList(new ArrayList<>(new LinkedHashSet<>(sessionQueryStoreIdList)));
            sessionReportQuery.setLandingPagePathList(new ArrayList<>(new LinkedHashSet<>(landingPagePathList)));
        }

        return sessionReportQuery;
    }

    /**
     * 多条件查询获取订单列表
     *
     * @param query
     * @return
     */
    @Override
    public PaginationResultVO<ShopifyOrder> loadShopifyOrderListByMultiQuery(ShopifyOrderQuery query) {
        // 根据多条件组装orderIdList
        List<String> queryOrderIdList = buildShopifyOrderQueryOrderIdList(query);
        query.setOrderIdList(queryOrderIdList);

        PaginationResultVO<ShopifyOrder> orderList = findListByPage(query);
        orderList.getList().forEach(order -> {
            ShopifyOrderItemQuery detailOrderItemQuery = new ShopifyOrderItemQuery();
            detailOrderItemQuery.setStoreId(order.getStoreId());
            detailOrderItemQuery.setOrderId(order.getOrderId());
            List<ShopifyOrderItem> orderItemList = shopifyOrderItemService.findListByParam(detailOrderItemQuery);
            orderItemList.forEach(orderItem -> {
                ShopifyProduct product = this.shopifyProductService.getShopifyProductByStoreIdAndProductIdAndVariantId(orderItem.getStoreId(), orderItem.getProductId(), orderItem.getVariantId());
                orderItem.setProductTitle(product.getProductTitle());
                orderItem.setAttribute(product.getAttribute());
                orderItem.setSku(product.getSku());
            });
            order.setOrderItemList(CopyTools.copyList(orderItemList, ShopifyOrderItemVO.class));
        });

        return orderList;
    }

    /**
     * 获取日销售表现(多条件)
     *
     * @param query
     * @return
     */
    @Override
    public List<ShopifySalesDailyPerformanceVo> getShopifySalesDailyPerformance(ShopifyOrderQuery query) {
        String createdAtStart = query.getCreatedAtStart();
        String createdAtEnd = query.getCreatedAtEnd();
        if (StringTools.isEmpty(createdAtStart) || StringTools.isEmpty(createdAtEnd)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        List<String> reportDateList = DateUtil.getDateList(createdAtStart, createdAtEnd);
        List<ShopifySalesDailyPerformanceVo> salesDailyPerformanceList = new ArrayList<>();
        // 根据多条件组装ShopifySessionReportQuery(storeIdList 和 landingPagePathList)
        ShopifySessionReportQuery commonSessionsQuery = buildShopifyProductLandingPagePathList(query);

        reportDateList.forEach(reportDate -> {
            ShopifySalesDailyPerformanceVo salesDailyPerformance = new ShopifySalesDailyPerformanceVo();
            salesDailyPerformance.setReportDate(reportDate);
            salesDailyPerformance.setTotalSalesPrice(BigDecimal.valueOf(0));
            salesDailyPerformance.setTotalSalesUnit(0L);
            salesDailyPerformance.setSessions(0L);
            salesDailyPerformance.setSessionsWithCartAdditions(0L);
            salesDailyPerformance.setSessionsThatReachedCheckout(0L);

            ShopifyOrderQuery copyQuery = CopyTools.copy(query, ShopifyOrderQuery.class);
            copyQuery.setCreatedAtStart(reportDate);
            copyQuery.setCreatedAtEnd(reportDate);
            // 根据多条件组装ShopifyOrderItemQuery(切割日期组装)
            ShopifyOrderItemQuery orderItemQuery = buildShopifyOrderItemQuery(copyQuery);

            // 销售额、销量
            List<ShopifyOrderItem> orderItemList = this.shopifyOrderItemService.findListByParam(orderItemQuery);
            orderItemList.forEach(orderItem -> {
                salesDailyPerformance.setTotalSalesPrice(salesDailyPerformance.getTotalSalesPrice().add(orderItem.getDiscountedUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))).setScale(2, RoundingMode.HALF_UP));
                salesDailyPerformance.setTotalSalesUnit(salesDailyPerformance.getTotalSalesUnit() + orderItem.getQuantity());
            });

            // 流量
            ShopifySessionReportQuery sessionQuery = CopyTools.copy(commonSessionsQuery, ShopifySessionReportQuery.class);
            sessionQuery.setReportDay(reportDate);
            sessionQuery.setLandingPageType(Constants.SHOPIFY_SESSION_PRODUCT_TYPE);
            List<ShopifySessionReport> sessionList = this.shopifySessionReportService.findListByParam(sessionQuery);
            sessionList.forEach(session -> {
                salesDailyPerformance.setSessions(salesDailyPerformance.getSessions() + session.getSessions());
                salesDailyPerformance.setSessionsWithCartAdditions(salesDailyPerformance.getSessionsWithCartAdditions() + session.getSessionsWithCartAdditions());
                salesDailyPerformance.setSessionsThatReachedCheckout(salesDailyPerformance.getSessionsThatReachedCheckout() + session.getSessionsThatReachedCheckout());
            });

            salesDailyPerformanceList.add(salesDailyPerformance);
        });

        return salesDailyPerformanceList;
    }
}