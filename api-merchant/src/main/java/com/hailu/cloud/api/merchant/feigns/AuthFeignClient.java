package com.hailu.cloud.api.merchant.feigns;

import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-api-auth")
public interface AuthFeignClient {

    /**
     * 登录
     * @param loginType
     * @param phone
     * @param code
     * @return
     */
    @GetMapping("/login/vericode/{loginType}")
    ApiResponse<MerchantUserLoginInfoModel> vericodeLogin(
            @PathVariable("loginType") int loginType,
            @RequestParam("phone") String phone,
            @RequestParam("code") String code);

}
