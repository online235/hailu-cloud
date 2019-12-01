package com.hailu.cloud.common.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuzhijie
 */
@FeignClient(name = "service-api-basic")
public interface BasicFeignClient {

    @GetMapping(Constant.API_VERSION + Constant.API_NAME_BASIC + "/uuid/create")
    ApiResponse<Long> uuid();

    @GetMapping(Constant.API_VERSION + Constant.API_NAME_BASIC + "/sms/send/free")
    ApiResponse send( @RequestParam("phone") String phone,@RequestParam("message") String message);
}