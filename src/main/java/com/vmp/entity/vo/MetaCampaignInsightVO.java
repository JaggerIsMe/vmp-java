package com.vmp.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class MetaCampaignInsightVO {

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

    /**
     * 点击率
     *
     * 点击/展示 (clicks/impressions)
     */
    private Double ctr;

    /**
     * 单次点击费用
     *
     * 广告花费/点击 (costAmount/clicks)
     */
    private BigDecimal cpc;

    /**
     * 转化率
     *
     * 转化次数/点击 (conversions/clicks)
     */
    private Double cvr;

    /**
     * 广告支出回报率
     *
     * 转化价值/广告花费 (conversionsValue/costAmount)
     */
    private Double roas;

    /**
     * 单次行动成本
     *
     * 广告花费/转化次数 (conversionsValue/conversions)
     */
    private BigDecimal cpa;

    private CrossPlatformProductCommonInfoVO crossPlatformProductCommonInfoVO;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getAdsAccountId() {
        return adsAccountId;
    }

    public void setAdsAccountId(String adsAccountId) {
        this.adsAccountId = adsAccountId;
    }

    public String getAdsAccountName() {
        return adsAccountName;
    }

    public void setAdsAccountName(String adsAccountName) {
        this.adsAccountName = adsAccountName;
    }

    public Integer getAdsAccountStatus() {
        return adsAccountStatus;
    }

    public void setAdsAccountStatus(Integer adsAccountStatus) {
        this.adsAccountStatus = adsAccountStatus;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(String effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public Long getImpressions() {
        return impressions;
    }

    public void setImpressions(Long impressions) {
        this.impressions = impressions;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public Long getConversions() {
        return conversions;
    }

    public void setConversions(Long conversions) {
        this.conversions = conversions;
    }

    public BigDecimal getConversionsValue() {
        return conversionsValue;
    }

    public void setConversionsValue(BigDecimal conversionsValue) {
        this.conversionsValue = conversionsValue;
    }

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }

    public BigDecimal getCpc() {
        return cpc;
    }

    public void setCpc(BigDecimal cpc) {
        this.cpc = cpc;
    }

    public Double getCvr() {
        return cvr;
    }

    public void setCvr(Double cvr) {
        this.cvr = cvr;
    }

    public Double getRoas() {
        return roas;
    }

    public void setRoas(Double roas) {
        this.roas = roas;
    }

    public BigDecimal getCpa() {
        return cpa;
    }

    public void setCpa(BigDecimal cpa) {
        this.cpa = cpa;
    }

    public CrossPlatformProductCommonInfoVO getCrossPlatformProductCommonInfoVO() {
        return crossPlatformProductCommonInfoVO;
    }

    public void setCrossPlatformProductCommonInfoVO(CrossPlatformProductCommonInfoVO crossPlatformProductCommonInfoVO) {
        this.crossPlatformProductCommonInfoVO = crossPlatformProductCommonInfoVO;
    }
}
