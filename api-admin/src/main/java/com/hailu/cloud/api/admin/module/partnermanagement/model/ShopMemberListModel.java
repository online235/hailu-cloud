package com.hailu.cloud.api.admin.module.partnermanagement.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("用户管理属性")
public class ShopMemberListModel {


    /**
     * 会员userId
     */
    @ApiModelProperty("会员userId")
    private String userId;


    /**
     * 会员头像
     */
    @ApiModelProperty("会员头像")
    private String userIcon;


    /**
     * 会员名称
     */
    @ApiModelProperty("会员名称")
    private String memberName;


    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String memberMobile;


    /**
     * 累计消费
     */
    @ApiModelProperty("累计消费")
    private BigDecimal totalConsumption;







}
