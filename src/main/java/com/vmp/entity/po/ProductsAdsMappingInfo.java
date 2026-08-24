package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 产品-广告映射表
 */
public class ProductsAdsMappingInfo implements Serializable {


	private static final long serialVersionUID = -2057719129637919671L;
	/**
	 * 广告平台 amazon、google、meta
	 */
	private String adsPlatform;

	/**
	 * 广告标识id
	 */
	private String adsCampaignId;

	/**
	 * 绑定产品uid
	 */
	private String mappingProductUid;


	public void setAdsPlatform(String adsPlatform){
		this.adsPlatform = adsPlatform;
	}

	public String getAdsPlatform(){
		return this.adsPlatform;
	}

	public void setAdsCampaignId(String adsCampaignId){
		this.adsCampaignId = adsCampaignId;
	}

	public String getAdsCampaignId(){
		return this.adsCampaignId;
	}

	public void setMappingProductUid(String mappingProductUid){
		this.mappingProductUid = mappingProductUid;
	}

	public String getMappingProductUid(){
		return this.mappingProductUid;
	}

	@Override
	public String toString (){
		return "广告平台 amazon、google、meta:"+(adsPlatform == null ? "空" : adsPlatform)+"，广告标识id:"+(adsCampaignId == null ? "空" : adsCampaignId)+"，绑定产品uid:"+(mappingProductUid == null ? "空" : mappingProductUid);
	}
}
