package com.vmp.controller;

import com.vmp.annotation.GlobalInterceptor;
import com.vmp.annotation.VerifyParam;
import com.vmp.entity.constants.Constants;
import com.vmp.entity.dto.TokenUserInfoDto;
import com.vmp.entity.vo.ResponseVO;
import com.vmp.service.DingTalkService;
import com.vmp.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 钉钉登录 Controller 实现钉钉OAuth2登录和用户信息获取
 */
@RestController("dingTalkLoginController")
@RequestMapping("/dtLogin")
public class DingTalkLoginController extends ABaseController {

    private static final Logger logger = LoggerFactory.getLogger(DingTalkLoginController.class);

    @Resource
    private DingTalkService dingTalkService;

    /**
     * 通过钉钉授权码获取用户信息 登录
     *
     * @param authCode 授权码
     * @return
     */
    @RequestMapping("/auth/code")
    @GlobalInterceptor(checkLogin = false, checkParams = true)
    public ResponseVO dingTalkLogin(HttpServletResponse response,
                                    @VerifyParam(required = true) String authCode) {
        TokenUserInfoDto tokenUserInfoDto = dingTalkService.dingTalkLogin(authCode);
        CookieUtils.addHttpOnlyCookie(response, Constants.WEB_TOKEN_KEY, tokenUserInfoDto.getToken(), Constants.REDIS_KEY_EXPIRES_THREE_DAY);
        return getSuccessResponseVO(tokenUserInfoDto);
    }

}