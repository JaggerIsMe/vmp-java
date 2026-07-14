package com.vmp.entity.query;

import java.util.Date;


/**
 * 系统角色信息参数
 */
public class RoleInfoQuery extends BaseParam {


	/**
	 * 角色id
	 */
	private String roleId;

	private String roleIdFuzzy;

	/**
	 * 角色名称
	 */
	private String name;

	private String nameFuzzy;

	/**
	 * 等级：1老板2主管3组长4组员
	 */
	private Integer level;

	/**
	 * 0禁用1启用
	 */
	private Integer status;

	/**
	 * 创建人id
	 */
	private String createBy;

	private String createByFuzzy;

	/**
	 * 修改人id
	 */
	private String updateBy;

	private String updateByFuzzy;

	/**
	 * 创建时间
	 */
	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 修改时间
	 */
	private String updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;


	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return this.roleId;
	}

	public void setRoleIdFuzzy(String roleIdFuzzy){
		this.roleIdFuzzy = roleIdFuzzy;
	}

	public String getRoleIdFuzzy(){
		return this.roleIdFuzzy;
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

	public void setLevel(Integer level){
		this.level = level;
	}

	public Integer getLevel(){
		return this.level;
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

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	public String getUpdateBy(){
		return this.updateBy;
	}

	public void setUpdateByFuzzy(String updateByFuzzy){
		this.updateByFuzzy = updateByFuzzy;
	}

	public String getUpdateByFuzzy(){
		return this.updateByFuzzy;
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
