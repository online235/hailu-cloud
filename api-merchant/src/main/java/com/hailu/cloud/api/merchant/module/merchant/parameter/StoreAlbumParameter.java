package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel
public class StoreAlbumParameter {

    @ApiModelProperty(value = "店铺id",required = true)
    private Long storeId;


    @ApiModelProperty(value = "相册对象数组字符串，例：[{'albumUrl':'相册路径','title':'图片标题','albumType':图片类型（首页轮播图-0 、环境-1、菜品-2、其他-3）},{'albumUrl':'String','title':'String','albumType':1}]")
    private String albumUrlsJsonString;


}
