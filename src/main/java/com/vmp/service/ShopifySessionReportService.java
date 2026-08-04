package com.vmp.service;

import java.util.Date;
import java.util.List;

import com.vmp.entity.query.ShopifySessionReportQuery;
import com.vmp.entity.po.ShopifySessionReport;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 官网流量报告 业务接口
 */
public interface ShopifySessionReportService {

	/**
	 * 根据条件查询列表
	 */
	List<ShopifySessionReport> findListByParam(ShopifySessionReportQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(ShopifySessionReportQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<ShopifySessionReport> findListByPage(ShopifySessionReportQuery param);

	/**
	 * 新增
	 */
	Integer add(ShopifySessionReport bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<ShopifySessionReport> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<ShopifySessionReport> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(ShopifySessionReport bean,ShopifySessionReportQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(ShopifySessionReportQuery param);

	/**
	 * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath查询对象
	 */
	ShopifySessionReport getShopifySessionReportByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(Date reportDay,String storeId,String landingPageType,String landingPagePath);


	/**
	 * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath修改
	 */
	Integer updateShopifySessionReportByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(ShopifySessionReport bean,Date reportDay,String storeId,String landingPageType,String landingPagePath);


	/**
	 * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath删除
	 */
	Integer deleteShopifySessionReportByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(Date reportDay,String storeId,String landingPageType,String landingPagePath);

	/**
	 * 分析官网流量Sessions
	 * @param host
	 * @param type
	 * @param day
	 */
	void getShopifySessionsReport(String host, String type, Date day);

}