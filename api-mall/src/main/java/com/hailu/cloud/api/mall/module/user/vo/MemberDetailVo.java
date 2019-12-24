package com.hailu.cloud.api.mall.module.user.vo;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author junpei.deng
 */
@Data
@InjectDict
public class MemberDetailVo {

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
     * 省Code
     */
    private String provinceId;
    @DictName(code = "NATION", joinField = "provinceId")
    private String provinceIdStr;

    /**
     * 市code
     */
    private String cityId;

    @DictName(code = "NATION", joinField = "cityId")
    private String cityIdStr;

    /**
     * 区县code
     */
    private String areaId;

    @DictName(code = "NATION", joinField = "areaId")
    private String areaIdStr;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 加入时间
     */
    private Date joinTime;

    /**
     * 头像地址
     */
    private String icon;
}
