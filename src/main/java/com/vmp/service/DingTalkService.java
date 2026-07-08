package com.vmp.service;

import com.vmp.entity.dto.DingTalkUserInfoDto;
import com.vmp.entity.dto.TokenUserInfoDto;

public interface DingTalkService {

    /**
     * 通过钉钉授权码进行登录
     * @param authCode
     * @return
     */
    TokenUserInfoDto dingTalkLogin(String authCode);

}
