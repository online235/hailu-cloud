package com.hailu.cloud.api.mall.module.ledger.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hailu.cloud.api.mall.constant.DateFormat;
import lombok.Data;

import java.util.Date;

/**
 * 收入明细表
 *
 * @Author xuzhijie
 * @Date 2019/10/25 9:03
 */
@Data
public class IncomeDetail {

    /**
     * 收入明细ID
     */
    private String id;

    /**
     * 会员表的user_id
     */
    private String memberId;

    /**
     * 收入描述，简短说明一下
     */
    private String remark;

    /**
     * 收入类型：0收入 1支出
     */
    private Integer type;

    /**
     * 提现阀值，只有金额超过该阀值才允许提现，例: 109块只能提现100， 99块不允许提现
     */
    private Integer transferOutThreshold;

    /**
     * 收入时间
     */
    @JsonFormat(pattern = DateFormat.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;

}
