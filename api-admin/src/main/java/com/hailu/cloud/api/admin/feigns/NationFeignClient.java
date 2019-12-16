package com.hailu.cloud.api.admin.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/12 0012
 * @program: cloud
 * @create: 2019-12-12 18:
 */
@FeignClient("service-api-xinan")
public interface NationFeignClient {


    @GetMapping(Constant.API_VERSION + Constant.API_NAME_XINAN + "/nation/findCityNameByCode")
    ApiResponse<String> findCityNameByCode(
            @RequestParam("code") String code);
}
