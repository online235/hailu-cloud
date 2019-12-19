package com.hailu.cloud.api.admin.module.merchant.model;

import com.hailu.cloud.api.admin.module.merchant.entity.McShopTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺标签model
 * @Date: 2019/12/19 0019
 * @program: cloud
 * @create: 2019-12-19 11:
 */
@Data
@ApiModel
public class McShopTagModel extends McShopTag {

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String tagName;
}
