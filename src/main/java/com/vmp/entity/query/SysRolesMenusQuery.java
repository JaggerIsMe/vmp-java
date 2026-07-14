package com.vmp.entity.query;


import java.util.List;

/**
 * 系统角色-菜单权限分配表参数
 */
public class SysRolesMenusQuery extends BaseParam {


	/**
	 * 角色id
	 */
	private String roleId;

	private String roleIdFuzzy;

	/**
	 * 菜单id
	 */
	private String menuId;

	private String menuIdFuzzy;


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

	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	public String getMenuId(){
		return this.menuId;
	}

	public void setMenuIdFuzzy(String menuIdFuzzy){
		this.menuIdFuzzy = menuIdFuzzy;
	}

	public String getMenuIdFuzzy(){
		return this.menuIdFuzzy;
	}
}
