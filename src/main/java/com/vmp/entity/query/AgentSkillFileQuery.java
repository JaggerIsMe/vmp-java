package com.vmp.entity.query;

import java.util.Date;


/**
 * Agent Skill 文件参数
 */
public class AgentSkillFileQuery extends BaseParam {


	/**
	 * skill版本ID
	 */
	private String versionId;

	private String versionIdFuzzy;

	/**
	 * skill ID
	 */
	private String skillId;

	private String skillIdFuzzy;

	/**
	 * 版本号
	 */
	private String versionName;

	private String versionNameFuzzy;

	/**
	 * 备注
	 */
	private String remark;

	private String remarkFuzzy;

	/**
	 * skill文件MD5值
	 */
	private String fileMd5;

	private String fileMd5Fuzzy;

	/**
	 * skill文件名
	 */
	private String fileName;

	private String fileNameFuzzy;

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

	private String createByFuzzy;

	/**
	 * 创建时间
	 */
	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 更新时间
	 */
	private String updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;


	public void setVersionId(String versionId){
		this.versionId = versionId;
	}

	public String getVersionId(){
		return this.versionId;
	}

	public void setVersionIdFuzzy(String versionIdFuzzy){
		this.versionIdFuzzy = versionIdFuzzy;
	}

	public String getVersionIdFuzzy(){
		return this.versionIdFuzzy;
	}

	public void setSkillId(String skillId){
		this.skillId = skillId;
	}

	public String getSkillId(){
		return this.skillId;
	}

	public void setSkillIdFuzzy(String skillIdFuzzy){
		this.skillIdFuzzy = skillIdFuzzy;
	}

	public String getSkillIdFuzzy(){
		return this.skillIdFuzzy;
	}

	public void setVersionName(String versionName){
		this.versionName = versionName;
	}

	public String getVersionName(){
		return this.versionName;
	}

	public void setVersionNameFuzzy(String versionNameFuzzy){
		this.versionNameFuzzy = versionNameFuzzy;
	}

	public String getVersionNameFuzzy(){
		return this.versionNameFuzzy;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setRemarkFuzzy(String remarkFuzzy){
		this.remarkFuzzy = remarkFuzzy;
	}

	public String getRemarkFuzzy(){
		return this.remarkFuzzy;
	}

	public void setFileMd5(String fileMd5){
		this.fileMd5 = fileMd5;
	}

	public String getFileMd5(){
		return this.fileMd5;
	}

	public void setFileMd5Fuzzy(String fileMd5Fuzzy){
		this.fileMd5Fuzzy = fileMd5Fuzzy;
	}

	public String getFileMd5Fuzzy(){
		return this.fileMd5Fuzzy;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName(){
		return this.fileName;
	}

	public void setFileNameFuzzy(String fileNameFuzzy){
		this.fileNameFuzzy = fileNameFuzzy;
	}

	public String getFileNameFuzzy(){
		return this.fileNameFuzzy;
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

	public void setCreateByFuzzy(String createByFuzzy){
		this.createByFuzzy = createByFuzzy;
	}

	public String getCreateByFuzzy(){
		return this.createByFuzzy;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeStart(String createTimeStart){
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart(){
		return this.createTimeStart;
	}
	public void setCreateTimeEnd(String createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd(){
		return this.createTimeEnd;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	public String getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTimeStart(String updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart(){
		return this.updateTimeStart;
	}
	public void setUpdateTimeEnd(String updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd(){
		return this.updateTimeEnd;
	}

}
