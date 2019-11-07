package com.hailu.cloud.common.exception;

/**
 * 业务异常
 *
 * @author zhijie
 */
public class AccessTokenExpiredException extends Exception {

    public AccessTokenExpiredException(String msg) {
        super(msg);
    }

    public AccessTokenExpiredException(String msg, Throwable e) {
        super(msg, e);
    }

}
