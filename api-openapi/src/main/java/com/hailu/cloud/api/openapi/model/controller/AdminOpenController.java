package com.hailu.cloud.api.openapi.model.controller;

import com.hailu.cloud.api.openapi.feigns.AdminFeignCilent;
import com.hailu.cloud.api.openapi.model.entity.CharitableCommonweal;
import com.hailu.cloud.api.openapi.model.entity.Government;
import com.hailu.cloud.api.openapi.model.vo.CharitableCommonwealDto;
import com.hailu.cloud.common.exception.BusinessException;
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
 * @Description: 政府公益开放接口
 * @Date: 2019/12/11 0011
 * @program: cloud
 * @create: 2019-12-11 18:
 */
@RestController
@RequestMapping("/open/publicWelfare")
@Validated
@Api(tags = "政府公益开放接口")
@Slf4j
public class AdminOpenController {

    @Autowired
    private AdminFeignCilent adminFeignCilent;

    /**
     * 查询账号下详细信息
     * @return
     */
    @ApiOperation(value = "查询账号下详细信息", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/detailedInformation")
    @ResponseBody
    public Government findCommonwealArticle(){

        return adminFeignCilent.findCommonwealArticle().getData();
    }

    @ApiOperation(value = "添加文章")
    @PostMapping("/addGovernment")
    @ResponseBody
    @ApiImplicitParam(name = "commonwealArticle", value = "公益文章", required = true, paramType = "query", dataType = "String")
    public Government insertSelective(@RequestBody String commonwealArticle){

        return adminFeignCilent.insertSelective(commonwealArticle).getData();
    }

    @ApiOperation(value = "修改文章")
    @PostMapping("/modify")
    @ResponseBody
    @ApiImplicitParam(name = "commonwealArticle", value = "公益文章", required = true, paramType = "query", dataType = "String")
    public Government modifyCommonwealArticle(@RequestBody String commonwealArticle){

        return adminFeignCilent.modifyCommonwealArticle(commonwealArticle).getData();
    }


    @ApiOperation(value = "管理员根据token查询公益列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/governmentPublicWelfare")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query")
    })
    public PageInfoModel<List<CharitableCommonweal>> findCommonwealArticle(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size){

        return adminFeignCilent.findCommonwealArticle(page,size).getData();
    }


    @ApiOperation(value = "根据编号查询公益详细", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/detailed")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "公益编号", required = true, paramType = "query")
    })
    public CharitableCommonweal findCharitableCommonwealById(@NotNull(message = "编号不能为空") Long Id){

        return adminFeignCilent.findCharitableCommonwealById(Id).getData();
    }



    @ApiOperation(value = "添加公益或者根据编号更改信息", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/addPublicInterest")
    @ResponseBody
    public CharitableCommonweal insertSelective(@RequestBody CharitableCommonwealDto record){
        return adminFeignCilent.insertAndUpdate(record).getData();
    }

}
