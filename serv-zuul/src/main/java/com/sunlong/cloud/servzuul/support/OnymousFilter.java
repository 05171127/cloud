package com.sunlong.cloud.servzuul.support;

import com.alibaba.fastjson.JSON;
import com.sunlong.cloud.servzuul.AuthenticateRes;
import com.sunlong.cloud.servzuul.GeneralResponse;
import com.sunlong.cloud.servzuul.TravelConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;

/**
 * 非匿名访问
 * @author : shipp
 * @data : 2018/12/10 14:58
 */
public class OnymousFilter extends JtZuulFilter {

    @Override
    public void doFilter() {
        String token = getToken(getCtx());

        AuthenticateRes authRes = getAuth(token);
        if (authRes == null) return;

        // 如果不继续走，只可能是认证用户的token 在缓存中找不到，概率很小
        if (TravelConstants.NO.equals(authRes.getGoOn())) {
            getCtx().setSendZuulResponse(false);

            GeneralResponse response = new GeneralResponse(TravelConstants.GENERAL_RESPONSE_CODE_NEED_LOGIN, "请先登录");
            writeErrResponse(getCtx().getResponse(), response);

            return;
        }

        // 如果jwt非空 写到cookie 无感记录身份
        if (!StringUtils.isEmpty(authRes.getJwt())) {
            Cookie cookie = new Cookie("jt-id", authRes.getJwt());
            cookie.setMaxAge(30 * 24 * 60 * 60);
            getCtx().getResponse().addCookie(cookie);
        }

        getCtx().getZuulRequestHeaders().put("user-info", JSON.toJSONString(authRes.getInfo()));
    }

    @Override
    public Byte getFilterType() {
        return null;
    }
}
