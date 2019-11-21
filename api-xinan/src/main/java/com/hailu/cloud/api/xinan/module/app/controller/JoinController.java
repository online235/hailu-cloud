package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.service.impl.InsuredService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.xinan.controller
 * @Author: junpei.deng
 * @CreateTime: 2019-10-23 14:55
 * @Description: 管理后台我想加入
 */
@RestController
@RequestMapping("xinan/platform/join/")
@Validated
@Api(tags = "心安-我要加入-管理后台")
@Slf4j
public class JoinController {

    @Autowired
    private InsuredService insuredService;

    @PostMapping("findList")
    @ApiOperation(notes = "", value = "获取参保人列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "int")
    })
    public Object findList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size){
        log.info("获取参保人信息");
        return insuredService.findList(page,size);
    }

    @PostMapping("findInsured")
    @ApiOperation(value = "获取参保人详细信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", dataType = "String")
    public Object findInsuredById(@NotBlank(message = "编号不能为空") String id) throws BusinessException {
        return insuredService.findInsuredById(id);
    }


    @PostMapping("updMemberStatus")
    @ApiOperation(value = "更改参保人审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memberStatus", value = "会员状态（1-代付款2-待审核3-观察期4-驳回   会员卡还有很多状态，后期再增加）", required = true, paramType = "query", dataType = "int")
    })
    public void updInsureByMemberStatus(
            @NotBlank(message = "编号不能为空") Integer memberStatus,
            @NotBlank(message = "修改状态不能为空") String id) throws BusinessException {

        insuredService.updInsureByMemberStatus(memberStatus,id);
    }

}
