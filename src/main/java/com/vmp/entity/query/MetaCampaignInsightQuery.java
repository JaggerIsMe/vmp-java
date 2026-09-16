package com.vmp.entity.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * meta广告系列表现指标参数
 */
public class MetaCampaignInsightQuery extends BaseParam {


	/**
	 * 细分报告日期
	 */
	private String segmentDate;

	private String segmentDateStart;

	private String segmentDateEnd;

	/**
	 * campaign唯一标识id
	 */
	private String campaignUid;

	private String campaignUidFuzzy;

	private List<String> campaignUidList;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	private String currencyCodeFuzzy;

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


	public void setSegmentDate(String segmentDate){
		this.segmentDate = segmentDate;
	}

	public String getSegmentDate(){
		return this.segmentDate;
	}

	public void setSegmentDateStart(String segmentDateStart){
		this.segmentDateStart = segmentDateStart;
	}

	public String getSegmentDateStart(){
		return this.segmentDateStart;
	}
	public void setSegmentDateEnd(String segmentDateEnd){
		this.segmentDateEnd = segmentDateEnd;
	}

	public String getSegmentDateEnd(){
		return this.segmentDateEnd;
	}

	public void setCampaignUid(String campaignUid){
		this.campaignUid = campaignUid;
	}

	public String getCampaignUid(){
		return this.campaignUid;
	}

	public void setCampaignUidFuzzy(String campaignUidFuzzy){
		this.campaignUidFuzzy = campaignUidFuzzy;
	}

	public String getCampaignUidFuzzy(){
		return this.campaignUidFuzzy;
	}

	public List<String> getCampaignUidList() {
		return campaignUidList;
	}

	public void setCampaignUidList(List<String> campaignUidList) {
		this.campaignUidList = campaignUidList;
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

}
