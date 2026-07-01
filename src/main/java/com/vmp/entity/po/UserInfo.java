package com.vmp.entity.po;

import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 用户信息
 */
public class UserInfo implements Serializable {


	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 钉钉开放平台unionId
	 */
	private String ddOpenUnionid;

	/**
	 * 钉钉头像
	 */
	private String ddAvatar;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 加入时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date joinTime;

	/**
	 * 最后登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
	 * 0禁用1启用
	 */
	private Integer status;


	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public String getNickName(){
		return this.nickName;
	}

	public void setDdOpenUnionid(String ddOpenUnionid){
		this.ddOpenUnionid = ddOpenUnionid;
	}

	public String getDdOpenUnionid(){
		return this.ddOpenUnionid;
	}

	public void setDdAvatar(String ddAvatar){
		this.ddAvatar = ddAvatar;
	}

	public String getDdAvatar(){
		return this.ddAvatar;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setJoinTime(Date joinTime){
		this.joinTime = joinTime;
	}

	public Date getJoinTime(){
		return this.joinTime;
	}

	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	@Override
	public String toString (){
		return "用户id:"+(userId == null ? "空" : userId)+"，昵称:"+(nickName == null ? "空" : nickName)+"，钉钉开放平台unionId:"+(ddOpenUnionid == null ? "空" : ddOpenUnionid)+"，钉钉头像:"+(ddAvatar == null ? "空" : ddAvatar)+"，密码:"+(password == null ? "空" : password)+"，加入时间:"+(joinTime == null ? "空" : DateUtils.format(joinTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，最后登录时间:"+(lastLoginTime == null ? "空" : DateUtils.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，0禁用1启用:"+(status == null ? "空" : status);
	}
}
