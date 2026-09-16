package com.vmp.entity.query;

public class MetaAdsCampaignCommonQuery extends BaseParam {

    // 以下原有MetaAdsCampaignInfoQuery相关查询

    /**
     * campaign唯一id
     */
    private String uid;

    private String uidFuzzy;

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

    // 以下Mapping相关查询
    private String mappingProductUid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUidFuzzy() {
        return uidFuzzy;
    }

    public void setUidFuzzy(String uidFuzzy) {
        this.uidFuzzy = uidFuzzy;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignIdFuzzy() {
        return campaignIdFuzzy;
    }

    public void setCampaignIdFuzzy(String campaignIdFuzzy) {
        this.campaignIdFuzzy = campaignIdFuzzy;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignNameFuzzy() {
        return campaignNameFuzzy;
    }

    public void setCampaignNameFuzzy(String campaignNameFuzzy) {
        this.campaignNameFuzzy = campaignNameFuzzy;
    }

    public String getAdsAccountId() {
        return adsAccountId;
    }

    public void setAdsAccountId(String adsAccountId) {
        this.adsAccountId = adsAccountId;
    }

    public String getAdsAccountIdFuzzy() {
        return adsAccountIdFuzzy;
    }

    public void setAdsAccountIdFuzzy(String adsAccountIdFuzzy) {
        this.adsAccountIdFuzzy = adsAccountIdFuzzy;
    }

    public String getAdsAccountName() {
        return adsAccountName;
    }

    public void setAdsAccountName(String adsAccountName) {
        this.adsAccountName = adsAccountName;
    }

    public String getAdsAccountNameFuzzy() {
        return adsAccountNameFuzzy;
    }

    public void setAdsAccountNameFuzzy(String adsAccountNameFuzzy) {
        this.adsAccountNameFuzzy = adsAccountNameFuzzy;
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

    public String getCurrencyCodeFuzzy() {
        return currencyCodeFuzzy;
    }

    public void setCurrencyCodeFuzzy(String currencyCodeFuzzy) {
        this.currencyCodeFuzzy = currencyCodeFuzzy;
    }

    public String getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(String effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }

    public String getEffectiveStatusFuzzy() {
        return effectiveStatusFuzzy;
    }

    public void setEffectiveStatusFuzzy(String effectiveStatusFuzzy) {
        this.effectiveStatusFuzzy = effectiveStatusFuzzy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeStart() {
        return startTimeStart;
    }

    public void setStartTimeStart(String startTimeStart) {
        this.startTimeStart = startTimeStart;
    }

    public String getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(String startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStopTimeStart() {
        return stopTimeStart;
    }

    public void setStopTimeStart(String stopTimeStart) {
        this.stopTimeStart = stopTimeStart;
    }

    public String getStopTimeEnd() {
        return stopTimeEnd;
    }

    public void setStopTimeEnd(String stopTimeEnd) {
        this.stopTimeEnd = stopTimeEnd;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getPersonInChargeFuzzy() {
        return personInChargeFuzzy;
    }

    public void setPersonInChargeFuzzy(String personInChargeFuzzy) {
        this.personInChargeFuzzy = personInChargeFuzzy;
    }

    public String getMappingProductUid() {
        return mappingProductUid;
    }

    public void setMappingProductUid(String mappingProductUid) {
        this.mappingProductUid = mappingProductUid;
    }
}
