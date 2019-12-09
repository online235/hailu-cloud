package com.hailu.cloud.api.mall.module.serviceproviders.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 190726
 */
@Data
@ApiModel("服务商Dto")
public class ServiceProvidersDto {

    @ApiModelProperty(value = "id（编辑时传入，新增是无需传）", dataType = "String",required = true)
    private Long id;

    /**
     * 服务商名称
     */
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "服务商名称", dataType = "String",required = true)
    private String name;

    /**
     * 电话号码
     */
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确")
    @ApiModelProperty(value = "电话号码", dataType = "String",required = true)
    private String phone;

    /**
     * 省ID
     */
    @NotNull(message = "省份不能为空")
    @ApiModelProperty(value = "省ID", dataType = "Long",required = true)
    private Long provinceId;
    /**
     * 市ID
     */
    @NotNull(message = "城市不能为空")
    @ApiModelProperty(value = "市ID", dataType = "Long",required = true)
    private Long cityId;
    /**
     * 区县ID
     */
    @NotNull(message = "区县不能为空")
    @ApiModelProperty(value = "区县ID", dataType = "Long",required = true)
    private Long areaId;
    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    @ApiModelProperty(value = "详细地址", dataType = "String",required = true)
    private String address;
}
