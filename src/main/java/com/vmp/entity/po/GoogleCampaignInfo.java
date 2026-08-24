package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 谷歌广告系列
 */
public class GoogleCampaignInfo implements Serializable {


	private static final long serialVersionUID = 7092878789221812636L;
	/**
	 * 广告系列id
	 */
	private String campaignId;

	/**
	 * 广告系列名称
	 */
	private String campaignName;

	/**
	 * 使用货币代码
	 */
	private String currencyCode;

	/**
	 * 每日预算(美元)
	 */
	private BigDecimal budgetAmount;

	/**
	 * 状态 ENABLED已启用 PAUSED已暂停 REMOVED已移除 UNKNOWN未知 UNSPECIFIED未指定
	 */
	private String status;

	/**
	 * 广告系列类型  DEMAND_GEN需求开发 DISPLAY展示 HOTEL酒店 LOCAL本地 LOCAL_SERVICES本地服务 MULTI_CHANNEL多渠道 PERFORMANCE_MAX效果最大化 SEARCH搜索 SHOPPING购物 SMART智能 TRAVEL旅游 UNKNOWN未知 UNSPECIFIED未指定 VIDEO视频
	 */
	private String advertisingChannelType;

	/**
	 * 投放开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDateTime;

	/**
	 * 投放结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDateTime;

	/**
	 * 负责人
	 */
	private String personInCharge;


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

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
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

	public void setAdvertisingChannelType(String advertisingChannelType){
		this.advertisingChannelType = advertisingChannelType;
	}

	public String getAdvertisingChannelType(){
		return this.advertisingChannelType;
	}

	public void setStartDateTime(Date startDateTime){
		this.startDateTime = startDateTime;
	}

	public Date getStartDateTime(){
		return this.startDateTime;
	}

	public void setEndDateTime(Date endDateTime){
		this.endDateTime = endDateTime;
	}

	public Date getEndDateTime(){
		return this.endDateTime;
	}

	public void setPersonInCharge(String personInCharge){
		this.personInCharge = personInCharge;
	}

	public String getPersonInCharge(){
		return this.personInCharge;
	}

	@Override
	public String toString (){
		return "广告系列id:"+(campaignId == null ? "空" : campaignId)+"，广告系列名称:"+(campaignName == null ? "空" : campaignName)+"，使用货币代码:"+(currencyCode == null ? "空" : currencyCode)+"，每日预算(美元):"+(budgetAmount == null ? "空" : budgetAmount)+"，状态 ENABLED已启用 PAUSED已暂停 REMOVED已移除 UNKNOWN未知 UNSPECIFIED未指定:"+(status == null ? "空" : status)+"，广告系列类型  DEMAND_GEN需求开发 DISPLAY展示 HOTEL酒店 LOCAL本地 LOCAL_SERVICES本地服务 MULTI_CHANNEL多渠道 PERFORMANCE_MAX效果最大化 SEARCH搜索 SHOPPING购物 SMART智能 TRAVEL旅游 UNKNOWN未知 UNSPECIFIED未指定 VIDEO视频:"+(advertisingChannelType == null ? "空" : advertisingChannelType)+"，投放开始时间:"+(startDateTime == null ? "空" : DateUtil.format(startDateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，投放结束时间:"+(endDateTime == null ? "空" : DateUtil.format(endDateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，负责人:"+(personInCharge == null ? "空" : personInCharge);
	}
}
