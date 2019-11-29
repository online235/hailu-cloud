package com.hailu.cloud.api.mall.module.payment.controller;

import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.common.utils.wechat.WechatUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付
 * @author Administrator
 */
@Slf4j
@Api(tags = "支付回调")
@Controller
@RequestMapping(value = "payment")
public class PaymentCallbackController {

    @Autowired
    private IOSSOrderService ossOrderService;

    /**
     * 支付成功 微信支付回调地址
     *
     * @returnN
     */
    @ResponseBody
    @RequestMapping(value = "/callback/weixin")
    public String weixinBack(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        log.info("微信回调开始");
        ossOrderService.callback(WechatUtil.weCatCallback(WechatUtil.xmlToMap(RequestUtils.getRequest().getInputStream())));
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";

    }

}
