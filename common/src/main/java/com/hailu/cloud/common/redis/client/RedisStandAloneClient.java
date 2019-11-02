package com.hailu.cloud.common.redis.client;

import com.hailu.cloud.common.redis.enums.RedisEnum;
import com.hailu.cloud.common.redis.exception.RedisException;
import com.hailu.cloud.common.redis.handle.IRedisConnectionHandle;
import com.hailu.cloud.common.redis.handle.IRedisSuccessHandle;
import com.hailu.cloud.common.redis.handle.IRedisSuccessResultHandle;
import com.hailu.cloud.common.thread.ThreadTool;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis工具库-单机模式
 *
 * @author xuzhijie
 */
@Slf4j
public class RedisStandAloneClient {

    // region 初始化

    /**
     * 主机
     **/
    @Setter
    private String hostname;

    /**
     * 端口
     **/
    @Setter
    private int port;

    /**
     * 密码
     **/
    @Setter
    private String password;

    /**
     * 最大闲置连接数
     */
    @Setter
    private int maxIdle;

    /**
     * 资源池确保最少空闲的连接数
     */
    @Setter
    private int minIdle;

    /**
     * 连接超时时间
     */
    @Setter
    private int connTimeout;

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
     * redis连接池
     **/
    private JedisPool jedisPool;

    /**
     * Redis连接池配置
     **/
    private JedisPoolConfig config;

    /**
     * 连接失败时最大重试次数，1秒重试一次
     */
    private int maxTry = 3;

    /**
     * 初始化配置
     */
    public void initConfigure() {
        config = new JedisPoolConfig();
        // 空闲的jedis实例, 默认值是5.
        config.setMaxIdle(this.maxIdle);
        // 可用连接实例的最大数目, 默认为8
        config.setMaxTotal(this.maxTotal);
        // 当资源池用尽后，调用者是否要等待。只有当为true时，下面的maxWaitMillis才会生效
        config.setBlockWhenExhausted(true);
        config.setMaxWaitMillis(this.maxWaitMillis);
        // 向资源池归还连接时是否做连接有效性检测(ping)，无效连接会被移除
        config.setTestOnReturn(true);
        // 向资源池借用连接时是否做连接有效性检测(ping)，无效连接会被移除
        config.setTestOnBorrow(true);
        initPool();
    }

    /**
     * 初始化连接池
     */
    private void initPool() {
        if (StringUtils.isEmpty(this.password)) {
            this.jedisPool = new JedisPool(this.config, this.hostname, this.port, this.connTimeout);
        } else {
            this.jedisPool = new JedisPool(this.config, this.hostname, this.port, this.connTimeout, this.password);
        }
    }

    /**
     * 获取jedis对象
     *
     * @param handle 获取连接回调
     */
    public void handle(IRedisConnectionHandle<Jedis> handle) {
        Jedis jedis = getConn();
        if (jedis == null) {
            handle.onError(new RedisException("获取redis连接失败！"));
            return;
        }
        try {
            handle.onSuccess(jedis);
        } catch (Exception e) {
            log.error("处理异常！", e);
        } finally {
            // 释放回连接池
            jedis.close();
        }
    }

    /**
     * 获取jedis对象
     *
     * @param handle 获取连接回调
     */
    public void handle(IRedisSuccessHandle<Jedis> handle) {
        Jedis jedis = getConn();
        if (jedis == null) {
            log.error("获取redis连接失败！");
            return;
        }
        try {
            handle.onSuccess(jedis);
        } catch (Exception e) {
            log.error("处理异常！", e);
        } finally {
            // 释放回连接池
            jedis.close();
        }
    }

    /**
     * 获取jedis对象，可能会返回null的情况，需要注意空指针异常
     *
     * @param handle 获取连接回调
     */
    public <R> R handle(IRedisSuccessResultHandle<Jedis, R> handle) {
        Jedis jedis = getConn();
        if (jedis == null) {
            return null;
        }
        try {
            return handle.onSuccess(jedis);
        } catch (Exception e) {
            return null;
        } finally {
            // 释放回连接池
            jedis.close();
        }
    }

    /**
     * 获取redis连接
     *
     * @return
     */
    private Jedis getConn() {
        Jedis jedis = null;
        // 最大尝试三次连接
        int maxTry = 3;
        int tryNum = 0;
        while (true) {
            try {
                if (tryNum > maxTry) {
                    log.error("获取redis连接失败！");
                    break;
                }
                if (this.jedisPool == null) {
                    // 如果为空则初始化
                    initPool();
                }
                if (this.jedisPool == null) {
                    // 经过初始化还是为空，一秒后重试
                    tryNum++;
                    log.warn("redis无法连接，1秒后重试。{}/" + maxTry, tryNum);
                    ThreadTool.sleep(1000);
                    continue;
                }

                jedis = this.jedisPool.getResource();
                if (jedis == null || !jedis.isConnected()) {
                    log.error("获取redis连接失败！");
                    break;
                }
                // 成功获取连接，默认选择0库
                jedis.select(RedisEnum.DB_0.ordinal());
                return jedis;
            } catch (Exception e) {
                // 休眠1秒后重试
                ThreadTool.sleep(1000);
            }
            tryNum++;
        }
        return jedis;
    }

    // endregion

    // region 设置过期

    /**
     * 设置redis过期时间
     *
     * @param jedis redis连接
     * @param key   redis key
     * @param time  过期时间
     * @return
     */
    public boolean expire(Jedis jedis, String key, int time) {
        try {
            jedis.expire(key, time);
            return true;
        } catch (Exception e) {
            log.error("redis设置过期时间异常:" + e.getMessage(), e);
        }
        return false;
    }

    /**
     * 设置redis过期时间
     *
     * @param key  redis key
     * @param time 过期时间
     * @return
     */
    public boolean expire(String key, int time) {
        return expire(RedisEnum.DB_0.ordinal(), key, time);
    }

    /**
     * 指定缓存失效时间
     *
     * @param db   db
     * @param key  redis key
     * @param time 过期时间
     * @return
     */
    public boolean expire(int db, String key, int time) {
        return handle(jedis -> {
            jedis.select(db);
            return expire(jedis, key, time);
        });
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
        return stringGet(RedisEnum.DB_0.ordinal(), key);
    }

    /**
     * 获取字符串
     *
     * @param db  redis db
     * @param key redis key
     * @return
     */
    public String stringGet(int db, String key) {
        return handle(jedis -> {
            jedis.select(db);
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
        return stringSet(RedisEnum.DB_0.ordinal(), key, value, time);
    }

    /**
     * 保存字符串
     *
     * @param db    redis db
     * @param key   redis key
     * @param value redis value
     * @param time  过期时间
     * @return
     */
    public Boolean stringSet(int db, String key, String value, int time) {
        return handle(jedis -> {
            jedis.select(db);
            jedis.set(key, value);
            if (time > 0) {
                // 设置过期时间
                expire(jedis, key, time);
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
        return hashGet(RedisEnum.DB_0.ordinal(), key, field);
    }

    /**
     * 从某个key的hash表里获取指定field的value
     *
     * @param db    redis db
     * @param key   redis key
     * @param field hash field
     * @return
     */
    public String hashGet(int db, String key, String field) {
        return handle(jedis -> {
            jedis.select(db);
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
        return hashSet(RedisEnum.DB_0.ordinal(), key, field, value, time);
    }

    /**
     * 往某个key的hash表添加一个key,value
     *
     * @param db    redis db
     * @param key   redis key
     * @param field hash field
     * @param value value
     * @param time  过期时间
     * @return
     */
    public Boolean hashSet(int db, String key, String field, String value, int time) {
        return handle(jedis -> {
            jedis.select(db);
            jedis.hset(key, field, value);
            if (time > 0) {
                return expire(jedis, key, time);
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
        return deleteKey(RedisEnum.DB_0.ordinal(), keys);
    }

    /**
     * 删除key
     *
     * @param db   redis db
     * @param keys redis key
     * @return
     */
    public Boolean deleteKey(int db, String... keys) {
        return handle(jedis -> {
            jedis.select(db);
            jedis.del(keys);
            return true;
        });
    }

    /**
     * 删除指定key对应的hash表里面的field
     *
     * @param db     redis db
     * @param key    redis key
     * @param fields hash fields
     * @return
     */
    public Boolean deleteHashField(int db, String key, String... fields) {
        return handle(jedis -> {
            jedis.select(db);
            jedis.hdel(key, fields);
            return true;
        });
    }

    /**
     * 删除指定key对应的hash表里面的field
     *
     * @param db     redis db
     * @param keys   redis keys
     * @param fields hash fields
     * @return
     */
    public Boolean deleteHashField(int db, String[] keys, String... fields) {
        return handle(jedis -> {
            jedis.select(db);
            for (String key : keys) {
                jedis.hdel(key, fields);
            }
            return true;
        });
    }

    // endregion
}
