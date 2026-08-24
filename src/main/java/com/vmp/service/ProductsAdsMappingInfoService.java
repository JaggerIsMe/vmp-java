package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.ProductsAdsMappingInfoQuery;
import com.vmp.entity.po.ProductsAdsMappingInfo;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 产品-广告映射表 业务接口
 */
public interface ProductsAdsMappingInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<ProductsAdsMappingInfo> findListByParam(ProductsAdsMappingInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(ProductsAdsMappingInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ProductsAdsMappingInfo> findListByPage(ProductsAdsMappingInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(ProductsAdsMappingInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ProductsAdsMappingInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ProductsAdsMappingInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(ProductsAdsMappingInfo bean,ProductsAdsMappingInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(ProductsAdsMappingInfoQuery param);

	/**
	 * 根据AdsPlatformAndAdsCampaignId查询对象
	 */
	ProductsAdsMappingInfo getProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(String adsPlatform,String adsCampaignId);


	/**
	 * 根据AdsPlatformAndAdsCampaignId修改
	 */
	Integer updateProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(ProductsAdsMappingInfo bean,String adsPlatform,String adsCampaignId);


	/**
	 * 根据AdsPlatformAndAdsCampaignId删除
	 */
	Integer deleteProductsAdsMappingInfoByAdsPlatformAndAdsCampaignId(String adsPlatform,String adsCampaignId);

}