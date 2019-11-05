package com.hailu.cloud.common.redis.handle;

/**
 * redis获取成功并处理相关逻辑后返回结果
 *
 * @author zhijie
 */
public interface IRedisSuccessResultHandle<T, R> {

    /**
     * 获取连接成功
     *
     * @param jedis
     * @return R
     */
    R onSuccess(T jedis);

}
