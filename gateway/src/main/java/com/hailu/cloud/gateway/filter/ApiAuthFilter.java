package com.hailu.cloud.gateway.filter;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponseEnum;
import com.hailu.cloud.common.security.AuthInfoParseTool;
import com.hailu.cloud.common.security.JwtUtil;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.gateway.config.RequestRangeCheck;
import jodd.util.Base64;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 接口认证拦截
 * 过滤拦截优先级如下：
 * 【mapping.auth.exclude.(startsWith|endsWith)】 -> 【token校验】 -> 【mapping.login-type.notAllow】
 *
 * @author zhijie
 */
@Slf4j
@Component
@ConfigurationProperties(value = "mapping.auth.exclude")
public class ApiAuthFilter implements GlobalFilter, Ordered {

    // region 排除对以下url的拦截

    /**
     * 以该url开头的
     */
    @Setter
    private List<String> startsWith;

    /**
     * 以该url结束的
     */
    @Setter
    private List<String> endsWith;

    @Autowired
    private RequestRangeCheck requestRangeCheck;

    @Value("${mapping.login-type.debug:false}")
    private boolean debug;

    // endregion

    // region 密钥

    @Value("${signature.public-key}")
    private String publicKey;

    @Value("${signature.private-key}")
    private String privateKey;

    // endregion

    // region client

    @Resource
    private RedisStandAloneClient redisClient;

    // endregion

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        Route route = (Route) exchange.getAttributes().get(Constant.GATEWAY_ROUTER_KEY);
        String serviceName = route.getId();
        String url = request.getURI().getPath();
        log.info("服务名:{}, 请求路径:{}", serviceName, url);

        if (ignore(url)) {
            return chain.filter(exchange);
        }

        // 检查是否携带Access-token请求头
        String accessToken = getAccessTokenFromRequestHeader(request);
        if (StringUtils.isBlank(accessToken)) {
            DataBuffer dataBuffer = RequestUtils.getDataBuffer(response, ApiResponseEnum.UN_AUTHORIZED);
            return response.writeWith(Mono.just(dataBuffer));
        }

        // 校验token是否有效
        AuthInfo authInfo = verifyToken(accessToken);
        if (authInfo == null) {
            DataBuffer dataBuffer = RequestUtils.getDataBuffer(response, ApiResponseEnum.ACCESS_TOKEN_EXPIRED);
            return response.writeWith(Mono.just(dataBuffer));
        }

        if (!debug) {
            // 判断当前登录用户是否允许访问该资源
            DataBuffer dataBuffer = requestRangeCheck.checkUrlAllow(authInfo, url, response);
            if (dataBuffer != null) {
                return response.writeWith(Mono.just(dataBuffer));
            }
            dataBuffer = requestRangeCheck.checkUrlPermission(authInfo, url, response);
            if (dataBuffer != null) {
                return response.writeWith(Mono.just(dataBuffer));
            }
        }

        // 添加到request中
        ServerHttpRequest serverHttpRequest = exchange
                .getRequest()
                .mutate()
                .header(Constant.REQUEST_HEADER_GATEWAY_USER_INFO, Base64.encodeToString(JSON.toJSONString(authInfo)))
                .header(Constant.JWT_LOGIN_TYPE, String.valueOf(authInfo.getLoginType()))
                .build();

        ServerWebExchange build = exchange
                .mutate()
                .request(serverHttpRequest)
                .build();
        return chain.filter(build);
    }

    // region 内部处理方法

    /**
     * 判断是否需要取消对该URL的拦截
     *
     * @param url 当前正在请求的URL
     * @return
     */
    private boolean ignore(String url) {
        if (this.startsWith != null) {
            for (String prefix : this.startsWith) {
                if (StringUtils.isBlank(prefix)) {
                    continue;
                }
                if (url.startsWith(prefix)) {
                    return true;
                }
            }
        }
        if (this.endsWith != null) {
            for (String suffix : this.endsWith) {
                if (StringUtils.isBlank(suffix)) {
                    continue;
                }
                if (url.endsWith(suffix)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查请求头中是否包含Access-token
     *
     * @param request http request
     * @return
     */
    private String getAccessTokenFromRequestHeader(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey(Constant.REQUEST_HEADER_ACCESS_TOKEN)) {
            return null;
        }
        List<String> tokens = request.getHeaders().get(Constant.REQUEST_HEADER_ACCESS_TOKEN);
        if (tokens == null) {
            return null;
        }
        return tokens.get(0);
    }

    /**
     * 验证token是否有效
     *
     * @param accessToken jwt token
     * @return
     */
    private AuthInfo verifyToken(String accessToken) {
        // 1. 验证token
        DecodedJWT tokenDecode = JwtUtil.verifierToken(accessToken);
        if (tokenDecode == null) {
            // 验证token失败
            return null;
        }
        // 2. 根据token获取授权信息
        String token = tokenDecode.getClaim(Constant.JWT_ACCESS_TOKEN).asString();
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + token;
        String redisUserInfoJsonValue = redisClient.stringGet(accessTokenRedisKey);
        if (StringUtils.isBlank(redisUserInfoJsonValue)) {
            // redis缓存已过期，说明token已失效
            return null;
        }
        // 转换成对应用户实体
        AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, tokenDecode);
        // 判断accessToken有效期
        Date current = new Date();
        Date expire = DateUtil.date(authInfo.getAccessTokenExpire());
        if (DateUtil.compare(expire, current) > 0) {
            return authInfo;
        }
        return authInfo;
    }

    // endregion

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}
