package com.vmp.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.vmp.entity.apidto.MetaAdsAccountApiDto;
import com.vmp.entity.apidto.MetaAdsApiResult;
import com.vmp.entity.apidto.MetaAdsCampaignApiDto;
import com.vmp.entity.apidto.MetaAdsCampaignInsightApiDto;
import com.vmp.entity.config.MetaConfig;
import com.vmp.service.MetaAdsApiService;
import com.vmp.utils.JsonUtils;
import com.vmp.utils.OKHttpUtils;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("metaAdsApiService")
public class MetaAdsApiServiceImpl implements MetaAdsApiService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleAdsApiServiceImpl.class);

    @Resource
    private MetaConfig metaConfig;

    /**
     * 获取广告账户
     *
     * @param after
     * @return
     */
    @Override
    public MetaAdsApiResult<MetaAdsAccountApiDto> getAdsAccountList(String after) {
        String url = metaConfig.getApiHost() + "/me/adaccounts?fields=id,name,account_status,currency,timezone_name&limit=100";
        if (!StringTools.isEmpty(after)) {
            url = url + "&after=" + after;
        }

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + metaConfig.getBearerToken());

        String respStr = OKHttpUtils.getRequest(url, header);
        return JsonUtils.convertJson2Obj(
                respStr,
                new TypeReference<MetaAdsApiResult<MetaAdsAccountApiDto>>() {
                });
    }

    /**
     * 获取广告系列
     *
     * @param accountId
     * @param after
     * @return
     */
    @Override
    public MetaAdsApiResult<MetaAdsCampaignApiDto> getCampaignList(String accountId, String after) {
        String url = buildAccountResourceUrl(accountId, "campaigns")
                + "?fields=id,name,effective_status,objective,start_time,stop_time&limit=100";
        if (!StringTools.isEmpty(after)) {
            url = url + "&after=" + after;
        }

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + metaConfig.getBearerToken());

        String respStr = OKHttpUtils.getRequest(url, header);
        return JsonUtils.convertJson2Obj(
                respStr,
                new TypeReference<MetaAdsApiResult<MetaAdsCampaignApiDto>>() {
                });
    }

    /**
     * 获取广告Insight表现
     *
     * @param accountId
     * @param after
     * @param since
     * @param until
     * @return
     */
    @Override
    public MetaAdsApiResult<MetaAdsCampaignInsightApiDto> getCampaignInsights(String accountId, String after, String since, String until) {
        String url = buildAccountResourceUrl(accountId, "insights")
                + "?level=campaign&fields=campaign_id,campaign_name,spend,impressions,clicks,"
                + "inline_link_clicks,actions,action_values,date_start,date_stop"
                + "&time_range={\"since\":\"" + since + "\",\"until\":\"" + until + "\"}"
                + "&time_increment=1&limit=100";
        if (!StringTools.isEmpty(after)) {
            url = url + "&after=" + after;
        }

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + metaConfig.getBearerToken());

        String respStr = OKHttpUtils.getRequest(url, header);
        return JsonUtils.convertJson2Obj(
                respStr,
                new TypeReference<MetaAdsApiResult<MetaAdsCampaignInsightApiDto>>() {
                });
    }

    String buildAccountResourceUrl(String accountId, String resource) {
        String normalizedAccountId = accountId.startsWith("act_") ? accountId : "act_" + accountId;
        return metaConfig.getApiHost() + "/" + normalizedAccountId + "/" + resource;
    }
}
