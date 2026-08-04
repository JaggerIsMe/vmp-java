package com.vmp.entity.apidto;

import java.util.List;

public class ShopifyProductPageInfo {

    private List<ShopifyProductApiDto> nodes;

    private ShopifyPageInfo pageInfo;

    public List<ShopifyProductApiDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<ShopifyProductApiDto> nodes) {
        this.nodes = nodes;
    }

    public ShopifyPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ShopifyPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
