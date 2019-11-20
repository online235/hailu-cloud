package com.hailu.cloud.api.merchant.clients;

import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-api-basic")
public interface UuidFeign {

    @GetMapping("/create")
    ApiResponse<Long> uuid();

}
