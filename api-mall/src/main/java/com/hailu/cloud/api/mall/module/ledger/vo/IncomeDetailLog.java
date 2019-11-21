package com.hailu.cloud.api.mall.module.ledger.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class IncomeDetailLog implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 操作纪律表的ID
     */
    private String editId;

    /**
     * 会员ID
     */
    private String userId;

    /**
     * 来源
     */
    private String froms;

    /**
     * 资金状态（1-待处理、2-已完成、3-已过期、4-驳回）
     */
    private Integer accountStatus;

    /**
     * 操作前的金额
     */
    private BigDecimal beforeMoney;

    /**
     * 操作后的金额
     */
    private BigDecimal afterMoney;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 修改时间
     */
    private Long updateDate;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;

    /**
     * hl_income_detail_log
     */
    private static final long serialVersionUID = 1L;
}