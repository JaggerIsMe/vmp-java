package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 系统菜单目录
 */
public class SysMenuInfo implements Serializable {


	private static final long serialVersionUID = 6632569700985882390L;
	/**
	 * 菜单id
	 */
	private String menuId;

	/**
	 * 父级菜单id
	 */
	private String pid;

	/**
	 * 菜单标题
	 */
	private String title;

	/**
	 * url路径
	 */
	private String path;

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


	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	public String getMenuId(){
		return this.menuId;
	}

	public void setPid(String pid){
		this.pid = pid;
	}

	public String getPid(){
		return this.pid;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return this.path;
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
		return "菜单id:"+(menuId == null ? "空" : menuId)+"，父级菜单id:"+(pid == null ? "空" : pid)+"，菜单标题:"+(title == null ? "空" : title)+"，url路径:"+(path == null ? "空" : path)+"，创建人id:"+(createBy == null ? "空" : createBy)+"，修改人id:"+(updateBy == null ? "空" : updateBy)+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，修改时间:"+(updateTime == null ? "空" : DateUtil.format(updateTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
