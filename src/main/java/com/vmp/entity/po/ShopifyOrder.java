package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.entity.vo.ShopifyOrderItemVO;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;


/**
 * 官网订单表
 */
public class ShopifyOrder implements Serializable {


	private static final long serialVersionUID = 7892899709348206169L;
	/**
	 * 店铺id
	 */
	private String storeId;

	/**
	 * 店铺名称
	 */
	private String storeName;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 平台订单名称号
	 */
	private String orderName;

	/**
	 * 销售渠道
	 */
	private String sourceName;

	/**
	 * 订单财务状态
	 */
	private String displayFinancialStatus;

	/**
	 * 订单履行状态
	 */
	private String displayFulfillmentStatus;

	/**
	 * 是否包含税费 0否1是
	 */
	private Integer taxesIncluded;

	/**
	 * 订单金额
	 */
	private BigDecimal totalPrice;

	/**
	 * 折扣金额
	 */
	private BigDecimal totalDiscount;

	/**
	 * 退款总金额
	 */
	private BigDecimal totalRefunded;

	/**
	 * 折扣码
	 */
	private String discountCode;

	/**
	 * 客户邮箱
	 */
	private String customerEmail;

	/**
	 * 客户姓名
	 */
	private String customerName;

	/**
	 * 目标国家
	 */
	private String customerCountry;

	/**
	 * 目标国家代码
	 */
	private String customerCountryCode;

	/**
	 * 订单退货状态
	 */
	private String returnStatus;

	/**
	 * 订单创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	private List<ShopifyOrderItemVO> orderItemList;

	public List<ShopifyOrderItemVO> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<ShopifyOrderItemVO> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public void setStoreId(String storeId){
		this.storeId = storeId;
	}

	public String getStoreId(){
		return this.storeId;
	}

	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getStoreName(){
		return this.storeName;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}

	public void setOrderName(String orderName){
		this.orderName = orderName;
	}

	public String getOrderName(){
		return this.orderName;
	}

	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}

	public String getSourceName(){
		return this.sourceName;
	}

	public void setDisplayFinancialStatus(String displayFinancialStatus){
		this.displayFinancialStatus = displayFinancialStatus;
	}

	public String getDisplayFinancialStatus(){
		return this.displayFinancialStatus;
	}

	public void setDisplayFulfillmentStatus(String displayFulfillmentStatus){
		this.displayFulfillmentStatus = displayFulfillmentStatus;
	}

	public String getDisplayFulfillmentStatus(){
		return this.displayFulfillmentStatus;
	}

	public void setTaxesIncluded(Integer taxesIncluded){
		this.taxesIncluded = taxesIncluded;
	}

	public Integer getTaxesIncluded(){
		return this.taxesIncluded;
	}

	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice = totalPrice;
	}

	public BigDecimal getTotalPrice(){
		return this.totalPrice;
	}

	public void setTotalDiscount(BigDecimal totalDiscount){
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getTotalDiscount(){
		return this.totalDiscount;
	}

	public void setTotalRefunded(BigDecimal totalRefunded){
		this.totalRefunded = totalRefunded;
	}

	public BigDecimal getTotalRefunded(){
		return this.totalRefunded;
	}

	public void setDiscountCode(String discountCode){
		this.discountCode = discountCode;
	}

	public String getDiscountCode(){
		return this.discountCode;
	}

	public void setCustomerEmail(String customerEmail){
		this.customerEmail = customerEmail;
	}

	public String getCustomerEmail(){
		return this.customerEmail;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public String getCustomerName(){
		return this.customerName;
	}

	public void setCustomerCountry(String customerCountry){
		this.customerCountry = customerCountry;
	}

	public String getCustomerCountry(){
		return this.customerCountry;
	}

	public void setCustomerCountryCode(String customerCountryCode){
		this.customerCountryCode = customerCountryCode;
	}

	public String getCustomerCountryCode(){
		return this.customerCountryCode;
	}

	public void setReturnStatus(String returnStatus){
		this.returnStatus = returnStatus;
	}

	public String getReturnStatus(){
		return this.returnStatus;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getCreatedAt(){
		return this.createdAt;
	}

	@Override
	public String toString (){
		return "店铺id:"+(storeId == null ? "空" : storeId)+"，店铺名称:"+(storeName == null ? "空" : storeName)+"，货币代码:"+(currencyCode == null ? "空" : currencyCode)+"，订单id:"+(orderId == null ? "空" : orderId)+"，平台订单名称号:"+(orderName == null ? "空" : orderName)+"，销售渠道:"+(sourceName == null ? "空" : sourceName)+"，订单财务状态:"+(displayFinancialStatus == null ? "空" : displayFinancialStatus)+"，订单履行状态:"+(displayFulfillmentStatus == null ? "空" : displayFulfillmentStatus)+"，是否包含税费 0否1是:"+(taxesIncluded == null ? "空" : taxesIncluded)+"，订单金额:"+(totalPrice == null ? "空" : totalPrice)+"，折扣金额:"+(totalDiscount == null ? "空" : totalDiscount)+"，退款总金额:"+(totalRefunded == null ? "空" : totalRefunded)+"，折扣码:"+(discountCode == null ? "空" : discountCode)+"，客户邮箱:"+(customerEmail == null ? "空" : customerEmail)+"，客户姓名:"+(customerName == null ? "空" : customerName)+"，目标国家:"+(customerCountry == null ? "空" : customerCountry)+"，目标国家代码:"+(customerCountryCode == null ? "空" : customerCountryCode)+"，订单退货状态:"+(returnStatus == null ? "空" : returnStatus)+"，订单创建时间:"+(createdAt == null ? "空" : DateUtil.format(createdAt, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
