package com.vmp.entity.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 谷歌广告系列参数
 */
public class GoogleCampaignInfoQuery extends BaseParam {


	/**
	 * 广告系列id
	 */
	private String campaignId;

	private String campaignIdFuzzy;

	private List<String> campaignIdList;

	/**
	 * 广告系列名称
	 */
	private String campaignName;

	private String campaignNameFuzzy;

	/**
	 * 使用货币代码
	 */
	private String currencyCode;

	private String currencyCodeFuzzy;

	/**
	 * 每日预算(美元)
	 */
	private BigDecimal budgetAmount;

	/**
	 * 状态 ENABLED已启用 PAUSED已暂停 REMOVED已移除 UNKNOWN未知 UNSPECIFIED未指定
	 */
	private String status;

	private String statusFuzzy;

	/**
	 * 广告系列类型  DEMAND_GEN需求开发 DISPLAY展示 HOTEL酒店 LOCAL本地 LOCAL_SERVICES本地服务 MULTI_CHANNEL多渠道 PERFORMANCE_MAX效果最大化 SEARCH搜索 SHOPPING购物 SMART智能 TRAVEL旅游 UNKNOWN未知 UNSPECIFIED未指定 VIDEO视频
	 */
	private String advertisingChannelType;

	private String advertisingChannelTypeFuzzy;

	/**
	 * 投放开始时间
	 */
	private String startDateTime;

	private String startDateTimeStart;

	private String startDateTimeEnd;

	/**
	 * 投放结束时间
	 */
	private String endDateTime;

	private String endDateTimeStart;

	private String endDateTimeEnd;

	/**
	 * 负责人
	 */
	private String personInCharge;

	private String personInChargeFuzzy;


	public void setCampaignId(String campaignId){
		this.campaignId = campaignId;
	}

	public String getCampaignId(){
		return this.campaignId;
	}

	public void setCampaignIdFuzzy(String campaignIdFuzzy){
		this.campaignIdFuzzy = campaignIdFuzzy;
	}

	public String getCampaignIdFuzzy(){
		return this.campaignIdFuzzy;
	}

	public void setCampaignName(String campaignName){
		this.campaignName = campaignName;
	}

	public String getCampaignName(){
		return this.campaignName;
	}

	public void setCampaignNameFuzzy(String campaignNameFuzzy){
		this.campaignNameFuzzy = campaignNameFuzzy;
	}

	public String getCampaignNameFuzzy(){
		return this.campaignNameFuzzy;
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

	public void setBudgetAmount(BigDecimal budgetAmount){
		this.budgetAmount = budgetAmount;
	}

	public BigDecimal getBudgetAmount(){
		return this.budgetAmount;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setStatusFuzzy(String statusFuzzy){
		this.statusFuzzy = statusFuzzy;
	}

	public String getStatusFuzzy(){
		return this.statusFuzzy;
	}

	public void setAdvertisingChannelType(String advertisingChannelType){
		this.advertisingChannelType = advertisingChannelType;
	}

	public String getAdvertisingChannelType(){
		return this.advertisingChannelType;
	}

	public void setAdvertisingChannelTypeFuzzy(String advertisingChannelTypeFuzzy){
		this.advertisingChannelTypeFuzzy = advertisingChannelTypeFuzzy;
	}

	public String getAdvertisingChannelTypeFuzzy(){
		return this.advertisingChannelTypeFuzzy;
	}

	public void setStartDateTime(String startDateTime){
		this.startDateTime = startDateTime;
	}

	public String getStartDateTime(){
		return this.startDateTime;
	}

	public void setStartDateTimeStart(String startDateTimeStart){
		this.startDateTimeStart = startDateTimeStart;
	}

	public String getStartDateTimeStart(){
		return this.startDateTimeStart;
	}
	public void setStartDateTimeEnd(String startDateTimeEnd){
		this.startDateTimeEnd = startDateTimeEnd;
	}

	public String getStartDateTimeEnd(){
		return this.startDateTimeEnd;
	}

	public void setEndDateTime(String endDateTime){
		this.endDateTime = endDateTime;
	}

	public String getEndDateTime(){
		return this.endDateTime;
	}

	public void setEndDateTimeStart(String endDateTimeStart){
		this.endDateTimeStart = endDateTimeStart;
	}

	public String getEndDateTimeStart(){
		return this.endDateTimeStart;
	}
	public void setEndDateTimeEnd(String endDateTimeEnd){
		this.endDateTimeEnd = endDateTimeEnd;
	}

	public String getEndDateTimeEnd(){
		return this.endDateTimeEnd;
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

	public List<String> getCampaignIdList() {
		return campaignIdList;
	}

	public void setCampaignIdList(List<String> campaignIdList) {
		this.campaignIdList = campaignIdList;
	}
}
