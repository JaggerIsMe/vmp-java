package com.vmp.service;

import com.vmp.entity.apidto.GoogleAdsCampaignMetricsApiDto;

import java.util.Date;
import java.util.List;

public interface GoogleAdsApiService {

    /**
     * 获取Access Token
     * @return
     */
    String getAccessToken();

    /**
     * 获取谷歌广告系列表现
     * @param segmentsDate
     * @return
     */
    List<GoogleAdsCampaignMetricsApiDto> getGoogleAdsCampaignMetrics(Date segmentsDate);

}
