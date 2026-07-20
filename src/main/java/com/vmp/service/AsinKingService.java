package com.vmp.service;

import com.vmp.entity.po.AKToken;

public interface AsinKingService {

    /**
     * 保存Access Token
     * @return
     */
    public AKToken saveAccessToken();

    /**
     * 刷新Access Token
     * @param akToken
     * @return
     */
    public void refreshAccessToken(AKToken akToken);

    /**
     * 获取Access Token
     * @return
     */
    public String getAccessToken();



}
