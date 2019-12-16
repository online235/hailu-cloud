package com.hailu.cloud.gateway.config;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.enums.LoginTypeEnum;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
import com.hailu.cloud.common.response.ApiResponseEnum;
import com.hailu.cloud.common.utils.RequestUtils;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author xuzhijie
 */
@Component
@ConfigurationProperties(value = "mapping.login-type")
public class RequestRangeCheck {

    /**
     * <登录类型,[允许访问路径]>
     */
    @Setter
    private Map<String, List<String>> notAllow;

    /**
     * 检查当前登录用户是否允许访问该URL
     */
    public DataBuffer checkUrlAllow(AuthInfo authInfo, String requestPath, ServerHttpResponse response) {
        String loginType = String.valueOf(authInfo.getLoginType());
        if (!notAllow.containsKey(loginType)) {
            return null;
        }
        for (String notAllowRequestPrefix : notAllow.get(loginType)) {
            if (requestPath.startsWith(notAllowRequestPrefix)) {
                return RequestUtils.getDataBuffer(response, ApiResponseEnum.PERMISSION_DENIED);
            }
        }
        return null;
    }

    /**
     * 检查当前登录用户是否有权限访问该URL
     */
    private static final String MERCHANT_PC_PATH = Constant.API_VERSION + "/merchant/merchant";

    public DataBuffer checkUrlPermission(AuthInfo authInfo, String requestPath, ServerHttpResponse response) {
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.of(authInfo.getLoginType());
        switch (loginTypeEnum) {
            case XINAN_AND_MALL:
                // nothing to do
                break;
            case MERCHANT:
                MerchantUserLoginInfoModel merchantLoginInfoModel = (MerchantUserLoginInfoModel) authInfo.getUserInfo();
                if (merchantLoginInfoModel.getAccounttype() != 2) {
                    if (requestPath.startsWith(MERCHANT_PC_PATH)) {
                        // 如果商户类型不是百货，但是却访问百货商户的接口数据，直接拒绝
                        return RequestUtils.getDataBuffer(response, ApiResponseEnum.PERMISSION_DENIED);
                    }
                }
                break;
            case ADMIN:
                AdminLoginInfoModel adminLoginInfoModel = (AdminLoginInfoModel) authInfo.getUserInfo();
                if (adminLoginInfoModel.getApis() == null || adminLoginInfoModel.getApis().isEmpty()) {
                    return RequestUtils.getDataBuffer(response, ApiResponseEnum.PERMISSION_DENIED);
                }
                boolean allow = false;
                for (String api : adminLoginInfoModel.getApis()) {
                    if (requestPath.contains(api.trim())) {
                        allow = true;
                        break;
                    }
                }
                if (!allow) {
                    return RequestUtils.getDataBuffer(response, ApiResponseEnum.PERMISSION_DENIED);
                }
                break;
            default:
                break;
        }
        return null;
    }

}
