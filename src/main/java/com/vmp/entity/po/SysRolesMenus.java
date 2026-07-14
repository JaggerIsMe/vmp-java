package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 系统角色-菜单权限分配表
 */
public class SysRolesMenus implements Serializable {


	private static final long serialVersionUID = -6395263775716207563L;
	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 菜单id
	 */
	private String menuId;


	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return this.roleId;
	}

	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	public String getMenuId(){
		return this.menuId;
	}

	@Override
	public String toString (){
		return "角色id:"+(roleId == null ? "空" : roleId)+"，菜单id:"+(menuId == null ? "空" : menuId);
	}
}
