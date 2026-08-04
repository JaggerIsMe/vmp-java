package com.vmp.entity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("shopifyConfig")
public class ShopifyConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShopifyConfig.class);

    @Value("${shopify.apiVersion}")
    private String apiVersion;
    @Value("${shopify.vantrue.host}")
    private String vantrueHost;
    @Value("${shopify.clientId}")
    private String clientId;
    @Value("${shopify.clientSecret}")
    private String clientSecret;

    public String getApiVersion() {
        return apiVersion;
    }

    public String getVantrueHost() {
        return vantrueHost;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
