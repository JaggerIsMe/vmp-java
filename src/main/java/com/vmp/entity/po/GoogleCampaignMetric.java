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
 * 谷歌广告系列表现指标
 */
public class GoogleCampaignMetric implements Serializable {


	private static final long serialVersionUID = 7366590730591272299L;
	/**
	 * 细分报告日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date segmentDate;

	/**
	 * 广告系列id
	 */
	private String campaignId;

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
	private BigDecimal conversions;

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

	public void setCampaignId(String campaignId){
		this.campaignId = campaignId;
	}

	public String getCampaignId(){
		return this.campaignId;
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

	public void setConversions(BigDecimal conversions){
		this.conversions = conversions;
	}

	public BigDecimal getConversions(){
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
		return "细分报告日期:"+(segmentDate == null ? "空" : DateUtil.format(segmentDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()))+"，广告系列id:"+(campaignId == null ? "空" : campaignId)+"，货币代码:"+(currencyCode == null ? "空" : currencyCode)+"，广告花费:"+(costAmount == null ? "空" : costAmount)+"，展示:"+(impressions == null ? "空" : impressions)+"，点击:"+(clicks == null ? "空" : clicks)+"，转化次数:"+(conversions == null ? "空" : conversions)+"，转化价值:"+(conversionsValue == null ? "空" : conversionsValue);
	}
}
