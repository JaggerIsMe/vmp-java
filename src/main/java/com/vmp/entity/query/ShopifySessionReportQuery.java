package com.vmp.entity.query;

import java.util.Date;
import java.util.List;


/**
 * 官网流量报告参数
 */
public class ShopifySessionReportQuery extends BaseParam {


	/**
	 * 报告日期
	 */
	private String reportDay;

	private String reportDayStart;

	private String reportDayEnd;

	/**
	 * 店铺id
	 */
	private String storeId;

	private String storeIdFuzzy;

	/**
	 * 落地页类型
	 */
	private String landingPageType;

	private String landingPageTypeFuzzy;

	/**
	 * 落地页url
	 */
	private String landingPagePath;

	private String landingPagePathFuzzy;

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

	private List<String> storeIdList;

	private List<String> landingPagePathList;

	public void setReportDay(String reportDay){
		this.reportDay = reportDay;
	}

	public String getReportDay(){
		return this.reportDay;
	}

	public void setReportDayStart(String reportDayStart){
		this.reportDayStart = reportDayStart;
	}

	public String getReportDayStart(){
		return this.reportDayStart;
	}
	public void setReportDayEnd(String reportDayEnd){
		this.reportDayEnd = reportDayEnd;
	}

	public String getReportDayEnd(){
		return this.reportDayEnd;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreIdFuzzy() {
		return storeIdFuzzy;
	}

	public void setStoreIdFuzzy(String storeIdFuzzy) {
		this.storeIdFuzzy = storeIdFuzzy;
	}

	public void setLandingPageType(String landingPageType){
		this.landingPageType = landingPageType;
	}

	public String getLandingPageType(){
		return this.landingPageType;
	}

	public void setLandingPageTypeFuzzy(String landingPageTypeFuzzy){
		this.landingPageTypeFuzzy = landingPageTypeFuzzy;
	}

	public String getLandingPageTypeFuzzy(){
		return this.landingPageTypeFuzzy;
	}

	public void setLandingPagePath(String landingPagePath){
		this.landingPagePath = landingPagePath;
	}

	public String getLandingPagePath(){
		return this.landingPagePath;
	}

	public void setLandingPagePathFuzzy(String landingPagePathFuzzy){
		this.landingPagePathFuzzy = landingPagePathFuzzy;
	}

	public String getLandingPagePathFuzzy(){
		return this.landingPagePathFuzzy;
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

	public List<String> getStoreIdList() {
		return storeIdList;
	}

	public void setStoreIdList(List<String> storeIdList) {
		this.storeIdList = storeIdList;
	}

	public List<String> getLandingPagePathList() {
		return landingPagePathList;
	}

	public void setLandingPagePathList(List<String> landingPagePathList) {
		this.landingPagePathList = landingPagePathList;
	}
}
