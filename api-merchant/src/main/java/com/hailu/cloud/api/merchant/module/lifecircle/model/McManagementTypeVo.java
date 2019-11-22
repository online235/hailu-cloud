package com.hailu.cloud.api.merchant.module.lifecircle.model;


import com.hailu.cloud.api.merchant.module.lifecircle.entity.McManagementType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangmugui
 */
@Data
@ApiModel(description = "经营类型返回数据")
public class McManagementTypeVo {

    /**
     * 经营类型id
     */
    @ApiModelProperty(value = "经营类型id",dataType = "Long")
    private Long managementId;


    /**
     * 父类编号
     */
    @ApiModelProperty(value = "父类编号",dataType = "Long")
    private Long parentId;

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接",dataType = "String")
    private String pictureUrl;


    /**
     * 经营名称
     */
    @ApiModelProperty(value = "经营名称",dataType = "String")
    private String managementName;



    /**
     * 子类列表
     */
    @ApiModelProperty(value = "子类列表",dataType = "list")
    private List<McManagementType> mcManagementTypeList;


}
