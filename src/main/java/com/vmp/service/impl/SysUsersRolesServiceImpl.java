package com.vmp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.SysUsersRolesQuery;
import com.vmp.entity.po.SysUsersRoles;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.SysUsersRolesMapper;
import com.vmp.service.SysUsersRolesService;
import com.vmp.utils.StringTools;


/**
 * 系统用户-角色分配信息 业务接口实现
 */
@Service("sysUsersRolesService")
public class SysUsersRolesServiceImpl implements SysUsersRolesService {

	@Resource
	private SysUsersRolesMapper<SysUsersRoles, SysUsersRolesQuery> sysUsersRolesMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<SysUsersRoles> findListByParam(SysUsersRolesQuery param) {
		return this.sysUsersRolesMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(SysUsersRolesQuery param) {
		return this.sysUsersRolesMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<SysUsersRoles> findListByPage(SysUsersRolesQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<SysUsersRoles> list = this.findListByParam(param);
		PaginationResultVO<SysUsersRoles> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(SysUsersRoles bean) {
		return this.sysUsersRolesMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<SysUsersRoles> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysUsersRolesMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<SysUsersRoles> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysUsersRolesMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(SysUsersRoles bean, SysUsersRolesQuery param) {
		StringTools.checkParam(param);
		return this.sysUsersRolesMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(SysUsersRolesQuery param) {
		StringTools.checkParam(param);
		return this.sysUsersRolesMapper.deleteByParam(param);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public SysUsersRoles getSysUsersRolesByUserId(String userId) {
		return this.sysUsersRolesMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateSysUsersRolesByUserId(SysUsersRoles bean, String userId) {
		return this.sysUsersRolesMapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteSysUsersRolesByUserId(String userId) {
		return this.sysUsersRolesMapper.deleteByUserId(userId);
	}
}