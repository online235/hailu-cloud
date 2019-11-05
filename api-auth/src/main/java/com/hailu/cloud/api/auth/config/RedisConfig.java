package com.hailu.cloud.api.auth.config;

import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
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
            @Value("${redis.hostname}") String hostname,
            @Value("${redis.port}") int port) {

        RedisStandAloneClient client = new RedisStandAloneClient();
        client.setHostname(hostname);
        client.setPort(port);
        client.initConfigure();
        return client;
    }

}