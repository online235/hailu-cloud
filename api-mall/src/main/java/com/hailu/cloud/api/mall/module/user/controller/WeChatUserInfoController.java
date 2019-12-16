package com.hailu.cloud.api.mall.module.user.controller;

import com.hailu.cloud.common.feigns.PaymentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author leiqi
 * @Description 微信登录app或网页接口
 */
@Slf4j
@Controller
@RequestMapping(value = "api/user")
public class WeChatUserInfoController {

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    /**
     * 根据code获取微信用户信息
     *
     * @param
     * @param code
     * @return
     * @Author huangl
     */
    @RequestMapping(value = "/getInfoByCode")
    @ResponseBody
    public Map<String, Object> getInfoByCode(@RequestParam(value = "code") String code) {
        return paymentFeignClient.getInfoByCode(code).getData();
    }

}
