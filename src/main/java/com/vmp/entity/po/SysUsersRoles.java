package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 系统用户-角色分配信息
 */
public class SysUsersRoles implements Serializable {


	private static final long serialVersionUID = -8582761209248274023L;
	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 角色id
	 */
	private String roleId;


	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return this.roleId;
	}

	@Override
	public String toString (){
		return "用户id:"+(userId == null ? "空" : userId)+"，角色id:"+(roleId == null ? "空" : roleId);
	}
}
