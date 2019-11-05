package com.hailu.cloud.common.exception;

/**
 * 业务异常
 *
 * @author zhijie
 */
public class BusinessException extends Exception {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }

}
