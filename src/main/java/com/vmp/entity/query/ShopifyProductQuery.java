package com.vmp.entity.query;

import java.math.BigDecimal;
import java.util.List;


/**
 * 官网产品-变体参数
 */
public class ShopifyProductQuery extends BaseParam {


	/**
	 * 店铺id
	 */
	private String storeId;

	private String storeIdFuzzy;

	/**
	 * 店铺名称
	 */
	private String storeName;

	private String storeNameFuzzy;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	private String currencyCodeFuzzy;

	/**
	 * 产品id
	 */
	private String productId;

	private String productIdFuzzy;

	/**
	 * 产品标题
	 */
	private String productTitle;

	private String productTitleFuzzy;

	/**
	 * 产品url后缀
	 */
	private String handle;

	private String handleFuzzy;

	/**
	 * 产品前台链接
	 */
	private String productUrl;

	private String productUrlFuzzy;

	/**
	 * 产品状态
	 */
	private String productStatus;

	private String productStatusFuzzy;

	/**
	 * 产品创建时间
	 */
	private String productCreatedAt;

	private String productCreatedAtStart;

	private String productCreatedAtEnd;

	/**
	 * 产品类型
	 */
	private String productType;

	private String productTypeFuzzy;

	/**
	 * 产品类目
	 */
	private String productCategory;

	private String productCategoryFuzzy;

	/**
	 * 产品主图链接
	 */
	private String productImgUrl;

	private String productImgUrlFuzzy;

	/**
	 * 变体id
	 */
	private String variantId;

	private String variantIdFuzzy;

	/**
	 * 变体属性
	 */
	private String attribute;

	private String attributeFuzzy;

	/**
	 * 库存编码
	 */
	private String sku;

	private String skuFuzzy;

	/**
	 * 条码
	 */
	private String barcode;

	private String barcodeFuzzy;

	/**
	 * 单价
	 */
	private BigDecimal unitPrice;

	/**
	 * 变体创建时间
	 */
	private String variantCreatedAt;

	private String variantCreatedAtStart;

	private String variantCreatedAtEnd;

	/**
	 * 变体标题
	 */
	private String variantTitle;

	private String variantTitleFuzzy;

	/**
	 * 变体主图链接
	 */
	private String variantImgUrl;

	private String variantImgUrlFuzzy;

	/**
	 * 总可用库存数
	 */
	private Long totalAvailableQuantity;

	/**
	 * 变体可售状态 0不可售 1可售
	 */
	private Integer availableForSale;

	/**
	 * Parent主体Child变体
	 */
	private String parentStatus;

	private String parentStatusFuzzy;

	/**
	 * 品牌
	 */
	private String brand;

	private String brandFuzzy;

	/**
	 * 负责人
	 */
	private String personInCharge;

	private String personInChargeFuzzy;

	/**
	 * 可提供storeId列表查询产品
	 */
	private List<String> storeIdList;

	/**
	 * 可提供productId列表查询产品
	 */
	private List<String> productIdList;

	/**
	 * 可提供brandId列表查询产品
	 */
	private List<String> brandList;

	/**
	 * 可提供prersonInCharge列表查询产品
	 */
	private List<String> personInChargeList;


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

	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getStoreName(){
		return this.storeName;
	}

	public void setStoreNameFuzzy(String storeNameFuzzy){
		this.storeNameFuzzy = storeNameFuzzy;
	}

	public String getStoreNameFuzzy(){
		return this.storeNameFuzzy;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
	}

	public void setCurrencyCodeFuzzy(String currencyCodeFuzzy){
		this.currencyCodeFuzzy = currencyCodeFuzzy;
	}

	public String getCurrencyCodeFuzzy(){
		return this.currencyCodeFuzzy;
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

	public void setProductTitle(String productTitle){
		this.productTitle = productTitle;
	}

	public String getProductTitle(){
		return this.productTitle;
	}

	public void setProductTitleFuzzy(String productTitleFuzzy){
		this.productTitleFuzzy = productTitleFuzzy;
	}

	public String getProductTitleFuzzy(){
		return this.productTitleFuzzy;
	}

	public void setHandle(String handle){
		this.handle = handle;
	}

	public String getHandle(){
		return this.handle;
	}

	public void setHandleFuzzy(String handleFuzzy){
		this.handleFuzzy = handleFuzzy;
	}

	public String getHandleFuzzy(){
		return this.handleFuzzy;
	}

	public void setProductUrl(String productUrl){
		this.productUrl = productUrl;
	}

	public String getProductUrl(){
		return this.productUrl;
	}

	public void setProductUrlFuzzy(String productUrlFuzzy){
		this.productUrlFuzzy = productUrlFuzzy;
	}

	public String getProductUrlFuzzy(){
		return this.productUrlFuzzy;
	}

	public void setProductStatus(String productStatus){
		this.productStatus = productStatus;
	}

	public String getProductStatus(){
		return this.productStatus;
	}

	public void setProductStatusFuzzy(String productStatusFuzzy){
		this.productStatusFuzzy = productStatusFuzzy;
	}

	public String getProductStatusFuzzy(){
		return this.productStatusFuzzy;
	}

	public void setProductCreatedAt(String productCreatedAt){
		this.productCreatedAt = productCreatedAt;
	}

	public String getProductCreatedAt(){
		return this.productCreatedAt;
	}

	public void setProductCreatedAtStart(String productCreatedAtStart){
		this.productCreatedAtStart = productCreatedAtStart;
	}

	public String getProductCreatedAtStart(){
		return this.productCreatedAtStart;
	}
	public void setProductCreatedAtEnd(String productCreatedAtEnd){
		this.productCreatedAtEnd = productCreatedAtEnd;
	}

	public String getProductCreatedAtEnd(){
		return this.productCreatedAtEnd;
	}

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return this.productType;
	}

	public void setProductTypeFuzzy(String productTypeFuzzy){
		this.productTypeFuzzy = productTypeFuzzy;
	}

	public String getProductTypeFuzzy(){
		return this.productTypeFuzzy;
	}

	public void setProductCategory(String productCategory){
		this.productCategory = productCategory;
	}

	public String getProductCategory(){
		return this.productCategory;
	}

	public void setProductCategoryFuzzy(String productCategoryFuzzy){
		this.productCategoryFuzzy = productCategoryFuzzy;
	}

	public String getProductCategoryFuzzy(){
		return this.productCategoryFuzzy;
	}

	public void setProductImgUrl(String productImgUrl){
		this.productImgUrl = productImgUrl;
	}

	public String getProductImgUrl(){
		return this.productImgUrl;
	}

	public void setProductImgUrlFuzzy(String productImgUrlFuzzy){
		this.productImgUrlFuzzy = productImgUrlFuzzy;
	}

	public String getProductImgUrlFuzzy(){
		return this.productImgUrlFuzzy;
	}

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

	public void setAttribute(String attribute){
		this.attribute = attribute;
	}

	public String getAttribute(){
		return this.attribute;
	}

	public void setAttributeFuzzy(String attributeFuzzy){
		this.attributeFuzzy = attributeFuzzy;
	}

	public String getAttributeFuzzy(){
		return this.attributeFuzzy;
	}

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return this.sku;
	}

	public void setSkuFuzzy(String skuFuzzy){
		this.skuFuzzy = skuFuzzy;
	}

	public String getSkuFuzzy(){
		return this.skuFuzzy;
	}

	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	public String getBarcode(){
		return this.barcode;
	}

	public void setBarcodeFuzzy(String barcodeFuzzy){
		this.barcodeFuzzy = barcodeFuzzy;
	}

	public String getBarcodeFuzzy(){
		return this.barcodeFuzzy;
	}

	public void setUnitPrice(BigDecimal unitPrice){
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice(){
		return this.unitPrice;
	}

	public void setVariantCreatedAt(String variantCreatedAt){
		this.variantCreatedAt = variantCreatedAt;
	}

	public String getVariantCreatedAt(){
		return this.variantCreatedAt;
	}

	public void setVariantCreatedAtStart(String variantCreatedAtStart){
		this.variantCreatedAtStart = variantCreatedAtStart;
	}

	public String getVariantCreatedAtStart(){
		return this.variantCreatedAtStart;
	}
	public void setVariantCreatedAtEnd(String variantCreatedAtEnd){
		this.variantCreatedAtEnd = variantCreatedAtEnd;
	}

	public String getVariantCreatedAtEnd(){
		return this.variantCreatedAtEnd;
	}

	public void setVariantTitle(String variantTitle){
		this.variantTitle = variantTitle;
	}

	public String getVariantTitle(){
		return this.variantTitle;
	}

	public void setVariantTitleFuzzy(String variantTitleFuzzy){
		this.variantTitleFuzzy = variantTitleFuzzy;
	}

	public String getVariantTitleFuzzy(){
		return this.variantTitleFuzzy;
	}

	public void setVariantImgUrl(String variantImgUrl){
		this.variantImgUrl = variantImgUrl;
	}

	public String getVariantImgUrl(){
		return this.variantImgUrl;
	}

	public void setVariantImgUrlFuzzy(String variantImgUrlFuzzy){
		this.variantImgUrlFuzzy = variantImgUrlFuzzy;
	}

	public String getVariantImgUrlFuzzy(){
		return this.variantImgUrlFuzzy;
	}

	public void setTotalAvailableQuantity(Long totalAvailableQuantity){
		this.totalAvailableQuantity = totalAvailableQuantity;
	}

	public Long getTotalAvailableQuantity(){
		return this.totalAvailableQuantity;
	}

	public void setAvailableForSale(Integer availableForSale){
		this.availableForSale = availableForSale;
	}

	public Integer getAvailableForSale(){
		return this.availableForSale;
	}

	public void setParentStatus(String parentStatus){
		this.parentStatus = parentStatus;
	}

	public String getParentStatus(){
		return this.parentStatus;
	}

	public void setParentStatusFuzzy(String parentStatusFuzzy){
		this.parentStatusFuzzy = parentStatusFuzzy;
	}

	public String getParentStatusFuzzy(){
		return this.parentStatusFuzzy;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return this.brand;
	}

	public void setBrandFuzzy(String brandFuzzy){
		this.brandFuzzy = brandFuzzy;
	}

	public String getBrandFuzzy(){
		return this.brandFuzzy;
	}

	public void setPersonInCharge(String personInCharge){
		this.personInCharge = personInCharge;
	}

	public String getPersonInCharge(){
		return this.personInCharge;
	}

	public void setPersonInChargeFuzzy(String personInChargeFuzzy){
		this.personInChargeFuzzy = personInChargeFuzzy;
	}

	public String getPersonInChargeFuzzy(){
		return this.personInChargeFuzzy;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}

	public List<String> getStoreIdList() {
		return storeIdList;
	}

	public void setStoreIdList(List<String> storeIdList) {
		this.storeIdList = storeIdList;
	}

	public List<String> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<String> brandList) {
		this.brandList = brandList;
	}

	public List<String> getPersonInChargeList() {
		return personInChargeList;
	}

	public void setPersonInChargeList(List<String> personInChargeList) {
		this.personInChargeList = personInChargeList;
	}
}
