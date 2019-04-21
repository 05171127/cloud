package com.sunlong.cloud.servzuul.support;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.sunlong.cloud.servzuul.*;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * jt的过滤器
 * @author : shipp
 * @data : 2018/12/10 10:48
 */
public abstract class JtZuulFilter {

    // 认证服务
    private AuthService service;

    // context
    private RequestContext ctx;

    /**
     * @author shipp
     * @date 2018/12/10 14:42
     * @param
     * @return com.sunlong.cloud.servzuul.AuthService
     */
    public AuthService getService() {
        return service;
    }

    /**
     * @author shipp
     * @date 2018/12/10 14:42
     * @param service
     * @return void
     */
    public void setService(AuthService service) {
        this.service = service;
    }

    /**
     * @author shipp
     * @date 2018/12/10 14:45
     * @param 
     * @return com.netflix.zuul.context.RequestContext
     */
    public RequestContext getCtx() {
        return ctx;
    }
    
    /**
     * @author shipp
     * @date 2018/12/10 14:47
     * @param ctx
     * @return void
     */
    public void setCtx(RequestContext ctx) {
        this.ctx = ctx;
    }

    /**
     * 过滤
     * @author shipp
     * @date 2018/12/10 10:50
     * @return void
     */
    public abstract void doFilter();

    /**
     * 写ErrResponse
     * @author shipp
     * @date 2018/12/7 16:36
     * @param response
     * @param res
     * @return void
     */
    protected void writeErrResponse(HttpServletResponse response, GeneralResponse res) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            response.getWriter().write(JSON.toJSONString(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回type
     * @author shipp
     * @date 2018/12/10 11:54
     * @param
     * @return java.lang.Byte
     */
    public abstract Byte getFilterType();

    /**
     * 获取认证信息
     * @author shipp
     * @date 2018/12/10 14:
     * @param token
     * @return com.sunlong.cloud.servzuul.AuthenticateRes
     */
    protected AuthenticateRes getAuth(String token) {
        AuthenticateReq authenticateReq = new AuthenticateReq();
        authenticateReq.setToken(token);
        authenticateReq.setType(getFilterType());

        // 请求失败或异常
        GeneralResponse authRes = service.getAuth(authenticateReq);
        if (authRes == null || authRes.getCode().equals(TravelConstants.GENERAL_RESPONSE_CODE_FAILED) || authRes.getData() == null) {
            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(200);
            writeErrResponse(ctx.getResponse(), authRes);

            return null;
        }

        // 不继续向下走
        AuthenticateRes authenticateRes = (AuthenticateRes) authRes.getData();

        return authenticateRes;
    }

    /**
     * 拿到token
     * @author shipp
     * @date 2018/12/10 14:44
     * @param ctx
     * @return java.lang.String
     */
    protected String getToken(RequestContext ctx) {
        String token = ctx.getRequest().getHeader("token");
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = ctx.getRequest().getCookies();
            if (cookies != null && cookies.length != 0) {
                Map<String, Cookie> map = Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, p -> p));
                token = map.get("jt-id").getValue();
            }
        }

        return token;
    }
}
