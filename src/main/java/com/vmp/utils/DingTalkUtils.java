package com.vmp.utils;

import com.aliyun.teaopenapi.models.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DingTalkUtils {

    private static final Logger logger = LoggerFactory.getLogger(DingTalkUtils.class);

    public static Config buildDingTalkConfig() {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return config;
    }

}
