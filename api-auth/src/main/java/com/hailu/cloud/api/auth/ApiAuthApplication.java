package com.hailu.cloud.api.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhijie
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.hailu.cloud.**.feigns")
@MapperScan(basePackages = {"com.hailu.cloud.**.dao"})
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAuthApplication.class, args);
    }

}
