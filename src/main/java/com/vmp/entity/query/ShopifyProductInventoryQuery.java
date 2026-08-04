package com.vmp.entity.query;



/**
 * 官网产品库存参数
 */
public class ShopifyProductInventoryQuery extends BaseParam {


	/**
	 * 变体id
	 */
	private String variantId;

	private String variantIdFuzzy;

	/**
	 * 产品id
	 */
	private String productId;

	private String productIdFuzzy;

	/**
	 * 仓库id
	 */
	private String warehouseId;

	private String warehouseIdFuzzy;

	/**
	 * 店铺id
	 */
	private String storeId;

	private String storeIdFuzzy;

	/**
	 * 仓库名称
	 */
	private String warehouseName;

	private String warehouseNameFuzzy;

	/**
	 * 可售库存数量
	 */
	private Integer availableQuantity;


	public void setVariantId(String variantId){
		this.variantId = variantId;
	}

	public String getVariantId(){
		return this.variantId;
	}

	public void setVariantIdFuzzy(String variantIdFuzzy){
		this.variantIdFuzzy = variantIdFuzzy;
	}

	public String getVariantIdFuzzy(){
		return this.variantIdFuzzy;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return this.productId;
	}

	public void setProductIdFuzzy(String productIdFuzzy){
		this.productIdFuzzy = productIdFuzzy;
	}

	public String getProductIdFuzzy(){
		return this.productIdFuzzy;
	}

	public void setWarehouseId(String warehouseId){
		this.warehouseId = warehouseId;
	}

	public String getWarehouseId(){
		return this.warehouseId;
	}

	public void setWarehouseIdFuzzy(String warehouseIdFuzzy){
		this.warehouseIdFuzzy = warehouseIdFuzzy;
	}

	public String getWarehouseIdFuzzy(){
		return this.warehouseIdFuzzy;
	}

	public void setStoreId(String storeId){
		this.storeId = storeId;
	}

	public String getStoreId(){
		return this.storeId;
	}

	public void setStoreIdFuzzy(String storeIdFuzzy){
		this.storeIdFuzzy = storeIdFuzzy;
	}

	public String getStoreIdFuzzy(){
		return this.storeIdFuzzy;
	}

	public void setWarehouseName(String warehouseName){
		this.warehouseName = warehouseName;
	}

	public String getWarehouseName(){
		return this.warehouseName;
	}

	public void setWarehouseNameFuzzy(String warehouseNameFuzzy){
		this.warehouseNameFuzzy = warehouseNameFuzzy;
	}

	public String getWarehouseNameFuzzy(){
		return this.warehouseNameFuzzy;
	}

	public void setAvailableQuantity(Integer availableQuantity){
		this.availableQuantity = availableQuantity;
	}

	public Integer getAvailableQuantity(){
		return this.availableQuantity;
	}

}
