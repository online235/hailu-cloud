package com.hailu.cloud.api.auth.module.login.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hailu.cloud.api.auth.module.login.dao.MemberMapper;
import com.hailu.cloud.api.auth.module.login.model.MemberModel;
import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.AuthInfo;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author zhijie
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private RedisStandAloneClient redisClient;

    private Type memberModelType = new TypeToken<AuthInfo<MemberModel>>() {
    }.getType();

    /**
     * 心安会员账号/商城会员账号
     */
    @Resource
    private MemberMapper memberMapper;

    @Override
    public String refreshAccessToken(String refreshToken) throws BusinessException {
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + refreshToken;
        // 获取存储于redis中的用户信息
        String redisUserInfoJsonValue = redisClient.stringGet(refreshTokenRedisKey);
        if (StringUtils.isBlank(redisUserInfoJsonValue)) {
            // 如果accessToken已过期
            throw new BusinessException("RefreshToken已失效，请重新登录");
        }
        AuthInfo<MemberModel> authInfo = new Gson().fromJson(redisUserInfoJsonValue, memberModelType);
        // 更新accessToken
        Date current = new Date();
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        authInfo.setAccessToken(IdUtil.simpleUUID());
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());
        authInfo.getUserInfo().setAccessToken(authInfo.getAccessToken());
        // 重新保存回redis
        String authJson = JSON.toJSONString(authInfo);
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + authInfo.getAccessToken();
        redisClient.stringSet(refreshTokenRedisKey, authJson);
        redisClient.stringSet(accessTokenRedisKey, authJson);
        return authInfo.getAccessToken();
    }

    @Override
    public MemberModel login(String phone, String code) throws BusinessException {
        String vericodeRedisKey = Constant.REDIS_KEY_VERIFICATION_CODE + phone;
        String redisCode = redisClient.stringGet(vericodeRedisKey);
        if (!code.equals(redisCode)) {
            throw new BusinessException("验证码不正确或已过期");
        }
        MemberModel memberModel = memberMapper.findMember(phone);
        if (memberModel == null) {
            throw new BusinessException("该手机号码尚未注册");
        }
        // 生成认证信息存储于redis, accessToken有效期2小时， refreshToken有效期7天
        Date current = new Date();
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        Date refreshTokenExpire = DateUtil.offset(current, DateField.DAY_OF_MONTH, 7);
        AuthInfo<MemberModel> authInfo = new AuthInfo<>();
        authInfo.setUserId(memberModel.getUserId());
        authInfo.setAccessToken(IdUtil.simpleUUID());
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());
        authInfo.setRefreshToken(IdUtil.simpleUUID());
        authInfo.setRefreshTokenExpire(refreshTokenExpire.getTime());
        // 保存到redis
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + authInfo.getRefreshToken();
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + authInfo.getAccessToken();
        String authJson = JSON.toJSONString(authInfo);
        redisClient.stringSet(accessTokenRedisKey, authJson);
        redisClient.stringSet(refreshTokenRedisKey, authJson);
        return memberModel;
    }

    @Override
    public void logout(String refreshToken) {
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + refreshToken;
        String accessToken = redisClient.stringGet(refreshTokenRedisKey);
        if (StringUtils.isBlank(accessToken)) {
            // 不存在refresh直接返回
            return;
        }
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        redisClient.deleteKey(accessTokenRedisKey, refreshTokenRedisKey);
    }

}
