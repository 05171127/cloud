package com.sunlong.cloud.servzuul;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * 基本的返回
 * @author : shipp
 * @data : 2018/12/3 13:39
 */
@Getter
@Setter
public class GeneralResponse {

    // todo shipp 这个类用共通的返回对象就好
    // 返回的code
    private Byte code;

    // 返回的msg
    private String msg = "";

    // 返回的数据
    private Object data = "";

    public boolean isSss() {
        return true;
    }
    @JSONField(serialize = false)
    public boolean isSuccessful() {
        return TravelConstants.GENERAL_RESPONSE_CODE_SUCCESS.equals(this.code);
    }

    /**
     * contructor
     * @author shipp
     * @date 2018/12/3 16:22
     * @param 
     * @return 
     */
    public GeneralResponse() {
    }

    /**
     * contructor
     * @param code
     * @param msg
     */
    public GeneralResponse(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * contructor
     * @param code
     * @param msg
     * @param data
     */
    public GeneralResponse(Byte code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     * @author shipp
     * @date 2018/12/3 16:29
     * @param
     * @return com.jolly.travel.auth.general.GeneralResponse
     */
    public static GeneralResponse success() {
        return new GeneralResponse(TravelConstants.GENERAL_RESPONSE_CODE_SUCCESS, "request success");
    }

    /**
     * @author shipp
     * @date 2018/12/3 16:37
     * @param data
     * @return com.jolly.travel.auth.general.GeneralResponse
     */
    public static GeneralResponse success(Object data) {
        return new GeneralResponse(TravelConstants.GENERAL_RESPONSE_CODE_SUCCESS, "request success", data);
    }

    /**
     * 失败
     * @author shipp
     * @date 2018/12/3 16:29
     * @param 
     * @return com.jolly.travel.auth.general.GeneralResponse
     */
    public static GeneralResponse failed() {
        return new GeneralResponse(TravelConstants.GENERAL_RESPONSE_CODE_FAILED, "request failed");
    }
}
