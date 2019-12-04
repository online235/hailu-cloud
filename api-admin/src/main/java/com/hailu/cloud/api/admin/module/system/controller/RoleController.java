package com.hailu.cloud.api.admin.module.system.controller;

import com.hailu.cloud.api.admin.module.system.service.IRoleService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysRoleModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "系统管理-角色")
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @ApiOperation(value = "列表查询", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'data': {\n" +
            "        'totalPage': 1,\n" +
            "        'datas': [{\n" +
            "            'id': 1,\n" +
            "            'roleName': '管理员',              // 账号昵称\n" +
            "            'enableStatus': 0,                // 状态\n" +
            "            'enableStatusDisplay': '启用',     // 状态中文描述\n" +
            "        }]\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "启用状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "String"),
    })
    @GetMapping("/list")
    public PageInfoModel<List<SysRoleModel>> list(
            String roleName,
            Integer enableStatus,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return roleService.roleList(roleName, enableStatus, Integer.parseInt(pageNum), pageSize);
    }

    @ApiOperation(value = "添加角色", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @PostMapping("/add-role")
    public void addAccount(@Valid SysRoleModel model) {

        roleService.addRole(model);
    }

    @ApiOperation(value = "编辑角色", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @PostMapping("/update-role")
    public void updateAccount(@Valid SysRoleModel model) throws BusinessException {

        roleService.updateRole(model);
    }

    @ApiOperation(value = "变更角色启用状态", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "状态0，1启用", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/change-status")
    public void changeStatus(
            @NotNull(message = "角色ID不能为空") Long id,
            @NotNull(message = "状态不能为空") Integer enableStatus) {

        roleService.changeStatus(id, enableStatus);
    }

    @ApiOperation(value = "变更菜单", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuIds", value = "菜单ID，多个菜单用逗号隔开", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/change-menus")
    public void changeRoles(
            @NotNull(message = "角色ID不能为空") Long id,
            @NotNull(message = "菜单不能为空") String menuIds) throws BusinessException {

        roleService.changeMenus(id, menuIds);
    }

}
