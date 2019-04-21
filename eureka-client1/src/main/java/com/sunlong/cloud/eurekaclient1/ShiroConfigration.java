// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1;

import com.sunlong.cloud.eurekaclient1.auth.shiro.OtherCredentialMatcher;
import com.sunlong.cloud.eurekaclient1.auth.shiro.OtherLoginToken;
import com.sunlong.cloud.eurekaclient1.auth.shiro.OtherUserRealm;
import com.sunlong.cloud.eurekaclient1.auth.shiro.ShiroTokenFilterFactoryBean;
import com.sunlong.cloud.eurekaclient1.auth.shiro.filters.AppUserFilter;
import com.sunlong.cloud.eurekaclient1.auth.shiro.security.TokenSecurityManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

;


/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年12月21日
 */
//@Configuration
public class ShiroConfigration {
    
    @Bean(name="shiroFilter")
//    @Order(Integer.MAX_VALUE)
    public ShiroTokenFilterFactoryBean shiroFilter(@Qualifier("securityManager") TokenSecurityManager manager) {
        ShiroTokenFilterFactoryBean bean = new ShiroTokenFilterFactoryBean();
        bean.setSecurityManager(manager);
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("user", new AppUserFilter());
//        filters.put("header", new RequestHeaderFilter());
//        filters.put("auth", new AppAuthFilter());
//        filters.put("logout", new AppLogoutFilter());
        
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        
//        filterChainDefinitionMap.put("/order/notify", "anon");
//        filterChainDefinitionMap.put("/user/login", "header, anon");
//
//
//        filterChainDefinitionMap.put("/test/aa", "header, anon");
//        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/**", "user");
        
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        UserFilter.class
        bean.setFilters(filters);
        return bean;
    }

    @Bean(name="otherCredentialMatcher")
    public OtherCredentialMatcher otherCredentialMatcher() {
        OtherCredentialMatcher matcher = new OtherCredentialMatcher();

        return matcher;
    }

    @Bean(name="otherUserRealm")
    public OtherUserRealm otherUserRealm(@Qualifier("otherCredentialMatcher") CredentialsMatcher matcher) {
        OtherUserRealm otherRealm = new OtherUserRealm();
        otherRealm.setCredentialsMatcher(matcher);
        otherRealm.setAuthenticationTokenClass(OtherLoginToken.class);
        return otherRealm;
    }
    
  //配置核心安全事务管理器
    @Bean(name="securityManager")
    public TokenSecurityManager securityManager(@Qualifier("otherUserRealm") OtherUserRealm otherUserRealm) {
        
        TokenSecurityManager manager=new TokenSecurityManager();
        
        manager.setRealm(otherUserRealm);
        return manager;
    }
    
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
