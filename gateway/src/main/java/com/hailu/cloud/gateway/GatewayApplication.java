package com.hailu.cloud.gateway;

import com.hailu.cloud.common.exception.GlobalExceptionHandler;
import com.hailu.cloud.common.response.ApiResponseWrapConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author zhijie
 */
@EnableConfigurationProperties
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableFeignClients(basePackages = "com.hailu.cloud.**.feigns")
@ComponentScan(
        basePackages = {"com.hailu.cloud"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        GlobalExceptionHandler.class,
                        ApiResponseWrapConfig.class
                })
        }
)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
