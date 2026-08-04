package com.vmp.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import com.vmp.entity.enums.DateTimePatternEnum;
import com.vmp.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 官网订单-订单项
 */
public class ShopifyOrderItem implements Serializable {


	private static final long serialVersionUID = -4276410767513059451L;
	/**
	 * 所属订单id
	 */
	private String orderId;

	/**
	 * 订单项id
	 */
	private String itemId;

	/**
	 * 店铺id
	 */
	private String storeId;

	/**
	 * 订单产品id
	 */
	private String productId;

	/**
	 * 订单变体id
	 */
	private String variantId;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	private String productTitle;
	private String attribute;
	private String sku;


	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}

	public void setItemId(String itemId){
		this.itemId = itemId;
	}

	public String getItemId(){
		return this.itemId;
	}

	public void setStoreId(String storeId){
		this.storeId = storeId;
	}

	public String getStoreId(){
		return this.storeId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return this.productId;
	}

	public void setVariantId(String variantId){
		this.variantId = variantId;
	}

	public String getVariantId(){
		return this.variantId;
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

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getCreatedAt(){
		return this.createdAt;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public String toString (){
		return "所属订单id:"+(orderId == null ? "空" : orderId)+"，itemId:"+(itemId == null ? "空" : itemId)+"，店铺id:"+(storeId == null ? "空" : storeId)+"，订单产品id:"+(productId == null ? "空" : productId)+"，订单变体id:"+(variantId == null ? "空" : variantId)+"，订购数量:"+(quantity == null ? "空" : quantity)+"，退款数量:"+(refundQuantity == null ? "空" : refundQuantity)+"，原订单项总金额(不含折扣):"+(originalTotalPrice == null ? "空" : originalTotalPrice)+"，原订单项单价(不含折扣):"+(originalUnitPrice == null ? "空" : originalUnitPrice)+"，折扣后订单项单价:"+(discountedUnitPrice == null ? "空" : discountedUnitPrice)+"，订单项总折扣:"+(totalDiscount == null ? "空" : totalDiscount)+"，所属订单创建时间:"+(createdAt == null ? "空" : DateUtil.format(createdAt, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
