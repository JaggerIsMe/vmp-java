package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 谷歌广告系列 数据库操作接口
 */
public interface GoogleCampaignInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据CampaignId更新
	 */
	 Integer updateByCampaignId(@Param("bean") T t,@Param("campaignId") String campaignId);


	/**
	 * 根据CampaignId删除
	 */
	 Integer deleteByCampaignId(@Param("campaignId") String campaignId);


	/**
	 * 根据CampaignId获取对象
	 */
	 T selectByCampaignId(@Param("campaignId") String campaignId);


}
