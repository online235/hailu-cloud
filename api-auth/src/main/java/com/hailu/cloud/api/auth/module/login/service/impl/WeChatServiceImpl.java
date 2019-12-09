package com.hailu.cloud.api.auth.module.login.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.hailu.cloud.api.auth.module.login.dao.MemberMapper;
import com.hailu.cloud.api.auth.module.login.model.WeChatUserInfoResponse;
import com.hailu.cloud.api.auth.module.login.service.IWeChatLoginCallback;
import com.hailu.cloud.api.auth.module.login.service.IWeChatService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.enums.LoginTypeEnum;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.WeChatFeignClient;
import com.hailu.cloud.common.function.RFourParamFunction;
import com.hailu.cloud.common.function.RFunction;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.WeChatAuthResponse;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xuzhijie
 */
@Slf4j
@Service
public class WeChatServiceImpl implements IWeChatService {

    @Resource
    private RedisStandAloneClient redisClient;

    @Autowired
    private WeChatFeignClient weChatFeignClient;

    /**
     * 心安会员账号/商城会员账号
     */
    @Resource
    private MemberMapper memberMapper;

    @Value("${wechat.config.appId}")
    private String appId;

    @Value("${wechat.config.secret}")
    private String secret;

    @Override
    public Object login(Integer loginType, String code) throws BusinessException {
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.of(loginType);
        switch (loginTypeEnum) {
            case ADMIN:
                throw new BusinessException("暂时不支持管理员使用微信登录");
            case MERCHANT:
                throw new BusinessException("暂时不支持商户使用微信登录");
            case XINAN_AND_MALL:
                return weChatLoginHandle(loginType, code, xinAnLoginCallback());
            default:
                break;
        }
        throw new BusinessException("该微信用户被禁止登录");
    }

    private Object weChatLoginHandle(Integer loginType, String code, IWeChatLoginCallback callback) throws BusinessException {
        String weChatResponse = weChatFeignClient.login(this.appId, this.secret, "authorization_code", code);
        WeChatAuthResponse weChatLoginInfoModel = JSON.parseObject(weChatResponse, WeChatAuthResponse.class);
        if (StringUtils.isNotBlank(weChatLoginInfoModel.getErrCode())) {
            log.error("微信登录失败, {}", JSON.toJSONString(weChatLoginInfoModel));
            throw new BusinessException("微信登录失败");
        }
        // 获取微信用户信息
        String weChatUserInfoResponse = weChatFeignClient.userInfo(weChatLoginInfoModel.getAccessToken(), weChatLoginInfoModel.getOpenId());
        WeChatUserInfoResponse weChatUserInfoModel = JSON.parseObject(weChatUserInfoResponse, WeChatUserInfoResponse.class);
        if (StringUtils.isNotBlank(weChatUserInfoModel.getErrCode())) {
            log.error("微信登录失败, {}", JSON.toJSONString(weChatUserInfoModel));
            throw new BusinessException("微信登录失败");
        }
        // 检查是否绑定有平台账号
        callback.exists(weChatLoginInfoModel.getUnionId());

        // 生成认证信息
        String userId = callback.getUserId();
        return generateAuthInfo(
                loginType,
                userId,
                weChatLoginInfoModel,
                weChatUserInfoModel,
                callback::handle,
                callback::beforeReturnExcludeField);
    }

    private IWeChatLoginCallback xinAnLoginCallback() {

        return new AbstractWeChatLoginCallback() {

            private MemberLoginInfoModel loginInfoModel;

            @Override
            public boolean exists(String unionId) {
                loginInfoModel = memberMapper.findWeChatMember(unionId);
                boolean exists = loginInfoModel != null;
                if (loginInfoModel == null) {
                    loginInfoModel = new MemberLoginInfoModel();
                }else{
                    loginInfoModel.setBindWeChat(true);
                }
                return exists;
            }

            @Override
            public String getUserId() {
                return loginInfoModel.getUserId();
            }

            @Override
            public Object handle(
                    String accessToken,
                    String refreshToken,
                    WeChatAuthResponse weChatLoginInfoModel,
                    WeChatUserInfoResponse weChatUserInfoModel) {

                loginInfoModel.setWeChatAccessToken(weChatLoginInfoModel.getAccessToken());
                loginInfoModel.setWeChatRefreshToken(weChatLoginInfoModel.getRefreshToken());
                loginInfoModel.setWeChatOpenId(weChatLoginInfoModel.getOpenId());
                loginInfoModel.setWeChatUnionId(weChatLoginInfoModel.getUnionId());
                loginInfoModel.setWeChatNickname(weChatUserInfoModel.getNickname());
                loginInfoModel.setWeChatHeadImgUrl(weChatUserInfoModel.getHeadImgUrl());

                // 设置微信过期时间， 提前30秒给accessToken续期
                loginInfoModel.setWeChatExpiresIn(System.currentTimeMillis() + ((weChatLoginInfoModel.getExpiresIn() - 30) * 1000));

                loginInfoModel.setHasPwd(StringUtils.isNotBlank(loginInfoModel.getPwd()));
                loginInfoModel.setAccessToken(accessToken);
                loginInfoModel.setRefreshToken(refreshToken);
                return loginInfoModel;
            }

            @Override
            public Object beforeReturnExcludeField() {
                loginInfoModel.setWeChatRefreshToken(null);
                loginInfoModel.setWeChatAccessToken(null);
                loginInfoModel.setWeChatExpiresIn(null);
                loginInfoModel.setWeChatOpenId(null);
                loginInfoModel.setWeChatUnionId(null);
                loginInfoModel.setPwd(null);
                return loginInfoModel;
            }
        };
    }

    /**
     * 生成token相关信息
     *
     * @param loginType            登录类型
     * @param userId               用户ID
     * @param weChatLoginInfoModel 微信授权信息
     * @param weChatUserInfoModel  微信用户信息
     * @param callback
     * @return
     * @throws BusinessException
     */
    private Object generateAuthInfo(
            Integer loginType,
            String userId,
            WeChatAuthResponse weChatLoginInfoModel,
            WeChatUserInfoResponse weChatUserInfoModel,
            RFourParamFunction<String, String, WeChatAuthResponse, WeChatUserInfoResponse, Object> callback,
            RFunction excludeField) throws BusinessException {

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

        Object userInfo = callback.apply(
                authInfo.getAccessToken(),
                authInfo.getRefreshToken(),
                weChatLoginInfoModel,
                weChatUserInfoModel);

        authInfo.setUserInfo(userInfo);
        authInfo.setWeChatLogin(true);

        // 存储到redis,并设置有效期
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + refreshToken;
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        String authJson = JSON.toJSONString(authInfo);
        redisClient.stringSet(accessTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        redisClient.stringSet(refreshTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_SEVEN_DAYS);
        return excludeField.apply();
    }
}
