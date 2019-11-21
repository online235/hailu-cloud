package com.hailu.cloud.api.mall.module.user.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author HuangL
 * @Email huangl96@163.com
 * @Description 用户钱包
 * @date 9/9/2019 5:59 PM
 */
@Data
public class UserWallet {

    private String userId;

    private String memberMoblie;
    /**
     * 预存款可用金额
     */
    private BigDecimal availablePredeposit;
    /**
     * 预存款冻结金额
     */
    private BigDecimal freezePredeposit;
    /**
     * 消费余额
     */
    private BigDecimal consumptionPredeposit;
    /**
     * 提现余额
     */
    private BigDecimal withdrawPredeposit;
    /**
     * 总余额
     */
    private BigDecimal totalPredeposit;

}
