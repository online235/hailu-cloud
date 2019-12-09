package com.hailu.cloud.api.xinan.module.app.entity;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;

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
     * 真实姓名
     */
    private String memberTruename;

    /**
     * null
     */
    private String memberAvatar;

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
     * 生日
     */
    private Long memberBirthday;

    /**
     * 密码
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
     * 会员注册时间
     */
    private Long createTime;

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
     * 会员详细信息
     */
    private String memberInfo;

    /**
     * 会员消费积分
     */
    private Integer memberConsumePoints;

    /**
     * 会员等级积分
     */
    private Integer memberRankPoints;

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
     * 隐私设定
     */
    private String memberPrivacy;

    /**
     * 删除标志0未删除1已删除
     */
    private Integer isDel;

    /**
     * null
     */
    private String signCode;

    /**
     * null
     */
    private String signCodeState;

    /**
     * 手机号
     */
    private String memberMobile;

    /**
     * 会员等级
     */
    private Integer memberGradeid;

    /**
     * 会员成长值
     */
    private Integer memberGrow;

    /**
     * 会员成长等级(会员等级注册就是0)
     */
    private Integer memberGrowGrade;

    /**
     * 是否升级 1_否,2_是
     */
    private String isUpgrade;

    /**
     * 用户类型
     */
    private String memberType;

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
     * 微信
     */
    private String wechat;

    /**
     * 职业
     */
    private String profession;

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
     * 消费健康豆
     */
    private Integer consumptionIntegral;

    /**
     * 可用健康豆
     */
    private Integer integral;

    /**
     * 总健康豆
     */
    private Integer totalIntegral;

    /**
     * 是否领取大礼包 0未  1已
     */
    private Integer isLqlb;

    /**
     * 成长值
     */
    private Integer growthVal;

    /**
     * 登录名字
     */
    private String loginName;

    /**
     * 未读消息数
     */
    private Integer unReadMsgs;

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
     * 0Android 2IOS
     */
    private Integer systemType;

    /**
     * 移动设备id
     */
    private String cid;

    /**
     * 审核时间
     */
    private Long auditTime;

    /**
     * 注册来源0_PC1_安卓2_ios3_微信4_H5
     */
    private String sourceRegistration;

}
