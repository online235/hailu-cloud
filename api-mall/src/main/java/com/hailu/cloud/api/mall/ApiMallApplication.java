package com.hailu.cloud.api.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhijie
 */
@EnableCaching
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hailu.cloud.**.feigns")
@MapperScan("com.hailu.cloud.api.mall.module.*.dao")
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMallApplication.class, args);
    }

}
