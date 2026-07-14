package com.vmp.entity.dto;

import com.vmp.entity.vo.RoleInfoVO;

import java.io.Serializable;

public class TokenUserInfoDto implements Serializable {
    private static final long serialVersionUID = -2179292713783632219L;
    private String userId;
    private String account;
    private String nickName;
    private String ddOpenUnionid;
    private String avatar;
    private Integer admin;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDdOpenUnionid() {
        return ddOpenUnionid;
    }

    public void setDdOpenUnionid(String ddOpenUnionid) {
        this.ddOpenUnionid = ddOpenUnionid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
