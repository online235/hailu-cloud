package com.hailu.cloud.api.auth.module.login.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuzhijie
 */
@FeignClient("service-api-admin")
public interface AdminAccountFeignClient {

    @GetMapping(Constant.API_VERSION + Constant.API_NAME_ADMIN + "/system/admin/search-account")
    ApiResponse<AdminLoginInfoModel> searchAccount(@RequestParam("account") String account);

}
