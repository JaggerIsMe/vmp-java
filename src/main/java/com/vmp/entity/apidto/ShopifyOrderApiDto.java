package com.vmp.entity.apidto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmp.utils.StringTools;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShopifyOrderApiDto {
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
    @JSONField(name = "id")
    private String orderId;

    /**
     * 平台订单名称号
     */
    @JSONField(name = "name")
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
    private MoneyBag totalPriceSet;
    private BigDecimal totalPrice;

    /**
     * 折扣金额
     */
    private MoneyBag totalDiscountsSet;
    private BigDecimal totalDiscount;

    /**
     * 退款总金额
     */
    private MoneyBag totalRefundedSet;
    private BigDecimal totalRefunded;

    /**
     * 折扣码
     */
    private List<String> discountCodes;
    private String discountCode;

    /**
     * 客户邮箱
     */
    @JSONField(name = "email")
    private String customerEmail;

    /**
     * 客户姓名
     */
    private DisplayAddress displayAddress;
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

    private LineItem lineItems;

    private List<Refund> refunds;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getOrderId() {
        return StringTools.extractShopifyLastId(orderId);
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDisplayFinancialStatus() {
        return displayFinancialStatus;
    }

    public void setDisplayFinancialStatus(String displayFinancialStatus) {
        this.displayFinancialStatus = displayFinancialStatus;
    }

    public String getDisplayFulfillmentStatus() {
        return displayFulfillmentStatus;
    }

    public void setDisplayFulfillmentStatus(String displayFulfillmentStatus) {
        this.displayFulfillmentStatus = displayFulfillmentStatus;
    }

    public Integer getTaxesIncluded() {
        return taxesIncluded;
    }

    public void setTaxesIncluded(Integer taxesIncluded) {
        this.taxesIncluded = taxesIncluded;
    }

    public BigDecimal getTotalPrice() {
        if (null == this.totalPriceSet || null == this.totalPriceSet.getShopMoney()) {
            return BigDecimal.ZERO;
        }
        return this.totalPriceSet.getShopMoney().getAmount();
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalDiscount() {
        if (null == this.totalDiscountsSet || null == this.totalDiscountsSet.getShopMoney()) {
            return BigDecimal.ZERO;
        }
        return this.totalDiscountsSet.getShopMoney().getAmount();
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getDiscountCode() {
        return CollectionUtils.isEmpty(this.discountCodes) ? null : String.join(",", this.discountCodes);
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return null == this.displayAddress ? null : this.displayAddress.getName();
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCountry() {
        return null == this.displayAddress ? null : this.displayAddress.getCountry();
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerCountryCode() {
        return null == this.displayAddress ? null : this.displayAddress.getCountryCodeV2();
    }

    public void setCustomerCountryCode(String customerCountryCode) {
        this.customerCountryCode = customerCountryCode;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public MoneyBag getTotalPriceSet() {
        return totalPriceSet;
    }

    public void setTotalPriceSet(MoneyBag totalPriceSet) {
        this.totalPriceSet = totalPriceSet;
    }

    public MoneyBag getTotalDiscountsSet() {
        return totalDiscountsSet;
    }

    public void setTotalDiscountsSet(MoneyBag totalDiscountsSet) {
        this.totalDiscountsSet = totalDiscountsSet;
    }

    public List<String> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(List<String> discountCodes) {
        this.discountCodes = discountCodes;
    }

    public DisplayAddress getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(DisplayAddress displayAddress) {
        this.displayAddress = displayAddress;
    }

    public LineItem getLineItems() {
        return lineItems;
    }

    public void setLineItems(LineItem lineItems) {
        this.lineItems = lineItems;
    }

    public MoneyBag getTotalRefundedSet() {
        return totalRefundedSet;
    }

    public void setTotalRefundedSet(MoneyBag totalRefundedSet) {
        this.totalRefundedSet = totalRefundedSet;
    }

    public BigDecimal getTotalRefunded() {
        if (null==this.totalRefundedSet||null==this.totalRefundedSet.getShopMoney()){
            return BigDecimal.ZERO;
        }
        return this.totalRefundedSet.getShopMoney().getAmount();
    }

    public void setTotalRefunded(BigDecimal totalRefunded) {
        this.totalRefunded = totalRefunded;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Refund> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<Refund> refunds) {
        this.refunds = refunds;
    }

    @Override
    public String toString() {
        return "ShopifyOrderApiDto{" +
                "storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderName='" + orderName + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", displayFinancialStatus='" + displayFinancialStatus + '\'' +
                ", displayFulfillmentStatus='" + displayFulfillmentStatus + '\'' +
                ", taxesIncluded=" + taxesIncluded +
                ", totalPriceSet=" + totalPriceSet +
                ", totalPrice=" + totalPrice +
                ", totalDiscountsSet=" + totalDiscountsSet +
                ", totalDiscount=" + totalDiscount +
                ", discountCodes=" + discountCodes +
                ", discountCode='" + discountCode + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", displayAddress=" + displayAddress +
                ", customerName='" + customerName + '\'' +
                ", customerCountry='" + customerCountry + '\'' +
                ", customerCountryCode='" + customerCountryCode + '\'' +
                ", returnStatus='" + returnStatus + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }

    public static class ShopMoney {
        private BigDecimal amount;
        private String currencyCode;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }

    public static class MoneyBag {
        private ShopMoney shopMoney;

        public ShopMoney getShopMoney() {
            return shopMoney;
        }

        public void setShopMoney(ShopMoney shopMoney) {
            this.shopMoney = shopMoney;
        }
    }

    public static class DisplayAddress {
        private String address1;
        private String address2;
        private String city;
        private String company;
        private String country;
        private String countryCodeV2;
        private String formattedArea;
        private String id;
        private String firstName;
        private String lastName;
        private String name;
        private String phone;
        private String province;
        private String provinceCode;
        private String zip;

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryCodeV2() {
            return countryCodeV2;
        }

        public void setCountryCodeV2(String countryCodeV2) {
            this.countryCodeV2 = countryCodeV2;
        }

        public String getFormattedArea() {
            return formattedArea;
        }

        public void setFormattedArea(String formattedArea) {
            this.formattedArea = formattedArea;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }
    }

    public static class LineItem {
        private List<LineItemNode> nodes;

        public List<LineItemNode> getNodes() {
            return nodes;
        }

        public void setNodes(List<LineItemNode> nodes) {
            this.nodes = nodes;
        }
    }

    public static class LineItemNode {
        private String id;
        private LineItemProduct product;
        private LineItemVariant variant;
        private Long quantity;
        private Long refundableQuantity;
        private MoneyBag originalTotalSet;
        private MoneyBag originalUnitPriceSet;
        private MoneyBag discountedUnitPriceAfterAllDiscountsSet;
        private MoneyBag totalDiscountSet;

        public String getId() {
            return StringTools.extractShopifyLastId(id);
        }

        public void setId(String id) {
            this.id = id;
        }

        public LineItemProduct getProduct() {
            return product;
        }

        public void setProduct(LineItemProduct product) {
            this.product = product;
        }

        public LineItemVariant getVariant() {
            return variant;
        }

        public void setVariant(LineItemVariant variant) {
            this.variant = variant;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }

        public MoneyBag getOriginalTotalSet() {
            return originalTotalSet;
        }

        public void setOriginalTotalSet(MoneyBag originalTotalSet) {
            this.originalTotalSet = originalTotalSet;
        }

        public MoneyBag getOriginalUnitPriceSet() {
            return originalUnitPriceSet;
        }

        public void setOriginalUnitPriceSet(MoneyBag originalUnitPriceSet) {
            this.originalUnitPriceSet = originalUnitPriceSet;
        }

        public MoneyBag getDiscountedUnitPriceAfterAllDiscountsSet() {
            return discountedUnitPriceAfterAllDiscountsSet;
        }

        public void setDiscountedUnitPriceAfterAllDiscountsSet(MoneyBag discountedUnitPriceAfterAllDiscountsSet) {
            this.discountedUnitPriceAfterAllDiscountsSet = discountedUnitPriceAfterAllDiscountsSet;
        }

        public MoneyBag getTotalDiscountSet() {
            return totalDiscountSet;
        }

        public void setTotalDiscountSet(MoneyBag totalDiscountSet) {
            this.totalDiscountSet = totalDiscountSet;
        }

        public Long getRefundableQuantity() {
            return refundableQuantity;
        }

        public void setRefundableQuantity(Long refundableQuantity) {
            this.refundableQuantity = refundableQuantity;
        }
    }

    public static class LineItemProduct {
        private String id;

        public String getId() {
            return StringTools.extractShopifyLastId(id);
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class LineItemVariant {
        private String id;

        public String getId() {
            return StringTools.extractShopifyLastId(id);
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Refund {
        private String id;
        private RefundLineItem refundLineItems;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public RefundLineItem getRefundLineItems() {
            return refundLineItems;
        }

        public void setRefundLineItems(RefundLineItem refundLineItems) {
            this.refundLineItems = refundLineItems;
        }
    }

    public static class RefundLineItemNode {
        private String id;
        private Long quantity;
        private LineItemNode lineItem;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }

        public LineItemNode getLineItem() {
            return lineItem;
        }

        public void setLineItem(LineItemNode lineItem) {
            this.lineItem = lineItem;
        }
    }

    public static class RefundLineItem {
        private List<RefundLineItemNode> nodes;

        public List<RefundLineItemNode> getNodes() {
            return nodes;
        }

        public void setNodes(List<RefundLineItemNode> nodes) {
            this.nodes = nodes;
        }
    }
}
