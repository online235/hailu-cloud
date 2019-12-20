package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhangmugui
 */
@Data
@ApiModel
public class McUserParameter {

    /**
     * 注册验证码
     */
    @ApiModelProperty(name = "code", value = "注册验证码", required = true)
    @NotEmpty(message = "验证码不能为空")
    private String code;


    /**
     * 联系人昵称
     */
    @ApiModelProperty(name = "nickName", value = "联系人昵称", required = true)
    private String nickName;


    /**
     * 注册登陆账号
     */
    @ApiModelProperty(name = "landingAccount", value = "注册登陆账号", required = true)
    @NotEmpty(message = "注册登陆账号不能为空")
    private String landingAccount;


    /**
     * 注册登陆密码
     */
    @ApiModelProperty(name = "landingPassword", value = "注册登陆密码", required = true)
    @NotEmpty(message = "注册登陆密码不能为空")
    private String landingPassword;


    /**
     * 注册手机号码
     */
    @ApiModelProperty(name = "phone", value = "注册手机号码", required = true)
    @NotEmpty(message = "注册手机号码不能为空")
    private String phone;


    /**
     * 商户类型
     */
    @ApiModelProperty(name = "accountType", value = "商户类型：1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户", required = true)
    @NotNull(message = "商户类型不能为空")
    private Integer accountType;


    @ApiModelProperty(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;


    @ApiModelProperty(name = "provinceCode", value = "门店省的code值")
    private String provinceCode;


    @ApiModelProperty(name = "cityCode", value = "门店城市的code值")
    private String cityCode;


    @ApiModelProperty(name = "areaCode", value = "门店区的code值")
    private String areaCode;


    @ApiModelProperty(name = "detailAddress", value = "门店详细地址")
    private String detailAddress;


}
