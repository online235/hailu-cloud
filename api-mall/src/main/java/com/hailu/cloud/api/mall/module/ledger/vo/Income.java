package com.hailu.cloud.api.mall.module.ledger.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 收入表
 *
 * @Author xuzhijie
 * @Date 2019/10/25 9:03
 */
@Data
public class Income {

    /**
     * 收入明细ID
     */
    private Long id;

    /**
     * 会员表的user_id
     */
    private String memberId;

    /**
     * 余额
     */
    private BigDecimal price;

    /**
     * 累计收入
     */
    private BigDecimal totalPrice;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 提现阀值
     */
    private Integer transferOutThreshold;

    /**
     * 收入明细
     */
    private List<IncomeDetail> incomeDetails;

    /**
     * 冻结金额
     */
    private BigDecimal freezePrice;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;

    /**
     * 版本号（乐观锁）
     */
    private int version;

    /**
     * 收入冻结金额
     */
    private BigDecimal inFreezePrice;
}
