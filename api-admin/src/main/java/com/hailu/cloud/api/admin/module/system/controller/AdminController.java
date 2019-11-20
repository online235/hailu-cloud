package com.hailu.cloud.api.admin.module.system.controller;

import com.hailu.cloud.api.admin.module.system.service.IAdminService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysAdminModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "系统管理-管理员")
@RequestMapping("/system")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "查询列表", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "account", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "10", paramType = "query", dataType = "String"),
    })
    @GetMapping("/admins")
    public PageInfoModel<List<SysAdminModel>> list(
            String nickName,
            String account,
            Integer status,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @Max(value = 200, message = "每页最大显示200条")
            @Min(value = 10, message = "每页最少显示10条")
            @RequestParam(name = "pageNum", defaultValue = "10") String pageSize) {

        return adminService.accountList(nickName, account, status, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
    }

    @ApiOperation(value = "添加账号", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @PostMapping("/admins")
    public void addAccount(@Valid SysAdminModel model) {

        adminService.addAccount(model);
    }

}
