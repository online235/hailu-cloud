package com.hailu.cloud.api.admin.module.merchant.parmeter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 标签Model
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 17:
 */
@ApiModel
@Data
public class McSysTagParameter {

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Long id;

    /**
     * 标签类型：1、停车信息；2、免费wifi；3、环境信息；4、其他
     */
    @ApiModelProperty(value = "标签类型：1、停车信息；2、免费wifi；3、环境信息；4、其他")
    private Integer tagType;

    /**
     * 状态(正常-1、删除-2)
     */
    @ApiModelProperty(value = "状态(启用-1、禁用-0)")
    private Integer state;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;

}
