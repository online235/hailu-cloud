package com.hailu.cloud.common.response;

import lombok.Getter;

/**
 * api 接口响应码
 *
 * @author zhijie
 */
public enum ApiResponseEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 未登录或登录已失效
     */
    UN_AUTHORIZED(401, "未登录或登录已失效"),
    /**
     * 请求数据不存在
     */
    NOT_FOUND(404, "请求数据或资源不存在"),
    /**
     * 请求方式不允许，请检查API支持的Method：GET,POST,PUT,DELETE,HEAD,OPTIONS
     */
    METHOD_NOT_ALLOWED(405, "请求方式不允许"),
    /**
     * 请求超时,一般微服务里服务之间调用超时，或者该接口没有在规定时间内处理好数据
     */
    REQUEST_TIMEOUT(408, "请求超时"),
    /**
     * 服务器内部异常
     */
    SERVER_ERROR(500, "服务器出现了点小问题"),
    /**
     * 业务异常
     */
    ABNORMAL_PARAMETER(1000, "业务异常"),
    /**
     * Access-token已过期
     */
    ACCESS_TOKEN_EXPIRED(1001, "Access-token不存在或已过期"),
    /**
     * Refresh-token已过期
     */
    REFRESH_TOKEN_EXPIRED(1002, "Refresh-token不存在或已过期"),
    /**
     * 无访问权限
     */
    PERMISSION_DENIED(1003, "您没有访问该接口的权限"),
    /**
     * 参数校验异常
     */
    PARAMETER_CONSTRAINT_VIOLATION_ERROR(2000, "参数校验异常"),
    ;

    @Getter
    private int responseCode;

    @Getter
    private String resultMsg;

    ApiResponseEnum(int responseCode, String resultMsg) {
        this.responseCode = responseCode;
        this.resultMsg = resultMsg;
    }

}
