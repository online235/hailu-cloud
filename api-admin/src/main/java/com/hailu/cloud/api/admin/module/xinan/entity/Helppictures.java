package com.hailu.cloud.api.admin.module.xinan.entity;

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
    private Long numberId;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String picture;

    /**
     * 图片名称
     */
    @ApiModelProperty(value = "图片名称")
    private String pictureName;

    /**
     * 互助表ID
     */
    @ApiModelProperty(value = "互助表ID")
    private Long mutualAid;

    /**
     * 图片类型
     */
    @ApiModelProperty(value = "图片类型")
    private String pictureType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdat;

    /**
     * xa_helppictures
     */
    private static final long serialVersionUID = 1L;
}