package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 官网订单表 数据库操作接口
 */
public interface ShopifyOrderMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据StoreIdAndOrderId更新
	 */
	 Integer updateByStoreIdAndOrderId(@Param("bean") T t,@Param("storeId") String storeId,@Param("orderId") String orderId);


	/**
	 * 根据StoreIdAndOrderId删除
	 */
	 Integer deleteByStoreIdAndOrderId(@Param("storeId") String storeId,@Param("orderId") String orderId);


	/**
	 * 根据StoreIdAndOrderId获取对象
	 */
	 T selectByStoreIdAndOrderId(@Param("storeId") String storeId,@Param("orderId") String orderId);


}
