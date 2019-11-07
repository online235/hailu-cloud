package com.hailu.cloud.gateway.config;

import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhijie
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisStandAloneClient redisStandAloneClient(
            @Value("${redis.hostname:}") String hostname,
            @Value("${redis.port:}") String port,
            @Value("${redis.password:}") String password,
            @Value("${redis.conn-timeout:}") String connTimeout,
            @Value("${redis.max-idle:}") String maxIdle,
            @Value("${redis.min-idle:}") String minIdle,
            @Value("${redis.max-total:}") String maxTotal,
            @Value("${redis.max-wait-millis:}") String maxWaitMillis) {

        RedisStandAloneClient client = new RedisStandAloneClient();
        if (StringUtils.isNotBlank(hostname)) {
            client.setHostname(hostname);
        }
        if (StringUtils.isNotBlank(port)) {
            client.setPort(Integer.valueOf(port));
        }
        if (StringUtils.isNotBlank(password)) {
            client.setPassword(password);
        }
        if (StringUtils.isNotBlank(connTimeout)) {
            client.setConnTimeout(Integer.valueOf(connTimeout));
        }
        if (StringUtils.isNotBlank(maxIdle)) {
            client.setMaxIdle(Integer.valueOf(maxIdle));
        }
        if (StringUtils.isNotBlank(minIdle)) {
            client.setMinIdle(Integer.valueOf(minIdle));
        }
        if (StringUtils.isNotBlank(maxTotal)) {
            client.setMaxTotal(Integer.valueOf(maxTotal));
        }
        if (StringUtils.isNotBlank(maxWaitMillis)) {
            client.setMaxWaitMillis(Integer.valueOf(maxWaitMillis));
        }
        client.initConfigure();
        return client;
    }

}