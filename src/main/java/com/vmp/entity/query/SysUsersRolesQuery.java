package com.vmp.entity.query;



/**
 * 系统用户-角色分配信息参数
 */
public class SysUsersRolesQuery extends BaseParam {


	/**
	 * 用户id
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 角色id
	 */
	private String roleId;

	private String roleIdFuzzy;


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

}
