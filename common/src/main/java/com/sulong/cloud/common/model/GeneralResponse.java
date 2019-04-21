package com.sulong.cloud.common.model;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/27 15:57
 */
public class GeneralResponse<T> {

    private byte code;

    private String msg;

    private T data;

    public byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
