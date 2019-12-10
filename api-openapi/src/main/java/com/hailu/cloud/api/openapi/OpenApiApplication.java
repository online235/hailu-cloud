package com.hailu.cloud.api.openapi;

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
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class OpenApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiApplication.class, args);
    }

}
