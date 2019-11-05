package com.hailu.cloud.common.redis.lock.impl;

import com.hailu.cloud.common.redis.enums.RedisEnum;
import com.hailu.cloud.common.redis.lock.IRedisRedLock;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * redis锁-单机版
 *
 * @author zhijie
 */
@Slf4j
public class RedisStandAloneRedLock implements IRedisRedLock {

    /**
     * 分布式锁前缀
     */
    private static final String LOCK_NAME_PREFIX = "STAND_ALONE_RED_LOCK_";

    /**
     * redis host
     */
    @Setter
    private String hostname;

    /**
     * redis port
     */
    @Setter
    private int port = 6379;

    /**
     * 密码
     */
    @Setter
    private String password;

    private RedissonClient redissonClient;

    @Override
    public void initConfigure() throws Exception {
        if (StringUtils.isBlank(hostname)) {
            throw new Exception("redis hostname is null");
        }
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + this.hostname + ":" + this.port);
        if (StringUtils.isNoneBlank(this.password)) {
            // 如果有密码则设置密码
            config.useSingleServer().setPassword(this.password);
        }
        // 默认使用0库
        config.useSingleServer().setDatabase(RedisEnum.DB_0.ordinal());
        this.redissonClient = Redisson.create(config);
    }

    /**
     * 锁
     *
     * @param lockName 锁名称-确保唯一
     * @param callback 成功获取锁后回调
     * @return true 执行成功、 false执行失败
     */
    @Override
    public boolean lock(String lockName, Runnable callback) {
        RLock redLock = redissonClient.getLock(LOCK_NAME_PREFIX + lockName);
        boolean isLock = false;
        try {
            // 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。
            isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("获取锁失败");
        }
        if (isLock) {
            try {
                callback.run();
                return true;
            } catch (Exception e) {
                log.warn("业务执行异常", e);
                return false;
            }
        }
        // 释放锁
        redLock.unlock();
        return false;
    }

}
