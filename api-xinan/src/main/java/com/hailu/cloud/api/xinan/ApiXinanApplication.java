package com.hailu.cloud.api.xinan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiXinanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiXinanApplication.class, args);
    }

}
