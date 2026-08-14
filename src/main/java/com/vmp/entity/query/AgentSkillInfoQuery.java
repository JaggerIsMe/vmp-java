package com.vmp.entity.query;

import java.util.Date;


/**
 * Agent Skill 管理表参数
 */
public class AgentSkillInfoQuery extends BaseParam {


	/**
	 * skill id
	 */
	private String skillId;

	private String skillIdFuzzy;

	/**
	 * skill名称
	 */
	private String name;

	private String nameFuzzy;

	/**
	 * skill描述
	 */
	private String description;

	private String descriptionFuzzy;

	/**
	 * 激活版本id
	 */
	private String activeVersionId;

	private String activeVersionIdFuzzy;

	/**
	 * 激活版本号
	 */
	private String activeVersionName;

	private String activeVersionNameFuzzy;

	/**
	 * 可用skill文件名
	 */
	private String activeFileName;

	private String activeFileNameFuzzy;

	/**
	 * 备注
	 */
	private String remark;

	private String remarkFuzzy;

	/**
	 * Agents平台
	 */
	private String platform;

	private String platformFuzzy;

	/**
	 * 所属部门
	 */
	private String dept;

	private String deptFuzzy;

	/**
	 * skill状态 0可用 1下架 2创建中 3创建失败
	 */
	private Integer status;

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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescriptionFuzzy(String descriptionFuzzy){
		this.descriptionFuzzy = descriptionFuzzy;
	}

	public String getDescriptionFuzzy(){
		return this.descriptionFuzzy;
	}

	public void setActiveVersionId(String activeVersionId){
		this.activeVersionId = activeVersionId;
	}

	public String getActiveVersionId(){
		return this.activeVersionId;
	}

	public void setActiveVersionIdFuzzy(String activeVersionIdFuzzy){
		this.activeVersionIdFuzzy = activeVersionIdFuzzy;
	}

	public String getActiveVersionIdFuzzy(){
		return this.activeVersionIdFuzzy;
	}

	public void setActiveVersionName(String activeVersionName){
		this.activeVersionName = activeVersionName;
	}

	public String getActiveVersionName(){
		return this.activeVersionName;
	}

	public void setActiveVersionNameFuzzy(String activeVersionNameFuzzy){
		this.activeVersionNameFuzzy = activeVersionNameFuzzy;
	}

	public String getActiveVersionNameFuzzy(){
		return this.activeVersionNameFuzzy;
	}

	public void setActiveFileName(String activeFileName){
		this.activeFileName = activeFileName;
	}

	public String getActiveFileName(){
		return this.activeFileName;
	}

	public void setActiveFileNameFuzzy(String activeFileNameFuzzy){
		this.activeFileNameFuzzy = activeFileNameFuzzy;
	}

	public String getActiveFileNameFuzzy(){
		return this.activeFileNameFuzzy;
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

	public void setPlatform(String platform){
		this.platform = platform;
	}

	public String getPlatform(){
		return this.platform;
	}

	public void setPlatformFuzzy(String platformFuzzy){
		this.platformFuzzy = platformFuzzy;
	}

	public String getPlatformFuzzy(){
		return this.platformFuzzy;
	}

	public void setDept(String dept){
		this.dept = dept;
	}

	public String getDept(){
		return this.dept;
	}

	public void setDeptFuzzy(String deptFuzzy){
		this.deptFuzzy = deptFuzzy;
	}

	public String getDeptFuzzy(){
		return this.deptFuzzy;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
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
