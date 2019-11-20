package com.hailu.cloud.common.utils;

import com.alibaba.fastjson.JSON;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.response.ApiResponse;
import com.hailu.cloud.common.response.ApiResponseEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
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
}
