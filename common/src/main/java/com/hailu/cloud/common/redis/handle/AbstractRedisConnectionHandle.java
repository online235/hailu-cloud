package com.hailu.cloud.common.redis.handle;

import com.hailu.cloud.common.redis.handle.IRedisConnectionHandle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRedisConnectionHandle<T> implements IRedisConnectionHandle<T> {

    @Override
    public void onError(Throwable e) {
        log.error("获取redis连接失败！", e);
    }
}
