// ******************************************************************************
// Copyright (C) 2018 Kezhixing, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2018年6月8日
 */
public class GeneralResponse {
    
    // 状态
    private Byte state;
    
    // 提示消息
    private String msg = "";
    
    // 返回对象
    private Object data = "";

    /**
     * @return the state
     */
    public Byte getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 
     */
    public GeneralResponse() {
        super();
    }

    /**
     * @param state
     * @param msg
     * @param data
     */
    public GeneralResponse(Byte state, String msg, Object data) {
        super();
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
}
