package com.vmp.entity.apidto;

import java.util.List;

public class ShopifyOrderPageInfo {

    private List<ShopifyOrderApiDto> nodes;

    private ShopifyPageInfo pageInfo;

    public List<ShopifyOrderApiDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<ShopifyOrderApiDto> nodes) {
        this.nodes = nodes;
    }

    public ShopifyPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(ShopifyPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
