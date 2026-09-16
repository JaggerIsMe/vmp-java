package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * meta广告系列信息 数据库操作接口
 */
public interface MetaAdsCampaignInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据Uid更新
	 */
	 Integer updateByUid(@Param("bean") T t,@Param("uid") String uid);


	/**
	 * 根据Uid删除
	 */
	 Integer deleteByUid(@Param("uid") String uid);


	/**
	 * 根据Uid获取对象
	 */
	 T selectByUid(@Param("uid") String uid);


	/**
	 * 根据CampaignIdAndAdsAccountId更新
	 */
	 Integer updateByCampaignIdAndAdsAccountId(@Param("bean") T t,@Param("campaignId") String campaignId,@Param("adsAccountId") String adsAccountId);


	/**
	 * 根据CampaignIdAndAdsAccountId删除
	 */
	 Integer deleteByCampaignIdAndAdsAccountId(@Param("campaignId") String campaignId,@Param("adsAccountId") String adsAccountId);


	/**
	 * 根据CampaignIdAndAdsAccountId获取对象
	 */
	 T selectByCampaignIdAndAdsAccountId(@Param("campaignId") String campaignId,@Param("adsAccountId") String adsAccountId);


}
