package com.hailu.cloud.api.xinan.module.app.model;

import com.hailu.cloud.api.xinan.module.app.entity.MutualAid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表Model
 * @Date: 2019/12/9 0009
 * @program: cloud
 * @create: 2019-12-09 10:
 */
@Data
@ApiModel
public class MutualAidModel extends MutualAid {

    /**
     * 图标路径
     */
    @ApiModelProperty(value = "图标路径")
    private String picture;

}
