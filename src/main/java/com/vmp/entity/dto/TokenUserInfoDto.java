package com.vmp.entity.dto;

public class TokenUserInfoDto {
    private String userId;
    private String account;
    private String nickName;
    private String ddOpenUnionid;
    private String avatar;
    private Boolean admin;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
