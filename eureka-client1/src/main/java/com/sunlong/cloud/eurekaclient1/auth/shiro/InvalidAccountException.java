// ******************************************************************************
// Copyright (C) 2017 Sulong, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @description 账号异常exception
 *
 * @author shipp
 *
 * @date 2017年11月25日
 */
public class InvalidAccountException extends AuthenticationException {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 910734715640510730L;

    /**
     * Creates a new InvalidAccountException.
     */
    public InvalidAccountException() {
        super();
    }

    /**
     * Constructs a new InvalidAccountException.
     *
     * @param message the reason for the exception
     */
    public InvalidAccountException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidAccountException.
     *
     * @param cause the underlying Throwable that caused this exception to be thrown.
     */
    public InvalidAccountException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidAccountException.
     *
     * @param message the reason for the exception
     * @param cause   the underlying Throwable that caused this exception to be thrown.
     */
    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "InvalidAccountException []";
    }
    
}
