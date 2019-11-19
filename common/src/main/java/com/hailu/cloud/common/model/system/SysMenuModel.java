package com.hailu.cloud.common.model.system;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 系统菜单
 *
 * @author zhijie
 */
@Data
@InjectDict
public class SysMenuModel {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 父菜单ID
     */
    @ApiModelProperty("父菜单ID")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
     * 菜单URL路径
     */
    @ApiModelProperty("菜单URL路径")
    private String url;

    /**
     * 菜单权限编码
     */
    @ApiModelProperty("菜单权限编码")
    private String permissionCode;

    /**
     * 菜单类型
     */
    @ApiModelProperty("菜单类型")
    private Integer menuType;

    /**
     * 启用状态
     */
    @ApiModelProperty("启用状态")
    private Integer status;

    @DictName(code = "ENABLE_STATUS", joinField = "status")
    private String statusDisplay;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateBy;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

}
