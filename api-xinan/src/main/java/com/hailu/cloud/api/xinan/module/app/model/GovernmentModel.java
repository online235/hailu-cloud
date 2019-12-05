package com.hailu.cloud.api.xinan.module.app.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 政府文章
 * @Date: 2019/12/4 0004
 * @program: cloud
 * @create: 2019-12-04 10:
 */
@Data
@ApiModel
public class GovernmentModel {

    /**
     * 政府管理员编号
     */
    @ApiModelProperty("政府管理员编号")
    private String id;

    /**
     * 市code
     */
    @ApiModelProperty("市code")
    private String cityCode;

    /**
     * 公益文章
     */
    @ApiModelProperty("公益文章")
    private String commonwealArticle;

}
