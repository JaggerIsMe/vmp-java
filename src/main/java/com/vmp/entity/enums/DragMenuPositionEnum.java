package com.vmp.entity.enums;

public enum DragMenuPositionEnum {

    BEFORE("before", "移到目标之前"),
    AFTER("after", "移到目标之后");

    private String position;
    private String desc;

    DragMenuPositionEnum(String position, String desc) {
        this.position = position;
        this.desc = desc;
    }

    public static DragMenuPositionEnum getByPosition(String position) {
        for (DragMenuPositionEnum item : DragMenuPositionEnum.values()) {
            if (item.getPosition().equals(position)) {
                return item;
            }
        }
        return null;
    }

    public String getPosition() {
        return position;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
