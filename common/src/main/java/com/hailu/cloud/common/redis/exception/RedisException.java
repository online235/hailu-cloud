package com.hailu.cloud.common.redis.exception;

/**
 * redis异常
 *
 * @author zhijie
 */
public class RedisException extends Exception {

    public RedisException(String msg) {
        super(msg);
    }

    public RedisException(String msg, Throwable e) {
        super(msg, e);
    }

}
