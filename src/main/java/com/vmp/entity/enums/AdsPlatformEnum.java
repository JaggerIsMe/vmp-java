package com.vmp.entity.enums;

public enum AdsPlatformEnum {

    AMAZON("amazon", "亚马逊广告"),
    GOOGLE("google", "谷歌广告"),
    META("meta", "Meta广告");

    private String platform;
    private String desc;

    AdsPlatformEnum(String platform, String desc) {
        this.platform = platform;
        this.desc = desc;
    }

    public static AdsPlatformEnum getByPlatform(String platform) {
        for (AdsPlatformEnum item : AdsPlatformEnum.values()) {
            if (item.getPlatform().equals(platform)) {
                return item;
            }
        }
        return null;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
