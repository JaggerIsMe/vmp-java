package com.vmp.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.vmp.entity.constants.Constants;
import com.vmp.entity.enums.ResponseCodeEnum;
import com.vmp.entity.enums.VerifyRegexEnum;
import com.vmp.exception.BusinessException;
import com.vmp.utils.VerifyUtils;
import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.SysMenuInfoQuery;
import com.vmp.entity.po.SysMenuInfo;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.SysMenuInfoMapper;
import com.vmp.service.SysMenuInfoService;
import com.vmp.utils.StringTools;
import org.springframework.transaction.annotation.Transactional;


/**
 * 系统菜单目录 业务接口实现
 */
@Service("sysMenuInfoService")
public class SysMenuInfoServiceImpl implements SysMenuInfoService {

	@Resource
	private SysMenuInfoMapper<SysMenuInfo, SysMenuInfoQuery> sysMenuInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<SysMenuInfo> findListByParam(SysMenuInfoQuery param) {
		return this.sysMenuInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(SysMenuInfoQuery param) {
		return this.sysMenuInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<SysMenuInfo> findListByPage(SysMenuInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<SysMenuInfo> list = this.findListByParam(param);
		PaginationResultVO<SysMenuInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(SysMenuInfo bean) {
		return this.sysMenuInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<SysMenuInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysMenuInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<SysMenuInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.sysMenuInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(SysMenuInfo bean, SysMenuInfoQuery param) {
		StringTools.checkParam(param);
		return this.sysMenuInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(SysMenuInfoQuery param) {
		StringTools.checkParam(param);
		return this.sysMenuInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据MenuId获取对象
	 */
	@Override
	public SysMenuInfo getSysMenuInfoByMenuId(String menuId) {
		return this.sysMenuInfoMapper.selectByMenuId(menuId);
	}

	/**
	 * 根据MenuId修改
	 */
	@Override
	public Integer updateSysMenuInfoByMenuId(SysMenuInfo bean, String menuId) {
		return this.sysMenuInfoMapper.updateByMenuId(bean, menuId);
	}

	/**
	 * 根据MenuId删除
	 */
	@Override
	public Integer deleteSysMenuInfoByMenuId(String menuId) {
		return this.sysMenuInfoMapper.deleteByMenuId(menuId);
	}

	/**
	 * 根据Title获取对象
	 */
	@Override
	public SysMenuInfo getSysMenuInfoByTitle(String title) {
		return this.sysMenuInfoMapper.selectByTitle(title);
	}

	/**
	 * 根据Title修改
	 */
	@Override
	public Integer updateSysMenuInfoByTitle(SysMenuInfo bean, String title) {
		return this.sysMenuInfoMapper.updateByTitle(bean, title);
	}

	/**
	 * 根据Title删除
	 */
	@Override
	public Integer deleteSysMenuInfoByTitle(String title) {
		return this.sysMenuInfoMapper.deleteByTitle(title);
	}

	/**
	 * 新增菜单页面
	 * @param userId
	 * @param menuInfo
	 */
	@Override
	public void newMenu(String userId, SysMenuInfo menuInfo) {
		if (!VerifyUtils.verify(VerifyRegexEnum.MENU_PATH, menuInfo.getPath())) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		menuInfo.setMenuId(StringTools.getRandomNumber(Constants.LENGTH_20));
		menuInfo.setCreateBy(userId);
		menuInfo.setUpdateBy(userId);
		Date curdate = new Date();
		menuInfo.setCreateTime(curdate);
		menuInfo.setUpdateTime(curdate);
		add(menuInfo);
	}

	/**
	 * 修改菜单页面
	 *
	 * @param userId
	 * @param menuInfo
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMenu(String userId, SysMenuInfo menuInfo) {
		if (!VerifyUtils.verify(VerifyRegexEnum.MENU_PATH, menuInfo.getPath())) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		menuInfo.setUpdateBy(userId);
		Date curdate = new Date();
		menuInfo.setUpdateTime(curdate);
		updateSysMenuInfoByMenuId(menuInfo, menuInfo.getMenuId());
	}
}