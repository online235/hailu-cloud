package com.hailu.cloud.common.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xuzhijie
 */
@FeignClient(name = "service-api-basic")
public interface UuidFeignClient {

    @GetMapping(Constant.API_VERSION_V1 + Constant.API_NAME_BASIC + "/uuid/create")
    ApiResponse<Long> uuid();

}
