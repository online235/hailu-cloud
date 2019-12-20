package com.hailu.cloud.api.xinan.module.app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangmugui
 */
@Data
@ApiModel
public class XaHelpMemberDetailModel{


    @ApiModelProperty("病历图片路径")
    private List<String>  pictureImages;


    @ApiModelProperty("互助者图片路径")
    private List<String>  pictureHelpImages;


    @ApiModelProperty("视频路径")
    private List<String>  pictureHelpVideos;


    @ApiModelProperty("详情")
    private XaHelpMemberModel xaHelpMemberModel;


}
