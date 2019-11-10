package com.hailu.cloud.common.model.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * 商城账号/心安账号共用一个账号体系，用于登录时返回给客户端的登录信息
 *
 * @Author xuzhijie
 * @Date 2019/11/6 10:50
 */
@Getter
@Setter
public class McUserLoginInfoModel {

    /**
     * 编号
     */
    private String numberid;

    /**
     * 登陆账号
     */
    private String landingaccount;

    /**
     * 网络名称
     */
    private String networkname;

    /**
     * 账号类型
     */
    private String accounttype;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * access token
     */
    private String accessToken;

    /**
     * refresh token
     */
    private String refreshToken;

}
