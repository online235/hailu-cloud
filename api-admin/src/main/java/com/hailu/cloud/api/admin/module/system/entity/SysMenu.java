package com.hailu.cloud.api.admin.module.system.entity;
import lombok.Data;
import java.util.Date;

/**
 * 系统菜单
 *
 * @author zhijie
 */
@Data
public class SysMenu {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单URL路径
     */
    private String url;

    /**
     * 菜单权限编码
     */
    private String permissionCode;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 启用状态
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

}
