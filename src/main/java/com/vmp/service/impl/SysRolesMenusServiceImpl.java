package com.vmp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.SysRolesMenusQuery;
import com.vmp.entity.po.SysRolesMenus;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.SysRolesMenusMapper;
import com.vmp.service.SysRolesMenusService;
import com.vmp.utils.StringTools;


/**
 * 系统角色-菜单权限分配表 业务接口实现
 */
@Service("sysRolesMenusService")
public class SysRolesMenusServiceImpl implements SysRolesMenusService {

	@Resource
	private SysRolesMenusMapper<SysRolesMenus, SysRolesMenusQuery> sysRolesMenusMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<SysRolesMenus> findListByParam(SysRolesMenusQuery param) {
		return this.sysRolesMenusMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(SysRolesMenusQuery param) {
		return this.sysRolesMenusMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<SysRolesMenus> findListByPage(SysRolesMenusQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<SysRolesMenus> list = this.findListByParam(param);
		PaginationResultVO<SysRolesMenus> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(SysRolesMenus bean) {
		return this.sysRolesMenusMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<SysRolesMenus> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysRolesMenusMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<SysRolesMenus> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysRolesMenusMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(SysRolesMenus bean, SysRolesMenusQuery param) {
		StringTools.checkParam(param);
		return this.sysRolesMenusMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(SysRolesMenusQuery param) {
		StringTools.checkParam(param);
		return this.sysRolesMenusMapper.deleteByParam(param);
	}

	/**
	 * 根据RoleIdAndMenuId获取对象
	 */
	@Override
	public SysRolesMenus getSysRolesMenusByRoleIdAndMenuId(String roleId, String menuId) {
		return this.sysRolesMenusMapper.selectByRoleIdAndMenuId(roleId, menuId);
	}

	/**
	 * 根据RoleIdAndMenuId修改
	 */
	@Override
	public Integer updateSysRolesMenusByRoleIdAndMenuId(SysRolesMenus bean, String roleId, String menuId) {
		return this.sysRolesMenusMapper.updateByRoleIdAndMenuId(bean, roleId, menuId);
	}

	/**
	 * 根据RoleIdAndMenuId删除
	 */
	@Override
	public Integer deleteSysRolesMenusByRoleIdAndMenuId(String roleId, String menuId) {
		return this.sysRolesMenusMapper.deleteByRoleIdAndMenuId(roleId, menuId);
	}
}