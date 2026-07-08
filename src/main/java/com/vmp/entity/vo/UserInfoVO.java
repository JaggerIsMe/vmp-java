package com.vmp.entity.vo;

public class UserInfoVO {
    private String userId;
    private String account;
    private String nickName;
    private String avatar;
    private Boolean admin;
    // 标记该用户是否是第一次登录使用该系统(即首次通过钉钉扫码登录注册)
    private Boolean newToHere;

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

    public Boolean getNewToHere() {
        return newToHere;
    }

    public void setNewToHere(Boolean newToHere) {
        this.newToHere = newToHere;
    }
}
