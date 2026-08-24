package com.vmp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vmp.entity.enums.PageSize;
import com.vmp.entity.query.ProductsAdsMappingInfoQuery;
import com.vmp.entity.po.ProductsAdsMappingInfo;
import com.vmp.entity.vo.PaginationResultVO;
import com.vmp.entity.query.SimplePage;
import com.vmp.mappers.ProductsAdsMappingInfoMapper;
import com.vmp.service.ProductsAdsMappingInfoService;
import com.vmp.utils.StringTools;


/**
 * 产品-广告映射表 业务接口实现
 */
@Service("productsAdsMappingInfoService")
public class ProductsAdsMappingInfoServiceImpl implements ProductsAdsMappingInfoService {

	@Resource
	private ProductsAdsMappingInfoMapper<ProductsAdsMappingInfo, ProductsAdsMappingInfoQuery> productsAdsMappingInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<ProductsAdsMappingInfo> findListByParam(ProductsAdsMappingInfoQuery param) {
		return this.productsAdsMappingInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(ProductsAdsMappingInfoQuery param) {
		return this.productsAdsMappingInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<ProductsAdsMappingInfo> findListByPage(ProductsAdsMappingInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<ProductsAdsMappingInfo> list = this.findListByParam(param);
		PaginationResultVO<ProductsAdsMappingInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(ProductsAdsMappingInfo bean) {
		return this.productsAdsMappingInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<ProductsAdsMappingInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.productsAdsMappingInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<ProductsAdsMappingInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.productsAdsMappingInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(ProductsAdsMappingInfo bean, ProductsAdsMappingInfoQuery param) {
		StringTools.checkParam(param);
		return this.productsAdsMappingInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(ProductsAdsMappingInfoQuery param) {
		StringTools.checkParam(param);
		return this.productsAdsMappingInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据AdsPlatformAndAdsCampaignId获取对象
	 */
	@Override
	public ProductsAdsMappingInfo getProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(String adsPlatform, String adsCampaignId) {
		return this.productsAdsMappingInfoMapper.selectByAdsPlatformAndAdsCampaignId(adsPlatform, adsCampaignId);
	}

	/**
	 * 根据AdsPlatformAndAdsCampaignId修改
	 */
	@Override
	public Integer updateProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(ProductsAdsMappingInfo bean, String adsPlatform, String adsCampaignId) {
		return this.productsAdsMappingInfoMapper.updateByAdsPlatformAndAdsCampaignId(bean, adsPlatform, adsCampaignId);
	}

	/**
	 * 根据AdsPlatformAndAdsCampaignId删除
	 */
	@Override
	public Integer deleteProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(String adsPlatform, String adsCampaignId) {
		return this.productsAdsMappingInfoMapper.deleteByAdsPlatformAndAdsCampaignId(adsPlatform, adsCampaignId);
	}
}