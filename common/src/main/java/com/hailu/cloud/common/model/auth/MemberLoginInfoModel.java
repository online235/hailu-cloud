package com.hailu.cloud.common.model.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 商城账号/心安账号共用一个账号体系，用于登录时返回给客户端的登录信息
 *
 * @Author xuzhijie
 * @Date 2019/11/6 10:50
 */
@Data
@ApiModel
public class MemberLoginInfoModel extends WeChatLoginModel {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String userId;

    /**
     * 会员名称
     */
    @ApiModelProperty("会员名称")
    private String memberName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String pwd;

    /**
     * 是否绑定微信
     */
    @ApiModelProperty("是否绑定微信")
    private boolean bindWeChat;

    /**
     * 商户类型 0_无，1_区域代理，2_服务商
     */
    @ApiModelProperty("商户类型 0_无，1_区域代理，2_服务商")
    private int merchantType;

    /**
     * 该会员归属于哪个区域代理商
     */
    @ApiModelProperty("该会员归属于哪个区域代理商")
    private String superiorMember;

    /**
     * 是否为海露会员（0-否、1-是）
     */
    @ApiModelProperty("是否为海露会员（0-否、1-是）")
    private int hlMember;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String memberMobile;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String userIcon;

    /**
     * 登录名称
     */
    @ApiModelProperty("登录名称")
    private String loginName;

    /**
     * 会员性别
     */
    @ApiModelProperty("会员性别")
    private String memberSex;

}
