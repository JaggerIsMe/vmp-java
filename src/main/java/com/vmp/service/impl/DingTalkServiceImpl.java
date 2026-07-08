package com.vmp.service.impl;

import com.aliyun.dingtalkcontact_1_0.Client;
import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponse;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.vmp.entity.config.AppConfig;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.DingTalkUserInfoDto;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.enums.AdminStatusEnum;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.UserStatusEnum;
import com.vmp.entity.po.UserInfo;
import com.vmp.exception.BusinessException;
import com.vmp.redis.RedisComponent;
import com.vmp.service.DingTalkService;
import com.vmp.service.UserInfoService;
import com.vmp.utils.DingTalkUtils;
import com.vmp.utils.OKHttpUtils;
import com.vmp.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

/**
 * 钉钉应用 业务接口实现
 */
@Service("dingTalkService")
public class DingTalkServiceImpl implements DingTalkService {

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private AppConfig appConfig;

    @Resource
    private UserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(DingTalkServiceImpl.class);

    /**
     * 获取钉钉accessToken
     *
     * @param authCode
     * @return
     */
    private String getDingTalkAccessTokenByAuthCode(String authCode) {
        try {
            Config config = DingTalkUtils.buildDingTalkConfig();
            com.aliyun.dingtalkoauth2_1_0.Client client = new com.aliyun.dingtalkoauth2_1_0.Client(config);
            GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()
                    .setClientId(appConfig.getDingTalkAppClientId())
                    .setClientSecret(appConfig.getDingTalkAppClientSecret())
                    .setCode(authCode)
                    .setGrantType("authorization_code");
            GetUserTokenResponse getUserTokenResponse = client.getUserToken(getUserTokenRequest);
            if (null == getUserTokenResponse
                    || null == getUserTokenResponse.getBody()
                    || StringTools.isEmpty(getUserTokenResponse.getBody().getAccessToken())) {
                throw new BusinessException(ResponseCodeEnum.CODE_600.getCode(), "获取钉钉授权失败");
            }
            return getUserTokenResponse.getBody().getAccessToken();
        } catch (BusinessException e) {
            throw e;
        } catch (TeaException e) {
            logger.error("获取钉钉授权失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500.getCode(), "钉钉授权接口异常");
        } catch (Exception e) {
            logger.error("获取钉钉授权失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    /**
     * 获取钉钉授权登录用户 用户信息
     *
     * @param accessToken
     * @return
     */
    private DingTalkUserInfoDto getDingTalkUserInfoDto(String accessToken) {
        try {
            Client client = new Client(DingTalkUtils.buildDingTalkConfig());
            GetUserHeaders getUserHeaders = new GetUserHeaders();
            getUserHeaders.xAcsDingtalkAccessToken = accessToken;
            GetUserResponse response = client.getUserWithOptions("me", getUserHeaders, new RuntimeOptions());
            if (null == response || null == response.getBody()) {
                throw new BusinessException(ResponseCodeEnum.CODE_600.getCode(), "获取钉钉用户信息失败");
            }
            DingTalkUserInfoDto dingTalkUserInfoDto = new DingTalkUserInfoDto();
            dingTalkUserInfoDto.setNickName(response.getBody().getNick());
            dingTalkUserInfoDto.setDdOpenUnionid(response.getBody().getUnionId());
            dingTalkUserInfoDto.setAvatarUrl(response.getBody().getAvatarUrl());
            return dingTalkUserInfoDto;
        } catch (BusinessException e) {
            throw e;
        } catch (TeaException e) {
            logger.error("获取钉钉用户信息失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500.getCode(), "钉钉用户接口异常");
        } catch (Exception e) {
            logger.error("获取钉钉用户信息失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    /**
     * 保存用户钉钉头像
     *
     * @param avatarUrl
     * @param targetAvatar
     */
    private void saveDingTalkAvatar(String avatarUrl, String targetAvatar) {
        String avatarFolderPath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + Constants.FILE_FOLDER_AVATAR_NAME;
        File targetFile = new File(avatarFolderPath + targetAvatar);
        OKHttpUtils.downloadFile(avatarUrl, targetFile);
    }

    /**
     * 通过钉钉授权码进行登录
     *
     * @param authCode
     * @return
     */
    @Override
    public TokenUserInfoDto dingTalkLogin(String authCode) {
        DingTalkUserInfoDto dingTalkUserInfoDto = getDingTalkUserInfoDto(getDingTalkAccessTokenByAuthCode(authCode));
        if (StringTools.isEmpty(dingTalkUserInfoDto.getDdOpenUnionid())) {
            throw new BusinessException(ResponseCodeEnum.CODE_600.getCode(), "钉钉用户unionId为空");
        }
        UserInfo userInfo = this.userInfoService.getUserInfoByDdOpenUnionid(dingTalkUserInfoDto.getDdOpenUnionid());
        if (null == userInfo) {
            userInfo = new UserInfo();

            String userId = StringTools.createUserId();
            String nickName = dingTalkUserInfoDto.getNickName();
            if (StringTools.isEmpty(nickName)) {
                nickName = userId;
            }
            nickName = nickName.length() > Constants.LENGTH_20 ? nickName.substring(0, Constants.LENGTH_20) : nickName;
            String avatarUrl = dingTalkUserInfoDto.getAvatarUrl();
            String avatar = userId + Constants.AVATAR_SUFFIX;
            Date curDate = new Date();

            userInfo.setUserId(userId);
            userInfo.setAccount(userId);
            userInfo.setNickName(nickName);
            userInfo.setDdOpenUnionid(dingTalkUserInfoDto.getDdOpenUnionid());
            userInfo.setJoinTime(curDate);
            userInfo.setLastLoginTime(curDate);
            userInfo.setAdmin(AdminStatusEnum.USER.getStatus());
            userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());

            // 保存钉钉用户头像
            if (!StringTools.isEmpty(avatarUrl)) {
                saveDingTalkAvatar(dingTalkUserInfoDto.getAvatarUrl(), avatar);
                userInfo.setAvatar(avatar);
            }

            this.userInfoService.add(userInfo);
        } else {
            UserInfo updateInfo = new UserInfo();
            updateInfo.setLastLoginTime(new Date());
            this.userInfoService.updateUserInfoByDdOpenUnionid(updateInfo, dingTalkUserInfoDto.getDdOpenUnionid());
        }
        if (UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())) {
            throw new BusinessException("账号已被禁用无法登录");
        }
        TokenUserInfoDto tokenUserInfoDto = this.userInfoService.getTokenUserInfoDto(userInfo);

        tokenUserInfoDto.setToken(StringTools.createToken(userInfo.getUserId()));
        redisComponent.saveTokenUserInfoDto(tokenUserInfoDto);

        return tokenUserInfoDto;
    }
}
