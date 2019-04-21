package com.sunlong.cloud.servzuul.support;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.sunlong.cloud.servzuul.AuthenticateRes;
import com.sunlong.cloud.servzuul.GeneralResponse;
import com.sunlong.cloud.servzuul.TravelConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;

/**
 * AnonymousFilter
 * @author : shipp
 * @data : 2018/12/10 11:04
 */
public class AnonymousFilter extends JtZuulFilter{

    @Override
    public void doFilter() {
        return;
    }

    @Override
    public Byte getFilterType() {
        return TravelConstants.ACCESSOR_ROLE_TOURIST;
    }
}
