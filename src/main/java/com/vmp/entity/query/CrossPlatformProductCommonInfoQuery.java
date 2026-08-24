package com.vmp.entity.query;



/**
 * 跨平台全产品公共属性参数
 */
public class CrossPlatformProductCommonInfoQuery extends BaseParam {


	/**
	 * 全局唯一id
	 */
	private String uid;

	private String uidFuzzy;

	/**
	 * 销售平台
	 */
	private String salesPlatform;

	private String salesPlatformFuzzy;

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
	 * 产品图片文件路径
	 */
	private String productImgPath;

	private String productImgPathFuzzy;

	/**
	 * 国家代码
	 */
	private String countryCode;

	private String countryCodeFuzzy;


	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return this.uid;
	}

	public void setUidFuzzy(String uidFuzzy){
		this.uidFuzzy = uidFuzzy;
	}

	public String getUidFuzzy(){
		return this.uidFuzzy;
	}

	public void setSalesPlatform(String salesPlatform){
		this.salesPlatform = salesPlatform;
	}

	public String getSalesPlatform(){
		return this.salesPlatform;
	}

	public void setSalesPlatformFuzzy(String salesPlatformFuzzy){
		this.salesPlatformFuzzy = salesPlatformFuzzy;
	}

	public String getSalesPlatformFuzzy(){
		return this.salesPlatformFuzzy;
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

	public void setProductImgPath(String productImgPath){
		this.productImgPath = productImgPath;
	}

	public String getProductImgPath(){
		return this.productImgPath;
	}

	public void setProductImgPathFuzzy(String productImgPathFuzzy){
		this.productImgPathFuzzy = productImgPathFuzzy;
	}

	public String getProductImgPathFuzzy(){
		return this.productImgPathFuzzy;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return this.countryCode;
	}

	public void setCountryCodeFuzzy(String countryCodeFuzzy){
		this.countryCodeFuzzy = countryCodeFuzzy;
	}

	public String getCountryCodeFuzzy(){
		return this.countryCodeFuzzy;
	}

}
