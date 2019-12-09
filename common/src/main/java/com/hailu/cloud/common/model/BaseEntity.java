package com.hailu.cloud.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 父类实体
 * @author junpei.deng
 */
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "id", dataType = "Long")
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyBy;

}
