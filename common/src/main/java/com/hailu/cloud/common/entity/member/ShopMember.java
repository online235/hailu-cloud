package com.hailu.cloud.common.entity.member;

import lombok.Data;

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
     * 手机号
     */
    private String memberMobile;

    /**
     * 会员表uuid
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

    /**
     * 会员类型 0会员, 1商户
     */
    private int memberType;

    /**
     * 入驻类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户
     */
    private int settleinType;
}
