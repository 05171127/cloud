// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.support;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sunlong.cloud.eurekaclient1.auth.model.RequestHeaderInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.apache.shiro.web.subject.WebSubject;


/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年11月28日
 */
public class TokenDelegatingSubject extends DelegatingSubject implements WebSubject {
    
    private final ServletRequest servletRequest;
    private final ServletResponse servletResponse;
    
    // 请求头信息
    private RequestHeaderInfo header;

    /**
     * @param securityManager
     */
    public TokenDelegatingSubject(SecurityManager securityManager) {
        super(null, false, null, null, securityManager);
        this.servletRequest = null;
        this.servletResponse = null;
    }
    
    @Override
    public void login(AuthenticationToken token) throws AuthenticationException {
        Subject subject = securityManager.login(this, token);

        PrincipalCollection principals;

        String host = null;

        if (subject instanceof DelegatingSubject) {
            DelegatingSubject delegating = (DelegatingSubject) subject;
            //we have to do this in case there are assumed identities - we don't want to lose the 'real' principals:
            principals = delegating.getPrincipals();
            host = delegating.getHost();
        } else {
            principals = subject.getPrincipals();
        }

        if (principals == null || principals.isEmpty()) {
            String msg = "Principals returned from securityManager.login( token ) returned a null or " +
                    "empty value.  This value must be non null and populated with one or more elements.";
            throw new IllegalStateException(msg);
        }
        this.principals = principals;
        this.authenticated = true;
        if (token instanceof HostAuthenticationToken) {
            host = ((HostAuthenticationToken) token).getHost();
        }
        if (host != null) {
            this.host = host;
        }
        
        if (subject instanceof TokenDelegatingSubject) {
            this.header = ((TokenDelegatingSubject)subject).getHeader();
        }
        
    }
    
    /**
     * @param principals
     * @param authenticated
     * @param host
     * @param session
     * @param securityManager
     */
    public TokenDelegatingSubject(PrincipalCollection principals, boolean authenticated, String host,
                             Session session, SecurityManager securityManager) {
        this(principals, authenticated, host, session, false, securityManager, null, null);
    }

    /**
     * @param principals
     * @param authenticated
     * @param host
     * @param session
     * @param securityManager
     */
    public TokenDelegatingSubject(PrincipalCollection principals, boolean authenticated, String host, Session session,
            SecurityManager securityManager, ServletRequest request, ServletResponse response,
            RequestHeaderInfo header) {
        super(principals, authenticated, host, session, true, securityManager);
        this.header = header;
        this.servletRequest = request;
        this.servletResponse = response;
    }
    
    /**
     * @param principals
     * @param authenticated
     * @param host
     * @param session
     * @param sessionCreationEnabled
     * @param securityManager
     */
    public TokenDelegatingSubject(PrincipalCollection principals, boolean authenticated, String host, Session session,
            boolean sessionCreationEnabled, SecurityManager securityManager, ServletRequest request, ServletResponse response) {
        super(principals, authenticated, host, session, sessionCreationEnabled, securityManager);
        this.servletRequest = request;
        this.servletResponse = response;
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the header
     */
    public RequestHeaderInfo getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(RequestHeaderInfo header) {
        this.header = header;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.web.subject.WebSubject#getServletRequest()
     */
    @Override
    public ServletRequest getServletRequest() {
        return this.servletRequest;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.web.subject.WebSubject#getServletResponse()
     */
    @Override
    public ServletResponse getServletResponse() {
        return this.servletResponse;
    }
    
    public static class Builder extends WebSubject.Builder {

        /**
         * @param securityManager
         * @param request
         * @param response
         */
        public Builder(SecurityManager securityManager, ServletRequest request, ServletResponse response) {
            super(securityManager, request, response);
            // TODO Auto-generated constructor stub
        }
    }
}
