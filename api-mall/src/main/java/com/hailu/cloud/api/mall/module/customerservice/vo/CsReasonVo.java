package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author Administrator
 */
@Data
public class CsReasonVo {
    private Integer csReasonId;
    /**
     * 原因类型（0，维修，1换货，2退货）
     */
    private Integer reasonType;
    private String reason;

    private Integer deleteStatus;
    private BigInteger createDate;
    private String createName;
    private BigInteger updateDate;
    private String updateName;

}
