package com.vmp.entity.po;

import java.io.Serializable;


/**
 * 官网产品库存
 */
public class ShopifyProductInventory implements Serializable {


	private static final long serialVersionUID = 729859025535831166L;
	/**
	 * 变体id
	 */
	private String variantId;

	/**
	 * 产品id
	 */
	private String productId;

	/**
	 * 仓库id
	 */
	private String warehouseId;

	/**
	 * 店铺id
	 */
	private String storeId;

	/**
	 * 仓库名称
	 */
	private String warehouseName;

	/**
	 * 可售库存数量
	 */
	private Long availableQuantity;


	public void setVariantId(String variantId){
		this.variantId = variantId;
	}

	public String getVariantId(){
		return this.variantId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return this.productId;
	}

	public void setWarehouseId(String warehouseId){
		this.warehouseId = warehouseId;
	}

	public String getWarehouseId(){
		return this.warehouseId;
	}

	public void setStoreId(String storeId){
		this.storeId = storeId;
	}

	public String getStoreId(){
		return this.storeId;
	}

	public void setWarehouseName(String warehouseName){
		this.warehouseName = warehouseName;
	}

	public String getWarehouseName(){
		return this.warehouseName;
	}

	public void setAvailableQuantity(Long availableQuantity){
		this.availableQuantity = availableQuantity;
	}

	public Long getAvailableQuantity(){
		return this.availableQuantity;
	}

	@Override
	public String toString (){
		return "变体id:"+(variantId == null ? "空" : variantId)+"，产品id:"+(productId == null ? "空" : productId)+"，仓库id:"+(warehouseId == null ? "空" : warehouseId)+"，店铺id:"+(storeId == null ? "空" : storeId)+"，仓库名称:"+(warehouseName == null ? "空" : warehouseName)+"，可售库存数量:"+(availableQuantity == null ? "空" : availableQuantity);
	}
}
