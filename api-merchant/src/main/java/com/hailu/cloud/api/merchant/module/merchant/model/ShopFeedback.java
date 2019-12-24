package com.hailu.cloud.api.merchant.module.merchant.model;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 用户反馈
 *
 * @author zhijie
 */
@Data
@ApiModel
public class ShopFeedback {

    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

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

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String phone;

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

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;


    /**
     * 用户名字
     */
    @ApiModelProperty("用户名字")
    private String userName;


    /**
     * 回复
     */
    @ApiModelProperty("回复")
    private String officialReply;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
