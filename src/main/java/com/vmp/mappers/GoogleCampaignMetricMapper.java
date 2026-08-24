package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 谷歌广告系列表现指标 数据库操作接口
 */
public interface GoogleCampaignMetricMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据SegmentDateAndCampaignId更新
	 */
	 Integer updateBySegmentDateAndCampaignId(@Param("bean") T t,@Param("segmentDate") Date segmentDate,@Param("campaignId") String campaignId);


	/**
	 * 根据SegmentDateAndCampaignId删除
	 */
	 Integer deleteBySegmentDateAndCampaignId(@Param("segmentDate") Date segmentDate, @Param("campaignId") String campaignId);


	/**
	 * 根据SegmentDateAndCampaignId获取对象
	 */
	 T selectBySegmentDateAndCampaignId(@Param("segmentDate") Date segmentDate,@Param("campaignId") String campaignId);


}
