package com.vmp.service;

import com.vmp.entity.po.RoleInfo;
import com.vmp.entity.query.RoleInfoQuery;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.vo.RoleInfoVO;
import com.vmp.entity.vo.SysMenuInfoVO;

import java.util.List;


/**
 * 系统角色信息 业务接口
 */
public interface RoleInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<RoleInfo> findListByParam(RoleInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(RoleInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<RoleInfo> findListByPage(RoleInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(RoleInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<RoleInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<RoleInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(RoleInfo bean,RoleInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(RoleInfoQuery param);

	/**
	 * 根据RoleId查询对象
	 */
	RoleInfo getRoleInfoByRoleId(String roleId);


	/**
	 * 根据RoleId修改
	 */
	Integer updateRoleInfoByRoleId(RoleInfo bean,String roleId);


	/**
	 * 根据RoleId删除
	 */
	Integer deleteRoleInfoByRoleId(String roleId);


	/**
	 * 根据Name查询对象
	 */
	RoleInfo getRoleInfoByName(String name);


	/**
	 * 根据Name修改
	 */
	Integer updateRoleInfoByName(RoleInfo bean,String name);


	/**
	 * 根据Name删除
	 */
	Integer deleteRoleInfoByName(String name);

	/**
	 * 分页查询 RoleInfoVO
	 * @param param
	 * @return
	 */
	PaginationResultVO<RoleInfoVO> loadRoleInfoVOListByPage(RoleInfoQuery param);

	/**
	 * 新增角色
	 * @param operatorId
	 * @param roleInfo
	 */
	void newRole(String operatorId, RoleInfo roleInfo);

	/**
	 * 修改角色信息
	 * @param operatorId
	 * @param roleInfo
	 */
	void updateRole(String operatorId, RoleInfo roleInfo);

	/**
	 * 根据RoleId 获取角色页面权限
	 * @param roleId
	 * @return
	 */
	List<SysMenuInfoVO> loadRoleMenuPermissionsByRoleId(String roleId);

	/**
	 * 根据RoleId 修改角色页面权限
	 * @param menuInfoVOList
	 * @param roleId
	 * @param userId
	 */
	void updateRoleMenuPermissionsByRoleId(List<SysMenuInfoVO> menuInfoVOList, String roleId, String userId);

	/**
	 * 获取指定用户角色权限信息
	 * @param userId
	 * @return
	 */
	RoleInfoVO getUserRoleMenuPermissionsInfo(String userId);

	/**
	 * 根据UserId 修改用户角色
	 *
	 * @param roleId
	 * @param userId
	 */
	void updateUserRoleByUserId(String userId, String roleId);
}