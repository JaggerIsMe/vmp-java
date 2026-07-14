package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.SysMenuInfoQuery;
import com.vmp.entity.po.SysMenuInfo;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 系统菜单目录 业务接口
 */
public interface SysMenuInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<SysMenuInfo> findListByParam(SysMenuInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(SysMenuInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<SysMenuInfo> findListByPage(SysMenuInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(SysMenuInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<SysMenuInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<SysMenuInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(SysMenuInfo bean,SysMenuInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(SysMenuInfoQuery param);

	/**
	 * 根据MenuId查询对象
	 */
	SysMenuInfo getSysMenuInfoByMenuId(String menuId);


	/**
	 * 根据MenuId修改
	 */
	Integer updateSysMenuInfoByMenuId(SysMenuInfo bean,String menuId);


	/**
	 * 根据MenuId删除
	 */
	Integer deleteSysMenuInfoByMenuId(String menuId);


	/**
	 * 根据Title查询对象
	 */
	SysMenuInfo getSysMenuInfoByTitle(String title);


	/**
	 * 根据Title修改
	 */
	Integer updateSysMenuInfoByTitle(SysMenuInfo bean,String title);


	/**
	 * 根据Title删除
	 */
	Integer deleteSysMenuInfoByTitle(String title);

	/**
	 * 新增菜单页面
	 * @param userId
	 * @param menuInfo
	 */
	void newMenu(String userId, SysMenuInfo menuInfo);

	/**
	 * 修改菜单页面
	 * @param userId
	 * @param menuInfo
	 */
	void updateMenu(String userId, SysMenuInfo menuInfo);
}