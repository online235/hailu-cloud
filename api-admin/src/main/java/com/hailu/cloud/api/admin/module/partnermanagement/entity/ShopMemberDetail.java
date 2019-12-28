package com.hailu.cloud.api.admin.module.partnermanagement.entity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员详情表
 *
 * @author zhijie
 */
@Data
public class ShopMemberDetail {

    /**
     * id

     */
    private Long id;

    /**
     * 会员userId
     */
    private String userId;

    /**
     * 累计消费
     */
    private BigDecimal totalConsumption;

    /**
     * 邀请会员数量
     */
    private Integer inviteMemberNum;

    /**
     * 邀请商家数量
     */
    private Integer inviteMerchatNum;

    /**
     * 邀请合伙人数量
     */
    private Integer invitePartnersNum;

    /**
     * 销售业绩
     */
    private BigDecimal salesPerformance;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 名称
     */
    private String name;

    /**
     * 省Code
     */
    private String provinceId;

    /**
     * 市code
     */
    private String cityId;

    /**
     * 区县code
     */
    private String areaId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 购买的类型（1-区代、2-城市合伙人、3-销售）
     */
    private Integer wantBuyType;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;

    /**
     * 加入时间
     */
    private Date joinTime;

}
