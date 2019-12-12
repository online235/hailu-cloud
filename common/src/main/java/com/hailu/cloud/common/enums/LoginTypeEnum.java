package com.hailu.cloud.common.enums;

/**
 * 登录类型
 *
 * @Author xuzhijie
 * @Date 2019/11/8 15:12
 */
public enum LoginTypeEnum {
    /**
     * 心安，商城用户
     */
    XINAN_AND_MALL,
    /**
     * 商户
     */
    MERCHANT,
    /**
     * 管理员
     */
    ADMIN,
    ;

    public static LoginTypeEnum of(int type) {
        switch (type) {
            case 1:
                return MERCHANT;
            case 2:
                return ADMIN;
            default:
                return XINAN_AND_MALL;
        }
    }
}
