package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 官网产品-变体
 */
public class ShopifyProduct implements Serializable {


	private static final long serialVersionUID = 821601129719631489L;
	/**
	 * 店铺id
	 */
	private String storeId;

	/**
	 * 店铺名称
	 */
	private String storeName;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	/**
	 * 产品id
	 */
	private String productId;

	/**
	 * 产品标题
	 */
	private String productTitle;

	/**
	 * 产品url后缀
	 */
	private String handle;

	/**
	 * 产品前台链接
	 */
	private String productUrl;

	/**
	 * 产品状态
	 */
	private String productStatus;

	/**
	 * 产品创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date productCreatedAt;

	/**
	 * 产品类型
	 */
	private String productType;

	/**
	 * 产品类目
	 */
	private String productCategory;

	/**
	 * 产品主图链接
	 */
	private String productImgUrl;

	/**
	 * 变体id
	 */
	private String variantId;

	/**
	 * 变体属性
	 */
	private String attribute;

	/**
	 * 库存编码
	 */
	private String sku;

	/**
	 * 条码
	 */
	private String barcode;

	/**
	 * 单价
	 */
	private BigDecimal unitPrice;

	/**
	 * 变体创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date variantCreatedAt;

	/**
	 * 变体标题
	 */
	private String variantTitle;

	/**
	 * 变体主图链接
	 */
	private String variantImgUrl;

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

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 负责人
	 */
	private String personInCharge;


	public void setStoreId(String storeId){
		this.storeId = storeId;
	}

	public String getStoreId(){
		return this.storeId;
	}

	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getStoreName(){
		return this.storeName;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return this.productId;
	}

	public void setProductTitle(String productTitle){
		this.productTitle = productTitle;
	}

	public String getProductTitle(){
		return this.productTitle;
	}

	public void setHandle(String handle){
		this.handle = handle;
	}

	public String getHandle(){
		return this.handle;
	}

	public void setProductUrl(String productUrl){
		this.productUrl = productUrl;
	}

	public String getProductUrl(){
		return this.productUrl;
	}

	public void setProductStatus(String productStatus){
		this.productStatus = productStatus;
	}

	public String getProductStatus(){
		return this.productStatus;
	}

	public void setProductCreatedAt(Date productCreatedAt){
		this.productCreatedAt = productCreatedAt;
	}

	public Date getProductCreatedAt(){
		return this.productCreatedAt;
	}

	public void setProductType(String productType){
		this.productType = productType;
	}

	public String getProductType(){
		return this.productType;
	}

	public void setProductCategory(String productCategory){
		this.productCategory = productCategory;
	}

	public String getProductCategory(){
		return this.productCategory;
	}

	public void setProductImgUrl(String productImgUrl){
		this.productImgUrl = productImgUrl;
	}

	public String getProductImgUrl(){
		return this.productImgUrl;
	}

	public void setVariantId(String variantId){
		this.variantId = variantId;
	}

	public String getVariantId(){
		return this.variantId;
	}

	public void setAttribute(String attribute){
		this.attribute = attribute;
	}

	public String getAttribute(){
		return this.attribute;
	}

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return this.sku;
	}

	public void setBarcode(String barcode){
		this.barcode = barcode;
	}

	public String getBarcode(){
		return this.barcode;
	}

	public void setUnitPrice(BigDecimal unitPrice){
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice(){
		return this.unitPrice;
	}

	public void setVariantCreatedAt(Date variantCreatedAt){
		this.variantCreatedAt = variantCreatedAt;
	}

	public Date getVariantCreatedAt(){
		return this.variantCreatedAt;
	}

	public void setVariantTitle(String variantTitle){
		this.variantTitle = variantTitle;
	}

	public String getVariantTitle(){
		return this.variantTitle;
	}

	public void setVariantImgUrl(String variantImgUrl){
		this.variantImgUrl = variantImgUrl;
	}

	public String getVariantImgUrl(){
		return this.variantImgUrl;
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

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return this.brand;
	}

	public void setPersonInCharge(String personInCharge){
		this.personInCharge = personInCharge;
	}

	public String getPersonInCharge(){
		return this.personInCharge;
	}

	@Override
	public String toString (){
		return "店铺id:"+(storeId == null ? "空" : storeId)+"，店铺名称:"+(storeName == null ? "空" : storeName)+"，货币代码:"+(currencyCode == null ? "空" : currencyCode)+"，产品id:"+(productId == null ? "空" : productId)+"，产品标题:"+(productTitle == null ? "空" : productTitle)+"，产品url后缀:"+(handle == null ? "空" : handle)+"，产品前台链接:"+(productUrl == null ? "空" : productUrl)+"，产品状态:"+(productStatus == null ? "空" : productStatus)+"，产品创建时间:"+(productCreatedAt == null ? "空" : DateUtil.format(productCreatedAt, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，产品类型:"+(productType == null ? "空" : productType)+"，产品类目:"+(productCategory == null ? "空" : productCategory)+"，产品主图链接:"+(productImgUrl == null ? "空" : productImgUrl)+"，变体id:"+(variantId == null ? "空" : variantId)+"，变体属性:"+(attribute == null ? "空" : attribute)+"，库存编码:"+(sku == null ? "空" : sku)+"，条码:"+(barcode == null ? "空" : barcode)+"，单价:"+(unitPrice == null ? "空" : unitPrice)+"，变体创建时间:"+(variantCreatedAt == null ? "空" : DateUtil.format(variantCreatedAt, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，变体标题:"+(variantTitle == null ? "空" : variantTitle)+"，变体主图链接:"+(variantImgUrl == null ? "空" : variantImgUrl)+"，总可用库存数:"+(totalAvailableQuantity == null ? "空" : totalAvailableQuantity)+"，变体可售状态 0不可售 1可售:"+(availableForSale == null ? "空" : availableForSale)+"，Parent主体Child变体:"+(parentStatus == null ? "空" : parentStatus)+"，品牌:"+(brand == null ? "空" : brand)+"，负责人:"+(personInCharge == null ? "空" : personInCharge);
	}
}
