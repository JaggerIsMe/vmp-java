package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 系统角色-菜单权限分配表 数据库操作接口
 */
public interface SysRolesMenusMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据RoleIdAndMenuId更新
	 */
	 Integer updateByRoleIdAndMenuId(@Param("bean") T t,@Param("roleId") String roleId,@Param("menuId") String menuId);


	/**
	 * 根据RoleIdAndMenuId删除
	 */
	 Integer deleteByRoleIdAndMenuId(@Param("roleId") String roleId,@Param("menuId") String menuId);


	/**
	 * 根据RoleIdAndMenuId获取对象
	 */
	 T selectByRoleIdAndMenuId(@Param("roleId") String roleId,@Param("menuId") String menuId);


}
