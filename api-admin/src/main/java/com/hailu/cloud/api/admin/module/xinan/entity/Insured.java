package com.hailu.cloud.api.admin.module.xinan.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Insured implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 
     */
    private String insuredMemberId;

    /**
     * 名称
     */
    private String name;

    /**
     * 参保人数据
     */
    private String value;

    /**
     * 参保人证件类型
     */
    private Integer type;

    /**
     * 是否享受年费机制(1-享受2-不享受)
     */
    private Integer isYearEnjoy;

    /**
     * 状态(1-正常2-删除)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图片地址
     */
    private String photoUrl;

    /**
     * 首次参保日期
     */
    private Date firstJoinInsured;

    /**
     * 会员有效期（日期时间）
     */
    private Date memberValidity;

    /**
     * 参保人关系
     */
    private Integer memberRelation;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date updateDate;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 会员状态（1-代付款2-待审核3-观察期4-驳回   会员卡还有很多状态，后期再增加）
     */
    private Integer memberStatus;

    /**
     * xa_insured
     */
    private static final long serialVersionUID = 1L;

}