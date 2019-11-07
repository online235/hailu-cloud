package com.hailu.cloud.common.exception;

/**
 * 业务异常
 *
 * @author zhijie
 */
public class RefreshTokenExpiredException extends Exception {

    public RefreshTokenExpiredException(String msg) {
        super(msg);
    }

    public RefreshTokenExpiredException(String msg, Throwable e) {
        super(msg, e);
    }

}
