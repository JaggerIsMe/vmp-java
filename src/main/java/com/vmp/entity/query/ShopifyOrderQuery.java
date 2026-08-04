package com.vmp.entity.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 官网订单表参数
 */
public class ShopifyOrderQuery extends BaseParam {


	/**
	 * 店铺id
	 */
	private String storeId;

	private String storeIdFuzzy;

	/**
	 * 店铺名称
	 */
	private String storeName;

	private String storeNameFuzzy;

	/**
	 * 货币代码
	 */
	private String currencyCode;

	private String currencyCodeFuzzy;

	/**
	 * 订单id
	 */
	private String orderId;

	private String orderIdFuzzy;

	/**
	 * 平台订单名称号
	 */
	private String orderName;

	private String orderNameFuzzy;

	/**
	 * 销售渠道
	 */
	private String sourceName;

	private String sourceNameFuzzy;

	/**
	 * 订单财务状态
	 */
	private String displayFinancialStatus;

	private String displayFinancialStatusFuzzy;

	/**
	 * 订单履行状态
	 */
	private String displayFulfillmentStatus;

	private String displayFulfillmentStatusFuzzy;

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

	private String discountCodeFuzzy;

	/**
	 * 客户邮箱
	 */
	private String customerEmail;

	private String customerEmailFuzzy;

	/**
	 * 客户姓名
	 */
	private String customerName;

	private String customerNameFuzzy;

	/**
	 * 目标国家
	 */
	private String customerCountry;

	private String customerCountryFuzzy;

	/**
	 * 目标国家代码
	 */
	private String customerCountryCode;

	private String customerCountryCodeFuzzy;

	/**
	 * 订单退货状态
	 */
	private String returnStatus;

	private String returnStatusFuzzy;

	/**
	 * 订单创建时间
	 */
	private String createdAt;

	private String createdAtStart;

	private String createdAtEnd;

	private String productId;

	private String variantId;

	private List<String> orderIdList;

	private List<String> storeIdList;

	private List<String> brandList;

	private List<String> personInChargeList;

	private List<String> customerCountryCodeList;

	private List<String> displayFinancialStatusList;

	private List<String> displayFulfillmentStatusList;

	public List<String> getDisplayFinancialStatusList() {
		return displayFinancialStatusList;
	}

	public void setDisplayFinancialStatusList(List<String> displayFinancialStatusList) {
		this.displayFinancialStatusList = displayFinancialStatusList;
	}

	public List<String> getDisplayFulfillmentStatusList() {
		return displayFulfillmentStatusList;
	}

	public void setDisplayFulfillmentStatusList(List<String> displayFulfillmentStatusList) {
		this.displayFulfillmentStatusList = displayFulfillmentStatusList;
	}

	public void setStoreId(String storeId){
		this.storeId = storeId;
	}

	public String getStoreId(){
		return this.storeId;
	}

	public void setStoreIdFuzzy(String storeIdFuzzy){
		this.storeIdFuzzy = storeIdFuzzy;
	}

	public String getStoreIdFuzzy(){
		return this.storeIdFuzzy;
	}

	public void setStoreName(String storeName){
		this.storeName = storeName;
	}

	public String getStoreName(){
		return this.storeName;
	}

	public void setStoreNameFuzzy(String storeNameFuzzy){
		this.storeNameFuzzy = storeNameFuzzy;
	}

	public String getStoreNameFuzzy(){
		return this.storeNameFuzzy;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return this.currencyCode;
	}

	public void setCurrencyCodeFuzzy(String currencyCodeFuzzy){
		this.currencyCodeFuzzy = currencyCodeFuzzy;
	}

	public String getCurrencyCodeFuzzy(){
		return this.currencyCodeFuzzy;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}

	public void setOrderIdFuzzy(String orderIdFuzzy){
		this.orderIdFuzzy = orderIdFuzzy;
	}

	public String getOrderIdFuzzy(){
		return this.orderIdFuzzy;
	}

	public void setOrderName(String orderName){
		this.orderName = orderName;
	}

	public String getOrderName(){
		return this.orderName;
	}

	public void setOrderNameFuzzy(String orderNameFuzzy){
		this.orderNameFuzzy = orderNameFuzzy;
	}

	public String getOrderNameFuzzy(){
		return this.orderNameFuzzy;
	}

	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}

	public String getSourceName(){
		return this.sourceName;
	}

	public void setSourceNameFuzzy(String sourceNameFuzzy){
		this.sourceNameFuzzy = sourceNameFuzzy;
	}

	public String getSourceNameFuzzy(){
		return this.sourceNameFuzzy;
	}

	public void setDisplayFinancialStatus(String displayFinancialStatus){
		this.displayFinancialStatus = displayFinancialStatus;
	}

	public String getDisplayFinancialStatus(){
		return this.displayFinancialStatus;
	}

	public void setDisplayFinancialStatusFuzzy(String displayFinancialStatusFuzzy){
		this.displayFinancialStatusFuzzy = displayFinancialStatusFuzzy;
	}

	public String getDisplayFinancialStatusFuzzy(){
		return this.displayFinancialStatusFuzzy;
	}

	public void setDisplayFulfillmentStatus(String displayFulfillmentStatus){
		this.displayFulfillmentStatus = displayFulfillmentStatus;
	}

	public String getDisplayFulfillmentStatus(){
		return this.displayFulfillmentStatus;
	}

	public void setDisplayFulfillmentStatusFuzzy(String displayFulfillmentStatusFuzzy){
		this.displayFulfillmentStatusFuzzy = displayFulfillmentStatusFuzzy;
	}

	public String getDisplayFulfillmentStatusFuzzy(){
		return this.displayFulfillmentStatusFuzzy;
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

	public void setDiscountCodeFuzzy(String discountCodeFuzzy){
		this.discountCodeFuzzy = discountCodeFuzzy;
	}

	public String getDiscountCodeFuzzy(){
		return this.discountCodeFuzzy;
	}

	public void setCustomerEmail(String customerEmail){
		this.customerEmail = customerEmail;
	}

	public String getCustomerEmail(){
		return this.customerEmail;
	}

	public void setCustomerEmailFuzzy(String customerEmailFuzzy){
		this.customerEmailFuzzy = customerEmailFuzzy;
	}

	public String getCustomerEmailFuzzy(){
		return this.customerEmailFuzzy;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public String getCustomerName(){
		return this.customerName;
	}

	public void setCustomerNameFuzzy(String customerNameFuzzy){
		this.customerNameFuzzy = customerNameFuzzy;
	}

	public String getCustomerNameFuzzy(){
		return this.customerNameFuzzy;
	}

	public void setCustomerCountry(String customerCountry){
		this.customerCountry = customerCountry;
	}

	public String getCustomerCountry(){
		return this.customerCountry;
	}

	public void setCustomerCountryFuzzy(String customerCountryFuzzy){
		this.customerCountryFuzzy = customerCountryFuzzy;
	}

	public String getCustomerCountryFuzzy(){
		return this.customerCountryFuzzy;
	}

	public void setCustomerCountryCode(String customerCountryCode){
		this.customerCountryCode = customerCountryCode;
	}

	public String getCustomerCountryCode(){
		return this.customerCountryCode;
	}

	public void setCustomerCountryCodeFuzzy(String customerCountryCodeFuzzy){
		this.customerCountryCodeFuzzy = customerCountryCodeFuzzy;
	}

	public String getCustomerCountryCodeFuzzy(){
		return this.customerCountryCodeFuzzy;
	}

	public void setReturnStatus(String returnStatus){
		this.returnStatus = returnStatus;
	}

	public String getReturnStatus(){
		return this.returnStatus;
	}

	public void setReturnStatusFuzzy(String returnStatusFuzzy){
		this.returnStatusFuzzy = returnStatusFuzzy;
	}

	public String getReturnStatusFuzzy(){
		return this.returnStatusFuzzy;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return this.createdAt;
	}

	public void setCreatedAtStart(String createdAtStart){
		this.createdAtStart = createdAtStart;
	}

	public String getCreatedAtStart(){
		return this.createdAtStart;
	}
	public void setCreatedAtEnd(String createdAtEnd){
		this.createdAtEnd = createdAtEnd;
	}

	public String getCreatedAtEnd(){
		return this.createdAtEnd;
	}

	public List<String> getStoreIdList() {
		return storeIdList;
	}

	public void setStoreIdList(List<String> storeIdList) {
		this.storeIdList = storeIdList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getVariantId() {
		return variantId;
	}

	public void setVariantId(String variantId) {
		this.variantId = variantId;
	}

	public List<String> getOrderIdList() {
		return orderIdList;
	}

	public void setOrderIdList(List<String> orderIdList) {
		this.orderIdList = orderIdList;
	}

	public List<String> getCustomerCountryCodeList() {
		return customerCountryCodeList;
	}

	public void setCustomerCountryCodeList(List<String> customerCountryCodeList) {
		this.customerCountryCodeList = customerCountryCodeList;
	}

	public List<String> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<String> brandList) {
		this.brandList = brandList;
	}

	public List<String> getPersonInChargeList() {
		return personInChargeList;
	}

	public void setPersonInChargeList(List<String> personInChargeList) {
		this.personInChargeList = personInChargeList;
	}
}
