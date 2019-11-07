package com.hailu.cloud.api.notify.config;

import com.hailu.cloud.common.filter.RequestHeaderUserInfoParseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xuzhijie
 * @Date 2019/11/7 14:24
 */
@Configuration
public class RequestConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        RequestHeaderUserInfoParseFilter filter = new RequestHeaderUserInfoParseFilter();
        FilterRegistrationBean register = new FilterRegistrationBean();
        register.setFilter(filter);
        register.addUrlPatterns("/*");
        register.setName(filter.getClass().getSimpleName());
        register.setOrder(1);
        return register;
    }

}
