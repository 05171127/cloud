// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.support;

import com.sunlong.cloud.eurekaclient1.auth.model.RequestHeaderInfo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.apache.shiro.web.util.RequestPairSource;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年11月27日
 */
public class TokenSubjectContext extends DefaultSubjectContext implements RequestPairSource {
    
    private static final String REQUEST_HEADER = DefaultSubjectContext.class.getName() + ".REQUEST_HEADER";
    
    private static final String SERVLET_REQUEST = DefaultWebSubjectContext.class.getName() + ".SERVLET_REQUEST";
    private static final String SERVLET_RESPONSE = DefaultWebSubjectContext.class.getName() + ".SERVLET_RESPONSE";
    
    public RequestHeaderInfo getRequestHeader() {
        return getTypedValue(REQUEST_HEADER, RequestHeaderInfo.class);
    }

    public void setRequestHeader(RequestHeaderInfo header) {
        nullSafePut(REQUEST_HEADER, header);
    }
    
    public TokenSubjectContext() {
        
    }

    /**
     * @param subjectContext
     */
    public TokenSubjectContext(SubjectContext subjectContext) {
        super(subjectContext);
        if (!(subjectContext instanceof TokenSubjectContext)) return;
        TokenSubjectContext ctx = (TokenSubjectContext) subjectContext;
        
        this.setServletRequest(getRequest(ctx));
        this.setServletResponse(getResponse(ctx));
    }
    
    private ServletRequest getRequest(TokenSubjectContext ctx) {
        if (ctx.getServletRequest() != null) return ctx.getServletRequest();
        
        TokenDelegatingSubject subject = (TokenDelegatingSubject) ctx.getSubject();
        return subject.getServletRequest();
    }
    
    private ServletResponse getResponse(TokenSubjectContext ctx) {
        if (ctx.getServletResponse() != null) return ctx.getServletResponse();
        
        TokenDelegatingSubject subject = (TokenDelegatingSubject) ctx.getSubject();
        return subject.getServletResponse();
    }
    
    @Override
    public PrincipalCollection resolvePrincipals() {
        PrincipalCollection principals = getPrincipals();

        if (CollectionUtils.isEmpty(principals)) {
            //check to see if they were just authenticated:
            AuthenticationInfo info = getAuthenticationInfo();
            if (info != null) {
                principals = info.getPrincipals();
            }
        }

        if (CollectionUtils.isEmpty(principals)) {
            Subject subject = getSubject();
            if (subject != null) {
                principals = subject.getPrincipals();
            }
        }

        if (CollectionUtils.isEmpty(principals)) {
            // TODO get principals from redis
            RequestHeaderInfo header = getRequestHeader();
            String token = header.getToken();
//            if (!StringUtil.isEmpty(token)) {
//
//                // 没有必要对token进行验证
////                Jwts.parser().setSigningKey(JqConstants.JWT_TOKEN_KEY).parseClaimsJwt(token).getBody();
//
//                String tokenKey = RedisConstants.TOKEN_PREFIX + token;
//
//                byte[] tokenValue = JedisUtils.get(tokenKey.getBytes());
//
//                if (tokenValue == null)  return principals;
//
//                principals = (PrincipalCollection) SerializationUtils.deserialize(tokenValue);
//
//                // 生成lastAccess
//                String lastAccess = RedisConstants.TOKEN_LAST_ACCESS_TIME_PREFIX + token;
//
//                // 查询redis 判断是否过期
//                byte[] ret = JedisUtils.get(lastAccess.getBytes());
//
//                // 设置是否在有效期内
//                setAuthenticated(ret != null);
//
//                if (ret != null) {
//                    // 保存到redis，并设置30分钟有效期
//                    JedisUtils.setex(lastAccess, String.valueOf(System.currentTimeMillis()), KzxConstants.HALF_ONE_HOUR);
//                }
//            }
        }

        return principals;
    }

    /* (non-Javadoc)
     * @see java.util.Map#putAll(java.util.Map)
     */
    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.web.util.RequestPairSource#getServletRequest()
     */
    @Override
    public ServletRequest getServletRequest() {
        return getTypedValue(SERVLET_REQUEST, ServletRequest.class);
    }

    public void setServletRequest(ServletRequest request) {
        if (request != null) {
            put(SERVLET_REQUEST, request);
        }
    }
    /* (non-Javadoc)
     * @see org.apache.shiro.web.util.RequestPairSource#getServletResponse()
     */
    @Override
    public ServletResponse getServletResponse() {
        return getTypedValue(SERVLET_RESPONSE, ServletResponse.class);
    }

    public void setServletResponse(ServletResponse response) {
        if (response != null) {
            put(SERVLET_RESPONSE, response);
        }
    }
    
    @Override
    public String resolveHost() {
        String host = super.resolveHost();
        if (host == null) {
            ServletRequest request = resolveServletRequest();
            if (request != null) {
                host = request.getRemoteHost();
            }
        }
        return host;
    }
    
    public ServletRequest resolveServletRequest() {

        ServletRequest request = getServletRequest();

        //fall back on existing subject instance if it exists:
        if (request == null) {
            Subject existing = getSubject();
            if (existing instanceof WebSubject) {
                request = ((WebSubject) existing).getServletRequest();
            }
        }

        return request;
    }
    
    public ServletResponse resolveServletResponse() {

        ServletResponse response = getServletResponse();

        //fall back on existing subject instance if it exists:
        if (response == null) {
            Subject existing = getSubject();
            if (existing instanceof WebSubject) {
                response = ((WebSubject) existing).getServletResponse();
            }
        }

        return response;
    }
}
