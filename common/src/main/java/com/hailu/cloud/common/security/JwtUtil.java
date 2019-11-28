package com.hailu.cloud.common.security;

import cn.hutool.core.codec.Base64;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author xuzhijie
 * @Date 2019/11/8 11:24
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtil {

    private static final String SECRET = "hailu1688.com+";

    private static final String ISSUSER = "hailu";

    /**
     * 登录类型
     *
     * @param loginType 0 心安/商城， 1商户
     * @return
     * @throws BusinessException
     */
    public static String createToken(String realToken, int loginType) throws BusinessException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer(ISSUSER)
                    .withClaim(Constant.JWT_LOGIN_TYPE, loginType)
                    .withClaim(Constant.JWT_TOKEN, realToken)
                    .sign(algorithm);
            return Base64.encode(token);
        } catch (Exception exception) {
            throw new BusinessException("生成Token异常");
        }
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static DecodedJWT verifierToken(String token) {
        String tmpToken = Base64.decodeStr(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            return verifier.verify(tmpToken);
        } catch (Exception exception) {
            log.error("无效Token：{}", token);
        }
        return null;
    }

    /**
     * 提取jwt里面的token
     *
     * @param jwtToken
     * @return
     */
    public static String extractToken(String jwtToken, String key) {
        DecodedJWT decodedJwt = verifierToken(jwtToken);
        return decodedJwt == null ? null : decodedJwt.getClaim(key).asString();
    }

}
