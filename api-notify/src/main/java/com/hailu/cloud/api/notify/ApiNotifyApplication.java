package com.hailu.cloud.api.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhijie
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiNotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNotifyApplication.class, args);
    }

}
