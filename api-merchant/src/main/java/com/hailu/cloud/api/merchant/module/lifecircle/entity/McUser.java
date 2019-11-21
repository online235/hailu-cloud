package com.hailu.cloud.api.merchant.module.lifecircle.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class McUser implements Serializable {
    /**
     * 编号
     */
    private String numberId;

    /**
     * 登陆账号
     */
    private String landingAccount;

    /**
     * 登陆密码
     */
    private String landingPassword;

    /**
     * 网络名称
     */
    private String networkName;

    /**
     * 账号类型(1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户)
     */
    private Integer accountType;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Long createdat;

    /**
     * 更新时间
     */
    private Long updatedat;

    /**
     * mc_user
     */
    private static final long serialVersionUID = 1L;


}