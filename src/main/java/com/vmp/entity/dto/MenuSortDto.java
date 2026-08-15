package com.vmp.entity.dto;

public class MenuSortDto {

    private String menuId;

    private String targetMenuId;

    private String position;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getTargetMenuId() {
        return targetMenuId;
    }

    public void setTargetMenuId(String targetMenuId) {
        this.targetMenuId = targetMenuId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
