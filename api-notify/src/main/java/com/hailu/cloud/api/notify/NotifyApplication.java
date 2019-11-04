package com.hailu.cloud.api.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhijie
 */
@SpringBootApplication(scanBasePackages = {"com.hailu.cloud"})
public class NotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifyApplication.class, args);
    }

}
