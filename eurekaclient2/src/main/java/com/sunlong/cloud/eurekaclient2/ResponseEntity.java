package com.sunlong.cloud.eurekaclient2;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Robb
 * @date 2018/4/23 20:53
 */
@Getter
@Setter
public class ResponseEntity<T> implements Serializable {

    private String code;
    private String message;
    private T data;

    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isSuccessful() {
        return "0".equals(this.code);
    }



    public ResponseEntity success(String code, String msg) {
        this.code = code;
        this.message = msg;
        return this;
    }

    public ResponseEntity success(String code, String msg,T data) {
        this.code = code;
        this.message = msg;
        this.data=data;
        return this;
    }


    public ResponseEntity failure(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }

    public ResponseEntity failure(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}
