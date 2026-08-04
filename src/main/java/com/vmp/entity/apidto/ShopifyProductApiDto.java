package com.vmp.entity.apidto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmp.utils.StringTools;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class ShopifyProductApiDto {
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

    @JSONField(name = "product")
    private ParentProduct parentProduct;
    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 产品url后缀
     */
    private String handle;

    /**
     * 产品前台链接
     */
    private String productUrl;

    /**
     * 产品状态
     */
    private String productStatus;

    /**
     * 产品创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productCreatedAt;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品类目
     */
    private String productCategory;

    /**
     * 产品主图链接
     */
    private String productImgUrl;

    /**
     * 变体id
     */
    @JSONField(name = "id")
    private String variantId;

    /**
     * 变体属性
     */
    @JSONField(name = "title")
    private String attribute;

    /**
     * 库存编码
     */
    @JSONField(name = "sku")
    private String sku;

    /**
     * 条码
     */
    @JSONField(name = "barcode")
    private String barcode;

    /**
     * 单价
     */
    @JSONField(name = "price")
    private BigDecimal unitPrice;

    /**
     * 变体创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(name = "createdAt")
    private Date variantCreatedAt;

    /**
     * 变体标题
     */
    @JSONField(name = "displayName")
    private String variantTitle;

    /**
     * 变体主图链接
     */
    @JSONField(name = "image")
    private Image variantImg;
    private String variantImgUrl;

    /**
     * 变体可售状态 0不可售 1可售
     */
    @JSONField(name = "availableForSale")
    private Integer availableForSale;

    private Long totalAvailableQuantity;

    private InventoryItem inventoryItem;

    private String parentStatus;


    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return null == this.parentProduct ? null : StringTools.extractShopifyLastId(this.parentProduct.getId());
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductTitle() {
        return null == this.parentProduct ? null : this.parentProduct.getTitle();
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getHandle() {
        return null == this.parentProduct ? null : this.parentProduct.getHandle();
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductUrl() {
        return null == this.parentProduct ? null : this.parentProduct.getOnlineStoreUrl();
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatus() {
        return null == this.parentProduct ? null : this.parentProduct.getStatus();
    }

    public void setProductCreatedAt(Date productCreatedAt) {
        this.productCreatedAt = productCreatedAt;
    }

    public Date getProductCreatedAt() {
        return null == this.parentProduct ? null : this.parentProduct.getCreatedAt();
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return null == this.parentProduct ? null : this.parentProduct.getProductType();
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        if (this.parentProduct == null || this.parentProduct.getCategory() == null) {
            return null;
        }
        return this.parentProduct.getCategory().getName();
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }

    public String getProductImgUrl() {
        if (this.parentProduct == null ||
                this.parentProduct.getMedia() == null ||
                this.parentProduct.getMedia().getNodes() == null ||
                this.parentProduct.getMedia().getNodes().isEmpty()) {
            return null;
        }
        ParentProductMediaNode firstNode = this.parentProduct.getMedia().getNodes().get(0);
        if (firstNode == null ||
                firstNode.getPreview() == null ||
                firstNode.getPreview().getImage() == null) {
            return null;
        }
        return firstNode.getPreview().getImage().getUrl();
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getVariantId() {
        return StringTools.extractShopifyLastId(this.variantId);
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return this.sku;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setVariantCreatedAt(Date variantCreatedAt) {
        this.variantCreatedAt = variantCreatedAt;
    }

    public Date getVariantCreatedAt() {
        return this.variantCreatedAt;
    }

    public void setVariantTitle(String variantTitle) {
        this.variantTitle = variantTitle;
    }

    public String getVariantTitle() {
        return this.variantTitle;
    }

    public void setVariantImgUrl(String variantImgUrl) {
        this.variantImgUrl = variantImgUrl;
    }

    public String getVariantImgUrl() {
        return null == this.variantImg ? null : this.variantImg.getUrl();
    }

    public void setAvailableForSale(Integer availableForSale) {
        this.availableForSale = availableForSale;
    }

    public Integer getAvailableForSale() {
        return this.availableForSale;
    }

    public Long getTotalAvailableQuantity() {
        return totalAvailableQuantity;
    }

    public void setTotalAvailableQuantity(Long totalAvailableQuantity) {
        this.totalAvailableQuantity = totalAvailableQuantity;
    }

    public ParentProduct getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(ParentProduct parentProduct) {
        this.parentProduct = parentProduct;
    }

    public Image getVariantImg() {
        return variantImg;
    }

    public void setVariantImg(Image variantImg) {
        this.variantImg = variantImg;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public String getParentStatus() {
        return parentStatus;
    }

    public void setParentStatus(String parentStatus) {
        this.parentStatus = parentStatus;
    }

    @Override
    public String toString() {
        return "ShopifyProductApiDto{" +
                "storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", parentProduct=" + parentProduct +
                ", productId='" + productId + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", handle='" + handle + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", productCreatedAt=" + productCreatedAt +
                ", productType='" + productType + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productImgUrl='" + productImgUrl + '\'' +
                ", variantId='" + variantId + '\'' +
                ", attribute='" + attribute + '\'' +
                ", sku='" + sku + '\'' +
                ", barcode='" + barcode + '\'' +
                ", unitPrice=" + unitPrice +
                ", variantCreatedAt=" + variantCreatedAt +
                ", variantTitle='" + variantTitle + '\'' +
                ", variantImg=" + variantImg +
                ", variantImgUrl='" + variantImgUrl + '\'' +
                ", availableForSale=" + availableForSale +
                ", totalInventoryQuantity=" + totalAvailableQuantity +
                ", inventoryItem=" + inventoryItem +
                '}';
    }

    public static class ParentProduct {
        private String id;
        private String title;
        private String handle;
        private String onlineStoreUrl;
        private Integer totalVariants;
        private String status;
        private List<String> tags;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createdAt;
        private String productType;
        private ParentProductCategory category;
        private ParentProductMedia media;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public String getOnlineStoreUrl() {
            return onlineStoreUrl;
        }

        public void setOnlineStoreUrl(String onlineStoreUrl) {
            this.onlineStoreUrl = onlineStoreUrl;
        }

        public Integer getTotalVariants() {
            return totalVariants;
        }

        public void setTotalVariants(Integer totalVariants) {
            this.totalVariants = totalVariants;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public ParentProductCategory getCategory() {
            return category;
        }

        public void setCategory(ParentProductCategory category) {
            this.category = category;
        }

        public ParentProductMedia getMedia() {
            return media;
        }

        public void setMedia(ParentProductMedia media) {
            this.media = media;
        }
    }

    public static class ParentProductCategory {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ParentProductMedia {
        List<ParentProductMediaNode> nodes;

        public List<ParentProductMediaNode> getNodes() {
            return nodes;
        }

        public void setNodes(List<ParentProductMediaNode> nodes) {
            this.nodes = nodes;
        }
    }

    public static class ParentProductMediaNode {
        private ParentProductMediaPreview preview;

        public ParentProductMediaPreview getPreview() {
            return preview;
        }

        public void setPreview(ParentProductMediaPreview preview) {
            this.preview = preview;
        }
    }

    public static class ParentProductMediaPreview {
        private Image image;

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }
    }

    public static class Image {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class InventoryItem {
        private String id;
        private InventoryLevel inventoryLevels;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public InventoryLevel getInventoryLevels() {
            return inventoryLevels;
        }

        public void setInventoryLevels(InventoryLevel inventoryLevels) {
            this.inventoryLevels = inventoryLevels;
        }
    }

    public static class InventoryLevel {
        private List<InventoryLevelNode> nodes;

        public List<InventoryLevelNode> getNodes() {
            return nodes;
        }

        public void setNodes(List<InventoryLevelNode> nodes) {
            this.nodes = nodes;
        }
    }

    public static class InventoryLevelNode {
        private String id;
        private WarehouseLocation location;
        private List<InventoryQuantity> quantities;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public WarehouseLocation getLocation() {
            return location;
        }

        public void setLocation(WarehouseLocation location) {
            this.location = location;
        }

        public List<InventoryQuantity> getQuantities() {
            return quantities;
        }

        public void setQuantities(List<InventoryQuantity> quantities) {
            this.quantities = quantities;
        }
    }

    public static class WarehouseLocation {
        private String id;
        private String name;

        public String getId() {
            return StringTools.extractShopifyLastId(id);
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class InventoryQuantity {
        private String name;
        private Long quantity;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }
    }
}
