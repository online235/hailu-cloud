package com.hailu.cloud.api.mall.module.user.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.PaymentFeignClient;
import com.hailu.cloud.common.feigns.WeChatFeignClient;
import com.hailu.cloud.common.model.auth.WeChatAuthResponse;
import com.hailu.cloud.common.model.auth.WeChatTicketModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 原商城接口：获取微信用户信息接口
 *
 * @author xuzhijie
 */
@Slf4j
@Controller
@RequestMapping(value = "api/user")
public class WeChatUserInfoController {

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @Autowired
    private WeChatFeignClient weChatFeignClient;

    @Value("${hailu.weixin.jsapi.appid}")
    private String appId;

    @Value("${hailu.weixin.jsapi.secret}")
    private String secret;

    private static final String TYPE_JS_API = "jsapi";

    @Autowired
    private RedisStandAloneClient redisClient;

    /**
     * 获取微信用户信息
     */
    @RequestMapping(value = "/getInfoByCode")
    @ResponseBody
    public Map<String, Object> getInfoByCode(@RequestParam(value = "code") String code) {
        return paymentFeignClient.getInfoByCode(code).getData();
    }

    /**
     * 获取微信jsAPI签名
     */
    @PostMapping(value = "/getJsApiSignature")
    @ResponseBody
    public Map<String, String> getJsApiSignature(String url) throws BusinessException {
        String globalAccessTokenKey = Constant.REDIS_KEY_WECHAT_PUBLIC_TOKEN + this.appId;
        String globalTokenInfo = redisClient.stringGet(globalAccessTokenKey);
        WeChatAuthResponse weChatLoginInfoModel;
        if (StringUtils.isBlank(globalTokenInfo)) {
            String weChatResponse = weChatFeignClient.weChatPublicGlobalToken(this.appId, this.secret, "client_credential");
            // 获取微信用户信息
            weChatLoginInfoModel = JSON.parseObject(weChatResponse, WeChatAuthResponse.class);
            if (StringUtils.isNotBlank(weChatLoginInfoModel.getErrCode())) {
                log.error("获取微信用户信息失败, {}", JSON.toJSONString(weChatLoginInfoModel));
                throw new BusinessException("获取微信用户信息失败");
            }
            // 两小时失效
            redisClient.stringSet(globalAccessTokenKey, JSON.toJSONString(weChatLoginInfoModel), Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        } else {
            weChatLoginInfoModel = JSON.parseObject(globalTokenInfo, WeChatAuthResponse.class);
        }
        // 判断是否存在票据信息
        String ticketKey = Constant.REDIS_KEY_WECHAT_TICKET + StringUtils.defaultString(weChatLoginInfoModel.getUnionId(), weChatLoginInfoModel.getOpenId());
        String redisTicket = redisClient.stringGet(ticketKey);
        if (StringUtils.isBlank(redisTicket)) {
            // 请求JSAPI临时票据信息
            String ticketResponse = weChatFeignClient.getTicket(weChatLoginInfoModel.getAccessToken(), TYPE_JS_API);
            WeChatTicketModel ticketModel = JSON.parseObject(ticketResponse, WeChatTicketModel.class);
            //errCode 为0代表请求成功
            if (!StringUtils.equals(ticketModel.getErrCode(), "0")) {
                log.error("获取JSAPI票据失败, {}", JSON.toJSONString(ticketModel));
                throw new BusinessException("获取JSAPI票据失败");
            }
            // 两小时失效
            redisClient.stringSet(ticketKey, ticketModel.getTicket(), Constant.REDIS_EXPIRE_OF_TWO_HOUR);
            redisTicket = ticketModel.getTicket();
        }
        long timestamp = System.currentTimeMillis() / 1000;
        SortedMap<String, String> params = new TreeMap<>();
        params.put("jsapi_ticket", redisTicket);
        params.put("noncestr", RandomUtil.randomNumbers(32));
        params.put("timestamp", String.valueOf(timestamp));
        params.put("url", url);
        String sign = SignUtil.createASCLLSign(params);
        params.put("signature", sign);
        // 移除前端使用不到的字段
        params.remove("jsapi_ticket");
        return params;
    }
}
