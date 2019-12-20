package com.hailu.cloud.api.auth.module.login.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MerchantLocalLifeResult {



    /**
     * 入驻审核状态 '0 资料填写  1 审核中','2 审核通过','3 审核不通过
     */
    private int toExamine;



    /**
     * 一级经营类型id
     * 是商户登陆，且需要填写资料的时候返回，其他不返回
     */
    @ApiModelProperty(name = "firstManagementTypeId", value = "一级经营类型id")
    private Long firstManagementTypeId;


}
