package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 产品-广告映射表 数据库操作接口
 */
public interface ProductsAdsMappingInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据AdsPlatformAndAdsCampaignId更新
	 */
	 Integer updateByAdsPlatformAndAdsCampaignId(@Param("bean") T t,@Param("adsPlatform") String adsPlatform,@Param("adsCampaignId") String adsCampaignId);


	/**
	 * 根据AdsPlatformAndAdsCampaignId删除
	 */
	 Integer deleteByAdsPlatformAndAdsCampaignId(@Param("adsPlatform") String adsPlatform,@Param("adsCampaignId") String adsCampaignId);


	/**
	 * 根据AdsPlatformAndAdsCampaignId获取对象
	 */
	 T selectByAdsPlatformAndAdsCampaignId(@Param("adsPlatform") String adsPlatform,@Param("adsCampaignId") String adsCampaignId);


}
