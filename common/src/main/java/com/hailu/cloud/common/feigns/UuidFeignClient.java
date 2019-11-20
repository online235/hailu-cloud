package com.hailu.cloud.common.feigns;

import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xuzhijie
 */
@FeignClient(name = "service-api-basic")
public interface UuidFeignClient {

    @GetMapping("/create")
    ApiResponse<Long> uuid();

}
