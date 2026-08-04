package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 官网订单-订单项 数据库操作接口
 */
public interface ShopifyOrderItemMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据OrderIdAndItemIdAndStoreId更新
	 */
	 Integer updateByOrderIdAndItemIdAndStoreId(@Param("bean") T t,@Param("orderId") String orderId,@Param("itemId") String itemId,@Param("storeId") String storeId);


	/**
	 * 根据OrderIdAndItemIdAndStoreId删除
	 */
	 Integer deleteByOrderIdAndItemIdAndStoreId(@Param("orderId") String orderId,@Param("itemId") String itemId,@Param("storeId") String storeId);


	/**
	 * 根据OrderIdAndItemIdAndStoreId获取对象
	 */
	 T selectByOrderIdAndItemIdAndStoreId(@Param("orderId") String orderId,@Param("itemId") String itemId,@Param("storeId") String storeId);


}
