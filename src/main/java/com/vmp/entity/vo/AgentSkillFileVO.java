package com.vmp.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


public class AgentSkillFileVO {
    /**
     * skill版本ID
     */
    private String versionId;

    /**
     * skill ID
     */
    private String skillId;

    /**
     * 版本号
     */
    private String versionName;

    /**
     * 备注
     */
    private String remark;

    /**
     * skill文件MD5值
     */
    private String fileMd5;

    /**
     * skill文件名
     */
    private String fileName;

    /**
     * skill版本状态 0可用 1禁用 2创建中 3创建失败
     */
    private Integer versionStatus;

    /**
     * 文件状态 0转码中 1转码失败 2转码成功
     */
    private Integer fileStatus;

    /**
     * 文件大小
     */
    private Long fileSize;

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


    public void setVersionId(String versionId){
        this.versionId = versionId;
    }

    public String getVersionId(){
        return this.versionId;
    }

    public void setSkillId(String skillId){
        this.skillId = skillId;
    }

    public String getSkillId(){
        return this.skillId;
    }

    public void setVersionName(String versionName){
        this.versionName = versionName;
    }

    public String getVersionName(){
        return this.versionName;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setFileMd5(String fileMd5){
        this.fileMd5 = fileMd5;
    }

    public String getFileMd5(){
        return this.fileMd5;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setVersionStatus(Integer versionStatus){
        this.versionStatus = versionStatus;
    }

    public Integer getVersionStatus(){
        return this.versionStatus;
    }

    public void setFileStatus(Integer fileStatus){
        this.fileStatus = fileStatus;
    }

    public Integer getFileStatus(){
        return this.fileStatus;
    }

    public void setFileSize(Long fileSize){
        this.fileSize = fileSize;
    }

    public Long getFileSize(){
        return this.fileSize;
    }

    public void setCreateBy(UserInfoVO createBy){
        this.createBy = createBy;
    }

    public UserInfoVO getCreateBy(){
        return this.createBy;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }
}
