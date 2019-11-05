package com.hailu.cloud.api.notify.module.sms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhijie
 */
@FeignClient(name = "sms-free", url = "${sms.free.server.address}")
public interface SmsFeignClient {

    /**
     * 发送短信
     *
     * @param sname
     * @param spwd
     * @param scorpid
     * @param sprdid
     * @param phone   手机号码
     * @param message 短信内容
     * @return
     */
    @GetMapping("/json/sms/g_Submit")
    String send(
            @RequestParam("sname") String sname,
            @RequestParam("spwd") String spwd,
            @RequestParam("scorpid") String scorpid,
            @RequestParam("sprdid") String sprdid,
            @RequestParam("sdst") String phone,
            @RequestParam("smsg") String message);

}
