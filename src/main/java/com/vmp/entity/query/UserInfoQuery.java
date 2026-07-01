package com.vmp.entity.query;

import java.util.Date;


/**
 * 用户信息参数
 */
public class UserInfoQuery extends BaseParam {


	/**
	 * 用户id
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 昵称
	 */
	private String nickName;

	private String nickNameFuzzy;

	/**
	 * 钉钉开放平台unionId
	 */
	private String ddOpenUnionid;

	private String ddOpenUnionidFuzzy;

	/**
	 * 钉钉头像
	 */
	private String ddAvatar;

	private String ddAvatarFuzzy;

	/**
	 * 密码
	 */
	private String password;

	private String passwordFuzzy;

	/**
	 * 加入时间
	 */
	private String joinTime;

	private String joinTimeStart;

	private String joinTimeEnd;

	/**
	 * 最后登录时间
	 */
	private String lastLoginTime;

	private String lastLoginTimeStart;

	private String lastLoginTimeEnd;

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

	public void setUserIdFuzzy(String userIdFuzzy){
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy(){
		return this.userIdFuzzy;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public String getNickName(){
		return this.nickName;
	}

	public void setNickNameFuzzy(String nickNameFuzzy){
		this.nickNameFuzzy = nickNameFuzzy;
	}

	public String getNickNameFuzzy(){
		return this.nickNameFuzzy;
	}

	public void setDdOpenUnionid(String ddOpenUnionid){
		this.ddOpenUnionid = ddOpenUnionid;
	}

	public String getDdOpenUnionid(){
		return this.ddOpenUnionid;
	}

	public void setDdOpenUnionidFuzzy(String ddOpenUnionidFuzzy){
		this.ddOpenUnionidFuzzy = ddOpenUnionidFuzzy;
	}

	public String getDdOpenUnionidFuzzy(){
		return this.ddOpenUnionidFuzzy;
	}

	public void setDdAvatar(String ddAvatar){
		this.ddAvatar = ddAvatar;
	}

	public String getDdAvatar(){
		return this.ddAvatar;
	}

	public void setDdAvatarFuzzy(String ddAvatarFuzzy){
		this.ddAvatarFuzzy = ddAvatarFuzzy;
	}

	public String getDdAvatarFuzzy(){
		return this.ddAvatarFuzzy;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	}

	public void setJoinTime(String joinTime){
		this.joinTime = joinTime;
	}

	public String getJoinTime(){
		return this.joinTime;
	}

	public void setJoinTimeStart(String joinTimeStart){
		this.joinTimeStart = joinTimeStart;
	}

	public String getJoinTimeStart(){
		return this.joinTimeStart;
	}
	public void setJoinTimeEnd(String joinTimeEnd){
		this.joinTimeEnd = joinTimeEnd;
	}

	public String getJoinTimeEnd(){
		return this.joinTimeEnd;
	}

	public void setLastLoginTime(String lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setLastLoginTimeStart(String lastLoginTimeStart){
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public String getLastLoginTimeStart(){
		return this.lastLoginTimeStart;
	}
	public void setLastLoginTimeEnd(String lastLoginTimeEnd){
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getLastLoginTimeEnd(){
		return this.lastLoginTimeEnd;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

}
