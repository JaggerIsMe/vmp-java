package com.vmp.entity.enums;

public enum AgentSkillVersionStatusEnum {

    ACTIVE(0, "可用"),
    DISABLE(1, "禁用"),
    CREATING(2, "创建中"),
    CREATE_FAILED(3, "创建失败");


    private Integer status;
    private String desc;

    AgentSkillVersionStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static AgentSkillVersionStatusEnum getByStatus(Integer status) {
        for (AgentSkillVersionStatusEnum item : AgentSkillVersionStatusEnum.values()) {
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
