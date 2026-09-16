package com.vmp.entity.enums;


public enum MetaAdsAccountStatusEnum {

    ACTIVE(1, "活跃"),
    DISABLE(2, "已停用");


    private Integer status;
    private String desc;

    MetaAdsAccountStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static MetaAdsAccountStatusEnum getByStatus(Integer status) {
        for (MetaAdsAccountStatusEnum item : MetaAdsAccountStatusEnum.values()) {
            if (item.getStatus().equals(status)) {
                return item;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
