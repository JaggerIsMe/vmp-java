package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 官网流量报告
 */
public class ShopifySessionReport implements Serializable {


	private static final long serialVersionUID = 5769929255729294472L;
	/**
	 * 报告日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reportDay;

	/**
	 * 店铺id
	 */
	private String storeId;

	/**
	 * 落地页类型
	 */
	private String landingPageType;

	/**
	 * 落地页url
	 */
	private String landingPagePath;

	/**
	 * 独立访客数
	 */
	private Long onlineStoreVisitors;

	/**
	 * 总访客会话总数
	 */
	private Long sessions;

	/**
	 * 加入购物车有效会话数
	 */
	private Long sessionsWithCartAdditions;

	/**
	 * 结账有效会话数
	 */
	private Long sessionsThatReachedCheckout;


	public void setReportDay(Date reportDay){
		this.reportDay = reportDay;
	}

	public Date getReportDay(){
		return this.reportDay;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setLandingPageType(String landingPageType){
		this.landingPageType = landingPageType;
	}

	public String getLandingPageType(){
		return this.landingPageType;
	}

	public void setLandingPagePath(String landingPagePath){
		this.landingPagePath = landingPagePath;
	}

	public String getLandingPagePath(){
		return this.landingPagePath;
	}

	public void setOnlineStoreVisitors(Long onlineStoreVisitors){
		this.onlineStoreVisitors = onlineStoreVisitors;
	}

	public Long getOnlineStoreVisitors(){
		return this.onlineStoreVisitors;
	}

	public void setSessions(Long sessions){
		this.sessions = sessions;
	}

	public Long getSessions(){
		return this.sessions;
	}

	public void setSessionsWithCartAdditions(Long sessionsWithCartAdditions){
		this.sessionsWithCartAdditions = sessionsWithCartAdditions;
	}

	public Long getSessionsWithCartAdditions(){
		return this.sessionsWithCartAdditions;
	}

	public void setSessionsThatReachedCheckout(Long sessionsThatReachedCheckout){
		this.sessionsThatReachedCheckout = sessionsThatReachedCheckout;
	}

	public Long getSessionsThatReachedCheckout(){
		return this.sessionsThatReachedCheckout;
	}

	@Override
	public String toString (){
		return "报告日期:"+(reportDay == null ? "空" : DateUtil.format(reportDay, DateTimePatternEnum.YYYY_MM_DD.getPattern()))+"，落地页类型:"+(landingPageType == null ? "空" : landingPageType)+"，落地页url:"+(landingPagePath == null ? "空" : landingPagePath)+"，独立访客数:"+(onlineStoreVisitors == null ? "空" : onlineStoreVisitors)+"，总访客会话总数:"+(sessions == null ? "空" : sessions)+"，加入购物车有效会话数:"+(sessionsWithCartAdditions == null ? "空" : sessionsWithCartAdditions)+"，结账有效会话数:"+(sessionsThatReachedCheckout == null ? "空" : sessionsThatReachedCheckout);
	}
}
