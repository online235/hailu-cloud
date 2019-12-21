package com.hailu.cloud.api.mall.module.user.vo;

import lombok.Data;

/**
 * 后期要删掉
 *
 * @author 刘柱栋
 * @Description 用户接口信息
 * @date 2016/6/9 21:46
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
@Data
@Deprecated
public class UserInfoVo {

    private static final long serialVersionUID = -2485672484540185587L;

    private Long memberId;

    /**
     * 用户ID(外键关联)
     */
    private String userId;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String userMobile;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 邀请人
     */
    private String beInviteUser;

    private String sex;

    private String unionid;
    /**
     * 对应的openId
     */
    private String openId;
    /**
     * 微信绑定状态 1_app,2_h5,3_pc
     */
    private String WXState;

    private double balance;

    private int isBindWeChat;//是否绑定微信

    private String memberSex;

    private int merchantType; //商户类型 0_无，1_区域代理，2_服务商

    private String superiorMember; //该会员归属于哪个区域代理商 上级ID

}
