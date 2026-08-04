package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 官网产品-变体 数据库操作接口
 */
public interface ShopifyProductMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据StoreIdAndProductIdAndVariantId更新
	 */
	 Integer updateByStoreIdAndProductIdAndVariantId(@Param("bean") T t,@Param("storeId") String storeId,@Param("productId") String productId,@Param("variantId") String variantId);


	/**
	 * 根据StoreIdAndProductIdAndVariantId删除
	 */
	 Integer deleteByStoreIdAndProductIdAndVariantId(@Param("storeId") String storeId,@Param("productId") String productId,@Param("variantId") String variantId);


	/**
	 * 根据StoreIdAndProductIdAndVariantId获取对象
	 */
	 T selectByStoreIdAndProductIdAndVariantId(@Param("storeId") String storeId,@Param("productId") String productId,@Param("variantId") String variantId);


}
