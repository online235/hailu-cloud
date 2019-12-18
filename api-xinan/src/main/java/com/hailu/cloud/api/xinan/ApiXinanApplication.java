package com.hailu.cloud.api.xinan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableDiscoveryClient
@EnableScheduling
@MapperScan(basePackages = {"com.hailu.cloud.**.dao"})
@EnableFeignClients(basePackages = "com.hailu.cloud.**.feigns")
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiXinanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiXinanApplication.class, args);
    }

}
