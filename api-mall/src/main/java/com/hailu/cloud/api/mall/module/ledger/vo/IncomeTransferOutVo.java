package com.hailu.cloud.api.mall.module.ledger.vo;

import com.hailu.cloud.api.mall.module.ledger.po.IncomeTransferOut;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 提现申请表
 *
 * @Author xuzhijie
 * @Date 2019/10/25 9:03
 */
@Data
@ApiModel("提现申请表")
public class IncomeTransferOutVo extends IncomeTransferOut {

    /**
     * 会员名称
     */
    @ApiModelProperty("会员名称")
    private String memberName;

}
