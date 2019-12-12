package com.hailu.cloud.api.admin.module.system.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xuzhijie
 */
@Data
public class ChangeMenuParams {

    @NotNull(message = "角色ID不能为空")
    private Long id;

    @NotNull(message = "菜单不能为空")
    private String menuIds;

}
