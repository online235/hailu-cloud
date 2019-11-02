package com.hailu.cloud.common.redis.lock.impl;

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
 * redis锁-基于redis cluster分布式锁
 */
@Slf4j
public class RedisDistributedRedLock implements IRedisRedLock {

    /**
     * 分布式锁前缀
     */
    private String lockNamePrefix = "DISTRIBUTED_RED_LOCK_";

    /**
     * redis host
     */
    @Setter
    private String[] nodeAddress;

    /**
     * 密码
     */
    @Setter
    private String password;

    private RedissonClient redissonClient;

    public void initConfigure() throws Exception {
        Config config = new Config();
        if (nodeAddress == null) {
            throw new Exception("redis cluster address is null");
        }
        config.useClusterServers().addNodeAddress(nodeAddress);
        if (StringUtils.isNoneBlank(this.password)) {
            // 如果有密码则设置密码
            config.useClusterServers().setPassword(this.password);
        }
        // 默认扫描频率
        config.useClusterServers().setScanInterval(5000);
        this.redissonClient = Redisson.create(config);
    }

    /**
     * 锁
     *
     * @param lockName 锁名称-确保唯一
     * @param callback 成功获取锁后回调
     * @return true 执行成功、 false执行失败
     */
    public boolean lock(String lockName, Runnable callback) {
        RLock redLock = redissonClient.getLock(this.lockNamePrefix + lockName);
        boolean isLock = false;
        try {
            // 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。
            isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("获取分布式锁失败", e);
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
