package com.vmp.entity.po;

import java.io.Serializable;

public class AKResult<T> implements Serializable {

    private static final long serialVersionUID = -2915203243206447824L;
    /**
     * 自定义业务码
     */
    private String code;

    /**
     * 自定义业务提示说明
     */
    private String msg;

    /**
     * 自定义返回 数据结果集
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
