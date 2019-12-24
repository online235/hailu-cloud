package com.hailu.cloud.common.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponse;
import com.hailu.cloud.common.response.ApiResponseEnum;
import com.hailu.cloud.common.security.AuthInfoParseTool;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * request工具类
 *
 * @author xuzhijie
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestUtils {

    /**
     * 获取request请求上下文
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response返回上下文
     * @return
     */
    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

    /**
     * 获取认证信息
     *
     * @return
     */
    public static AuthInfo getAuthInfo() {
        HttpServletRequest request = getRequest();
        AuthInfo authInfo = (AuthInfo) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        return authInfo;
    }

    /**
     * 获取心安&商城-当前登录账号
     *
     * @return
     */
    public static MemberLoginInfoModel getMemberLoginInfo() {
        HttpServletRequest request = getRequest();
        AuthInfo<MemberLoginInfoModel> authInfo = (AuthInfo<MemberLoginInfoModel>) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        return authInfo.getUserInfo();
    }

    /**
     * 获取商户-当前登录账号
     *
     * @return
     */
    public static MerchantUserLoginInfoModel getMerchantUserLoginInfo() {
        HttpServletRequest request = getRequest();
        AuthInfo<MerchantUserLoginInfoModel> authInfo = (AuthInfo<MerchantUserLoginInfoModel>) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        return authInfo.getUserInfo();
    }

    /**
     * 获取管理员-当前登录账号
     *
     * @return
     */
    public static AdminLoginInfoModel getAdminLoginInfo() {
        HttpServletRequest request = getRequest();
        AuthInfo<AdminLoginInfoModel> authInfo = (AuthInfo<AdminLoginInfoModel>) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        return authInfo.getUserInfo();
    }

    /**
     * 生成DataBuffer
     *
     * @param response     http response
     * @param responseEnum response enum
     * @return
     */
    public static DataBuffer getDataBuffer(ServerHttpResponse response, ApiResponseEnum responseEnum) {
        return getDataBuffer(response, responseEnum, responseEnum.getResultMsg());
    }

    /**
     * 生成DataBuffer
     *
     * @param response     http response
     * @param responseEnum response enum
     * @return
     */
    public static DataBuffer getDataBuffer(ServerHttpResponse response, ApiResponseEnum responseEnum, String message) {
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add(Constant.REQUEST_HEADER_CONTENT_TYPE, "application/json; charset=UTF-8");
        ApiResponse apiResponse = new ApiResponse(responseEnum);
        apiResponse.setMessage(message);
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.bufferFactory().wrap(JSON.toJSONString(apiResponse).getBytes());
    }


    /**
     * 验证token是否有效
     *
     * @param accessToken jwt token
     * @return
     */
    public static AuthInfo verifyToken(RedisStandAloneClient redisClient, String accessToken) {
        // 1. 验证token
        DecodedJWT tokenDecode = JwtUtil.verifierToken(accessToken);
        if (tokenDecode == null) {
            // 验证token失败
            return null;
        }
        // 2. 根据token获取授权信息
        String token = tokenDecode.getClaim(Constant.JWT_TOKEN).asString();
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
        // token已过期
        return null;
    }
}
