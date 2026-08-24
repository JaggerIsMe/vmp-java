package com.vmp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.CrossPlatformProductCommonInfoQuery;
import com.vmp.entity.po.CrossPlatformProductCommonInfo;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.CrossPlatformProductCommonInfoMapper;
import com.vmp.service.CrossPlatformProductCommonInfoService;
import com.vmp.utils.StringTools;


/**
 * 跨平台全产品公共属性 业务接口实现
 */
@Service("crossPlatformProductCommonInfoService")
public class CrossPlatformProductCommonInfoServiceImpl implements CrossPlatformProductCommonInfoService {

	@Resource
	private CrossPlatformProductCommonInfoMapper<CrossPlatformProductCommonInfo, CrossPlatformProductCommonInfoQuery> crossPlatformProductCommonInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<CrossPlatformProductCommonInfo> findListByParam(CrossPlatformProductCommonInfoQuery param) {
		return this.crossPlatformProductCommonInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(CrossPlatformProductCommonInfoQuery param) {
		return this.crossPlatformProductCommonInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<CrossPlatformProductCommonInfo> findListByPage(CrossPlatformProductCommonInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<CrossPlatformProductCommonInfo> list = this.findListByParam(param);
		PaginationResultVO<CrossPlatformProductCommonInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(CrossPlatformProductCommonInfo bean) {
		return this.crossPlatformProductCommonInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<CrossPlatformProductCommonInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.crossPlatformProductCommonInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<CrossPlatformProductCommonInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.crossPlatformProductCommonInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(CrossPlatformProductCommonInfo bean, CrossPlatformProductCommonInfoQuery param) {
		StringTools.checkParam(param);
		return this.crossPlatformProductCommonInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(CrossPlatformProductCommonInfoQuery param) {
		StringTools.checkParam(param);
		return this.crossPlatformProductCommonInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据Uid获取对象
	 */
	@Override
	public CrossPlatformProductCommonInfo getCrossPlatformProductCommonInfoByUid(String uid) {
		return this.crossPlatformProductCommonInfoMapper.selectByUid(uid);
	}

	/**
	 * 根据Uid修改
	 */
	@Override
	public Integer updateCrossPlatformProductCommonInfoByUid(CrossPlatformProductCommonInfo bean, String uid) {
		return this.crossPlatformProductCommonInfoMapper.updateByUid(bean, uid);
	}

	/**
	 * 根据Uid删除
	 */
	@Override
	public Integer deleteCrossPlatformProductCommonInfoByUid(String uid) {
		return this.crossPlatformProductCommonInfoMapper.deleteByUid(uid);
	}

	/**
	 * 根据SalesPlatformAndStoreIdAndProductId获取对象
	 */
	@Override
	public CrossPlatformProductCommonInfo getCrossPlatformProductCommonInfoBySalesPlatformAndStoreIdAndProductId(String salesPlatform, String storeId, String productId) {
		return this.crossPlatformProductCommonInfoMapper.selectBySalesPlatformAndStoreIdAndProductId(salesPlatform, storeId, productId);
	}

	/**
	 * 根据SalesPlatformAndStoreIdAndProductId修改
	 */
	@Override
	public Integer updateCrossPlatformProductCommonInfoBySalesPlatformAndStoreIdAndProductId(CrossPlatformProductCommonInfo bean, String salesPlatform, String storeId, String productId) {
		return this.crossPlatformProductCommonInfoMapper.updateBySalesPlatformAndStoreIdAndProductId(bean, salesPlatform, storeId, productId);
	}

	/**
	 * 根据SalesPlatformAndStoreIdAndProductId删除
	 */
	@Override
	public Integer deleteCrossPlatformProductCommonInfoBySalesPlatformAndStoreIdAndProductId(String salesPlatform, String storeId, String productId) {
		return this.crossPlatformProductCommonInfoMapper.deleteBySalesPlatformAndStoreIdAndProductId(salesPlatform, storeId, productId);
	}
}