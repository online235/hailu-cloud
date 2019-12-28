package com.hailu.cloud.api.merchant.module.merchant.result;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
@InjectDict
public class McSysTagResult{
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
     * 标签类型：1、停车信息；2、免费wifi；3、环境信息；4、其他
     */
    @ApiModelProperty(value = "标签类型：1、停车信息；2、免费wifi；3、环境信息；4、其他")
    @DictName(code = "TAG_TYPE", joinField = "tagType")
    private String tagTypeDisplay;


    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;


    @ApiModelProperty(value = "使用状态：1、已经使用；2、未使用")
    private Integer storeUseState;


}