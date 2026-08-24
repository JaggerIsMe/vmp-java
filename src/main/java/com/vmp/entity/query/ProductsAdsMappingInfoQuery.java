package com.vmp.entity.query;


import java.util.List;

/**
 * 产品-广告映射表参数
 */
public class ProductsAdsMappingInfoQuery extends BaseParam {


	/**
	 * 广告平台 amazon、google、meta
	 */
	private String adsPlatform;

	private String adsPlatformFuzzy;

	/**
	 * 广告标识id
	 */
	private String adsCampaignId;

	private String adsCampaignIdFuzzy;

	private List<String> adsCampaignIdList;

	/**
	 * 绑定产品uid
	 */
	private String mappingProductUid;

	private String mappingProductUidFuzzy;


	public void setAdsPlatform(String adsPlatform){
		this.adsPlatform = adsPlatform;
	}

	public String getAdsPlatform(){
		return this.adsPlatform;
	}

	public void setAdsPlatformFuzzy(String adsPlatformFuzzy){
		this.adsPlatformFuzzy = adsPlatformFuzzy;
	}

	public String getAdsPlatformFuzzy(){
		return this.adsPlatformFuzzy;
	}

	public void setAdsCampaignId(String adsCampaignId){
		this.adsCampaignId = adsCampaignId;
	}

	public String getAdsCampaignId(){
		return this.adsCampaignId;
	}

	public void setAdsCampaignIdFuzzy(String adsCampaignIdFuzzy){
		this.adsCampaignIdFuzzy = adsCampaignIdFuzzy;
	}

	public String getAdsCampaignIdFuzzy(){
		return this.adsCampaignIdFuzzy;
	}

	public void setMappingProductUid(String mappingProductUid){
		this.mappingProductUid = mappingProductUid;
	}

	public String getMappingProductUid(){
		return this.mappingProductUid;
	}

	public void setMappingProductUidFuzzy(String mappingProductUidFuzzy){
		this.mappingProductUidFuzzy = mappingProductUidFuzzy;
	}

	public String getMappingProductUidFuzzy(){
		return this.mappingProductUidFuzzy;
	}

	public List<String> getAdsCampaignIdList() {
		return adsCampaignIdList;
	}

	public void setAdsCampaignIdList(List<String> adsCampaignIdList) {
		this.adsCampaignIdList = adsCampaignIdList;
	}
}
