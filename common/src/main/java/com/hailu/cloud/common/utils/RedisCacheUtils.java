package com.hailu.cloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.AuthInfoParseTool;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xuzhijie
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisCacheUtils {

    public static void updateUserInfo(RedisStandAloneClient redisClient, MemberLoginInfoModel loginInfoModel) {

        try {
            if (redisClient == null) {
                return;
            }
            // 处理 accessToken
            {
                DecodedJWT tokenDecode = JwtUtil.verifierToken(loginInfoModel.getAccessToken());
                if (tokenDecode == null) {
                    return;
                }
                String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + tokenDecode.getClaim(Constant.JWT_TOKEN).asString();
                String redisUserInfoJsonValue = redisClient.stringGet(accessTokenRedisKey);
                if (StringUtils.isBlank(redisUserInfoJsonValue)) {
                    return;
                }
                AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, tokenDecode);
                authInfo.setUserInfo(loginInfoModel);

                String authJson = JSON.toJSONString(authInfo);
                redisClient.stringSet(accessTokenRedisKey, authJson);
            }
            // 处理 refreshToken
            {
                DecodedJWT tokenDecode = JwtUtil.verifierToken(loginInfoModel.getRefreshToken());
                if (tokenDecode == null) {
                    return;
                }
                String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + tokenDecode.getClaim(Constant.JWT_TOKEN).asString();
                String redisUserInfoJsonValue = redisClient.stringGet(refreshTokenRedisKey);
                if (StringUtils.isBlank(redisUserInfoJsonValue)) {
                    return;
                }
                AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, tokenDecode);
                authInfo.setUserInfo(loginInfoModel);

                String authJson = JSON.toJSONString(authInfo);
                redisClient.stringSet(refreshTokenRedisKey, authJson);
            }
        } catch (Exception e) {
            log.warn("无法更新redis缓存", e);
        }
    }

}
