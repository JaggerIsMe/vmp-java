package com.vmp.entity.enums;


public enum RoleStatusEnum {

    DISABLE(0, "禁用"),
    ENABLE(1, "启用");


    private Integer status;
    private String desc;

    RoleStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static RoleStatusEnum getByStatus(Integer status) {
        for (RoleStatusEnum item : RoleStatusEnum.values()) {
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
