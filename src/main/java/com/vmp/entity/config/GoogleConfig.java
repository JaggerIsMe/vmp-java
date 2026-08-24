package com.vmp.entity.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("googleConfig")
public class GoogleConfig {

    private static final Logger logger = LoggerFactory.getLogger(GoogleConfig.class);

    @Value("${google.cloud.clientId}")
    private String cloudClientId;
    @Value("${google.cloud.clientSecret}")
    private String cloudClientSecret;
    @Value("${google.cloud.refreshToken}")
    private String cloudRefreshToken;
    @Value("${google.ads.developerToken}")
    private String adsDeveloperToken;
    @Value("${google.ads.loginCustomerId}")
    private String adsLoginCustomerId;
    @Value("${google.ads.customerId}")
    private String adsCustomerId;
    @Value("${google.ads.host}")
    private String adsHost;
    @Value("${google.ads.version}")
    private String adsVersion;

    public String getCloudClientId() {
        return cloudClientId;
    }

    public String getCloudClientSecret() {
        return cloudClientSecret;
    }

    public String getCloudRefreshToken() {
        return cloudRefreshToken;
    }

    public String getAdsDeveloperToken() {
        return adsDeveloperToken;
    }

    public String getAdsLoginCustomerId() {
        return adsLoginCustomerId;
    }

    public String getAdsCustomerId() {
        return adsCustomerId;
    }

    public String getAdsHost() {
        return adsHost;
    }

    public String getAdsVersion() {
        return adsVersion;
    }
}
