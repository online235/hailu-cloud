package com.hailu.cloud.gateway.filter;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.AuthInfo;
import com.hailu.cloud.common.model.MemberModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponse;
import com.hailu.cloud.common.response.ApiResponseEnum;
import jodd.util.Base64;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * 接口认证拦截
 *
 * @author zhijie
 */
@Slf4j
@Component
@ConfigurationProperties(value = "mapping.auth.exclude")
public class ApiAuthFilter implements GlobalFilter, Ordered {

    /**
     * Route Key
     **/
    private static final String ROUTE_KEY = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute";

    private static final String HEADER_ACCESS_TOKEN = "Access-token";

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

    // region gson

    private Type memberModelType = new TypeToken<AuthInfo<MemberModel>>() {
    }.getType();

    // endregion

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        Route route = (Route) exchange.getAttributes().get(ROUTE_KEY);
        String serviceName = route.getId();
        String url = request.getURI().getPath();
        log.info("服务名:{}, 请求路径:{}", serviceName, url);
        if (ignore(url)) {
            return chain.filter(exchange);
        }

        // 检查是否携带Access-token请求头
        String accessToken = getAccessTokenFromRequestHeader(request);
        if (StringUtils.isBlank(accessToken)) {
            DataBuffer dataBuffer = getDataBuffer(response, ApiResponseEnum.UN_AUTHORIZED);
            return response.writeWith(Mono.just(dataBuffer));
        }

        // 校验token是否有效
        AuthInfo<MemberModel> authInfo = verifyToken(accessToken);
        if (authInfo == null) {
            DataBuffer dataBuffer = getDataBuffer(response, ApiResponseEnum.ACCESS_TOKEN_EXPIRED);
            return response.writeWith(Mono.just(dataBuffer));
        }

        // 添加到request中
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().header(Constant.HEADER_GATEWAY_USER_INFO, Base64.encodeToString(JSON.toJSONString(authInfo))).build();
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(build);
    }

    // region response

    /**
     * 生成DataBuffer
     *
     * @param response
     * @param responseEnum
     * @return
     */
    private DataBuffer getDataBuffer(ServerHttpResponse response, ApiResponseEnum responseEnum) {
        return getDataBuffer(response, responseEnum, null);
    }

    /**
     * 生成DataBuffer
     *
     * @param response
     * @param responseEnum
     * @return
     */
    private DataBuffer getDataBuffer(ServerHttpResponse response, ApiResponseEnum responseEnum, String message) {
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        ApiResponse apiResponse = new ApiResponse(responseEnum);
        apiResponse.setMessage(message);
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.bufferFactory().wrap(JSON.toJSONString(apiResponse).getBytes());
    }

    // endregion

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
     * @param request
     * @return
     */
    private String getAccessTokenFromRequestHeader(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey(HEADER_ACCESS_TOKEN)) {
            return null;
        }
        List<String> tokens = request.getHeaders().get(HEADER_ACCESS_TOKEN);
        if (tokens == null) {
            return null;
        }
        return tokens.get(0);
    }

    /**
     * 验证token是否有效
     *
     * @param accessToken
     * @return
     */
    private AuthInfo<MemberModel> verifyToken(String accessToken) {
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        String redisUserInfoJsonValue = redisClient.stringGet(accessTokenRedisKey);
        if (StringUtils.isNotBlank(redisUserInfoJsonValue)) {
            AuthInfo<MemberModel> authInfo = new Gson().fromJson(redisUserInfoJsonValue, memberModelType);
            // 判断accessToken有效期
            Date current = new Date();
            Date expire = DateUtil.date(authInfo.getAccessTokenExpire());
            if (DateUtil.compare(expire, current) > 0) {
                return authInfo;
            }
        }
        return null;
    }

    // endregion

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

}
