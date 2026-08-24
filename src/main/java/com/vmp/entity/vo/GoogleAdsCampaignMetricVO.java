package com.vmp.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmp.entity.po.CrossPlatformProductCommonInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class GoogleAdsCampaignMetricVO {

    /**
     * 广告系列id
     */
    private String campaignId;

    /**
     * 广告系列名称
     */
    private String campaignName;

    /**
     * 货币代码
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdvertisingChannelType() {
        return advertisingChannelType;
    }

    public void setAdvertisingChannelType(String advertisingChannelType) {
        this.advertisingChannelType = advertisingChannelType;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
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

    public BigDecimal getConversions() {
        return conversions;
    }

    public void setConversions(BigDecimal conversions) {
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
