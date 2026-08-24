package com.vmp.entity.dashboardVo;

import java.math.BigDecimal;

public class GoogleAdsCampaignMetricDailyPerformanceVO {

    private String reportDate;

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

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
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
}
