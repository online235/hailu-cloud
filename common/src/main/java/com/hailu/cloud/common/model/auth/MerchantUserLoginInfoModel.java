package com.hailu.cloud.common.model.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * 商户账号共用一个账号体系，用于登录时返回给客户端的登录信息
 *
 * @Author xuzhijie
 * @Date 2019/11/6 10:50
 */
@Getter
@Setter
public class MerchantUserLoginInfoModel extends LoginModel {

    /**
     * 编号
     */
    private String numberid;

    /**
     * 登陆账号
     */
    private String landingaccount;

    /**
     * 登陆账号
     */
    private String landingpassword;

    /**
     * 网络名称
     */
    private String networkname;

    /**
     * 账号类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户
     */
    private int accounttype;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 审核
     */
    private String toExamine;

}
