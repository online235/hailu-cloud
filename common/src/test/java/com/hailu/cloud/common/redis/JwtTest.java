package com.hailu.cloud.common.redis;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @Author xuzhijie
 * @Date 2019/11/8 11:11
 */
@Slf4j
public class JwtTest {

    @Test
    public void t() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("hailu1688.com");
            String token = JWT.create()
                    .withIssuer("hailu")
                    .withClaim("type", "0")
                    .sign(algorithm);
            log.info(token);
            log.info(Base64.encode(token));
        } catch (JWTCreationException exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void t2() {
        try {
            String token = "ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SnBjM01pT2lKb1lXbHNkU0lzSW5SNWNHVWlPaUl3SW4wLldJb3JfSi1GVTdsek9iamxvY3Bna2ZHd1dUbnZNeUpYN0RrNXE4WHI4YmM=";
            token = Base64.decodeStr(token);
            try {
                Algorithm algorithm = Algorithm.HMAC256("hailu1688.com");
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("hailu")
                        .build(); //Reusable verifier instance
                DecodedJWT jwt = verifier.verify(token);
                System.out.println(jwt.getToken());
            } catch (JWTVerificationException exception){
                log.error(exception.getMessage(), exception);
            }
        } catch (JWTCreationException exception) {
            log.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void t3() throws BusinessException {
        String token = JwtUtil.createToken(IdUtil.simpleUUID(), 0);
        log.info(token);

        log.info("0");
        JwtUtil.verifierToken(token);
        log.info("1");
        JwtUtil.verifierToken(Base64.encode("eyJhbGciOiJIUzI1NiIsImxvZ2luVXNlclR5cGUiOjAsInR5cCI6IkpXVCJ9.eyJpc3MiOiJoYWlsdSIsImxvZ2luVXNlclR5cGUiOjB9.t6f6S0v1ZYu-iUfHNeUJtJPkHFOTCB-qwwhEvwtcBIE"));
        log.info("2");
        JwtUtil.verifierToken(Base64.encode("eyJhbGciOiJIUzI1NiIsImxvZ2luVXNlclR5cGUiOjAsInR5cCI6IkpXVCJ9.eyJpc3MiOiJoYWlsdSIsImxvZ2luVXNlclR5cGUiOjJ9.qpcwkmM3xzLuATgLvfUG2qw0_rLZI6t19ZyWA-e6Fyk"));
        log.info("3");
        JwtUtil.verifierToken(Base64.encode("eyJhbGciOiJIUzI1NiIsImxvZ2luVXNlclR5cGUiOjAsInR5cCI6IkpXVCJ9.eyJpc3MiOiJoYWlsdSIsImxvZ2luVXNlclR5cGUiOjJ9.pPAnj6TzlBNy-3xki8bLgMDxn_THAY8emesn8RutWcE"));


    }




}
