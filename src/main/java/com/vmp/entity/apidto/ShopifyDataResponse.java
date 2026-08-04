package com.vmp.entity.apidto;

public class ShopifyDataResponse {

    private ShopifyShopInfo shop;

    private ShopifyProductPageInfo productVariants;

    private ShopifyOrderPageInfo orders;

    private ShopifyqlQueryResponse shopifyqlQuery;

    public ShopifyShopInfo getShop() {
        return shop;
    }

    public void setShop(ShopifyShopInfo shop) {
        this.shop = shop;
    }

    public ShopifyProductPageInfo getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(ShopifyProductPageInfo productVariants) {
        this.productVariants = productVariants;
    }

    public ShopifyOrderPageInfo getOrders() {
        return orders;
    }

    public void setOrders(ShopifyOrderPageInfo orders) {
        this.orders = orders;
    }

    public ShopifyqlQueryResponse getShopifyqlQuery() {
        return shopifyqlQuery;
    }

    public void setShopifyqlQuery(ShopifyqlQueryResponse shopifyqlQueryResponse) {
        this.shopifyqlQuery = shopifyqlQueryResponse;
    }
}
