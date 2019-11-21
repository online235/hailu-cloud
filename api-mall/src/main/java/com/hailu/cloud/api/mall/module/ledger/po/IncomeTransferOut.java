package com.hailu.cloud.api.mall.module.ledger.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hailu.cloud.api.mall.constant.DateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现申请表
 *
 * @Author xuzhijie
 * @Date 2019/10/25 9:03
 */
@Data
@ApiModel("提现申请表")
public class IncomeTransferOut {

    /**
     * 收入明细ID
     */
    @ApiModelProperty("收入明细ID")
    private String id;

    /**
     * 会员表的user_id
     */
    @ApiModelProperty("会员表的user_id")
    private String memberId;

    /**
     * 提现金额
     */
    @ApiModelProperty("提现金额")
    private BigDecimal price;

    /**
     * 银行卡卡号
     */
    @ApiModelProperty("银行卡卡号")
    private String bankCard;

    /**
     * 持卡人名称
     */
    @ApiModelProperty("持卡人名称")
    private String cardholder;

    /**
     * 拒绝原因
     */
    @ApiModelProperty("拒绝原因")
    private String remark;

    /**
     * 审核状态：0申请中 1申请成功 2已拒绝
     */
    @ApiModelProperty("审核状态：0申请中 1申请成功 2已拒绝")
    private Integer state;

    /**
     * 提现时间
     */
    @ApiModelProperty("提现时间")
    @JsonFormat(pattern = DateFormat.YYYY_MM_DD_HH_MM_SS, timezone = "GMT+8")
    @DateTimeFormat(pattern = DateFormat.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;

    /**
     * 审核人
     */
    private String updateBy;

    /**
     * 审核时间
     */
    private Date updateTime;
}
