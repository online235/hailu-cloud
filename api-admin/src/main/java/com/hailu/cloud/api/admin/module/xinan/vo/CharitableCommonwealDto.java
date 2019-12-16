package com.hailu.cloud.api.admin.module.xinan.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 政府公益Dto
 * @Date: 2019/12/4 0004
 * @program: cloud
 * @create: 2019-12-04 18:
 */
@Data
public class CharitableCommonwealDto {

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 政府用户编号
     */
    @ApiModelProperty("政府用户编号")
    private Long adminId;

    /**
     * 公益标题
     */
    @ApiModelProperty("公益标题")
    private String commonwealTitle;

    /**
     * 默认图片
     */
    @ApiModelProperty("默认图片")
    private String defaultPicture;

    /**
     * 文章
     */
    @ApiModelProperty("文章")
    private String article;
}
