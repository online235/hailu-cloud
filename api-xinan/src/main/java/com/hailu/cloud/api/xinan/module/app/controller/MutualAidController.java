package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.Helppictures;
import com.hailu.cloud.api.xinan.module.app.entity.MutualAid;
import com.hailu.cloud.api.xinan.module.app.model.MutualAidModel;
import com.hailu.cloud.api.xinan.module.app.service.impl.HelpPicturesService;
import com.hailu.cloud.api.xinan.module.app.service.impl.MutualAidInfoService;
import com.hailu.cloud.api.xinan.module.app.service.impl.MutualAidService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
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
@RequestMapping("/app/xinanhelp")
@Validated
@Api(tags = "心安-互助")
@Slf4j
public class MutualAidController {


    @Autowired
    private MutualAidInfoService mutualAidInfoService;

    @Autowired
    private MutualAidService mutualAidService;

    @Autowired
    private HelpPicturesService helpPicturesService;



    @ApiOperation(notes = "", value = "上传互助信息")
    @PostMapping("/uploadMutualHelp")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="targetAmount", value = "目标金额", required = true, paramType="query", dataType = "BigDecimal", defaultValue = "10000000.00"),
            @ApiImplicitParam(name="title", value = "筹款标题" , required = true, paramType = "query", dataType = "String", defaultValue = "好嗨哟"),
            @ApiImplicitParam(name="explain", value = "救助说明" , required = true, paramType = "query", dataType = "String", defaultValue = "太爽了"),
            @ApiImplicitParam(name="hospitalName", value = "医院名称" , required = true, paramType = "query", dataType = "String", defaultValue = "神经病医院"),
            @ApiImplicitParam(name="hospitalAccount", value = "医院收款账号" , required = true, paramType = "query", dataType = "String", defaultValue = "123456789123456789"),
            @ApiImplicitParam(name="diseaseName", value = "确诊病名" , required = true, paramType = "query", dataType = "String", defaultValue = "艾滋"),
            @ApiImplicitParam(name="picture", value = "图片路径" ,  paramType = "query", allowMultiple=true, dataType = "String")

    })
    public void insHelpANDMtualAid(MutualAid mutualAid, String[] picture) throws BusinessException {

        MemberLoginInfoModel loginInfo = RequestUtils.getMemberLoginInfo();
        mutualAid.setMemberId(loginInfo.getUserId());
        mutualAidInfoService.insHelpANDMtualAid(mutualAid,picture);
    }

    @ApiOperation(value = "互助列表", notes = "")
    @PostMapping("/findMutualAidList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value = "第N页", required = true, paramType="query", dataType = "Integer"),
            @ApiImplicitParam(name="size", value = "页面大小" , required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name="rescueType", value = "救助类型(助学-1,助残-2,助老-3,疾病-4,扶贫-5,公益-6,救灾-7 ,医疗-8,就业-9,自然-10)" ,  paramType = "query", dataType = "Integer")

    })
    public PageInfoModel<List<MutualAidModel>> findMutualAidList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            Integer rescueType){

        return mutualAidService.findMutualAidList(page, size, rescueType);
    }

    @ApiOperation(value = "根据便号查询互助详细信息")
    @PostMapping("/MutualAidDetails")
    @ResponseBody
    @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query")
    public MutualAid findMutualAid(
            @NotNull(message = "编号不能为空") Long numberId){

        return mutualAidService.findMutualAid(numberId);
    }

    @ApiOperation(value = "根据编号查询互助者图片列表")
    @PostMapping("/MutualAidPicture")
    @ResponseBody
    @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query")
    public List<Helppictures> findHelpPicturesList(
            @NotNull(message = "编号不能为空") Long numberId){

        return helpPicturesService.findHelpPicturesList(numberId);
    }
}
