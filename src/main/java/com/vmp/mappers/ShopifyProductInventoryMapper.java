package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 官网产品库存 数据库操作接口
 */
public interface ShopifyProductInventoryMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId更新
	 */
	 Integer updateByVariantIdAndProductIdAndWarehouseIdAndStoreId(@Param("bean") T t,@Param("variantId") String variantId,@Param("productId") String productId,@Param("warehouseId") String warehouseId,@Param("storeId") String storeId);


	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId删除
	 */
	 Integer deleteByVariantIdAndProductIdAndWarehouseIdAndStoreId(@Param("variantId") String variantId,@Param("productId") String productId,@Param("warehouseId") String warehouseId,@Param("storeId") String storeId);


	/**
	 * 根据VariantIdAndProductIdAndWarehouseIdAndStoreId获取对象
	 */
	 T selectByVariantIdAndProductIdAndWarehouseIdAndStoreId(@Param("variantId") String variantId,@Param("productId") String productId,@Param("warehouseId") String warehouseId,@Param("storeId") String storeId);


}
