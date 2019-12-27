package com.hailu.cloud.api.merchant.module.merchant.entity;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;

/**
 * @author xuzhijie
 */
@Data
@InjectDict
public class McUser {
    /**
     * 编号
     */
    private String numberId;

    /**
     * 登陆账号
     */
    private String landingAccount;


    /**
     * 昵称
     */
    private String nickName;


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


    @DictName(code = "ACCOUNT_TYPE", joinField = "accountType")
    private String accountTypeString;


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

}