package com.hailu.cloud.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Author xuzhijie
 * @Date 2019/11/6 11:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

    // region redis key 前缀

    /**
     * 验证码前缀，用于保存验证码
     * 如：
     * VERICODE:13825693085
     * VERICODE:13825693086
     */
    public static final String REDIS_KEY_VERIFICATION_CODE = "VERICODE:";

    /**
     * 认证信息前缀，用于存储用户登录信息
     * 如：
     * AUTH_INFO:5775b772b5cb4206bdc74f3cb74eaaf3
     * AUTH_INFO:5f3b4d496062443b83feccb67d7733ac
     */
    public static final String REDIS_KEY_AUTH_INFO = "AUTH_INFO:";

    /**
     * redis key 7天有效期
     */
    public static final int REDIS_EXPIRE_OF_SEVEN_DAYS = 604800;

    /**
     * redis key 2小时有效期
     */
    public static final int REDIS_EXPIRE_OF_TWO_HOUR = 7200;

    /**
     * token存储，用于存储refreshToken与accessToken关联
     * 如：
     * TOKEN_STORE:5775b772b5cb4206bdc74f3cb74eaaf3
     * TOKEN_STORE:5f3b4d496062443b83feccb67d7733ac
     */
    public static final String REDIS_KEY_REFRESH_TOKEN_STORE = "TOKEN_STORE:";

    /**
     * 字典存储
     * 如：
     * DICT_CACHE:ORDER_STATUS
     * DICT_CACHE:TO_EXAMINE
     */
    public static final String REDIS_KEY_DICT_CACHE = "DICT_CACHE:";

    /**
     * 省市区字典Key值
     */
    public static final String REDIS_KEY_DICT_CACHE_NATION = "NATION";

    /**
     * 省市区字典描述值
     */
    public static final String REDIS_KEY_DICT_CACHE_NATION_DESC = "NATION_DESC";

    /**
     * 调用微信JS接口的临时票据
     */
    public static final String REDIS_KEY_WECHAT_TICKET = "WECHAT_TICKET:";

    /**
     * 微信公众号全局token
     */
    public static final String REDIS_KEY_WECHAT_PUBLIC_TOKEN = "WECHAT_GLOBAL_TOKEN:";

    /**
     * 省市区缓存
     */
    public static final String REDIS_NATION_CACHE = "redis:cache:nation:";

    /**
     * 分享购买服务商key
     */
    public static final String REDIS_INVITATION_MEMBER_POVIDER_CACHE = "cache:invitationMember:poviderUserId:";

    /**
     * 旧省市区
     */
    public static final String REDIS_NATION_CACHE_OLD = "redis:cache:old:nation:";

    // endregion

    // region request header

    /**
     * 网关设置到请求头里面
     */
    public static final String REQUEST_HEADER_GATEWAY_USER_INFO = "GATEWAY_USER_INFO";

    /**
     * 请求头里的Access-token
     */
    public static final String REQUEST_HEADER_ACCESS_TOKEN = "Access-token";

    /**
     * 请求头里的Content-Type
     */
    public static final String REQUEST_HEADER_CONTENT_TYPE = "Content-Type";

    // endregion

    // region request attribute

    /**
     * 网关设置到请求头里面
     */
    public static final String REQUEST_ATTRIBUTE_CURRENT_USER = "CURRENT_USER";

    // endregion

    // region jwt const

    /**
     * jwt 用户登录类型
     */
    public static final String JWT_LOGIN_TYPE = "LOGIN_USER_TYPE";

    /**
     * jwt 用户token
     */
    public static final String JWT_TOKEN = "TOKEN";

    // endregion

    // region gateway

    /**
     * gateway router key
     */
    public static final String GATEWAY_ROUTER_KEY = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute";

    // endregion

    // region 版本&服务名称

    public static final String API_VERSION_V2 = "/api/v2";
    public static final String API_NAME_BASIC = "/basic";
    public static final String API_NAME_ADMIN = "/admin";
    public static final String API_NAME_AUTH = "/auth";
    public static final String API_NAME_PAYMENT = "/payment";
    public static final String API_NAME_XINAN = "/xinan";
    public static final String API_NAME_MALL = "/mall";
    public static final String API_VERSION = API_VERSION_V2;

    // endregion

    // region 时分秒

    public static final int MINUTES = 60;
    public static final int HOUR = MINUTES * 60;
    public static final int DAY = HOUR * 24;

    /**
     * 银联支付订单头部
     */
    public static final String CHINAUMS_ORDER_HEADER = "6692";

    /**
     * 防止银联因网络问题多次回调
     */
    public static final String PAY_CALLBACK_AGAIN_TIME = "PAY_CALLBACK_AGAIN:";

    /**
     * 防止银联因网络问题多次回调  时间：1秒
     */
    public static final int PAY_CALLBACK_AGAIN_TIME_OUT = 60;

}
