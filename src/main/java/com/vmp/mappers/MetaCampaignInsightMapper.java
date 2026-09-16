package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * meta广告系列表现指标 数据库操作接口
 */
public interface MetaCampaignInsightMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据SegmentDateAndCampaignUid更新
	 */
	 Integer updateBySegmentDateAndCampaignUid(@Param("bean") T t, @Param("segmentDate") Date segmentDate, @Param("campaignUid") String campaignUid);


	/**
	 * 根据SegmentDateAndCampaignUid删除
	 */
	 Integer deleteBySegmentDateAndCampaignUid(@Param("segmentDate") Date segmentDate,@Param("campaignUid") String campaignUid);


	/**
	 * 根据SegmentDateAndCampaignUid获取对象
	 */
	 T selectBySegmentDateAndCampaignUid(@Param("segmentDate") Date segmentDate,@Param("campaignUid") String campaignUid);


}
