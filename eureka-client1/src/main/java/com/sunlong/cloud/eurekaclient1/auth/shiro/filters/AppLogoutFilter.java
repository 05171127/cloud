// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunlong.cloud.eurekaclient1.GeneralResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description 退出filter
 *
 * @author shipp
 *
 * @date 2017年12月23日
 */
public class AppLogoutFilter extends AdviceFilter {
    
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return false;
    }
    
    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        GeneralResponse res = new GeneralResponse();
        
        res.setState((byte)1);
        res.setData("");
        res.setMsg("退出成功");
        
        ObjectMapper mapper = new ObjectMapper();
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write(mapper.writeValueAsString(res));
    }
}
