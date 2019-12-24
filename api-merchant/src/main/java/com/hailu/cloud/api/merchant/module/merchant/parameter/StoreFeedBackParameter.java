package com.hailu.cloud.api.merchant.module.merchant.parameter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@ApiModel("反馈信息")
@Data
public class StoreFeedBackParameter {

//    /**
//     * 用户id
//     */
//    @ApiModelProperty(value = "用户id",required = true)
//    private String userId;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

//    /**
//     * 邮箱
//     */
//    @ApiModelProperty("邮箱")
//    private String email;


//    /**
//     * 电话
//     */
//    @ApiModelProperty("电话")
//    private String phone;
//
//    /**
//     * 用户名字
//     */
//    @ApiModelProperty("用户名字")
//    private String userName;


    /**
     * 上传凭证，（多张用“,”拼接）
     */
    @ApiModelProperty("上传凭证，（多张用“,”拼接）")
    private String voucher;


    /**
     * IOS 1,ANDROID 2,other 3
     */
    @ApiModelProperty("IOS 1,ANDROID 2,other 3")
    private Integer phoneType;


}
