package com.hailu.cloud.api.basic.module.dict.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * null
 *
 * @author zhijie
 */
@Data
public class SysDictModel implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 分类code
     */
    @NotBlank(message = "分类code不能为空")
    @ApiModelProperty("分类code")
    private String code;

    /**
     * 分类描述
     */
    @ApiModelProperty("分类描述")
    private String desc;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @ApiModelProperty("字典名称")
    private String name;

    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空")
    @ApiModelProperty("字典值")
    private String value;

}
