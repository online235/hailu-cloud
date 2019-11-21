package com.hailu.cloud.api.mall.module.common.enums;

/**
 * 业务编码类型
 */
public enum BusinessCode {

    SUCCESS(0, "成功"),
    ERROR(1, "请勿频繁操作"),

    USER_NOT_LOGIN(999, "用户未登录或登录超时"),
    /**
     * 101~200 系统错误
     */
    UNKNOWN_ERROR(101, "未知错误"),
    DATABASE_FAILURE(102, "数据库异常"),
    SYSTEM_ERROR(103, "系统繁忙,请稍后再试!"),
    BASE_PARAM_EMPTY(104, "接口版本参数为空"),
    ILLEGAL_ACCESS(105, "不合法的用户请求"),

    /**
     * 201~300 业务公共错误
     */
    PARAM_ERROR(201, "参数错误"),
    INFO_NOT_EXISTS(202, "未获取到数据"),
    SIGN_ERROR(203, "签名错误"),
    STATUS_NOT_CORRENT(204, "数据状态不正确"),
    DATA_VERSION_ERROR(205, "数据版本问题"),
    MOBILE_ERROR(206, "手机号码格式错误"),
    CODE_NOT_EXISTS(207, "无效的验证码"),
    CODE_EXPIRE(208, "验证码已过期"),
    CODE_ERROR(209, "验证码输入错误"),
    CODE_ERROR_LIMIT(210, "验证码超过最大错误次数"),
    CODE_SEND_FAIL(211, "验证码发送失败"),
    USER_STOCK_EXISTS(212, "自选股中已存在"),
    USER_ALREADY_SIGN(213, "今天已经签到"),
    INVOICE_NOT_NULL(214, "该订单已有发票"),

    /**
     * 301~400 自定义错误
     */
    GOODS_NUM_NO(301, "商品库存不足"),
    ACT_NOSTART_OREND(302, "活动已结束"),
    LIMIT_NUMBER(303, "超出限购数量"),
    COUPON_PAST_DUE(304, "优惠卷已过期"),
    ACTIVITY_PAST_DUE(305, "活动已过期"),
    BALANCE(402, "余额不足"),
    TO_EXAMINE(309,"未填写信息"),
    PARENTID_EXAMINE(310,"父类ID不能为空"),
    EXISTENCE_INFORMATION(311,"信息已存在"),
    PASSWORD_CONSISTENCY(312,"密码与未更改前一致"),

    /**
     * 我增加一个新的 , 但是在用户登入了的重置密码都需要调用 , 像支付密码 登入密码
     * 801~999 用户模块错误
     */
    USER_NOT_EXISTS(801, "用户不存在"),
    LOGIN_PWD_ERROR(802, "登录账户或密码错误"),
    OLD_LOGIN_PWD_ERROR(803, "原登录密码错误"),
    LOGIN_PWD_LOCK(804, "登录密码输入错误次数已大于最大"),
    PAY_PWD_ERROR(805, "交易密码错误"),
    PAY_PWD_LOCK(806, "交易密码输入错误次数已大于最大"),
    USER_EXISTS(807, "用户已存在"),
    REFFER_NOT_EXIST(808, "推荐人不存在"),
    NOT_BINDING(809, "账户没有绑定"),
    PAY_PWD_EMPTY(810, "未设置支付密码"),
    IDENTITY_ERROR(811, "用户身份证信息不匹配"),
    REGISTER_NOT_OPEN(812, "暂未开放注册"),
    USER_NEED_PRIVILEGE(813, "需要相关特权,请到查看特权详情"),
    USER_NOT_MOBILE(814, "手机号码与当前用户不匹配"),
    WE_CHAT_BINDED(815, "微信已绑定过用户"),
    WE_CHAT_G(816, "请先绑定微信"),
    /**
     * 1000~1100 商城错误
     */
    GOODS_NOT_EXISTS(1001, "商品不存在"),
    GOODS_ORDER_NOT_EXISTS(1002, "商品订单不存在"),
    GOODS_ORDER_PAY_SUCCESS(1003, "商品订单已经支付成功"),
    POINTS_INADEQUATE(1004, "健康豆不足"),
    ORDER_AMOUNT(1006, "订单金额不足以使用健康豆"),
    TODO_NOT_EXISTS(1005, "操作错误"),
    SYSTEM_OPTIMIZATION(1007, "系统优化");

    private int code;

    private String description;

    BusinessCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
