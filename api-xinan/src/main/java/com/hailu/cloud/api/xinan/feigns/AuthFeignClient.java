package com.hailu.cloud.api.xinan.feigns;

import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-api-auth")
public interface AuthFeignClient {

    /**
     * 验证码登录
     * @param loginType
     * @param phone
     * @param code
     * @return
     */
    @GetMapping("/login/vericode/{loginType}")
    ApiResponse<MerchantUserLoginInfoModel> vericodeLogin(
            @PathVariable("loginType") String loginType,
            @RequestParam("phone") String phone,
            @RequestParam("code") String code);

    /**
     * 登录
     * @param loginType
     * @param phone
     * @param pwd
     * @return
     */
    @GetMapping("/login/{loginType}")
    ApiResponse<MerchantUserLoginInfoModel> login(
            @PathVariable("loginType") String loginType,
            @RequestParam("phone") String phone,
            @RequestParam("pwd") String pwd);

}
