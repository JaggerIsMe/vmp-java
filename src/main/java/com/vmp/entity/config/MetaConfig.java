package com.vmp.entity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("metaConfig")
public class MetaConfig {

    private static final Logger logger = LoggerFactory.getLogger(MetaConfig.class);

    @Value("${meta.host}")
    private String apiHost;
    @Value("${meta.bearerToken}")
    private String bearerToken;

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }
}
