package com.hailu.cloud.api.admin.module.openapi.controller;

import com.hailu.cloud.api.admin.module.openapi.service.IOpenApiAccountService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.OpenApiAccountModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author xuzhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "开放平台-账号管理")
@RequestMapping("/open-api/account")
public class OpenApiAccountController {

    @Autowired
    private IOpenApiAccountService openApiAccountService;

    @ApiOperation(value = "列表查询", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'totalPage': 1,\n" +
            "        'total': 1,\n" +
            "        'datas': [{\n" +
            "                'id': '10991529980198912',\n" +
            "                'companyName': '海露科技',     // 接入公司名称\n" +
            "                'appId': 'xxx',                // app id\n" +
            "                'appSecret': 'xxx',            // app secret\n" +
            "                'time': '1575964668422'        // time\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "公司名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "String"),
    })
    @GetMapping("/list")
    public PageInfoModel<List<OpenApiAccountModel>> list(
            String companyName,
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return openApiAccountService.list(companyName, Integer.parseInt(pageNum), pageSize);
    }

    @ApiOperation(value = "添加账号", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParam(name = "companyName", value = "接入公司名称", paramType = "query", required = true, dataType = "String")
    @PostMapping("/add-account")
    public void addAccount(@NotBlank(message = "接入公司名称不能为空") String companyName) {
        OpenApiAccountModel model = new OpenApiAccountModel();
        model.setCompanyName(companyName);
        openApiAccountService.addAccount(model);
    }

    @ApiOperation(value = "删除账号", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}" +
            "</pre>")
    @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "String")
    @DeleteMapping("/del-account")
    public void delAccount(@NotNull(message = "id不能为空") Long id) {
        openApiAccountService.delAccount(id);
    }

}
