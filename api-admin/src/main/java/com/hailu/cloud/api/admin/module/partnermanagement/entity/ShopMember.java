package com.hailu.cloud.api.admin.module.partnermanagement.entity;
import lombok.Data;
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
     * 手机号
     */
    private String memberMobile;

    /**
     * 登陆账号
     */
    private String memberAccount;

    /**
     * 服务商城市
     */
    private Integer merchantCityId;

    /**
     * 用户类型 0_普通会员，啥都不是，1_区域代理，2_城市合伙人、3-销售人员
     */
    private Integer merchantType;

    /**
     * 该会员归属于哪个区域代理商
     */
    private String superiorMember;

    /**
     * 会员类型 0会员, 1商户
     */
    private Integer memberType;

    /**
     * 入驻类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户
     */
    private Integer settleinType;

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
     * 删除标志0未删除1已删除
     */
    private Integer isDel;

    /**
     * 用户字符Id
     */
    private String userId;

    /**
     * 会员头像
     */
    private String userIcon;

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
