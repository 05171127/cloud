// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro.security;

import com.sunlong.cloud.eurekaclient1.auth.model.RequestHeaderInfo;
import com.sunlong.cloud.eurekaclient1.auth.shiro.support.TokenSubjectContext;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.AuthorizingSecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年11月25日
 */
public class TokenSecurityManager extends AuthorizingSecurityManager {
    
    private static final Logger log = LoggerFactory.getLogger(TokenSecurityManager.class);

    protected SubjectFactory subjectFactory;
    
    public TokenSecurityManager() {
        super();
        this.subjectFactory = new TokenSubjectFactory();
    }

    /**
     * @return the subjectFactory
     */
    public SubjectFactory getSubjectFactory() {
        return subjectFactory;
    }

    /**
     * @param subjectFactory the subjectFactory to set
     */
    public void setSubjectFactory(SubjectFactory subjectFactory) {
        this.subjectFactory = subjectFactory;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.mgt.SecurityManager#login(org.apache.shiro.subject.Subject, org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    public Subject login(Subject subject, AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo info;
        try {
            info = authenticate(token);
        } catch (AuthenticationException ae) {
            throw ae;
        }
        
        Subject loggedIn = createSubject(token, info, subject);
        
//        DefaultSecurityManager.class;
        
        onSuccessfulLogin(token, info, loggedIn);
        return loggedIn;
    }
    
    /**   
     * @description 
     * @author shipp
     * @date 2017年11月27日
     * @param token
     * @param info
     * @param loggedIn    
     * @throws   
     */  
    private void onSuccessfulLogin(AuthenticationToken token, AuthenticationInfo info, Subject loggedIn) {
        // TODO 生成token并缓存起来
//        String jwt = Jwts.builder().claim("identity", UuidUtil.getUuid()).setIssuer(KzxConstants.JWT_TOKEN_ISSUER)
//                .setIssuedAt(new Date()).setExpiration(DateUtil.getOneMonthLater())
//                .setSubject(KzxConstants.JWT_TOKEN_SUBJECT)
//                .signWith(SignatureAlgorithm.HS512, KzxConstants.JWT_TOKEN_KEY).compact();
//
//        TokenUser user = (TokenUser) info.getPrincipals().getPrimaryPrincipal();
//        String userToken = RedisConstants.USER_TOKEN_CURRENT + user.getId();
//
//        // 删除原先已经存在的token
//        String preToken = JedisUtils.get(userToken);
//
//        if (preToken != null) {
//            String preTokenKey = RedisConstants.TOKEN_PREFIX + preToken;
//            String preLastAccess = RedisConstants.TOKEN_LAST_ACCESS_TIME_PREFIX + preToken;
//
//            String[] keys = new String[2];
//            keys[0] = preTokenKey;
//            keys[1] = preLastAccess;
//
//            JedisUtils.del(keys);
//        }
//
//        // 缓存token
//        String tokenKey = RedisConstants.TOKEN_PREFIX + jwt;
//        // 保存到redis，并设置一个月到期时间
//        JedisUtils.setex(tokenKey.getBytes(), SerializationUtils.serialize(info.getPrincipals()), KzxConstants.ONE_MONTH_SECONDS);
//
//        // 生成lastAccess
//        String lastAccess = RedisConstants.TOKEN_LAST_ACCESS_TIME_PREFIX + jwt;
//        // 保存到redis，并设置30分钟有效期
//        JedisUtils.setex(lastAccess, String.valueOf(System.currentTimeMillis()), KzxConstants.HALF_ONE_HOUR);
//
//        // 生成唯一值 做踢出动作
//
//
//        // 保存用户的唯一token，已踢出部分token
//
//        // TODO shipp tomorrow
//        JedisUtils.setex(userToken, jwt, KzxConstants.ONE_MONTH_SECONDS);
//
//        // 如果是tokenDelegatingSubject 把请求头信息设置进去
//        if (loggedIn instanceof TokenDelegatingSubject) {
//            RequestHeaderInfo header = ((TokenDelegatingSubject) loggedIn).getHeader();
//            if (header != null) header.setToken(jwt);
//        }
    }

    /**   
     * @description 
     * @author shipp
     * @date 2017年11月25日
     * @param token
     * @param info
     * @param existing
     * @return    
     * @throws   
     */  
    protected Subject createSubject(AuthenticationToken token, AuthenticationInfo info, Subject existing) {
        TokenSubjectContext context = createSubjectContext();
        context.setAuthenticated(true);
        context.setAuthenticationToken(token);
        context.setAuthenticationInfo(info);
        
        // TODO 不允许设置token 不知道是否会生效
        context.setSessionCreationEnabled(false);
        
        if (existing != null) {
            context.setSubject(existing);
        }
        return createSubject(context);
        
    }
    
    /**   
     * @description 
     * @author shipp
     * @date 2017年11月25日
     * @return    
     * @throws   
     */  
    protected TokenSubjectContext createSubjectContext() {
        return new TokenSubjectContext();
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.mgt.SecurityManager#logout(org.apache.shiro.subject.Subject)
     */
    @Override
    public void logout(Subject subject) {
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            if (log.isDebugEnabled()) {
                log.debug("Logging out subject with primary principal {}", principals.getPrimaryPrincipal());
            }
            Authenticator authc = getAuthenticator();
            if (authc instanceof LogoutAware) {
                ((LogoutAware) authc).onLogout(principals);
            }
            
            removeLoginCache(subject);
        }
    }
    
    /**   
     * @description 从缓存移除登陆信息
     * @author shipp
     * @date 2017年12月23日
     * @param subject    
     * @throws   
     */  
    private void removeLoginCache(Subject subject) {
//        TokenUser user = (TokenUser) subject.getPrincipal();
//        String userToken = RedisConstants.USER_TOKEN_CURRENT + user.getId();
//
//        String[] keys = new String[3];
//        keys[0] = userToken;
//        // 删除原先已经存在的token
//        String preToken = JedisUtils.get(userToken);
//
//        if (preToken != null) {
//            String preTokenKey = RedisConstants.TOKEN_PREFIX + preToken;
//            String preLastAccess = RedisConstants.TOKEN_LAST_ACCESS_TIME_PREFIX + preToken;
//
//            keys[1] = preTokenKey;
//            keys[2] = preLastAccess;
//        }
//        JedisUtils.del(keys);
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.mgt.SecurityManager#createSubject(org.apache.shiro.subject.SubjectContext)
     */
    @Override
    public Subject createSubject(SubjectContext subjectContext) {
        //create a copy so we don't modify the argument's backing map:
        TokenSubjectContext context = copy(subjectContext);

        //ensure that the context has a SecurityManager instance, and if not, add one:
        context = ensureSecurityManager(context);

        //Resolve an associated Session (usually based on a referenced session ID), and place it in the context before
        //ending to the SubjectFactory.  The SubjectFactory should not need to know how to acquire sessions as the
        //process is often environment specific - better to shield the SF from these details:
        
        // mark 原先的读取session的代码就在这里
//        context = resolveSession(context);
        context = resolveHeader(context);

        //Similarly, the SubjectFactory should not require any concept of RememberMe - translate that here first
        //if possible before handing off to the SubjectFactory:
        context = resolvePrincipals(context);
        
        
        // TODO shipp now
        Subject subject = doCreateSubject(context);

        //save this subject for future reference if necessary:
        //(this is needed here in case rememberMe principals were resolved and they need to be stored in the
        //session, so we don't constantly rehydrate the rememberMe PrincipalCollection on every operation).
        //Added in 1.2:
//        save(subject);

        return subject;
    }
    
    /**   
     * @description 
     * @author shipp
     * @date 2017年11月29日
     * @param context
     * @return    
     * @throws   
     */  
    private Subject doCreateSubject(TokenSubjectContext context) {
        
        return getSubjectFactory().createSubject(context);
    }

    /**   
     * @description 解析头部
     * @author shipp
     * @date 2017年11月27日
     * @param context
     * @return    
     * @throws   
     */  
    private TokenSubjectContext resolveHeader(TokenSubjectContext context) {
        
        // get request and response
        HttpServletRequest request = (HttpServletRequest) WebUtils.getRequest(context);
        HttpServletResponse response = (HttpServletResponse) WebUtils.getResponse(context);
        
        // get extra info from reqeust header
        System.out.println(request.getHeader("Content-Type"));
        String platform = request.getHeader("platform");
        String token = request.getHeader("token");
        String appName = request.getHeader("appname");
        String version = request.getHeader("v");
        String lang = request.getHeader("lang");
        String country = request.getHeader("country");
        
        RequestHeaderInfo header = new RequestHeaderInfo();
        header.setAppName(appName);
        header.setVersion(version);
        header.setToken(token);
        header.setPlatform(platform);
        header.setLang(lang);
        header.setCountry(country);
        
        context.setServletRequest(request);
        context.setServletResponse(response);
        
        context.setRequestHeader(header);
        
        return context;
    }

    protected TokenSubjectContext resolvePrincipals(TokenSubjectContext context) {

        PrincipalCollection principals = context.resolvePrincipals();

        if (CollectionUtils.isEmpty(principals)) {
            log.trace("No identity (PrincipalCollection) found in the context.  Looking for a remembered identity.");

            if (!CollectionUtils.isEmpty(principals)) {
                log.debug("Found remembered PrincipalCollection.  Adding to the context to be used " +
                        "for subject construction by the SubjectFactory.");

                context.setPrincipals(principals);

            } else {
                log.trace("No remembered identity found.  Returning original context.");
            }
        }
        
        return context;
    }

    protected TokenSubjectContext ensureSecurityManager(TokenSubjectContext context) {
        if (context.resolveSecurityManager() != null) {
            log.trace("Context already contains a SecurityManager instance.  Returning.");
            return context;
        }
        log.trace("No SecurityManager found in context.  Adding self reference.");
        context.setSecurityManager(this);
        return context;
    }
    
    /**   
     * @description 
     * @author shipp
     * @date 2017年11月25日
     * @param subjectContext
     * @return    
     * @throws   
     */  
    protected TokenSubjectContext copy(SubjectContext subjectContext) {
//        DefaultWebSubjectContext.class
        return new TokenSubjectContext(subjectContext);
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.SessionManager#start(org.apache.shiro.session.mgt.SessionContext)
     */
    @Override
    public Session start(SessionContext context) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.SessionManager#getSession(org.apache.shiro.session.mgt.SessionKey)
     */
    @Override
    public Session getSession(SessionKey key) throws SessionException {
        // TODO Auto-generated method stub
        return null;
    }

}
