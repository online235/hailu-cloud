package com.hailu.cloud.api.merchant.feigns;

import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@FeignClient("service-api-auth")
public interface AuthFeignClient {

    /**
     * 登录
     * @param loginType
     * @param account
     * @param pwd
     * @return
     */
    @GetMapping("/login/{loginType}")
    ApiResponse<MerchantUserLoginInfoModel> Login(
            @NotBlank(message = "登录类型不能为空")
            @Pattern(regexp = "^[012]$", message = "不支持的登录类型")
            @PathVariable("loginType") String loginType,
            @NotBlank(message = "登录账号不能为空") String account,
            @NotBlank(message = "密码不能为空") String pwd);

}
