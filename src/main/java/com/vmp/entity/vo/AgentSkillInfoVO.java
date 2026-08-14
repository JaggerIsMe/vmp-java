package com.vmp.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class AgentSkillInfoVO {


    /**
     * skill id
     */
    private String skillId;

    /**
     * skill名称
     */
    private String name;

    /**
     * skill描述
     */
    private String description;

    /**
     * 激活版本id
     */
    private String activeVersionId;

    /**
     * 激活版本号
     */
    private String activeVersionName;

    /**
     * 可用skill文件名
     */
    private String activeFileName;

    /**
     * 备注
     */
    private String remark;

    /**
     * Agents平台
     */
    private String platform;

    /**
     * 所属部门
     */
    private String dept;

    /**
     * skill状态 0可用 1下架 2创建中 3创建失败
     */
    private Integer status;

    /**
     * 创建人
     */
    private UserInfoVO createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getSkillId() {
        return this.skillId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setActiveVersionId(String activeVersionId) {
        this.activeVersionId = activeVersionId;
    }

    public String getActiveVersionId() {
        return this.activeVersionId;
    }

    public void setActiveVersionName(String activeVersionName) {
        this.activeVersionName = activeVersionName;
    }

    public String getActiveVersionName() {
        return this.activeVersionName;
    }

    public void setActiveFileName(String activeFileName) {
        this.activeFileName = activeFileName;
    }

    public String getActiveFileName() {
        return this.activeFileName;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return this.dept;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setCreateBy(UserInfoVO createBy) {
        this.createBy = createBy;
    }

    public UserInfoVO getCreateBy() {
        return this.createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    @Override
    public String toString() {
        return "skill id:" + (skillId == null ? "空" : skillId) + "，skill名称:" + (name == null ? "空" : name) + "，skill描述:" + (description == null ? "空" : description) + "，激活版本id:" + (activeVersionId == null ? "空" : activeVersionId) + "，激活版本号:" + (activeVersionName == null ? "空" : activeVersionName) + "，可用skill文件名:" + (activeFileName == null ? "空" : activeFileName) + "，备注:" + (remark == null ? "空" : remark) + "，Agents平台:" + (platform == null ? "空" : platform) + "，所属部门:" + (dept == null ? "空" : dept) + "，skill状态 0可用 1下架 2创建中 3创建失败:" + (status == null ? "空" : status) + "，创建人userId:" + (createBy == null ? "空" : createBy) + "，创建时间:" + (createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "，更新时间:" + (updateTime == null ? "空" : DateUtil.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
    }
}
