package com.hailu.cloud.api.admin.module.xinan.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class HelpPictureParameter {


    @ApiModelProperty("病历图片路径，多张用“,”拼接")
    private String pictureImage;



    @ApiModelProperty("互助者图片路径，多张用“,”拼接")
    private String pictureHelpImage;



    @ApiModelProperty("视频路径，多个用“,”拼接")
    private String pictureHelpVideo;


}
