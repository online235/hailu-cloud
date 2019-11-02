package com.hailu.cloud.common.redis.client;

import com.hailu.cloud.common.redis.handle.IRedisConnectionHandle;
import com.hailu.cloud.common.redis.handle.IRedisSuccessHandle;
import com.hailu.cloud.common.redis.handle.IRedisSuccessResultHandle;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * redis工具库-redis cluster模式
 *
 * @author xuzhijie
 */
@Slf4j
public class RedisDistributedClient {

    // region 初始化

    /**
     * redis节点
     * ip:port
     */
    private List<String> redisNodes;

    /**
     * 资源池允许最大空闲的连接数
     */
    @Setter
    private int maxIdle;

    /**
     * 资源池确保最少空闲的连接数
     */
    @Setter
    private int minIdle;

    /**
     * 资源池中最大连接数
     */
    @Setter
    private int maxTotal;

    /**
     * 当资源池连接用尽后，调用者的最大等待时间(单位为毫秒)
     */
    @Setter
    private int maxWaitMillis;

    /**
     * Redis连接池配置
     **/
    private JedisPoolConfig config;

    private JedisCluster jedis;

    /**
     * 初始化配置
     */
    public void initConfigure() {
        // 添加redis node
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        this.redisNodes.forEach(redisNode -> {
            String[] ipPort = redisNode.split(":");
            hostAndPortsSet.add(new HostAndPort(ipPort[0], Integer.valueOf(ipPort[1])));
        });

        this.config = new JedisPoolConfig();
        this.config.setMinIdle(this.minIdle);
        // 空闲的jedis实例, 默认值是5.
        this.config.setMaxIdle(this.maxIdle);
        // 可用连接实例的最大数目, 默认为8
        this.config.setMaxTotal(this.maxTotal);
        // 当资源池用尽后，调用者是否要等待。只有当为true时，下面的maxWaitMillis才会生效
        this.config.setBlockWhenExhausted(true);
        this.config.setMaxWaitMillis(this.maxWaitMillis);
        // 向资源池归还连接时是否做连接有效性检测(ping)，无效连接会被移除
        this.config.setTestOnReturn(true);
        // 向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除
        this.config.setTestOnBorrow(true);
        this.jedis = new JedisCluster(hostAndPortsSet, this.config);
    }

    /**
     * 获取jedis对象
     *
     * @param handle 获取连接回调
     */
    public void handle(IRedisConnectionHandle<JedisCluster> handle) {
        try {
            handle.onSuccess(jedis);
        } catch (Exception e) {
            log.error("处理异常！", e);
        }
    }

    /**
     * 获取jedis对象
     *
     * @param handle 获取连接回调
     */
    public void handle(IRedisSuccessHandle<JedisCluster> handle) {
        try {
            handle.onSuccess(jedis);
        } catch (Exception e) {
            log.error("处理异常！", e);
        }
    }

    /**
     * 获取jedis对象，可能会返回null的情况，需要注意空指针异常
     *
     * @param handle 获取连接回调
     */
    public <R> R handle(IRedisSuccessResultHandle<JedisCluster, R> handle) {
        try {
            return handle.onSuccess(jedis);
        } catch (Exception e) {
            return null;
        }
    }


    // endregion

    // region 设置过期

    /**
     * 设置redis过期时间
     *
     * @param key  redis key
     * @param time 过期时间
     * @return
     */
    public boolean expire(String key, int time) {
        try {
            jedis.expire(key, time);
            return true;
        } catch (Exception e) {
            log.error("redis设置过期时间异常:" + e.getMessage(), e);
        }
        return false;
    }

    // endregion

    // region 字符串操作

    /**
     * 获取字符串
     *
     * @param key redis key
     * @return
     */
    public String stringGet(String key) {
        return handle(jedis -> {
            return jedis.get(key);
        });
    }

    /**
     * 保存字符串
     *
     * @param key   redis key
     * @param value redis value
     * @return
     */
    public Boolean stringSet(String key, String value) {
        return stringSet(key, value, 0);
    }

    /**
     * 保存字符串
     *
     * @param key   redis key
     * @param value redis value
     * @param time  过期时间
     * @return
     */
    public Boolean stringSet(String key, String value, int time) {
        return handle(jedis -> {
            jedis.set(key, value);
            if (time > 0) {
                // 设置过期时间
                expire(key, time);
            }
            return true;
        });
    }

    // endregion

    // region hash操作

    /**
     * 从某个key的hash表里获取指定field的value
     *
     * @param key   redis key
     * @param field hash field
     * @return
     */
    public String hashGet(String key, String field) {
        return handle(jedis -> {
            return jedis.hget(key, field);
        });
    }

    /**
     * 往某个key的hash表添加一个key,value
     *
     * @param key   redis key
     * @param field hash field
     * @param value value
     * @return
     */
    public Boolean hashSet(String key, String field, String value) {
        return hashSet(key, field, value, 0);
    }

    /**
     * 往某个key的hash表添加一个key,value
     *
     * @param key   redis key
     * @param field hash field
     * @param value value
     * @param time  过期时间
     * @return
     */
    public Boolean hashSet(String key, String field, String value, int time) {
        return handle(jedis -> {
            jedis.hset(key, field, value);
            if (time > 0) {
                return expire(key, time);
            }
            return true;
        });
    }

    // endregion

    // region 删除

    /**
     * 删除key
     *
     * @param keys redis key
     * @return
     */
    public Boolean deleteKey(String... keys) {
        return handle(jedis -> {
            jedis.del(keys);
            return true;
        });
    }

    /**
     * 删除指定key对应的hash表里面的field
     *
     * @param key    redis key
     * @param fields hash fields
     * @return
     */
    public Boolean deleteHashField(String key, String... fields) {
        return handle(jedis -> {
            jedis.hdel(key, fields);
            return true;
        });
    }

    /**
     * 删除指定key对应的hash表里面的field
     *
     * @param keys   redis keys
     * @param fields hash fields
     * @return
     */
    public Boolean deleteHashField(String[] keys, String... fields) {
        return handle(jedis -> {
            for (String key : keys) {
                jedis.hdel(key, fields);
            }
            return true;
        });
    }

    // endregion
}
