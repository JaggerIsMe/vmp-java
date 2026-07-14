package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.SysRolesMenusQuery;
import com.vmp.entity.po.SysRolesMenus;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 系统角色-菜单权限分配表 业务接口
 */
public interface SysRolesMenusService {

	/**
	 * 根据条件查询列表
	 */
	List<SysRolesMenus> findListByParam(SysRolesMenusQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(SysRolesMenusQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SysRolesMenus> findListByPage(SysRolesMenusQuery param);

	/**
	 * 新增
	 */
	Integer add(SysRolesMenus bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SysRolesMenus> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<SysRolesMenus> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(SysRolesMenus bean,SysRolesMenusQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(SysRolesMenusQuery param);

	/**
	 * 根据RoleIdAndMenuId查询对象
	 */
	SysRolesMenus getSysRolesMenusByRoleIdAndMenuId(String roleId,String menuId);


	/**
	 * 根据RoleIdAndMenuId修改
	 */
	Integer updateSysRolesMenusByRoleIdAndMenuId(SysRolesMenus bean,String roleId,String menuId);


	/**
	 * 根据RoleIdAndMenuId删除
	 */
	Integer deleteSysRolesMenusByRoleIdAndMenuId(String roleId,String menuId);

}