package com.vmp.entity.enums;

public enum GoogleAdvertisingChannelTypeEnum {
    
    DEMAND_GEN("DEMAND_GEN", "需求开发"),
    DISPLAY("DISPLAY", "展示"),
    HOTEL("HOTEL", "酒店"),
    LOCAL("LOCAL", "本地"),
    LOCAL_SERVICES("LOCAL_SERVICES", "本地服务"),
    MULTI_CHANNEL("MULTI_CHANNEL", "多渠道"),
    PERFORMANCE_MAX("PERFORMANCE_MAX", "效果最大化"),
    SEARCH("SEARCH", "搜索"),
    SHOPPING("SHOPPING", "购物"),
    SMART("SMART", "智能"),
    TRAVEL("TRAVEL", "旅游"),
    UNKNOWN("UNKNOWN", "未知"),
    UNSPECIFIED("UNSPECIFIED", "未指定"),
    VIDEO("VIDEO", "视频");

    private String channel;
    private String desc;

    GoogleAdvertisingChannelTypeEnum(String channel, String desc) {
        this.channel = channel;
        this.desc = desc;
    }

    public static GoogleAdvertisingChannelTypeEnum getByChannel(String channel) {
        for (GoogleAdvertisingChannelTypeEnum item : GoogleAdvertisingChannelTypeEnum.values()) {
            if (item.getChannel().equals(channel)) {
                return item;
            }
        }
        return null;
    }

    public String getChannel() {
        return channel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
