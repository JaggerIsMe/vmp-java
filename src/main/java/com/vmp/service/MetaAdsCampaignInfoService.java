package com.vmp.service;

import java.util.Date;
import java.util.List;

import com.vmp.entity.dashboardVo.MetaAdsCampaignInsightDailyPerformanceVO;
import com.vmp.entity.query.GoogleCampaignInfoQuery;
import com.vmp.entity.query.MetaAdsCampaignCommonQuery;
import com.vmp.entity.query.MetaAdsCampaignInfoQuery;
import com.vmp.entity.po.MetaAdsCampaignInfo;
import com.vmp.entity.vo.MetaCampaignInsightVO;
import com.vmp.entity.vo.PaginationResultVO;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * meta广告系列信息 业务接口
 */
public interface MetaAdsCampaignInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<MetaAdsCampaignInfo> findListByParam(MetaAdsCampaignInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(MetaAdsCampaignInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<MetaAdsCampaignInfo> findListByPage(MetaAdsCampaignInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(MetaAdsCampaignInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<MetaAdsCampaignInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<MetaAdsCampaignInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(MetaAdsCampaignInfo bean,MetaAdsCampaignInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(MetaAdsCampaignInfoQuery param);

	/**
	 * 根据Uid查询对象
	 */
	MetaAdsCampaignInfo getMetaAdsCampaignInfoByUid(String uid);


	/**
	 * 根据Uid修改
	 */
	Integer updateMetaAdsCampaignInfoByUid(MetaAdsCampaignInfo bean,String uid);


	/**
	 * 根据Uid删除
	 */
	Integer deleteMetaAdsCampaignInfoByUid(String uid);


	/**
	 * 根据CampaignIdAndAdsAccountId查询对象
	 */
	MetaAdsCampaignInfo getMetaAdsCampaignInfoByCampaignIdAndAdsAccountId(String campaignId,String adsAccountId);


	/**
	 * 根据CampaignIdAndAdsAccountId修改
	 */
	Integer updateMetaAdsCampaignInfoByCampaignIdAndAdsAccountId(MetaAdsCampaignInfo bean,String campaignId,String adsAccountId);


	/**
	 * 根据CampaignIdAndAdsAccountId删除
	 */
	Integer deleteMetaAdsCampaignInfoByCampaignIdAndAdsAccountId(String campaignId,String adsAccountId);


	/**
	 * 同步Meta广告系列表现
	 * @param since
	 * @param until
	 */
	void syncMetaAdsCampaignInsight(@DateTimeFormat(pattern = "yyyy-MM-dd") String since, @DateTimeFormat(pattern = "yyyy-MM-dd") String until);

	/**
	 * 查询Meta广告系列--分页
	 * @param dateRangeStart
	 * @param dateRangeEnd
	 * @param query
	 * @return
	 */
	PaginationResultVO<MetaCampaignInsightVO> loadMetaAdsCampaignInsightsByPage(Date dateRangeStart, Date dateRangeEnd, MetaAdsCampaignCommonQuery query);

	/**
	 * 获取Meta广告系列数据表现
	 * @param dateRangeStart
	 * @param dateRangeEnd
	 * @param query
	 * @return
	 */
	List<MetaAdsCampaignInsightDailyPerformanceVO> getMetaAdsCampaignInsightsDailyPerformance(Date dateRangeStart, Date dateRangeEnd, MetaAdsCampaignCommonQuery query);

	/**
	 * 分配Meta广告投手
	 * @param newPersonInCharge
	 * @param param
	 */
	void allocatePerson(String newPersonInCharge, MetaAdsCampaignInfoQuery param);
}