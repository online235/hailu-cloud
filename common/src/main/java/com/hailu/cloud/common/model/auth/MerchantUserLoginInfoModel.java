package com.hailu.cloud.common.model.auth;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import com.hailu.cloud.common.model.merchant.StoreInformationModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 商户账号共用一个账号体系，用于登录时返回给客户端的登录信息
 *
 * @Author xuzhijie
 * @Date 2019/11/6 10:50
 */
@Getter
@Setter
@InjectDict
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
     * 一级经营类型id
     * 是商户登陆，且需要填写资料的时候返回，其他不返回
     */
    @ApiModelProperty(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;


    /**
     * 手机号码
     */
    private String phone;

    /**
     * 入驻审核状态 '0 资料填写  1 审核中','2 审核通过','3 审核不通过
     */
    private Integer toExamine;

    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty(value="审核(''0 资料填写  审核中-1'',''审核通过-2'',''审核不通过-3'')'")
    private String toExamineDisplay;

    /**
     * 店铺列表
     */
    private List<StoreInformationModel> stores;

}
