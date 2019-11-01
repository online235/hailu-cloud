package com.hailu.cloud.api.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class ApiMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMallApplication.class, args);
    }

}
