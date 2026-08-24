package com.vmp.entity.apidto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class GoogleAdsCampaignMetricsApiDto {

    private Campaign campaign;

    private Metrics metrics;

    private CampaignBudget campaignBudget;

    private Customer customer;

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public CampaignBudget getCampaignBudget() {
        return campaignBudget;
    }

    public void setCampaignBudget(CampaignBudget campaignBudget) {
        this.campaignBudget = campaignBudget;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static class Campaign{
        private String resourceName;
        private String status;
        private String advertisingChannelType;
        private String name;
        private String id;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date startDateTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date endDateTime;

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }

    public static class Metrics{
        private Long clicks;
        private BigDecimal conversionsValue;
        private BigDecimal conversions;
        private Long costMicros;
        private BigDecimal ctr;
        private BigDecimal averageCpc;
        private Long impressions;

        public Long getClicks() {
            return clicks;
        }

        public void setClicks(Long clicks) {
            this.clicks = clicks;
        }

        public BigDecimal getConversionsValue() {
            return conversionsValue;
        }

        public void setConversionsValue(BigDecimal conversionsValue) {
            this.conversionsValue = conversionsValue;
        }

        public BigDecimal getConversions() {
            return conversions;
        }

        public void setConversions(BigDecimal conversions) {
            this.conversions = conversions;
        }

        public Long getCostMicros() {
            return costMicros;
        }

        public void setCostMicros(Long costMicros) {
            this.costMicros = costMicros;
        }

        public BigDecimal getCtr() {
            return ctr;
        }

        public void setCtr(BigDecimal ctr) {
            this.ctr = ctr;
        }

        public BigDecimal getAverageCpc() {
            return averageCpc;
        }

        public void setAverageCpc(BigDecimal averageCpc) {
            this.averageCpc = averageCpc;
        }

        public Long getImpressions() {
            return impressions;
        }

        public void setImpressions(Long impressions) {
            this.impressions = impressions;
        }
    }

    public static class CampaignBudget{
        private String resourceName;
        private Long amountMicros;

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

        public Long getAmountMicros() {
            return amountMicros;
        }

        public void setAmountMicros(Long amountMicros) {
            this.amountMicros = amountMicros;
        }
    }

    public static class Customer {
        private String resourceName;
        private String id;
        private String currencyCode;

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }

}
