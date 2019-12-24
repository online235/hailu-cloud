package com.hailu.cloud.api.mall.module.ledger.vo;

import com.hailu.cloud.common.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BankCard extends BaseEntity {
    /**
     * ID
     */
    private Long id;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 持卡人名称
     */
    private String name;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 开户行
     */
    private String openAccountBank;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;


    /**
     * hl_bankcard
     */
    private static final long serialVersionUID = 1L;


}