package com.vmp.service;

import java.util.List;

import com.vmp.entity.query.CrossPlatformProductCommonInfoQuery;
import com.vmp.entity.po.CrossPlatformProductCommonInfo;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 跨平台全产品公共属性 业务接口
 */
public interface CrossPlatformProductCommonInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<CrossPlatformProductCommonInfo> findListByParam(CrossPlatformProductCommonInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(CrossPlatformProductCommonInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<CrossPlatformProductCommonInfo> findListByPage(CrossPlatformProductCommonInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(CrossPlatformProductCommonInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<CrossPlatformProductCommonInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<CrossPlatformProductCommonInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(CrossPlatformProductCommonInfo bean,CrossPlatformProductCommonInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(CrossPlatformProductCommonInfoQuery param);

	/**
	 * 根据Uid查询对象
	 */
	CrossPlatformProductCommonInfo getCrossPlatformProductCommonInfoByUid(String uid);


	/**
	 * 根据Uid修改
	 */
	Integer updateCrossPlatformProductCommonInfoByUid(CrossPlatformProductCommonInfo bean,String uid);


	/**
	 * 根据Uid删除
	 */
	Integer deleteCrossPlatformProductCommonInfoByUid(String uid);


	/**
	 * 根据SalesPlatformAndStoreIdAndProductId查询对象
	 */
	CrossPlatformProductCommonInfo getCrossPlatformProductCommonInfoBySalesPlatformAndStoreIdAndProductId(String salesPlatform,String storeId,String productId);


	/**
	 * 根据SalesPlatformAndStoreIdAndProductId修改
	 */
	Integer updateCrossPlatformProductCommonInfoBySalesPlatformAndStoreIdAndProductId(CrossPlatformProductCommonInfo bean,String salesPlatform,String storeId,String productId);


	/**
	 * 根据SalesPlatformAndStoreIdAndProductId删除
	 */
	Integer deleteCrossPlatformProductCommonInfoBySalesPlatformAndStoreIdAndProductId(String salesPlatform,String storeId,String productId);

}