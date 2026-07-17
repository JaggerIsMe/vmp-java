package com.vmp.entity.query;

import java.util.Date;


/**
 * 系统字典管理参数
 */
public class SysDictDataQuery extends BaseParam {


	/**
	 * 字典主键id
	 */
	private String dictId;

	private String dictIdFuzzy;

	/**
	 * 父级id, VMP表示一级字典类型
	 */
	private String pid;

	private String pidFuzzy;

	/**
	 * 字典代码
	 */
	private String dictCode;

	private String dictCodeFuzzy;

	/**
	 * 字典名称
	 */
	private String dictName;

	private String dictNameFuzzy;

	/**
	 * 0禁用1启用
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	private String remarkFuzzy;

	/**
	 * 创建时间
	 */
	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;


	public void setDictId(String dictId){
		this.dictId = dictId;
	}

	public String getDictId(){
		return this.dictId;
	}

	public void setDictIdFuzzy(String dictIdFuzzy){
		this.dictIdFuzzy = dictIdFuzzy;
	}

	public String getDictIdFuzzy(){
		return this.dictIdFuzzy;
	}

	public void setPid(String pid){
		this.pid = pid;
	}

	public String getPid(){
		return this.pid;
	}

	public void setPidFuzzy(String pidFuzzy){
		this.pidFuzzy = pidFuzzy;
	}

	public String getPidFuzzy(){
		return this.pidFuzzy;
	}

	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}

	public String getDictCode(){
		return this.dictCode;
	}

	public void setDictCodeFuzzy(String dictCodeFuzzy){
		this.dictCodeFuzzy = dictCodeFuzzy;
	}

	public String getDictCodeFuzzy(){
		return this.dictCodeFuzzy;
	}

	public void setDictName(String dictName){
		this.dictName = dictName;
	}

	public String getDictName(){
		return this.dictName;
	}

	public void setDictNameFuzzy(String dictNameFuzzy){
		this.dictNameFuzzy = dictNameFuzzy;
	}

	public String getDictNameFuzzy(){
		return this.dictNameFuzzy;
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

	public void setRemarkFuzzy(String remarkFuzzy){
		this.remarkFuzzy = remarkFuzzy;
	}

	public String getRemarkFuzzy(){
		return this.remarkFuzzy;
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

}
