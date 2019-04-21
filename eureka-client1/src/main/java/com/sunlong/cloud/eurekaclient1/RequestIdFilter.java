package com.sunlong.cloud.eurekaclient1;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author : shipp
 * @description : 生成请求id filter
 * @data : 2018/10/10 18:56
 */
//@WebFilter(filterName = "requestIdFilter",urlPatterns = {"/*"})
public class RequestIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
//        RequestIdHolder.setId(UUID.randomUUID().toString().replace("-", ""));
        System.out.println("123");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
