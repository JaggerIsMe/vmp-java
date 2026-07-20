package com.vmp.asinking;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("akConfig")
public class AKConfig {

    // 领星asinking相关
    @Value("${asinking.appId}")
    private String asinkingAppId;
    @Value("${asinking.appSecret}")
    private String asinkingAppSecret;
    @Value("${asinking.endpoint}")
    private String asinkingEndpoint;

    public String getAsinkingAppId() {
        return asinkingAppId;
    }

    public String getAsinkingAppSecret() {
        return asinkingAppSecret;
    }

    public String getAsinkingEndpoint() {
        return asinkingEndpoint;
    }
}
