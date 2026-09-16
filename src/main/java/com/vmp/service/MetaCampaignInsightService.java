package com.vmp.service;

import java.util.Date;
import java.util.List;

import com.vmp.entity.query.MetaCampaignInsightQuery;
import com.vmp.entity.po.MetaCampaignInsight;
import com.vmp.entity.vo.PaginationResultVO;


/**
 * meta广告系列表现指标 业务接口
 */
public interface MetaCampaignInsightService {

	/**
	 * 根据条件查询列表
	 */
	List<MetaCampaignInsight> findListByParam(MetaCampaignInsightQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(MetaCampaignInsightQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<MetaCampaignInsight> findListByPage(MetaCampaignInsightQuery param);

	/**
	 * 新增
	 */
	Integer add(MetaCampaignInsight bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<MetaCampaignInsight> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<MetaCampaignInsight> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(MetaCampaignInsight bean,MetaCampaignInsightQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(MetaCampaignInsightQuery param);

	/**
	 * 根据SegmentDateAndCampaignUid查询对象
	 */
	MetaCampaignInsight getMetaCampaignInsightBySegmentDateAndCampaignUid(Date segmentDate, String campaignUid);


	/**
	 * 根据SegmentDateAndCampaignUid修改
	 */
	Integer updateMetaCampaignInsightBySegmentDateAndCampaignUid(MetaCampaignInsight bean,Date segmentDate,String campaignUid);


	/**
	 * 根据SegmentDateAndCampaignUid删除
	 */
	Integer deleteMetaCampaignInsightBySegmentDateAndCampaignUid(Date segmentDate,String campaignUid);

}