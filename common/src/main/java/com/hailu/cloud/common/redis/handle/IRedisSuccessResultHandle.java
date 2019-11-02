package com.hailu.cloud.common.redis.handle;

import redis.clients.jedis.Jedis;

/**
 * redis获取成功并处理相关逻辑后返回结果
 */
public interface IRedisSuccessResultHandle<T, R> {

    /**
     * 获取连接成功
     *
     * @param jedis
     */
    R onSuccess(T jedis);

}
