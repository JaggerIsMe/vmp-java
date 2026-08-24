package com.vmp.entity.enums;

public enum GoogleAdsCampaignStatusEnum {

    ENABLED("ENABLED", "已启用"),
    PAUSED("PAUSED", "已暂停"),
    REMOVED("REMOVED", "已移除"),
    UNKNOWN("UNKNOWN", "未知"),
    UNSPECIFIED("UNSPECIFIED", "未指定");

    private String status;
    private String desc;

    GoogleAdsCampaignStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static GoogleAdsCampaignStatusEnum getByPlatform(String status) {
        for (GoogleAdsCampaignStatusEnum item : GoogleAdsCampaignStatusEnum.values()) {
            if (item.getPlatform().equals(status)) {
                return item;
            }
        }
        return null;
    }

    public String getPlatform() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
