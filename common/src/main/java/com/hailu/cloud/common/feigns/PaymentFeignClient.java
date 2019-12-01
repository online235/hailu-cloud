package com.hailu.cloud.common.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.payment.PayRequest;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 支付模块终端
 * @author junpei.deng
 */
@FeignClient(name = "service-api-payment")
public interface PaymentFeignClient {

    @PostMapping(Constant.API_VERSION + Constant.API_NAME_PAYMENT + "/payment/gateway")
    ApiResponse<Map<String,Object>> gateway(@RequestBody PayRequest payRequest);

    @PostMapping(Constant.API_VERSION + Constant.API_NAME_PAYMENT + "/wechat/getInfoByCode")
    ApiResponse<Map<String, Object>> getInfoByCode(@RequestParam(value = "code") String code);
}
