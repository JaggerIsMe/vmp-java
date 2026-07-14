package com.vmp.entity.enums;


public enum RoleLevelEnum {
    DEVELOPER(0, "开发者"),
    BOSS(1, "老板"),
    MANAGER(2, "主管"),
    LEADER(3, "组长"),
    MEMBER(4, "组员");


    private Integer level;
    private String desc;

    RoleLevelEnum(Integer level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public static RoleLevelEnum getByLevel(Integer level) {
        for (RoleLevelEnum item : RoleLevelEnum.values()) {
            if (item.getLevel().equals(level)) {
                return item;
            }
        }
        return null;
    }

    public Integer getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
