package com.vmp.entity.enums;


public enum AdminStatusEnum {

    USER(0, "普通用户"),
    ADMIN(1, "管理员");


    private Integer status;
    private String desc;

    AdminStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static AdminStatusEnum getByStatus(Integer status) {
        for (AdminStatusEnum item : AdminStatusEnum.values()) {
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

