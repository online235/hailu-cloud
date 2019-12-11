package com.hailu.cloud.api.mall.module.user.entity;

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
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8063242689214064367L;

    /**
     * 主键ID
     */
    private Integer id;

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
     * 手机号
     */
    private String userMobile;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 邮箱
     */
    private String email;

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
     * 职业
     */
    private String profession;

    /**
     * 生日
     */
    private String birthday;

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

    private String sourceRegistration;

    private String cid;//设备唯一id
    private Integer systemType;//0Android 1IOS

    private int merchantType; //商户类型 0_无，1_区域代理，2_服务商

    private String superiorMember; //该会员归属于哪个区域代理商 上级ID

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
    private String memberTruename;

    /**
     *
     */
    private String memberAvatar;

    /**
     * 生日
     */
    private Long memberBirthday;

    /**
     *
     */
    private String memberPasswd;

    /**
     * 会员邮箱
     */
    private String memberEmail;

    /**
     * qq
     */
    private String memberQq;

    /**
     * 阿里旺旺
     */
    private String memberWw;

    /**
     * 登录次数
     */
    private Integer memberLoginNum;

    /**
     * 当前登录时间
     */
    private Long memberLoginTime;

    /**
     * 上次登录时间
     */
    private Long memberOldLoginTime;

    /**
     * 当前登录ip
     */
    private String memberLoginIp;

    /**
     * 上次登录ip
     */
    private String memberOldLoginIp;

    /**
     * 会员微信id
     */
    private String memberOpenid;



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
     * 红包
     */
    private BigDecimal redEnvelope;

    /**
     * 是否允许举报(1可以/2不可以)
     */
    private Integer informAllow;

    /**
     * 会员是否有购买权限 1为开启 0为关闭
     */
    private Integer isBuy;

    /**
     * 会员是否有咨询和发送站内信的权限 1为开启 0为关闭
     */
    private Integer isAllowtalk;

    /**
     * 会员的开启状态 1为开启 0为关闭
     */
    private Integer memberState;

    /**
     * 会员信用
     */
    private Integer memberCredit;

    /**
     * sns空间访问次数
     */
    private Integer memberSnsvisitnum;

    /**
     * 地区ID
     */
    private Integer memberAreaid;

    /**
     * 城市ID
     */
    private Integer memberCityid;

    /**
     * 省份ID
     */
    private Integer memberProvinceid;

    /**
     * 地区内容
     */
    private String memberAreainfo;

    /**
     * 删除标志0未删除1已删除
     */
    private Integer isDel;

    /**
     *
     */
    private String signCode;

    /**
     *
     */
    private String signCodeState;


    /**
     * 是否升级 1_否,2_是
     */
    private String isUpgrade;

    /**
     * 用户类型
     */
    private String memberType;

    /**
     * 微信状态1_app,2_H5,3_pc
     */
    private String wxState;


    /**
     * 是否领取大礼包 0未  1已
     */
    private Integer isLqlb;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 身份证正面照
     */
    private String idcardImgx;

    /**
     * 身份证反面照
     */
    private String idcardImgy;

    /**
     * 是否提交认证信息
     */
    private Integer isSub;

    /**
     * 实名认证审核状态  0未审核  1审核通过,2审核失败
     */
    private Integer auditState;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 审核时间
     */
    private Long auditTime;

    /**
     * 会员详细信息
     */
    private String memberInfo;

    /**
     * 隐私设定
     */
    private String memberPrivacy;

    /**
     * 服务商城市
     */
    private Integer merchantCityId;

    /**
     * 海露会员过期时间
     */
    private Date hlMemberTimeout;
}
