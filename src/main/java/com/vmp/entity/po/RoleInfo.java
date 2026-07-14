package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统角色信息
 */
public class RoleInfo implements Serializable {


	private static final long serialVersionUID = 7252967290799336688L;
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
	 * 创建人id
	 */
	private String createBy;

	/**
	 * 修改人id
	 */
	private String updateBy;

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


	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return this.roleId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
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

	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	public String getUpdateBy(){
		return this.updateBy;
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
		return "角色id:"+(roleId == null ? "空" : roleId)+"，角色名称:"+(name == null ? "空" : name)+"，等级：1老板2主管3组长4组员:"+(level == null ? "空" : level)+"，0禁用1启用:"+(status == null ? "空" : status)+"，创建人id:"+(createBy == null ? "空" : createBy)+"，修改人id:"+(updateBy == null ? "空" : updateBy)+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，修改时间:"+(updateTime == null ? "空" : DateUtil.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
