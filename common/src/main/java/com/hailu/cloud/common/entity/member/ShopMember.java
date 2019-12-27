package com.hailu.cloud.common.entity.member;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员表
 *
 * @author zhijie
 */
@Data
public class ShopMember {

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 服务商城市
     */
    private Integer merchantCityId;

    /**
     * 商户类型 0_无，1_区域代理，2_服务商
     */
    private Integer merchantType;

    /**
     * 该会员归属于哪个区域代理商
     */
    private String superiorMember;

    /**
     * 海露会员卡号
     */
    private String hlMemberCard;

    /**
     * 海露会员过期时间
     */
    private Date hlMemberTimeout;

    /**
     * 是否为海露会员（0-否、1-是）
     */
    private Integer hlMember;

    /**
     * 商户总金额（针对于商户）
     */
    private BigDecimal totalMerchantsMoney;

    /**
     * 会员性别1男2女
     */
    private String memberSex;

    /**
     * 密码
     */
    private String memberPasswd;

    /**
     * 会员注册时间
     */
    private Long createTime;

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
     * 手机号
     */
    private String memberMobile;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 用户字符Id
     */
    private String userId;

    /**
     * null
     */
    private String userIcon;

    /**
     * 邀请人
     */
    private String beInviteUser;

    /**
     * 注册时间
     */
    private Long registTime;

    /**
     * 微信状态1_app,2_H5,3_pc、4-公众号
     */
    private String wxState;

    /**
     * openId
     */
    private String openId;

    /**
     * unionid
     */
    private String unionid;

    /**
     * 登录名字
     */
    private String loginName;

}
