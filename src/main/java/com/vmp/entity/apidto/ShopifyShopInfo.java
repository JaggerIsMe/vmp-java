package com.vmp.entity.apidto;

import com.vmp.utils.StringTools;

public class ShopifyShopInfo {

    private String id;

    private String name;

    private String currencyCode;

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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
