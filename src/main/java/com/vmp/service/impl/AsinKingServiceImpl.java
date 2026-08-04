package com.vmp.service.impl;

import com.vmp.asinking.AKConfig;
import com.vmp.asinking.ApiSign;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.apidto.AKResult;
import com.vmp.entity.apidto.AKToken;
import com.vmp.redis.RedisUtils;
import com.vmp.service.AsinKingService;
import com.vmp.utils.JsonUtils;
import com.vmp.utils.OKHttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 领星业务接口
 */
@Service("asinKingService")
public class AsinKingServiceImpl implements AsinKingService {

    private static final Logger logger = LoggerFactory.getLogger(AsinKingServiceImpl.class);

    @Resource
    private AKConfig akConfig;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 构建通用查询参数
     *
     * @return
     */
    private Map<String, String> buildCommonQueryParams() {
        String appId = akConfig.getAsinkingAppId();
        String accessToken = getAccessToken();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("timestamp", System.currentTimeMillis() / 1000 + "");
        queryParams.put("access_token", accessToken);
        queryParams.put("app_key", appId);

        return queryParams;
    }

    /**
     * 获取加密Sign
     * @param queryParamsMap
     * @param bodyMap
     * @return
     */
    private String getSign(Map<String, String> queryParamsMap, Map<String, Object> bodyMap) {
        Map<String, Object> signMap = new HashMap<>(queryParamsMap);
        if (null != bodyMap) {
            signMap.putAll(bodyMap);
        }
        return ApiSign.sign(signMap, akConfig.getAsinkingAppId());
    }

    /**
     * 保存Access Token
     *
     * @return
     */
    public AKToken saveAccessToken() {
        String url = akConfig.getAsinkingEndpoint() + "/api/auth-server/oauth/access-token";
        String appId = akConfig.getAsinkingAppId();
        String appSecret = akConfig.getAsinkingAppSecret();

        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        params.put("appSecret", appSecret);

        String respStr = OKHttpUtils.postRequest(url, params);
        AKResult result = JsonUtils.convertJson2Obj(respStr, AKResult.class);
        AKToken token = JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(result.getData()), AKToken.class);

        redisUtils.setex(Constants.AK_ACCESS_TOKEN, token, token.getExpiresIn());

        return token;
    }

    /**
     * 刷新Access Token
     *
     * @param akToken
     * @return
     */
    public void refreshAccessToken(AKToken akToken) {
        String url = akConfig.getAsinkingEndpoint() + "/api/auth-server/oauth/refresh";
        String appId = akConfig.getAsinkingAppId();
        String refreshToken = akToken.getRefreshToken();

        Map<String, String> params = new HashMap<>();
        params.put("appId", appId);
        params.put("refreshToken", refreshToken);

        String respStr = OKHttpUtils.postRequest(url, params);
        AKResult result = JsonUtils.convertJson2Obj(respStr, AKResult.class);
        AKToken token = JsonUtils.convertJson2Obj(JsonUtils.convertObj2Json(result.getData()), AKToken.class);

        redisUtils.setex(Constants.AK_ACCESS_TOKEN, token, token.getExpiresIn());
    }

    /**
     * 获取Access Token
     *
     * @return
     */
    @Override
    public String getAccessToken() {
        AKToken akToken = (AKToken) redisUtils.get(Constants.AK_ACCESS_TOKEN);
        if (null == akToken) {
            return saveAccessToken().getAccessToken();
        }
        // 刷新token
        Long ttl = redisUtils.getExpires(Constants.AK_ACCESS_TOKEN);
        if (null != ttl && ttl < Constants.REDIS_KEY_EXPIRES_FIVE_MIN) {
            refreshAccessToken(akToken);
        }
        return akToken.getAccessToken();
    }
}
