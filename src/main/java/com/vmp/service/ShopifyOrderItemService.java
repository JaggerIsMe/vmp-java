package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.ShopifyOrderItemQuery;
import com.vmp.entity.po.ShopifyOrderItem;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 官网订单-订单项 业务接口
 */
public interface ShopifyOrderItemService {

	/**
	 * 根据条件查询列表
	 */
	List<ShopifyOrderItem> findListByParam(ShopifyOrderItemQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(ShopifyOrderItemQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ShopifyOrderItem> findListByPage(ShopifyOrderItemQuery param);

	/**
	 * 新增
	 */
	Integer add(ShopifyOrderItem bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ShopifyOrderItem> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ShopifyOrderItem> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(ShopifyOrderItem bean,ShopifyOrderItemQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(ShopifyOrderItemQuery param);

	/**
	 * 根据OrderIdAndItemIdAndStoreId查询对象
	 */
	ShopifyOrderItem getShopifyOrderItemByOrderIdAndItemIdAndStoreId(String orderId,String itemId,String storeId);


	/**
	 * 根据OrderIdAndItemIdAndStoreId修改
	 */
	Integer updateShopifyOrderItemByOrderIdAndItemIdAndStoreId(ShopifyOrderItem bean,String orderId,String itemId,String storeId);


	/**
	 * 根据OrderIdAndItemIdAndStoreId删除
	 */
	Integer deleteShopifyOrderItemByOrderIdAndItemIdAndStoreId(String orderId,String itemId,String storeId);

}