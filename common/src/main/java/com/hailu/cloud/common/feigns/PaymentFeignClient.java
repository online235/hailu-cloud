package com.hailu.cloud.common.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.payment.PayRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 支付模块终端
 * @author junpei.deng
 */
@FeignClient(name = "service-api-payment")
public interface PaymentFeignClient {

    @PostMapping(Constant.API_VERSION_V1 + Constant.API_NAME_PAYMENT + "/payment/gateway")
    Map<String,Object> gateway(@RequestBody PayRequest payRequest);

}
