package com.vmp.entity.apidto;

import java.util.List;

public class GoogleAdsApiResult<T> {

    private List<T> results;

    private String fieldMask;

    private String queryResourceConsumption;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getFieldMask() {
        return fieldMask;
    }

    public void setFieldMask(String fieldMask) {
        this.fieldMask = fieldMask;
    }

    public String getQueryResourceConsumption() {
        return queryResourceConsumption;
    }

    public void setQueryResourceConsumption(String queryResourceConsumption) {
        this.queryResourceConsumption = queryResourceConsumption;
    }
}
