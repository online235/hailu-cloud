package com.hailu.cloud.common.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.enums.LoginTypeEnum;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * 认证信息解析工具
 *
 * @Author xuzhijie
 * @Date 2019/11/8 15:20
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthInfoParseTool {

    private static final Type MEMBER_MODEL_TYPE = new TypeToken<AuthInfo<MemberLoginInfoModel>>() {
    }.getType();

    private static final Type MC_USER_MODEL_TYPE = new TypeToken<AuthInfo<MerchantUserLoginInfoModel>>() {
    }.getType();

    private static final Type ADMIN_USER_MODEL_TYPE = new TypeToken<AuthInfo<AdminLoginInfoModel>>() {
    }.getType();

    /**
     * json 转 AuthInfo
     *
     * @param authInfoJsonValue
     * @param decodedJwt
     * @return
     */
    public static AuthInfo parse(String authInfoJsonValue, DecodedJWT decodedJwt) {
        return parse(authInfoJsonValue, decodedJwt.getClaim(Constant.JWT_LOGIN_TYPE).asInt());
    }

    public static AuthInfo parse(String authInfoJsonValue, int loginType) {
        AuthInfo authInfo = null;
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.of(loginType);
        switch (loginTypeEnum) {
            case XINAN_AND_MALL:
                // 解析authInfo
                authInfo = new Gson().fromJson(authInfoJsonValue, MEMBER_MODEL_TYPE);
                break;
            case MERCHANT:
                // 解析authInfo
                authInfo = new Gson().fromJson(authInfoJsonValue, MC_USER_MODEL_TYPE);
                break;
            case ADMIN:
                // 解析authInfo
                authInfo = new Gson().fromJson(authInfoJsonValue, ADMIN_USER_MODEL_TYPE);
                break;
            default:
                break;
        }
        return authInfo;
    }

    /**
     * 更新AuthInfo里面的userInfo信息
     *
     * @param authInfo
     * @param accessToken
     * @param refreshToken
     */
    public static void updateAuthInfoToken(AuthInfo authInfo, String accessToken, String refreshToken) {
        LoginTypeEnum loginType = LoginTypeEnum.of(authInfo.getLoginType());
        switch (loginType) {
            case XINAN_AND_MALL:
                if (StringUtils.isNotBlank(accessToken)) {
                    ((MemberLoginInfoModel) authInfo.getUserInfo()).setAccessToken(accessToken);
                }
                if (StringUtils.isNotBlank(refreshToken)) {
                    ((MemberLoginInfoModel) authInfo.getUserInfo()).setRefreshToken(accessToken);
                }
                break;
            case MERCHANT:
                if (StringUtils.isNotBlank(accessToken)) {
                    ((MerchantUserLoginInfoModel) authInfo.getUserInfo()).setAccessToken(accessToken);
                }
                if (StringUtils.isNotBlank(refreshToken)) {
                    ((MerchantUserLoginInfoModel) authInfo.getUserInfo()).setRefreshToken(accessToken);
                }
                break;
            case ADMIN:
                if (StringUtils.isNotBlank(accessToken)) {
                    ((AdminLoginInfoModel) authInfo.getUserInfo()).setAccessToken(accessToken);
                }
                if (StringUtils.isNotBlank(refreshToken)) {
                    ((AdminLoginInfoModel) authInfo.getUserInfo()).setRefreshToken(accessToken);
                }
                break;
            default:
                break;
        }
    }
}
