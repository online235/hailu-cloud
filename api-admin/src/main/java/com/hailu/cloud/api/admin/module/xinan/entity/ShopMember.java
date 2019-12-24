package com.hailu.cloud.api.admin.module.xinan.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 刘柱栋
 * @Description 用户信息
 * @date 2016/6/7 23:57
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
@Data
public class ShopMember implements Serializable {

    private static final long serialVersionUID = -8063242689214064367L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID(外键关联)
     */
    private String userId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPasswd;

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
     * 会员名称
     */
    private String memberName;

    /**
     * 注册时间
     */
    private long registTime;

    /**
     * 手机号
     */
    private String memberMobile;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 被邀请码
     */
    private String beInviteUser;

    private String unionid;
    private String openId;

    private Long createDate;

    private String memberSex;

    private int merchantType; //商户类型 0_无，1_区域代理，2_服务商

    private String superiorMember; //该会员归属于哪个区域代理商 上级ID

    /**
     * 累计金额（针对于商户）
     */
    private BigDecimal cumulativeMerchantsMoney;

    /**
     * 冻结提现金额（针对于商户）
     */
    private BigDecimal freezeWithdrawMerchants;

    /**
     * 可提现收入（针对于商户）
     */
    private BigDecimal availableWithdrawMerchants;

    /**
     * 商户总金额（针对于商户）
     */
    private BigDecimal totalMerchantsMoney;
    /**
     * 是否为海露会员（0-否、1-是）
     */
    private int hlMember;



    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 真实姓名
     */
    private String memberTrueName;

    /**
     *
     */
    private String memberAvatar;

    /**
     *
     */
    private String memberPasswd;

    /**
     * 预存款可用金额
     */
    private BigDecimal availablePredeposit;

    /**
     * 预存款冻结金额
     */
    private BigDecimal freezePredeposit;

    /**
     * 消费余额
     */
    private BigDecimal consumptionPredeposit;

    /**
     * 提现余额
     */
    private BigDecimal withdrawPredeposit;

    /**
     * 总余额
     */
    private BigDecimal totalPredeposit;

    /**
     * 删除标志0未删除1已删除
     */
    private Integer isDel;

    /**
     * 微信状态1_app,2_H5,3_pc
     */
    private String wxState;

    /**
     * 服务商城市
     */
    private Integer merchantCityId;

    /**
     * 海露会员过期时间
     */
    private Date hlMemberTimeout;
}
