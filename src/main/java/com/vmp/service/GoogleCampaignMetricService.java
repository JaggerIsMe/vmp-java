package com.vmp.service;

import java.util.Date;
import java.util.List;

import com.vmp.entity.query.GoogleCampaignMetricQuery;
import com.vmp.entity.po.GoogleCampaignMetric;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * 谷歌广告系列表现指标 业务接口
 */
public interface GoogleCampaignMetricService {

	/**
	 * 根据条件查询列表
	 */
	List<GoogleCampaignMetric> findListByParam(GoogleCampaignMetricQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(GoogleCampaignMetricQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<GoogleCampaignMetric> findListByPage(GoogleCampaignMetricQuery param);

	/**
	 * 新增
	 */
	Integer add(GoogleCampaignMetric bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<GoogleCampaignMetric> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<GoogleCampaignMetric> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(GoogleCampaignMetric bean,GoogleCampaignMetricQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(GoogleCampaignMetricQuery param);

	/**
	 * 根据SegmentDateAndCampaignId查询对象
	 */
	GoogleCampaignMetric getGoogleCampaignMetricBySegmentDateAndCampaignId(Date segmentDate,String campaignId);


	/**
	 * 根据SegmentDateAndCampaignId修改
	 */
	Integer updateGoogleCampaignMetricBySegmentDateAndCampaignId(GoogleCampaignMetric bean, Date segmentDate, String campaignId);


	/**
	 * 根据SegmentDateAndCampaignId删除
	 */
	Integer deleteGoogleCampaignMetricBySegmentDateAndCampaignId(Date segmentDate,String campaignId);

}