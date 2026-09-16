package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * meta广告系列信息
 */
public class MetaAdsCampaignInfo implements Serializable {


	private static final long serialVersionUID = 2142017932119749349L;
	/**
	 * campaign唯一id
	 */
	private String uid;

	/**
	 * 广告系列id
	 */
	private String campaignId;

	/**
	 * 广告系列名称
	 */
	private String campaignName;

	/**
	 * 广告账户id
	 */
	private String adsAccountId;

	/**
	 * 广告账户名称
	 */
	private String adsAccountName;

	/**
	 * 广告账户状态
	 */
	private Integer adsAccountStatus;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	/**
	 * 广告系列状态
	 */
	private String effectiveStatus;

	/**
	 * 投放开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	/**
	 * 投放结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stopTime;

	/**
	 * 负责人
	 */
	private String personInCharge;


	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return this.uid;
	}

	public void setCampaignId(String campaignId){
		this.campaignId = campaignId;
	}

	public String getCampaignId(){
		return this.campaignId;
	}

	public void setCampaignName(String campaignName){
		this.campaignName = campaignName;
	}

	public String getCampaignName(){
		return this.campaignName;
	}

	public void setAdsAccountId(String adsAccountId){
		this.adsAccountId = adsAccountId;
	}

	public String getAdsAccountId(){
		return this.adsAccountId;
	}

	public void setAdsAccountName(String adsAccountName){
		this.adsAccountName = adsAccountName;
	}

	public String getAdsAccountName(){
		return this.adsAccountName;
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

	public void setEffectiveStatus(String effectiveStatus){
		this.effectiveStatus = effectiveStatus;
	}

	public String getEffectiveStatus(){
		return this.effectiveStatus;
	}

	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	public Date getStartTime(){
		return this.startTime;
	}

	public void setStopTime(Date stopTime){
		this.stopTime = stopTime;
	}

	public Date getStopTime(){
		return this.stopTime;
	}

	public void setPersonInCharge(String personInCharge){
		this.personInCharge = personInCharge;
	}

	public String getPersonInCharge(){
		return this.personInCharge;
	}

	@Override
	public String toString (){
		return "campaign唯一id:"+(uid == null ? "空" : uid)+"，广告系列id:"+(campaignId == null ? "空" : campaignId)+"，广告系列名称:"+(campaignName == null ? "空" : campaignName)+"，广告账户id:"+(adsAccountId == null ? "空" : adsAccountId)+"，广告账户名称:"+(adsAccountName == null ? "空" : adsAccountName)+"，广告账户状态:"+(adsAccountStatus == null ? "空" : adsAccountStatus)+"，货币代码:"+(currencyCode == null ? "空" : currencyCode)+"，广告系列状态:"+(effectiveStatus == null ? "空" : effectiveStatus)+"，投放开始时间:"+(startTime == null ? "空" : DateUtil.format(startTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，投放结束时间:"+(stopTime == null ? "空" : DateUtil.format(stopTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，负责人:"+(personInCharge == null ? "空" : personInCharge);
	}
}
