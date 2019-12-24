package com.hailu.cloud.api.mall.feigns;

import com.hailu.cloud.api.mall.module.multiindustry.model.McStoreFunctionModel;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户接口
 * @Date: 2019/12/23 0023
 * @program: cloud
 * @create: 2019-12-23 18:
 */
@FeignClient("service-api-merchant")
public interface MerChantFeignClient {


    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL + "/app/store-function/getStoreFunctionDetail")
    ApiResponse<McStoreFunctionModel> getStoreFunctionDetail(
            @RequestParam(value = "storeId") Long storeId);
}
