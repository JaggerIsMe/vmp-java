package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 系统字典管理
 */
public class SysDictData implements Serializable {


	private static final long serialVersionUID = 7677882297120329294L;
	/**
	 * 字典主键id
	 */
	private String dictId;

	/**
	 * 父级id, VMP表示一级字典类型
	 */
	private String pid;

	/**
	 * 字典代码
	 */
	private String dictCode;

	/**
	 * 字典名称
	 */
	private String dictName;

	/**
	 * 0禁用1启用
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	public void setDictId(String dictId){
		this.dictId = dictId;
	}

	public String getDictId(){
		return this.dictId;
	}

	public void setPid(String pid){
		this.pid = pid;
	}

	public String getPid(){
		return this.pid;
	}

	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}

	public String getDictCode(){
		return this.dictCode;
	}

	public void setDictName(String dictName){
		this.dictName = dictName;
	}

	public String getDictName(){
		return this.dictName;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	@Override
	public String toString (){
		return "字典主键id:"+(dictId == null ? "空" : dictId)+"，父级id, VMP表示一级字典类型:"+(pid == null ? "空" : pid)+"，字典代码:"+(dictCode == null ? "空" : dictCode)+"，字典名称:"+(dictName == null ? "空" : dictName)+"，0禁用1启用:"+(status == null ? "空" : status)+"，备注:"+(remark == null ? "空" : remark)+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
