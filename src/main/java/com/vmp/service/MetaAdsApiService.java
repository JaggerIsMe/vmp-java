package com.vmp.service;

import com.vmp.entity.apidto.MetaAdsAccountApiDto;
import com.vmp.entity.apidto.MetaAdsApiResult;
import com.vmp.entity.apidto.MetaAdsCampaignApiDto;
import com.vmp.entity.apidto.MetaAdsCampaignInsightApiDto;
import com.vmp.entity.po.MetaAdsCampaignInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public interface MetaAdsApiService {

    /**
     * 获取广告账户
     * @param after
     * @return
     */
    MetaAdsApiResult<MetaAdsAccountApiDto> getAdsAccountList(String after);

    /**
     * 获取广告系列
     * @param accountId
     * @param after
     * @return
     */
    MetaAdsApiResult<MetaAdsCampaignApiDto> getCampaignList(String accountId, String after);

    /**
     * 获取广告Insight表现
     * @param accountId
     * @param after
     * @param since
     * @param until
     * @return
     */
    MetaAdsApiResult<MetaAdsCampaignInsightApiDto> getCampaignInsights(String accountId, String after, @DateTimeFormat(pattern = "yyyy-MM-dd") String since, @DateTimeFormat(pattern = "yyyy-MM-dd") String until);

}
