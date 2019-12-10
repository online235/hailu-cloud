package com.hailu.cloud.api.auth.module.login.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.hailu.cloud.api.auth.module.login.dao.OpenApiAccountMapper;
import com.hailu.cloud.api.auth.module.login.service.IOpenApiAuthService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.enums.LoginTypeEnum;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.function.RFunction;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.OpenApiLoginInfoModel;
import com.hailu.cloud.common.model.system.OpenApiAccountModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.function.BiFunction;

/**
 * @author xuzhijie
 */
@Slf4j
@Service
public class OpenApiAuthServiceImpl implements IOpenApiAuthService {

    @Resource
    private RedisStandAloneClient redisClient;

    @Resource
    private OpenApiAccountMapper openApiAccountMapper;

    @Override
    public OpenApiLoginInfoModel login(String appId, String appSecret) throws BusinessException {
        OpenApiAccountModel model = openApiAccountMapper.searchAccount(appId, appSecret);
        if (model == null) {
            throw new BusinessException("appId或appSecret不正确");
        }
        OpenApiLoginInfoModel loginInfoModel = new OpenApiLoginInfoModel();
        return generateAuthInfo(
                LoginTypeEnum.OPEN_API.ordinal(),
                String.valueOf(model.getId()),
                (accessToken, refreshToken) -> {
                    loginInfoModel.setAccessToken(accessToken);
                    loginInfoModel.setRefreshToken(refreshToken);
                    return loginInfoModel;
                },
                () -> {
                    loginInfoModel.setHasPwd(null);
                    return loginInfoModel;
                }
        );
    }

    /**
     * 生成token相关信息
     *
     * @param userId   用户ID
     * @param callback
     * @return
     * @throws BusinessException
     */
    private OpenApiLoginInfoModel generateAuthInfo(
            Integer loginType,
            String userId,
            BiFunction<String, String, OpenApiLoginInfoModel> callback,
            RFunction<OpenApiLoginInfoModel> excludeField) throws BusinessException {

        // 生成认证信息存储于redis, accessToken有效期2小时， refreshToken有效期7天
        Date current = new Date();
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUserId(userId);
        authInfo.setLoginType(loginType);

        String accessToken = IdUtil.simpleUUID();
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        authInfo.setAccessToken(JwtUtil.createToken(accessToken, loginType));
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());

        String refreshToken = IdUtil.simpleUUID();
        Date refreshTokenExpire = DateUtil.offset(current, DateField.DAY_OF_MONTH, 7);
        authInfo.setRefreshToken(JwtUtil.createToken(refreshToken, loginType));
        authInfo.setRefreshTokenExpire(refreshTokenExpire.getTime());

        Object userInfo = callback.apply(authInfo.getAccessToken(), authInfo.getRefreshToken());
        authInfo.setUserInfo(userInfo);

        // 存储到redis,并设置有效期
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + refreshToken;
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        String authJson = JSON.toJSONString(authInfo);
        redisClient.stringSet(accessTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        redisClient.stringSet(refreshTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_SEVEN_DAYS);
        return excludeField.apply();
    }

}
