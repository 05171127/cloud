package com.sulong.cloud.common.model;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/27 15:41
 */
public class User {

    @NotNull
    @Min(1)
    private int id;

    @NotBlank
    @Length(max = 20, min = 5)
    private String name;

    @NotBlank
    @Length(max = 20, min = 5)
    private String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
