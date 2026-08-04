package com.vmp.entity.apidto;

public class ShopifyApiResult<T> {

    /**
     * 自定义返回 数据结果集
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
