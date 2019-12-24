package com.hailu.cloud.api.admin.module.withdrawal.entity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现申请表
 *
 * @author zhijie
 */
@Data
public class HlIncomeTransferOut {

    /**
     * 提现申请ID
     */
    private Long id;

    /**
     * 会员表的user_id
     */
    private String memberId;

    /**
     * 提现金额
     */
    private BigDecimal price;

    /**
     * 开户行名称
     */
    private String openAccountBank;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡卡号
     */
    private String bankCard;

    /**
     * 持卡人名称
     */
    private String cardholder;

    /**
     * 审核状态：0申请中 1申请成功 2已拒绝
     */
    private Integer state;

    /**
     * 拒绝原因
     */
    private String remark;

    /**
     * 提现时间
     */
    private Date createTime;

    /**
     * 审核人
     */
    private String updateBy;


    /**
     * 审核时间
     */
    private Date examineTime;


    /**
     * 更新时间
     */
    private Date updateTime;

}
