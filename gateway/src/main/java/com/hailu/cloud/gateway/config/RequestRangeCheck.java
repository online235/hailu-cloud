package com.hailu.cloud.gateway.config;

import com.hailu.cloud.common.enums.LoginTypeEnum;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.AuthInfo;
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
                return RequestUtils.getDataBuffer(response, ApiResponseEnum.ABNORMAL_PARAMETER, "您没有访问该接口的权限");
            }
        }
        return null;
    }

    /**
     * 检查当前登录用户是否有权限访问该URL
     */
    public DataBuffer checkUrlPermission(AuthInfo authInfo, String requestPath, ServerHttpResponse response) {
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.of(authInfo.getLoginType());
        switch (loginTypeEnum) {
            case XINAN_AND_MALL:
                // nothing to do
                break;
            case MERCHANT:
                // nothing to do
                break;
            case ADMIN:
                AdminLoginInfoModel adminLoginInfoModel = (AdminLoginInfoModel) authInfo.getUserInfo();
                if (adminLoginInfoModel.getMenus() == null || adminLoginInfoModel.getMenus().isEmpty()) {
                    return RequestUtils.getDataBuffer(response, ApiResponseEnum.ABNORMAL_PARAMETER, "您没有访问该接口的权限");
                }
                boolean allow = false;
                for (SysMenuModel menu : adminLoginInfoModel.getMenus()) {
                    if (requestPath.contains(menu.getUrl())) {
                        allow = true;
                        break;
                    }
                }
                if (!allow) {
                    return RequestUtils.getDataBuffer(response, ApiResponseEnum.ABNORMAL_PARAMETER, "您没有访问该接口的权限");
                }
                break;
            default:
                break;
        }
        return null;
    }

}
