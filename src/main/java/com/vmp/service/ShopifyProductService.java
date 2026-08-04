package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.ShopifyProductQuery;
import com.vmp.entity.po.ShopifyProduct;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 官网产品-变体 业务接口
 */
public interface ShopifyProductService {

	/**
	 * 根据条件查询列表
	 */
	List<ShopifyProduct> findListByParam(ShopifyProductQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(ShopifyProductQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ShopifyProduct> findListByPage(ShopifyProductQuery param);

	/**
	 * 新增
	 */
	Integer add(ShopifyProduct bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ShopifyProduct> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ShopifyProduct> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(ShopifyProduct bean,ShopifyProductQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(ShopifyProductQuery param);

	/**
	 * 根据StoreIdAndProductIdAndVariantId查询对象
	 */
	ShopifyProduct getShopifyProductByStoreIdAndProductIdAndVariantId(String storeId,String productId,String variantId);


	/**
	 * 根据StoreIdAndProductIdAndVariantId修改
	 */
	Integer updateShopifyProductByStoreIdAndProductIdAndVariantId(ShopifyProduct bean,String storeId,String productId,String variantId);


	/**
	 * 根据StoreIdAndProductIdAndVariantId删除
	 */
	Integer deleteShopifyProductByStoreIdAndProductIdAndVariantId(String storeId,String productId,String variantId);

	/**
	 * 同步Shopify商品数据-子体
	 * @param host
	 * @param first
	 * @param after
	 */
	void syncShopifyProducts(String host, Integer first, String after);

	/**
	 * 获取商品列表-父体
	 * @param query
	 * @return
	 */
	PaginationResultVO<ShopifyProduct> loadShopifyParentProductList(ShopifyProductQuery query);

	/**
	 * 获取指定商品下的变体列表-默认分页大小是10
	 * @param query
	 * @return
	 */
	PaginationResultVO<ShopifyProduct> loadShopifyChildProductListUnderSpecificParent(ShopifyProductQuery query);

	/**
	 * 修改指定商品信息(父体和)-分配品牌或人员
	 * @param brand
	 * @param personInCharge
	 * @param param
	 */
	void updateBrandOrPerson4ShopifySpecificProduct(String brand, String personInCharge,ShopifyProductQuery param);
}