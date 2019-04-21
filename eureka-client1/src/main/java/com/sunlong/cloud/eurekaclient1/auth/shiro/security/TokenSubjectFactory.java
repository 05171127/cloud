// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.security;

import com.sunlong.cloud.eurekaclient1.auth.model.RequestHeaderInfo;
import com.sunlong.cloud.eurekaclient1.auth.shiro.support.TokenDelegatingSubject;
import com.sunlong.cloud.eurekaclient1.auth.shiro.support.TokenSubjectContext;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年12月21日
 */
public class TokenSubjectFactory implements SubjectFactory {

    /* (non-Javadoc)
     * @see org.apache.shiro.mgt.SubjectFactory#createSubject(org.apache.shiro.subject.SubjectContext)
     */
    @Override
    public Subject createSubject(SubjectContext context) {
        // TODO shiro 这个地方要不要加异常判断
        TokenSubjectContext ctt = (TokenSubjectContext) context;
        SecurityManager securityManager = ctt.resolveSecurityManager();
        Session session = ctt.resolveSession();
        PrincipalCollection principals = ctt.resolvePrincipals();
        boolean authenticated = ctt.resolveAuthenticated();
        String host = ctt.resolveHost();
        ServletRequest request = ctt.getServletRequest();
        ServletResponse response = ctt.getServletResponse();
        RequestHeaderInfo header = ctt.getRequestHeader();

        return new TokenDelegatingSubject(principals, authenticated, host, session, securityManager, request, response, header);
    }

}
