package com.hailu.cloud.common.redis.handle;

/**
 * redis成功后处理相关逻辑
 *
 * @author zhijie
 */
public interface IRedisSuccessHandle<T> {

    /**
     * 获取连接成功
     *
     * @param jedis
     */
    void onSuccess(T jedis);

}
