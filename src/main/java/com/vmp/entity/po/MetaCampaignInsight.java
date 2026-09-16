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
 * meta广告系列表现指标
 */
public class MetaCampaignInsight implements Serializable {


	private static final long serialVersionUID = -9214917038222113760L;
	/**
	 * 细分报告日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date segmentDate;

	/**
	 * campaign唯一标识id
	 */
	private String campaignUid;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	/**
	 * 广告花费
	 */
	private BigDecimal costAmount;

	/**
	 * 展示
	 */
	private Long impressions;

	/**
	 * 点击
	 */
	private Long clicks;

	/**
	 * 转化次数
	 */
	private Long conversions;

	/**
	 * 转化价值
	 */
	private BigDecimal conversionsValue;


	public void setSegmentDate(Date segmentDate){
		this.segmentDate = segmentDate;
	}

	public Date getSegmentDate(){
		return this.segmentDate;
	}

	public void setCampaignUid(String campaignUid){
		this.campaignUid = campaignUid;
	}

	public String getCampaignUid(){
		return this.campaignUid;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
	}

	public void setCostAmount(BigDecimal costAmount){
		this.costAmount = costAmount;
	}

	public BigDecimal getCostAmount(){
		return this.costAmount;
	}

	public void setImpressions(Long impressions){
		this.impressions = impressions;
	}

	public Long getImpressions(){
		return this.impressions;
	}

	public void setClicks(Long clicks){
		this.clicks = clicks;
	}

	public Long getClicks(){
		return this.clicks;
	}

	public void setConversions(Long conversions){
		this.conversions = conversions;
	}

	public Long getConversions(){
		return this.conversions;
	}

	public void setConversionsValue(BigDecimal conversionsValue){
		this.conversionsValue = conversionsValue;
	}

	public BigDecimal getConversionsValue(){
		return this.conversionsValue;
	}

	@Override
	public String toString (){
		return "细分报告日期:"+(segmentDate == null ? "空" : DateUtil.format(segmentDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()))+"，campaign唯一标识id:"+(campaignUid == null ? "空" : campaignUid)+"，货币代码:"+(currencyCode == null ? "空" : currencyCode)+"，广告花费:"+(costAmount == null ? "空" : costAmount)+"，展示:"+(impressions == null ? "空" : impressions)+"，点击:"+(clicks == null ? "空" : clicks)+"，转化次数:"+(conversions == null ? "空" : conversions)+"，转化价值:"+(conversionsValue == null ? "空" : conversionsValue);
	}
}
