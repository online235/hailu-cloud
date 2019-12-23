package com.hailu.cloud.common.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuzhijie
 */
@FeignClient(name = "WeChat", url = "${wechat.server:}")
public interface WeChatFeignClient {

    /**
     * 微信登录
     *
     * @param appId
     * @param secret
     * @param grantType
     * @param code
     * @return
     */
    @GetMapping("/sns/oauth2/access_token")
    String login(
            @RequestParam("appid") String appId,
            @RequestParam("secret") String secret,
            @RequestParam("grant_type") String grantType,
            @RequestParam("code") String code);

    /**
     * 刷新微信accessToken
     *
     * @param appId
     * @param grantType
     * @param refreshToken
     * @return
     */
    @GetMapping("/sns/oauth2/refresh_token")
    String refreshToken(
            @RequestParam("appid") String appId,
            @RequestParam("grant_type") String grantType,
            @RequestParam("refresh_token") String refreshToken);

    /**
     * 获取微信用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    @GetMapping("/sns/userinfo")
    String userInfo(@RequestParam("access_token") String accessToken, @RequestParam("openid") String openId);

    /**
     * 获取微信临时票据
     *
     * @param accessToken
     * @param type
     * @return
     */
    @GetMapping("/cgi-bin/ticket/getticket")
    String getTicket(@RequestParam("access_token") String accessToken, @RequestParam("type") String type);

    /**
     * 公众号全局token
     *
     * @param appId
     * @param secret
     * @param grantType
     * @return
     */
    @GetMapping("/cgi-bin/token")
    String weChatPublicGlobalToken(
            @RequestParam("appid") String appId,
            @RequestParam("secret") String secret,
            @RequestParam("grant_type") String grantType);

}
