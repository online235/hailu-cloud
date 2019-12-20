package com.hailu.cloud.api.xinan.module.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表图片实体类
 * @Date: 18:17 2019/11/12 0012
 */
@Data
@ApiModel
public class Helppictures implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private Long numberId;

    /**
     * 图片路径
     */
    @ApiModelProperty("图片路径")
    private String picture;

    /**
     * 图片名称
     */
    @ApiModelProperty("图片名称")
    private String pictureName;

    /**
     * 互助表ID
     */
    @ApiModelProperty("互助表ID")
    private Long mutualaId;

    /**
     * 图片类型1-病历','2-互助者图片;3-视频
     */
    @ApiModelProperty("图片类型1-病历','2-互助者图片;3-视频")
    private Integer pictureType;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date dateTime;


    /**
     * xa_helppictures
     */
    private static final long serialVersionUID = 1L;
}