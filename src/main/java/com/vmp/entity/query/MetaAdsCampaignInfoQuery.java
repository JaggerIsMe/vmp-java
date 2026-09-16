package com.vmp.entity.query;

import java.util.Date;
import java.util.List;


/**
 * meta广告系列信息参数
 */
public class MetaAdsCampaignInfoQuery extends BaseParam {


	/**
	 * campaign唯一id
	 */
	private String uid;

	private String uidFuzzy;

	private List<String> uidList;

	/**
	 * 广告系列id
	 */
	private String campaignId;

	private String campaignIdFuzzy;

	/**
	 * 广告系列名称
	 */
	private String campaignName;

	private String campaignNameFuzzy;

	/**
	 * 广告账户id
	 */
	private String adsAccountId;

	private String adsAccountIdFuzzy;

	/**
	 * 广告账户名称
	 */
	private String adsAccountName;

	private String adsAccountNameFuzzy;

	/**
	 * 广告账户状态
	 */
	private Integer adsAccountStatus;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	private String currencyCodeFuzzy;

	/**
	 * 广告系列状态
	 */
	private String effectiveStatus;

	private String effectiveStatusFuzzy;

	/**
	 * 投放开始时间
	 */
	private String startTime;

	private String startTimeStart;

	private String startTimeEnd;

	/**
	 * 投放结束时间
	 */
	private String stopTime;

	private String stopTimeStart;

	private String stopTimeEnd;

	/**
	 * 负责人
	 */
	private String personInCharge;

	private String personInChargeFuzzy;


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

	public List<String> getUidList() {
		return uidList;
	}

	public void setUidList(List<String> uidList) {
		this.uidList = uidList;
	}

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

	public void setAdsAccountId(String adsAccountId){
		this.adsAccountId = adsAccountId;
	}

	public String getAdsAccountId(){
		return this.adsAccountId;
	}

	public void setAdsAccountIdFuzzy(String adsAccountIdFuzzy){
		this.adsAccountIdFuzzy = adsAccountIdFuzzy;
	}

	public String getAdsAccountIdFuzzy(){
		return this.adsAccountIdFuzzy;
	}

	public void setAdsAccountName(String adsAccountName){
		this.adsAccountName = adsAccountName;
	}

	public String getAdsAccountName(){
		return this.adsAccountName;
	}

	public void setAdsAccountNameFuzzy(String adsAccountNameFuzzy){
		this.adsAccountNameFuzzy = adsAccountNameFuzzy;
	}

	public String getAdsAccountNameFuzzy(){
		return this.adsAccountNameFuzzy;
	}

	public void setAdsAccountStatus(Integer adsAccountStatus){
		this.adsAccountStatus = adsAccountStatus;
	}

	public Integer getAdsAccountStatus(){
		return this.adsAccountStatus;
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

	public void setEffectiveStatus(String effectiveStatus){
		this.effectiveStatus = effectiveStatus;
	}

	public String getEffectiveStatus(){
		return this.effectiveStatus;
	}

	public void setEffectiveStatusFuzzy(String effectiveStatusFuzzy){
		this.effectiveStatusFuzzy = effectiveStatusFuzzy;
	}

	public String getEffectiveStatusFuzzy(){
		return this.effectiveStatusFuzzy;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return this.startTime;
	}

	public void setStartTimeStart(String startTimeStart){
		this.startTimeStart = startTimeStart;
	}

	public String getStartTimeStart(){
		return this.startTimeStart;
	}
	public void setStartTimeEnd(String startTimeEnd){
		this.startTimeEnd = startTimeEnd;
	}

	public String getStartTimeEnd(){
		return this.startTimeEnd;
	}

	public void setStopTime(String stopTime){
		this.stopTime = stopTime;
	}

	public String getStopTime(){
		return this.stopTime;
	}

	public void setStopTimeStart(String stopTimeStart){
		this.stopTimeStart = stopTimeStart;
	}

	public String getStopTimeStart(){
		return this.stopTimeStart;
	}
	public void setStopTimeEnd(String stopTimeEnd){
		this.stopTimeEnd = stopTimeEnd;
	}

	public String getStopTimeEnd(){
		return this.stopTimeEnd;
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

}
