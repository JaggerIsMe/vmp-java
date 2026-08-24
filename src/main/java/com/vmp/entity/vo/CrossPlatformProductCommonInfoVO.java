package com.vmp.entity.vo;

public class CrossPlatformProductCommonInfoVO {

    /**
     * 全局唯一id
     */
    private String uid;

    /**
     * 销售平台
     */
    private String salesPlatform;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 产品图片文件路径
     */
    private String productImgPath;

    /**
     * 国家代码
     */
    private String countryCode;


    public void setUid(String uid){
        this.uid = uid;
    }

    public String getUid(){
        return this.uid;
    }

    public void setSalesPlatform(String salesPlatform){
        this.salesPlatform = salesPlatform;
    }

    public String getSalesPlatform(){
        return this.salesPlatform;
    }

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

    public void setProductImgPath(String productImgPath){
        this.productImgPath = productImgPath;
    }

    public String getProductImgPath(){
        return this.productImgPath;
    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    public String getCountryCode(){
        return this.countryCode;
    }

}
