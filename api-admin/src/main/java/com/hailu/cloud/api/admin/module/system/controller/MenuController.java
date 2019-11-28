package com.hailu.cloud.api.admin.module.system.controller;

import com.hailu.cloud.api.admin.module.system.service.IMenuService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
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
@Api(tags = "系统管理-菜单")
@RequestMapping("/system/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @ApiOperation(value = "列表查询", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'data': {\n" +
            "        'totalPage': 1,\n" +
            "        'datas': [{\n" +
            "            'id': 1,\n" +
            "            'menuName': '系统管理',            // 菜单名称\n" +
            "            'permissionCode': 'MENU-LIST',    // 权限编码\n" +
            "            'url': '/system/menu/list',        // 菜单URL\n" +
            "            'menuType': '0',                   // 菜单类型\n" +
            "            'menuTypeDisplay': '菜单',         // 菜单类型中文描述\n" +
            "            'enableStatus': 0,                // 状态\n" +
            "            'enableStatusDisplay': '启用',     // 状态中文描述\n" +
            "        }]\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuName", value = "菜单名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "menuType", value = "菜单类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "启用状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "String"),
    })
    @GetMapping("/list")
    public PageInfoModel<List<SysMenuModel>> list(
            String menuName,
            Integer enableStatus,
            Integer menuType,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return menuService.menuList(menuName, menuType, enableStatus, Integer.parseInt(pageNum), pageSize);
    }

    @ApiOperation(value = "添加菜单", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @PostMapping("/add-menu")
    public void addAccount(@Valid SysMenuModel model) {

        menuService.addMenu(model);
    }

    @ApiOperation(value = "编辑菜单", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @PostMapping("/update-menu")
    public void updateAccount(@Valid SysMenuModel model) throws BusinessException {

        menuService.updateMenu(model);
    }

    @ApiOperation(value = "变更菜单启用状态", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "状态0，1启用", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/change-status")
    public void changeStatus(
            @NotNull(message = "菜单ID不能为空") Long id,
            @NotNull(message = "状态不能为空") Integer enableStatus) {

        menuService.changeStatus(id, enableStatus);
    }

}
