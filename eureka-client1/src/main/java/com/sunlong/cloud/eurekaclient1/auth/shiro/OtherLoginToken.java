// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年12月28日
 */
public class OtherLoginToken implements AuthenticationToken {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 754461862507061120L;
    
    private String code;

    private String host;
    
    /**
     * @param type
     * @param code
     */
    public OtherLoginToken(String code) {
        super();
        this.code = code;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        return this.code;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

}
