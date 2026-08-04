package com.vmp.entity.apidto;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ShopifyqlTableData {

    private List<ShopifyqlTableDataColum> colums;
    private List<JSONObject> rows;

    public List<ShopifyqlTableDataColum> getColums() {
        return colums;
    }

    public void setColums(List<ShopifyqlTableDataColum> colums) {
        this.colums = colums;
    }

    public List<JSONObject> getRows() {
        return rows;
    }

    public void setRows(List<JSONObject> rows) {
        this.rows = rows;
    }

    public static class ShopifyqlTableDataColum {
        private String name;
        private String dataType;
        private String displayName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

}
