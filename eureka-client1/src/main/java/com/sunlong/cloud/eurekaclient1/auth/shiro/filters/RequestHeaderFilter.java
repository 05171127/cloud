// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.filters;

import com.sunlong.cloud.eurekaclient1.auth.model.RequestHeaderInfo;
import com.sunlong.cloud.eurekaclient1.auth.shiro.support.TokenDelegatingSubject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年12月22日
 */
public class RequestHeaderFilter extends PathMatchingFilter {
    
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
    }
    
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        TokenDelegatingSubject subject = (TokenDelegatingSubject) SecurityUtils.getSubject();
        return checkHeader(subject.getHeader());
    }
    
    private boolean checkHeader(RequestHeaderInfo header) {
//        if (CommonUtils.isNullOrEmpty(header.getAppName()) || CommonUtils.isNullOrEmpty(header.getCountry())
//                || CommonUtils.isNullOrEmpty(header.getLang()) || CommonUtils.isNullOrEmpty(header.getPlatform())
//                || CommonUtils.isNullOrEmpty(header.getVersion())) {
//            return false;
//        }
        
        if (!header.getAppName().equals("com.kzx.euler")) return false;
        
        if (!header.getPlatform().equals("applet")) return false;
        
        if (!header.getVersion().equals("1.0")) return false;
        
        return true;
    }
    
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        GeneralResponse res = new GeneralResponse();
//
//        res.setState("123");
//        res.setData("");
//        res.setMsg("header异常");
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html");
//        response.getWriter().write(mapper.writeValueAsString(res));
        return false;
    }
}
