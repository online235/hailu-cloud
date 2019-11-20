package com.hailu.cloud.api.merchant.module;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhijie
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "api.admin hello";
    }

}
