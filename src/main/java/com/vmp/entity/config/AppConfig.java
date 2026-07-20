package com.vmp.entity.config;

import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfig")
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    /**
     * 文件目录
     */
    @Value("${project.folder:}")
    private String projectFolder;

    // 钉钉相关
    @Value("${dingtalk.app.clientId}")
    private String dingTalkAppClientId;
    @Value("${dingtalk.app.clientSecret}")
    private String dingTalkAppClientSecret;

    public String getProjectFolder() {
        if (!StringTools.isEmpty(projectFolder) && !projectFolder.endsWith("/")) {
            projectFolder = projectFolder + "/";
        }
        return projectFolder;
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getDingTalkAppClientId() {
        return dingTalkAppClientId;
    }

    public String getDingTalkAppClientSecret() {
        return dingTalkAppClientSecret;
    }
}
