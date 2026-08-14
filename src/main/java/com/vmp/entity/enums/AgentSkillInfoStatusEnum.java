package com.vmp.entity.enums;

public enum AgentSkillInfoStatusEnum {

    ACTIVE(0, "可用"),
    DISABLE(1, "下架"),
    CREATING(2, "创建中"),
    CREATE_FAILED(3, "创建失败");


    private Integer status;
    private String desc;

    AgentSkillInfoStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static AgentSkillInfoStatusEnum getByStatus(Integer status) {
        for (AgentSkillInfoStatusEnum item : AgentSkillInfoStatusEnum.values()) {
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
