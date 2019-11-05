package com.hailu.cloud.api.auth.module.login.service.impl;

import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhijie
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private RedisStandAloneClient redisClient;

    @Override
    public String refreshAccessToken(String refreshToken) throws BusinessException {

        return null;
    }

    @Override
    public String login(String phone, String code) throws BusinessException {
        return null;
    }

    @Override
    public void logout(String accessToken) {
        redisClient.deleteKey(accessToken);
    }

}
