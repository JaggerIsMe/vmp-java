package com.vmp.entity.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 官网订单-订单项参数
 */
public class ShopifyOrderItemQuery extends BaseParam {


	/**
	 * 所属订单id
	 */
	private String orderId;

	private String orderIdFuzzy;

	/**
	 * 
	 */
	private String itemId;

	private String itemIdFuzzy;

	/**
	 * 店铺id
	 */
	private String storeId;

	private String storeIdFuzzy;

	/**
	 * 订单产品id
	 */
	private String productId;

	private String productIdFuzzy;

	/**
	 * 订单变体id
	 */
	private String variantId;

	private String variantIdFuzzy;

	/**
	 * 订购数量
	 */
	private Long quantity;

	/**
	 * 退款数量
	 */
	private Long refundQuantity;

	/**
	 * 原订单项总金额(不含折扣)
	 */
	private BigDecimal originalTotalPrice;

	/**
	 * 原订单项单价(不含折扣)
	 */
	private BigDecimal originalUnitPrice;

	/**
	 * 折扣后订单项单价
	 */
	private BigDecimal discountedUnitPrice;

	/**
	 * 订单项总折扣
	 */
	private BigDecimal totalDiscount;

	/**
	 * 所属订单创建时间
	 */
	private String createdAt;

	private String createdAtStart;

	private String createdAtEnd;

	private List<String> storeIdList;

	private List<String> orderIdList;

	private List<String> productIdList;

	private List<String> variantIdList;


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

	public void setItemId(String itemId){
		this.itemId = itemId;
	}

	public String getItemId(){
		return this.itemId;
	}

	public void setItemIdFuzzy(String itemIdFuzzy){
		this.itemIdFuzzy = itemIdFuzzy;
	}

	public String getItemIdFuzzy(){
		return this.itemIdFuzzy;
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

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return this.productId;
	}

	public void setProductIdFuzzy(String productIdFuzzy){
		this.productIdFuzzy = productIdFuzzy;
	}

	public String getProductIdFuzzy(){
		return this.productIdFuzzy;
	}

	public void setVariantId(String variantId){
		this.variantId = variantId;
	}

	public String getVariantId(){
		return this.variantId;
	}

	public void setVariantIdFuzzy(String variantIdFuzzy){
		this.variantIdFuzzy = variantIdFuzzy;
	}

	public String getVariantIdFuzzy(){
		return this.variantIdFuzzy;
	}

	public void setQuantity(Long quantity){
		this.quantity = quantity;
	}

	public Long getQuantity(){
		return this.quantity;
	}

	public void setRefundQuantity(Long refundQuantity){
		this.refundQuantity = refundQuantity;
	}

	public Long getRefundQuantity(){
		return this.refundQuantity;
	}

	public void setOriginalTotalPrice(BigDecimal originalTotalPrice){
		this.originalTotalPrice = originalTotalPrice;
	}

	public BigDecimal getOriginalTotalPrice(){
		return this.originalTotalPrice;
	}

	public void setOriginalUnitPrice(BigDecimal originalUnitPrice){
		this.originalUnitPrice = originalUnitPrice;
	}

	public BigDecimal getOriginalUnitPrice(){
		return this.originalUnitPrice;
	}

	public void setDiscountedUnitPrice(BigDecimal discountedUnitPrice){
		this.discountedUnitPrice = discountedUnitPrice;
	}

	public BigDecimal getDiscountedUnitPrice(){
		return this.discountedUnitPrice;
	}

	public void setTotalDiscount(BigDecimal totalDiscount){
		this.totalDiscount = totalDiscount;
	}

	public BigDecimal getTotalDiscount(){
		return this.totalDiscount;
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

	public List<String> getOrderIdList() {
		return orderIdList;
	}

	public void setOrderIdList(List<String> orderIdList) {
		this.orderIdList = orderIdList;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}

	public List<String> getVariantIdList() {
		return variantIdList;
	}

	public void setVariantIdList(List<String> variantIdList) {
		this.variantIdList = variantIdList;
	}
}
