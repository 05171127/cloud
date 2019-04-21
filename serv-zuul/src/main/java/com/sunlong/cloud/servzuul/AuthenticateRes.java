package com.sunlong.cloud.servzuul;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/4 13:24
 */
@Getter
@Setter
public class AuthenticateRes {

    // 是否继续走下去
    private Byte goOn;

    // jwt
    private String jwt = "";

    // 返回的数据
    private AccessorInfo info;

    public AuthenticateRes() {
    }
}
