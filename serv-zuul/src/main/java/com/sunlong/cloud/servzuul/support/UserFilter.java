package com.sunlong.cloud.servzuul.support;

import com.alibaba.fastjson.JSON;
import com.sunlong.cloud.servzuul.AuthenticateRes;
import com.sunlong.cloud.servzuul.GeneralResponse;
import com.sunlong.cloud.servzuul.TravelConstants;
import org.apache.commons.lang.StringUtils;

/**
 * userFilter
 * @author : shipp
 * @data : 2018/12/10 11:04
 */
public class UserFilter extends JtZuulFilter{

    @Override
    public void doFilter() {

        String token = getToken(getCtx());

        GeneralResponse noLoginRes = new GeneralResponse(TravelConstants.GENERAL_RESPONSE_CODE_NEED_LOGIN, "请先登录");

        // 无登录用户信息
        if (StringUtils.isEmpty(token)) {
            getCtx().setSendZuulResponse(false);
            writeErrResponse(getCtx().getResponse(), noLoginRes);
            return;
        }

        AuthenticateRes authRes = getAuth(token);
        if (authRes == null) return;

        // 不继续向下走
        if (authRes.getGoOn().equals(TravelConstants.NO)) {
            getCtx().setSendZuulResponse(false);

            writeErrResponse(getCtx().getResponse(), noLoginRes);
            return;
        }

        // 继续走 用户信息放请求头
        getCtx().getZuulRequestHeaders().put("user-info", JSON.toJSONString(authRes.getInfo()));
    }

    @Override
    public Byte getFilterType() {
        return TravelConstants.ACCESSOR_ROLE_USER;
    }
}
