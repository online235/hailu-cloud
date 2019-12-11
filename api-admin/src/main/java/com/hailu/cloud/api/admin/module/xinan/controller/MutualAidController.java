package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.entity.Helppictures;
import com.hailu.cloud.api.admin.module.xinan.entity.MutualAid;
import com.hailu.cloud.api.admin.module.xinan.service.impl.HelpPicturesService;
import com.hailu.cloud.api.admin.module.xinan.service.impl.MutualAidService;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安互助接口
 * @Date: 16:32 2019/11/2 0002
 */
@RestController
@RequestMapping("/pc/xinAnBackstage")
@Validated
@Api(tags = "心安-互助-管理后台")
@Slf4j
public class MutualAidController {

    @Autowired
    private MutualAidService mutualAidService;

    @Autowired
    private HelpPicturesService helpPicturesService;


    @ApiOperation(value = "互助审核列表", notes = "")
    @PostMapping("/findMutualAidList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value = "第N页", required = true, paramType="query", dataType = "Integer"),
            @ApiImplicitParam(name="size", value = "页面大小" , required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name="rescueType", value = "救助类型(助学-1,助残-2,助老-3,疾病-4,扶贫-5,公益-6,救灾-7 ,医疗-8,就业-9,自然-10)" ,  paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name="diseaseName", value = "确诊病名",  paramType = "query", dataType = "String")

    })
    public PageInfoModel<List<MutualAid>> findMutualAidList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            Integer rescueType,
            String diseaseName){

        return mutualAidService.findMutualAidList(page, size, rescueType, diseaseName);
    }

    @ApiOperation(value = "根据便号查询互助详细信息")
    @GetMapping("/MutualAidDetails")
    @ResponseBody
    @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query")
    public MutualAid findMutualAid(
            @NotNull(message = "编号不能为空") Long numberId){

        return mutualAidService.findMutualAid(numberId);
    }

    @ApiOperation(value = "根据互助编号查询互助者图片列表")
    @GetMapping("/MutualAidPicture")
    @ResponseBody
    @ApiImplicitParam(name = "mutualAid", value = "编号", required = true, paramType = "query")
    public List<Helppictures> findHelpPicturesList(
            @NotNull(message = "编号不能为空") Long mutualAid){

        return helpPicturesService.findHelpPicturesList(mutualAid);
    }

    @ApiOperation(value = "更改审核状态")
    @PostMapping("/changeAuditStatus")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examine", value = "审核(审核中-1、审核通过-2、审核不通过-3)", required = true, paramType = "query"),
            @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query")
    })
    public void updateMutualAidOfExamine(
            @NotNull(message = "修改的状态不能为空") Integer examine,
            @NotNull(message = "编号不能为空") Long numberId){

        mutualAidService.updateMutualAidOfExamine(examine,numberId);
    }
}
