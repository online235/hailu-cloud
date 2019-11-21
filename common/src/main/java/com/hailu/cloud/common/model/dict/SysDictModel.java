package com.hailu.cloud.common.model.dict;

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
    @ApiModelProperty(value = "分类code", required = true)
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
    @ApiModelProperty(value = "字典名称", required = true)
    private String name;

    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空")
    @ApiModelProperty(value = "字典值", required = true)
    private String value;

}
