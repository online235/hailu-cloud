package com.hailu.cloud.api.mall.module.ledger.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 银行卡实体
 * @author junpei.deng
 */
@ToString
@Data
@ApiModel(value = "银行卡")
public class BankCardDto {

    /**
     * ID
     */
    @ApiModelProperty(value = "id（编辑时传入，新增是无需传）", dataType = "String",required = true)
    private Long id;

    /**
     * 卡号
     */
    @NotBlank(message = "卡号不能为空")
    @ApiModelProperty(value = "卡号", dataType = "String",required = true)
    private String cardNo;

    /**
     * 持卡人名称
     */
    @NotBlank(message = "持卡人名称不能为空")
    @ApiModelProperty(value = "持卡人名称", dataType = "String",required = true)
    private String name;

    /**
     * 银行名称
     */
    @NotBlank(message = "银行名称不能为空")
    @ApiModelProperty(value = "银行名称", dataType = "String",required = true)
    private String bankName;

    /**
     * 开户行
     */
    @NotBlank(message = "开户行不能为空")
    @ApiModelProperty(value = "开户行", dataType = "String",required = true)
    private String openAccountBank;

}
