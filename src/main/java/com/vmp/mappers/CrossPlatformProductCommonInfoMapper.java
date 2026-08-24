package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 跨平台全产品公共属性 数据库操作接口
 */
public interface CrossPlatformProductCommonInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据Uid更新
	 */
	 Integer updateByUid(@Param("bean") T t,@Param("uid") String uid);


	/**
	 * 根据Uid删除
	 */
	 Integer deleteByUid(@Param("uid") String uid);


	/**
	 * 根据Uid获取对象
	 */
	 T selectByUid(@Param("uid") String uid);


	/**
	 * 根据SalesPlatformAndStoreIdAndProductId更新
	 */
	 Integer updateBySalesPlatformAndStoreIdAndProductId(@Param("bean") T t,@Param("salesPlatform") String salesPlatform,@Param("storeId") String storeId,@Param("productId") String productId);


	/**
	 * 根据SalesPlatformAndStoreIdAndProductId删除
	 */
	 Integer deleteBySalesPlatformAndStoreIdAndProductId(@Param("salesPlatform") String salesPlatform,@Param("storeId") String storeId,@Param("productId") String productId);


	/**
	 * 根据SalesPlatformAndStoreIdAndProductId获取对象
	 */
	 T selectBySalesPlatformAndStoreIdAndProductId(@Param("salesPlatform") String salesPlatform,@Param("storeId") String storeId,@Param("productId") String productId);


}
