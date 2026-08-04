package com.vmp.entity.apidto;

import java.util.List;

public class ShopifyqlQueryResponse {

    private ShopifyqlTableData tableData;
    private List<String> parseErrors;

    public ShopifyqlTableData getTableData() {
        return tableData;
    }

    public void setTableData(ShopifyqlTableData tableData) {
        this.tableData = tableData;
    }

    public List<String> getParseErrors() {
        return parseErrors;
    }

    public void setParseErrors(List<String> parseErrors) {
        this.parseErrors = parseErrors;
    }
}
