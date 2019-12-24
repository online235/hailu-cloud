package com.hailu.cloud.api.payment.feigns;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 银联下单
 * @author junpei.deng
 */
@FeignClient(name = "chinaums-pay", url = "${chinaums.baseUrl}")
public interface ChinaumsFeignClient {

    /**
     * App下单支付
     * @return
     */
    @PostMapping("netpay-route-server/api/")
    String chinaumsWechatPay(
            JSONObject jsonObject
    );
}
