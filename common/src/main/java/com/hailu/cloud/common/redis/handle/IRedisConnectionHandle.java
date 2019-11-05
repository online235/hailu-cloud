package com.hailu.cloud.common.redis.handle;

/**
 * redis获取连接后处理相关逻辑
 *
 * @author zhijie
 */
public interface IRedisConnectionHandle<T> {

    /**
     * 获取连接成功
     *
     * @param jedis
     */
    void onSuccess(T jedis);

    /**
     * 获取连接失败
     *
     * @param e
     */
    void onError(Throwable e);
}
