package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.ShopifyProductInventoryQuery;
import com.vmp.entity.po.ShopifyProductInventory;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 官网产品库存 业务接口
 */
public interface ShopifyProductInventoryService {

	/**
	 * 根据条件查询列表
	 */
	List<ShopifyProductInventory> findListByParam(ShopifyProductInventoryQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(ShopifyProductInventoryQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ShopifyProductInventory> findListByPage(ShopifyProductInventoryQuery param);

	/**
	 * 新增
	 */
	Integer add(ShopifyProductInventory bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ShopifyProductInventory> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ShopifyProductInventory> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(ShopifyProductInventory bean,ShopifyProductInventoryQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(ShopifyProductInventoryQuery param);

	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId查询对象
	 */
	ShopifyProductInventory getShopifyProductInventoryByVariantIdAndProductIdAndWarehouseIdAndStoreId(String variantId,String productId,String warehouseId,String storeId);


	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId修改
	 */
	Integer updateShopifyProductInventoryByVariantIdAndProductIdAndWarehouseIdAndStoreId(ShopifyProductInventory bean,String variantId,String productId,String warehouseId,String storeId);


	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId删除
	 */
	Integer deleteShopifyProductInventoryByVariantIdAndProductIdAndWarehouseIdAndStoreId(String variantId,String productId,String warehouseId,String storeId);

}