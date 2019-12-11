package com.hailu.cloud.api.admin.module.xinan.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表图片实体类
 * @Date: 18:17 2019/11/12 0012
 */
@Data
public class RescuePictures implements Serializable {
    /**
     * 编号
     */
    private Long numberId;

    /**
     * 图片路径
     */
    private String picture;

    /**
     * 图片名称
     */
    private String pictureName;

    /**
     * 救助表ID
     */
    private String mutualaId;

    /**
     * 图片类型
     */
    private String pictureType;

    /**
     * 创建时间
     */
    private Date createdat;

    /**
     * xa_rescue_pictures
     */
    private static final long serialVersionUID = 1L;
}