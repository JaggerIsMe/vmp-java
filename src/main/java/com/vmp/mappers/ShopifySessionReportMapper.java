package com.vmp.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 官网流量报告 数据库操作接口
 */
public interface ShopifySessionReportMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath更新
	 */
	 Integer updateByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(@Param("bean") T t, @Param("reportDay") Date reportDay, @Param("storeId") String storeId, @Param("landingPageType") String landingPageType, @Param("landingPagePath") String landingPagePath);


	/**
	 * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath删除
	 */
	 Integer deleteByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(@Param("reportDay") Date reportDay,@Param("storeId") String storeId,@Param("landingPageType") String landingPageType,@Param("landingPagePath") String landingPagePath);


	/**
	 * 根据ReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath获取对象
	 */
	 T selectByReportDayAndStoreIdAndLandingPageTypeAndLandingPagePath(@Param("reportDay") Date reportDay,@Param("storeId") String storeId,@Param("landingPageType") String landingPageType,@Param("landingPagePath") String landingPagePath);


}
