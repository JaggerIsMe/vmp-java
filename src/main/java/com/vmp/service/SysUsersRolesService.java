package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.SysUsersRolesQuery;
import com.vmp.entity.po.SysUsersRoles;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 系统用户-角色分配信息 业务接口
 */
public interface SysUsersRolesService {

	/**
	 * 根据条件查询列表
	 */
	List<SysUsersRoles> findListByParam(SysUsersRolesQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(SysUsersRolesQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SysUsersRoles> findListByPage(SysUsersRolesQuery param);

	/**
	 * 新增
	 */
	Integer add(SysUsersRoles bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SysUsersRoles> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<SysUsersRoles> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(SysUsersRoles bean,SysUsersRolesQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(SysUsersRolesQuery param);

	/**
	 * 根据UserId查询对象
	 */
	SysUsersRoles getSysUsersRolesByUserId(String userId);


	/**
	 * 根据UserId修改
	 */
	Integer updateSysUsersRolesByUserId(SysUsersRoles bean,String userId);


	/**
	 * 根据UserId删除
	 */
	Integer deleteSysUsersRolesByUserId(String userId);

}