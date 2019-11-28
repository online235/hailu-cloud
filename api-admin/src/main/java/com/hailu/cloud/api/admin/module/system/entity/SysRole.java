package com.hailu.cloud.api.admin.module.system.entity;
import lombok.Data;
import java.util.Date;

/**
 * 系统角色
 *
 * @author zhijie
 */
@Data
public class SysRole {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

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
