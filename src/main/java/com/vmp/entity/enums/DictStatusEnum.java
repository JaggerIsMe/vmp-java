package com.vmp.entity.enums;


public enum DictStatusEnum {

    DISABLE(0, "禁用"),
    ENABLE(1, "启用");


    private Integer status;
    private String desc;

    DictStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static DictStatusEnum getByStatus(Integer status) {
        for (DictStatusEnum item : DictStatusEnum.values()) {
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
