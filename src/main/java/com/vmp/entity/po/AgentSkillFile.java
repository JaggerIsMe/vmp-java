package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * Agent Skill 文件
 */
public class AgentSkillFile implements Serializable {


	private static final long serialVersionUID = -8313834413778442127L;
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
	 * 创建人userId
	 */
	private String createBy;

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

	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	public String getCreateBy(){
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

	@Override
	public String toString (){
		return "skill版本ID:"+(versionId == null ? "空" : versionId)+"，skill ID:"+(skillId == null ? "空" : skillId)+"，版本号:"+(versionName == null ? "空" : versionName)+"，备注:"+(remark == null ? "空" : remark)+"，skill文件MD5值:"+(fileMd5 == null ? "空" : fileMd5)+"，skill文件名:"+(fileName == null ? "空" : fileName)+"，skill版本状态 0可用 1禁用 2创建中 3创建失败:"+(versionStatus == null ? "空" : versionStatus)+"，文件状态 0转码中 1转码失败 2转码成功:"+(fileStatus == null ? "空" : fileStatus)+"，文件大小:"+(fileSize == null ? "空" : fileSize)+"，创建人userId:"+(createBy == null ? "空" : createBy)+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，更新时间:"+(updateTime == null ? "空" : DateUtil.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
