package com.hailu.cloud.api.admin.config;

import com.hailu.cloud.common.filter.RequestHeaderUserInfoParseFilter;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author xuzhijie
 * @Date 2019/11/7 14:24
 */
@Configuration
public class RequestConfig {

    @Resource
    private RedisStandAloneClient redisClient;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        RequestHeaderUserInfoParseFilter filter = new RequestHeaderUserInfoParseFilter(redisClient);
        FilterRegistrationBean register = new FilterRegistrationBean();
        register.setFilter(filter);
        register.addUrlPatterns("/*");
        register.setName(filter.getClass().getSimpleName());
        register.setOrder(1);
        return register;
    }

}
