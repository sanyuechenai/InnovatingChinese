package com.yueworld.baseproject.api;

/**
 * 创建时间: 2018/7/9.
 * 创建人: Vincent Chen
 * 功能描述:
 */

public class ResultResponse<T> {

    public int code;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
