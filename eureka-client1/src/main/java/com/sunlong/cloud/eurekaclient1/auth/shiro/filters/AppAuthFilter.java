// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunlong.cloud.eurekaclient1.GeneralResponse;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description 认证filter
 *
 * @author shipp
 *
 * @date 2017年12月22日
 */
public class AppAuthFilter extends AuthenticationFilter {

    /* (non-Javadoc)
     * @see org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        GeneralResponse res = new GeneralResponse();
        
        res.setState((byte)2);
        res.setData("");
        res.setMsg("无法获取登录用户,请重新登录");
        
        ObjectMapper mapper = new ObjectMapper();
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write(mapper.writeValueAsString(res));
        return false;
    }

}
