package com.vmp.entity.dashboardVo;

import java.math.BigDecimal;

public class ShopifySalesDailyPerformanceVO {

    private String reportDate;

    private BigDecimal totalSalesPrice;
    private Long totalSalesUnit;

    private Long sessions;

    private Long sessionsWithCartAdditions;

    private Long sessionsThatReachedCheckout;

    public BigDecimal getTotalSalesPrice() {
        return totalSalesPrice;
    }

    public void setTotalSalesPrice(BigDecimal totalSalesPrice) {
        this.totalSalesPrice = totalSalesPrice;
    }

    public Long getTotalSalesUnit() {
        return totalSalesUnit;
    }

    public void setTotalSalesUnit(Long totalSalesUnit) {
        this.totalSalesUnit = totalSalesUnit;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public Long getSessions() {
        return sessions;
    }

    public void setSessions(Long sessions) {
        this.sessions = sessions;
    }

    public Long getSessionsWithCartAdditions() {
        return sessionsWithCartAdditions;
    }

    public void setSessionsWithCartAdditions(Long sessionsWithCartAdditions) {
        this.sessionsWithCartAdditions = sessionsWithCartAdditions;
    }

    public Long getSessionsThatReachedCheckout() {
        return sessionsThatReachedCheckout;
    }

    public void setSessionsThatReachedCheckout(Long sessionsThatReachedCheckout) {
        this.sessionsThatReachedCheckout = sessionsThatReachedCheckout;
    }
}
