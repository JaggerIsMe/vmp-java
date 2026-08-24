package com.vmp.service;

import java.util.Date;
import java.util.List;

import com.vmp.entity.dashboardVo.GoogleAdsCampaignMetricDailyPerformanceVO;
import com.vmp.entity.query.GoogleAdsCampaignCommonQuery;
import com.vmp.entity.query.GoogleCampaignInfoQuery;
import com.vmp.entity.po.GoogleCampaignInfo;
import com.vmp.entity.vo.GoogleAdsCampaignMetricVO;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 谷歌广告系列 业务接口
 */
public interface GoogleCampaignInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<GoogleCampaignInfo> findListByParam(GoogleCampaignInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(GoogleCampaignInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<GoogleCampaignInfo> findListByPage(GoogleCampaignInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(GoogleCampaignInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<GoogleCampaignInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<GoogleCampaignInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(GoogleCampaignInfo bean,GoogleCampaignInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(GoogleCampaignInfoQuery param);

	/**
	 * 根据CampaignId查询对象
	 */
	GoogleCampaignInfo getGoogleCampaignInfoByCampaignId(String campaignId);


	/**
	 * 根据CampaignId修改
	 */
	Integer updateGoogleCampaignInfoByCampaignId(GoogleCampaignInfo bean,String campaignId);


	/**
	 * 根据CampaignId删除
	 */
	Integer deleteGoogleCampaignInfoByCampaignId(String campaignId);

	/**
	 * 同步谷歌广告系列表现
	 * @param segmentsDate
	 */
	void syncGoogleAdsCampaignMetrics(Date segmentsDate);

	/**
	 * 查询谷歌广告系列--分页
	 * @param dateRangeStart
	 * @param dateRangeEnd
	 * @param query
	 * @return
	 */
	PaginationResultVO<GoogleAdsCampaignMetricVO> loadGoogleAdsCampaignMetricsByPage(Date dateRangeStart, Date dateRangeEnd, GoogleAdsCampaignCommonQuery query);

	/**
	 * 获取谷歌广告系列数据表现
	 * @param dateRangeStart
	 * @param dateRangeEnd
	 * @param query
	 * @return
	 */
	List<GoogleAdsCampaignMetricDailyPerformanceVO> getGoogleAdsCampaignMetricsDailyPerformance(Date dateRangeStart, Date dateRangeEnd, GoogleAdsCampaignCommonQuery query);

	/**
	 * 分配谷歌广告投手
	 * @param newPersonInCharge
	 * @param param
	 * @return
	 */
    void allocatePerson(String newPersonInCharge, GoogleCampaignInfoQuery param);
}