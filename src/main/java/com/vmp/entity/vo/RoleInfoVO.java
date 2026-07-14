package com.vmp.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class RoleInfoVO {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 等级：1老板2主管3组长4组员
     */
    private Integer level;

    /**
     * 0禁用1启用
     */
    private Integer status;

    /**
     * 创建人
     */
    private UserInfoVO createBy;

    /**
     * 修改人
     */
    private UserInfoVO updateBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    List<SysMenuInfoVO> roleMenuPermissionsList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserInfoVO getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserInfoVO createBy) {
        this.createBy = createBy;
    }

    public UserInfoVO getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UserInfoVO updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<SysMenuInfoVO> getRoleMenuPermissionsList() {
        return roleMenuPermissionsList;
    }

    public void setRoleMenuPermissionsList(List<SysMenuInfoVO> roleMenuPermissionsList) {
        this.roleMenuPermissionsList = roleMenuPermissionsList;
    }
}
