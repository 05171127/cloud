package com.sunlong.cloud.servzuul;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/4 10:12
 */
@Getter
@Setter
public class LoginReq {

    @NotEmpty
    @Length(min = 10, max = 50)
    private String name;

    @NotEmpty
    @Length(min = 8, max = 20)
    private String password;

    private String host;
}
