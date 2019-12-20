package com.hailu.cloud.api.merchant.module.merchant.result;


import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
@InjectDict
public class ShopExamineResult {


    @ApiModelProperty(" '提交资料-0 审核中-1'',''审核通过-2'',''审核不通过-3'")
    private Integer toExamine;


    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty(" '提交资料-0 审核中-1'',''审核通过-2'',''审核不通过-3'")
    private String toExamineDisplay;

}
