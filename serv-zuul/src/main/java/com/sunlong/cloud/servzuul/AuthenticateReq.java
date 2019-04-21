package com.sunlong.cloud.servzuul;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/4 11:53
 */
@Getter
@Setter
public class AuthenticateReq {

    // token
    private String token;

    // 类型
    @NotNull
    @Range(min = 1, max = 2)
    private Byte type;
}
