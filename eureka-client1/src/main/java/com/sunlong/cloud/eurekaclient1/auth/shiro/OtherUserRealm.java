// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * @description 
 *
 * @author shipp
 *
 * @date 2017年12月28日
 */
public class OtherUserRealm extends AuthorizingRealm {
    
//    @Resource
//    private IUserService userService;

    /* (non-Javadoc)
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (!(token instanceof OtherLoginToken)) throw new AccountException("incorrect token type");
        
        OtherLoginToken otherToken = (OtherLoginToken) token;
        
//        GeneralResponse response = userService.getUserByToken(otherToken.getCode(), otherToken.getHost());
//
//        // 过没有检索到
//        if (response == null || KzxConstants.GENERAL_RESPONSE_STATE_FALSE.equals(response.getState())
//                || response.getData() == null)
//            throw new UnknownAccountException();
//
//        User user = (User) response.getData();
//
//        TokenUser u = new TokenUser();
//        u.setId(user.getId());
//        u.setAvatar(user.getAvatar());
//        u.setBirthday(user.getBirthday());
//        u.setNickname(user.getNickname());
//        u.setRealname(user.getRealname());
//        u.setSex(user.getSex());
//        u.setOpenId(user.getAppletOpenId());
//
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(u, // 用户
//                null, // 密码
//                getName() // realm name
//        );

        return null;
    }

}
