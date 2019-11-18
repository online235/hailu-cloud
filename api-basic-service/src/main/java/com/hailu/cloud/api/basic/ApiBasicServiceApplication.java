package com.hailu.cloud.api.basic;

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
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.hailu.cloud.**.dao"})
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiBasicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBasicServiceApplication.class, args);
    }

}
