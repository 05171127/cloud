package com.sunlong.cloud.servzuul;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sunlong.cloud.servzuul.support.JtZuulFilter;
import com.sunlong.cloud.servzuul.support.JtZuulFilterFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : shipp
 * @description :
 * @data : 2018/11/6 20:13
 */
@Component
public class MyFilter extends ZuulFilter {

    @Resource
    private AuthService service;

    @Resource
    private JtZuulFilterFactory filterFactory;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        //
        JtZuulFilter jtFilter = filterFactory.getFilter(req.getRequestURI());
        if (jtFilter == null) {
            ctx.setSendZuulResponse(false);
            GeneralResponse res = new GeneralResponse(TravelConstants.GENERAL_RESPONSE_CODE_FAILED, "系统异常");
            writeErrResponse(response, res);
            return null;
        }
        jtFilter.setCtx(ctx);
        jtFilter.setService(service);

        jtFilter.doFilter();

        return null;
    }

    /**
     * 写ErrResponse
     * @author shipp
     * @date 2018/12/7 16:36
     * @param response
     * @param res
     * @return void
     */
    private void writeErrResponse(HttpServletResponse response, GeneralResponse res) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            response.getWriter().write(JSON.toJSONString(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
