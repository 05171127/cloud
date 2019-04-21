// ******************************************************************************
// Copyright (C) 2017 Sulong, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunlong.cloud.eurekaclient1.GeneralResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2016年7月12日
 */
public class AppUserFilter extends UserFilter {
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        // If principal is not null, then the user is known and should be allowed access.
        return subject.getPrincipal() != null;
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        GeneralResponse res = new GeneralResponse();
        
        res.setState((byte)3);
        res.setData("");
        res.setMsg("无法获取登录用户,请重新登录");
        
        ObjectMapper mapper = new ObjectMapper();
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().write(mapper.writeValueAsString(res));
        return false;
    }
}
