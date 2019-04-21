// ******************************************************************************
// Copyright (C) 2018 Kezhixing, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.support;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2018年6月8日
 */
public class TokenUser implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6577109560424359308L;

    // 用户编号
    private int id;

    // 昵称
    private String nickname;
    
    // 真实姓名
    private String realname;
    
    // 头像
    private String avatar;
    
    // 性别
    private Byte sex;
    
    // 生日
    private Date birthday;
    
    // openId
    private String openId;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname the realname to set
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the sex
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
}
