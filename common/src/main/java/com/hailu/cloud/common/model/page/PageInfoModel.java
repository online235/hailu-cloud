package com.hailu.cloud.common.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuzhijie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PageInfoModel<T> {

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private int totalPage;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private long total;

    /**
     * 分页数据
     */
    @ApiModelProperty(value = "分页数据")
    private T datas;

}
