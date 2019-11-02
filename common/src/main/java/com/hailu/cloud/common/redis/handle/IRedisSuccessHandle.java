package com.hailu.cloud.common.redis.handle;

import redis.clients.jedis.Jedis;

/**
 * redis成功后处理相关逻辑
 */
public interface IRedisSuccessHandle<T> {

    /**
     * 获取连接成功
     *
     * @param jedis
     */
    void onSuccess(T jedis);

}
