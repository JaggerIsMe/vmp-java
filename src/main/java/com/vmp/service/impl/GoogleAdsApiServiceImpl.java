package com.vmp.service.impl;

import com.vmp.entity.apidto.GoogleAdsApiResult;
import com.vmp.entity.apidto.GoogleAdsCampaignMetricsApiDto;
import com.vmp.entity.apidto.GoogleToken;
import com.vmp.entity.config.GoogleConfig;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.service.GoogleAdsApiService;
import com.vmp.utils.DateUtil;
import com.vmp.utils.JsonUtils;
import com.vmp.utils.OKHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("googleAdsApiService")
public class GoogleAdsApiServiceImpl implements GoogleAdsApiService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleAdsApiServiceImpl.class);

    @Resource
    private GoogleConfig googleConfig;

    /**
     * 获取Access Token
     *
     * @return
     */
    @Override
    public String getAccessToken() {
        String url = "https://www.googleapis.com/oauth2/v3/token";
        String grantType = "refresh_token";
        String clientId = googleConfig.getCloudClientId();
        String clientSecret = googleConfig.getCloudClientSecret();
        String refreshToken = googleConfig.getCloudRefreshToken();

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", grantType);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("refresh_token", refreshToken);

        String respStr = OKHttpUtils.postRequest(url, params);
        GoogleToken token = JsonUtils.convertJson2Obj(respStr, GoogleToken.class);

        return token.getAccessToken();
    }

    /**
     * 获取谷歌广告系列表现
     *
     * @param segmentsDate
     * @return
     */
    @Override
    public List<GoogleAdsCampaignMetricsApiDto> getGoogleAdsCampaignMetrics(Date segmentsDate) {
        String url = googleConfig.getAdsHost() + "/" + googleConfig.getAdsVersion() + "/customers/" + googleConfig.getAdsCustomerId() + "/googleAds:search";

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("developer-token", googleConfig.getAdsDeveloperToken());
        header.put("login-customer-id", googleConfig.getAdsLoginCustomerId());
        header.put("Authorization", "Bearer " + getAccessToken());

        String query =
                "SELECT " +
                        "campaign.id, " +
                        "campaign.name, " +
                        "campaign.status, " +
                        "campaign.optimization_score, " +
                        "campaign.advertising_channel_type, " +
                        "campaign.start_date_time, " +
                        "campaign.end_date_time, " +
                        "campaign_budget.amount_micros, " +
                        "metrics.clicks, " +
                        "metrics.impressions, " +
                        "metrics.ctr, " +
                        "metrics.average_cpc, " +
                        "metrics.cost_micros, " +
                        "metrics.conversions, " +
                        "metrics.conversions_value, " +
                        "customer.id, " +
                        "customer.currency_code " +
                "FROM " +
                        "campaign " +
                "WHERE " +
                        "segments.date = '" + DateUtil.format(segmentsDate, DateTimePatternEnum.YYYYMMDD.getPattern()) + "'";


        Map<String, Object> body = new HashMap<>();
        body.put("query", query);

        String respStr = OKHttpUtils.postJsonRequest(url, body, header);
        GoogleAdsApiResult result = JsonUtils.convertJson2Obj(respStr, GoogleAdsApiResult.class);

        return JsonUtils.convertJsonArray2List(JsonUtils.convertObj2Json(result.getResults()), GoogleAdsCampaignMetricsApiDto.class);
    }
}
