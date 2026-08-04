package com.vmp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.vmp.controller.ShopifyController;
import com.vmp.entity.apidto.ShopifyProductApiDto;
import com.vmp.entity.apidto.ShopifyProductPageInfo;
import com.vmp.entity.apidto.ShopifyShopInfo;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.po.ShopifyProductInventory;
import com.vmp.service.ShopifyApiService;
import com.vmp.service.ShopifyProductInventoryService;
import com.vmp.utils.CopyTools;
import com.vmp.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.ShopifyProductQuery;
import com.vmp.entity.po.ShopifyProduct;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.ShopifyProductMapper;
import com.vmp.service.ShopifyProductService;
import com.vmp.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 官网产品-变体 业务接口实现
 */
@Service("shopifyProductService")
public class ShopifyProductServiceImpl implements ShopifyProductService {

    private static final Logger logger = LoggerFactory.getLogger(ShopifyProductServiceImpl.class);


    @Resource
    private ShopifyProductMapper<ShopifyProduct, ShopifyProductQuery> shopifyProductMapper;

    @Resource
    private ShopifyApiService shopifyApiService;

    @Resource
    private ShopifyProductInventoryService shopifyProductInventoryService;

    /**
     * 根据条件查询列表
     */
    @Override
    public List<ShopifyProduct> findListByParam(ShopifyProductQuery param) {
        return this.shopifyProductMapper.selectList(param);
    }

    /**
     * 根据条件查询列表
     */
    @Override
    public Integer findCountByParam(ShopifyProductQuery param) {
        return this.shopifyProductMapper.selectCount(param);
    }

    /**
     * 分页查询方法
     */
    @Override
    public PaginationResultVO<ShopifyProduct> findListByPage(ShopifyProductQuery param) {
        int count = this.findCountByParam(param);
        int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

        SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
        param.setSimplePage(page);
        List<ShopifyProduct> list = this.findListByParam(param);
        PaginationResultVO<ShopifyProduct> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */
    @Override
    public Integer add(ShopifyProduct bean) {
        return this.shopifyProductMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    @Override
    public Integer addBatch(List<ShopifyProduct> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.shopifyProductMapper.insertBatch(listBean);
    }

    /**
     * 批量新增或者修改
     */
    @Override
    public Integer addOrUpdateBatch(List<ShopifyProduct> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.shopifyProductMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 多条件更新
     */
    @Override
    public Integer updateByParam(ShopifyProduct bean, ShopifyProductQuery param) {
        StringTools.checkParam(param);
        return this.shopifyProductMapper.updateByParam(bean, param);
    }

    /**
     * 多条件删除
     */
    @Override
    public Integer deleteByParam(ShopifyProductQuery param) {
        StringTools.checkParam(param);
        return this.shopifyProductMapper.deleteByParam(param);
    }

    /**
     * 根据StoreIdAndProductIdAndVariantId获取对象
     */
    @Override
    public ShopifyProduct getShopifyProductByStoreIdAndProductIdAndVariantId(String storeId, String productId, String variantId) {
        return this.shopifyProductMapper.selectByStoreIdAndProductIdAndVariantId(storeId, productId, variantId);
    }

    /**
     * 根据StoreIdAndProductIdAndVariantId修改
     */
    @Override
    public Integer updateShopifyProductByStoreIdAndProductIdAndVariantId(ShopifyProduct bean, String storeId, String productId, String variantId) {
        return this.shopifyProductMapper.updateByStoreIdAndProductIdAndVariantId(bean, storeId, productId, variantId);
    }

    /**
     * 根据StoreIdAndProductIdAndVariantId删除
     */
    @Override
    public Integer deleteShopifyProductByStoreIdAndProductIdAndVariantId(String storeId, String productId, String variantId) {
        return this.shopifyProductMapper.deleteByStoreIdAndProductIdAndVariantId(storeId, productId, variantId);
    }

    /**
     * 同步Shopify商品数据-子体-单页
     *
     * @param host
     * @param first
     * @param after
     * @param shopInfo
     * @return 返回当页的endCursor
     */
    @Transactional(rollbackFor = Exception.class)
    public String syncShopifyProductsSinglePage(String host, Integer first, String after, ShopifyShopInfo shopInfo) {
        ShopifyProductPageInfo productPageInfo = shopifyApiService.getShopifyProducts(host, first, after);
        List<ShopifyProductApiDto> nodes = productPageInfo.getNodes();

        List<ShopifyProduct> parentProducts = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        List<ShopifyProductInventory> inventoryList = new ArrayList<>();
        nodes.forEach(node -> {
            node.setStoreId(shopInfo.getId());
            node.setStoreName(shopInfo.getName());
            node.setCurrencyCode(shopInfo.getCurrencyCode());

            Long totalAvailableQuantity = 0L;
            List<ShopifyProductApiDto.InventoryLevelNode> inventoryLevelNodeList = node.getInventoryItem().getInventoryLevels().getNodes();
            if (null != inventoryLevelNodeList && !inventoryLevelNodeList.isEmpty()) {
                for (ShopifyProductApiDto.InventoryLevelNode inventoryLevelNode : inventoryLevelNodeList) {
                    List<ShopifyProductApiDto.InventoryQuantity> quantities = inventoryLevelNode.getQuantities();
                    if (null == quantities || quantities.isEmpty()) {
                        continue;
                    }
                    ShopifyProductInventory productInventory = new ShopifyProductInventory();
                    productInventory.setVariantId(node.getVariantId());
                    productInventory.setProductId(node.getProductId());
                    productInventory.setWarehouseId(inventoryLevelNode.getLocation().getId());
                    productInventory.setStoreId(shopInfo.getId());
                    productInventory.setWarehouseName(inventoryLevelNode.getLocation().getName());
                    productInventory.setAvailableQuantity(quantities.get(0).getQuantity());

                    totalAvailableQuantity += productInventory.getAvailableQuantity();
                    inventoryList.add(productInventory);
                }
            } else {
                logger.debug("当前变体无库存数据, variantId: {}, productId: {}", node.getVariantId(), node.getProductId());
            }
            node.setTotalAvailableQuantity(totalAvailableQuantity);
            node.setParentStatus(Constants.SHOPIFY_CHILD_PRODUCT);

            // 添加父体产品
            String parentProductUniqueKey = node.getStoreId() + "_" + node.getProductId();
            if (seenKeys.add(parentProductUniqueKey)) {
                ShopifyProduct parentProduct = new ShopifyProduct();
                parentProduct.setStoreId(node.getStoreId());
                parentProduct.setStoreName(node.getStoreName());
                parentProduct.setCurrencyCode(node.getCurrencyCode());
                parentProduct.setProductId(node.getProductId());
                parentProduct.setProductTitle(node.getProductTitle());
                parentProduct.setHandle(node.getHandle());
                parentProduct.setProductUrl(node.getProductUrl());
                parentProduct.setProductCreatedAt(node.getProductCreatedAt());
                parentProduct.setProductType(node.getProductType());
                parentProduct.setProductCategory(node.getProductCategory());
                parentProduct.setProductImgUrl(node.getProductImgUrl());
                parentProduct.setVariantId(Constants.SHOPIFY_PARENT_PRODUCT);
                parentProduct.setParentStatus(Constants.SHOPIFY_PARENT_PRODUCT);
                parentProducts.add(parentProduct);
            }
        });

        List<ShopifyProduct> childProducts = CopyTools.copyList(nodes, ShopifyProduct.class);

        addOrUpdateBatch(childProducts);
        addOrUpdateBatch(parentProducts);
        this.shopifyProductInventoryService.addOrUpdateBatch(inventoryList);

        if (productPageInfo.getPageInfo().getHasNextPage()) {
            return productPageInfo.getPageInfo().getEndCursor();
        } else {
            return null;
        }
    }

    /**
     * 同步Shopify商品数据-子体
     *
     * @param host
     * @param first
     * @param after
     */
    @Override
    public void syncShopifyProducts(String host, Integer first, String after) {
        ShopifyShopInfo shopInfo = shopifyApiService.getShopifyShopInfo(host);

        String cursor = after;
        do {
            cursor = syncShopifyProductsSinglePage(host, first, cursor, shopInfo);
        } while (!StringTools.isEmpty(cursor));
    }

    /**
     * 获取商品列表-父体
     *
     * @param query
     * @return
     */
    @Override
    public PaginationResultVO<ShopifyProduct> loadShopifyParentProductList(ShopifyProductQuery query) {
        query.setOrderBy("product_created_at desc");
        query.setParentStatus(Constants.SHOPIFY_PARENT_PRODUCT);
        if (!StringTools.isEmpty(query.getSku())) {
            ShopifyProductQuery childProductQuery = new ShopifyProductQuery();
            childProductQuery.setSku(query.getSku());
            childProductQuery.setStoreId(query.getStoreId());
            List<ShopifyProduct> childProductList = findListByParam(childProductQuery);

            List<String> targetProductIdList = new ArrayList<>();
            childProductList.forEach(childProduct -> targetProductIdList.add(childProduct.getProductId()));
            query.setProductIdList(targetProductIdList);
            query.setSku(null);
        }
        return findListByPage(query);
    }

    /**
     * 获取指定父体商品下的子体列表-默认分页大小是10
     *
     * @param query
     * @return
     */
    @Override
    public PaginationResultVO<ShopifyProduct> loadShopifyChildProductListUnderSpecificParent(ShopifyProductQuery query) {
        query.setOrderBy("variant_created_at desc");
        query.setPageSize(Constants.LENGTH_10);
        query.setParentStatus(Constants.SHOPIFY_CHILD_PRODUCT);

        return findListByPage(query);
    }

    /**
     * 修改指定商品信息(父体和子体)-分配品牌或人员
     *
     * @param brand
     * @param personInCharge
     * @param param
     */
    @Override
    public void updateBrandOrPerson4ShopifySpecificProduct(String brand, String personInCharge, ShopifyProductQuery param) {
        ShopifyProduct updateInfo = new ShopifyProduct();
        if (!StringTools.isEmpty(brand)){
            updateInfo.setBrand(brand);
        }
        if (!StringTools.isEmpty(personInCharge)){
            updateInfo.setPersonInCharge(personInCharge);
        }
        updateByParam(updateInfo, param);
    }
}