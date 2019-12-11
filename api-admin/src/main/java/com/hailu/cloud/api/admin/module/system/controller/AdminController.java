package com.hailu.cloud.api.admin.module.system.controller;

import com.hailu.cloud.api.admin.module.system.service.IAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysAdminModel;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "系统管理-管理员")
@RequestMapping("/system/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "列表查询", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'data': {\n" +
            "        'totalPage': 1,\n" +
            "        'datas': [{\n" +
            "            'id': 1,\n" +
            "            'nickName': 'zhijie',              // 账号昵称\n" +
            "            'account': '13825693085',          // 账号\n" +
            "            'enableStatus': 0,                 // 状态\n" +
            "            'enableStatusDisplay': '启用',     // 状态中文描述\n" +
            "            'phone': '13825693085'             // 手机号\n" +
            "        }]\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "昵称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "account", value = "账号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "启用状态", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "accountType", value = "账号类型（管理员-1、政府-2）", paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "String"),
    })
    @GetMapping("/list")
    public PageInfoModel<List<SysAdminModel>> list(
            String nickName,
            String account,
            Integer enableStatus,
            Integer accountType,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return adminService.accountList(nickName, account, enableStatus, accountType, Integer.parseInt(pageNum), pageSize);
    }

    @ApiOperation(value = "添加账号", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @PostMapping("/add-account")
    public void addAccount(@Valid SysAdminModel model) throws BusinessException {

        adminService.addAccount(model);
    }

    @ApiOperation(value = "修改密码", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPwd", value = "旧密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true, paramType = "query", dataType = "String"),
    })
    @PatchMapping("/modify-pwd")
    public void changePwd(
            @NotBlank(message = "旧密码不能为空") String oldPwd,
            @NotBlank(message = "新密码不能为空") String newPwd) throws BusinessException {

        adminService.changePwd(oldPwd, newPwd);
    }

    @ApiOperation(value = "重置密码", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账号ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPwd", value = "新密码", required = true, paramType = "query", dataType = "String"),
    })
    @PatchMapping("/reset-pwd")
    public void changePwdByAdmin(
            @NotNull(message = "账号ID不能为空") Long id,
            @NotBlank(message = "新密码不能为空") String newPwd) {

        adminService.changePwdByAdmin(id, newPwd);
    }

    @ApiOperation(value = "根据账号查询用户信息", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "登录账号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "状态0禁用，1启用", paramType = "query", dataType = "String"),
    })
    @GetMapping("/search-account")
    public SysAdminModel searchAccount(
            @NotBlank(message = "登录账号不能为空") String account,
            @RequestParam(value = "enableStatus", defaultValue = "1", required = false)
            @Pattern(regexp = "^$|^[01]$", message = "状态值只能为0或1") String enableStatus) {

        return adminService.searchAccount(account, Integer.parseInt(enableStatus));
    }

    @ApiOperation(value = "变更账号启用状态", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账号ID", paramType = "query", required = true, dataType = "String"),
            @ApiImplicitParam(name = "enableStatus", value = "状态0，1启用", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/change-status")
    public void changeStatus(
            @NotNull(message = "账号ID不能为空") Long id,
            @NotNull(message = "状态不能为空") Integer enableStatus) throws BusinessException {

        adminService.changeStatus(id, enableStatus);
    }

    @ApiOperation(value = "变更角色", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账号ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "roleIds", value = "角色ID，多个角色用逗号隔开", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/change-roles")
    public void changeRoles(
            @NotNull(message = "账号ID不能为空") Long id,
            @NotNull(message = "角色不能为空") String roleIds) throws BusinessException {

        adminService.changeRoles(id, roleIds);
    }

}
