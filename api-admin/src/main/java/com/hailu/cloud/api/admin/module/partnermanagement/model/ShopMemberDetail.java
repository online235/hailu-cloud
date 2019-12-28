package com.hailu.cloud.api.admin.module.partnermanagement.model;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员详情表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class ShopMemberDetail {

    /**
     * id

     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 会员userId
     */
    @ApiModelProperty("会员userId")
    private String userId;

    /**
     * 累计消费
     */
    @ApiModelProperty("累计消费")
    private BigDecimal totalConsumption;

    /**
     * 邀请会员数量
     */
    @ApiModelProperty("邀请会员数量")
    private Integer inviteMemberNum;

    /**
     * 邀请商家数量
     */
    @ApiModelProperty("邀请商家数量")
    private Integer inviteMerchatNum;

    /**
     * 邀请合伙人数量
     */
    @ApiModelProperty("邀请合伙人数量")
    private Integer invitePartnersNum;

    /**
     * 销售业绩
     */
    @ApiModelProperty("销售业绩")
    private BigDecimal salesPerformance;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String phone;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 省Code
     */
    @ApiModelProperty("省Code")
    private String provinceId;

    /**
     * 市code
     */
    @ApiModelProperty("市code")
    private String cityId;

    /**
     * 区县code
     */
    @ApiModelProperty("区县code")
    private String areaId;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String address;

    /**
     * 购买的类型（1-区代、2-城市合伙人、3-销售）
     */
    @ApiModelProperty("购买的类型（1-区代、2-城市合伙人、3-销售）")
    private Integer wantBuyType;

    /**
     * 状态（1-正常、2-删除）
     */
    @ApiModelProperty("状态（1-正常、2-删除）")
    private Integer status;

    /**
     * 加入时间
     */
    @ApiModelProperty("加入时间")
    private Date joinTime;

}
