package com.vmp.entity.enums;

public enum SalesPlatformEnum {

    AMAZON("amazon", "amazon"),
    SHOPIFY("shopify", "shopify");

    private String platform;
    private String desc;

    SalesPlatformEnum(String platform, String desc) {
        this.platform = platform;
        this.desc = desc;
    }

    public static SalesPlatformEnum getByPlatform(String platform) {
        for (SalesPlatformEnum item : SalesPlatformEnum.values()) {
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
