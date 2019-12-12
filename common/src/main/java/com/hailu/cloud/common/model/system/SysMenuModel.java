package com.hailu.cloud.common.model.system;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统菜单
 *
 * @author zhijie
 */
@Data
@InjectDict
@ApiModel
public class SysMenuModel {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 父菜单ID
     */
    @ApiModelProperty("父菜单ID")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称", required = true)
    private String menuName;

    /**
     * 前端路由
     */
    @ApiModelProperty(value = "前端路由", required = true)
    private String url;

    /**
     * 后端接口
     */
    @ApiModelProperty(value = "后端接口", required = true)
    private String api;

    /**
     * 菜单类型
     */
    @ApiModelProperty(value = "菜单类型：0菜单，1按钮, 2接口", required = true)
    private Integer menuType;

    @DictName(code = "MENU_TYPE", joinField = "menuType")
    private String menuTypeDisplay;

    /**
     * 启用状态
     */
    @ApiModelProperty("启用状态")
    private Integer enableStatus;

    @DictName(code = "ENABLE_STATUS", joinField = "enableStatus")
    private String enableStatusDisplay;

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

    private List<SysMenuModel> children = new ArrayList<>();

}
