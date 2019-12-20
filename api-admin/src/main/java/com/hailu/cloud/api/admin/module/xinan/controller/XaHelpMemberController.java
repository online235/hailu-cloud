package com.hailu.cloud.api.admin.module.xinan.controller;


import com.hailu.cloud.api.admin.module.xinan.entity.XaHelpMember;
import com.hailu.cloud.api.admin.module.xinan.model.XaHelpMemberModel;
import com.hailu.cloud.api.admin.module.xinan.parameter.HelpPictureParameter;
import com.hailu.cloud.api.admin.module.xinan.parameter.XaHelpMemberParameter;
import com.hailu.cloud.api.admin.module.xinan.parameter.XaHelpMemberParameterUpdate;
import com.hailu.cloud.api.admin.module.xinan.service.XaHelpMenberService;
import com.hailu.cloud.api.admin.module.xinan.service.impl.HelpPicturesService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangmugui
 * @Date 2019-12-11
 */
@RestController
@RequestMapping("/pc/xaHelpMember")
@Validated
@Api(tags = "心安-心安救助历史案例")
@Slf4j
public class XaHelpMemberController {


    @Resource
    private XaHelpMenberService xaHelpMenberService;
    @Resource
    private HelpPicturesService helpPicturesService;

    @ApiOperation(value = "查询救助历史案例列表")
    @PostMapping("/list")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query"),
            @ApiImplicitParam(name = "timeDate", value = "时间（yyyy-MM）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "periodsNumber", value = "期数", required = false, paramType = "query"),

    })
    public PageInfoModel<List<XaHelpMemberModel>> findXaHelpMemberModelList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size, String timeDate, Integer periodsNumber) {

        Map map = new HashMap();
        map.put("timeDate", timeDate);
        map.put("periodsNumber", periodsNumber);
        return xaHelpMenberService.findListByParameterNewPage(page, size, map);
    }


    @ApiOperation(value = "插入救助案例")
    @PostMapping("/insertXaHelpMember")
    @ResponseBody
    public void insertXaHelpMember(@RequestBody XaHelpMemberParameter xaHelpMemberParameter, BindingResult result) throws BusinessException {
        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        XaHelpMember xaHelpMember = new XaHelpMember();
        BeanUtils.copyProperties(xaHelpMemberParameter, xaHelpMember);
        Long xaHelpMemberId = xaHelpMenberService.insert(xaHelpMember);
        HelpPictureParameter helpPictureParameter = new HelpPictureParameter();
        BeanUtils.copyProperties(xaHelpMemberParameter,helpPictureParameter);
        helpPicturesService.sertHelpPictures(helpPictureParameter,xaHelpMemberId);

    }


    @ApiOperation(value = "更新救助案例数据")
    @PostMapping("/updateXaHelpMember")
    @ResponseBody
    public void updateXaHelpMember(@RequestBody XaHelpMemberParameterUpdate xaHelpMemberParameterUpdate) throws BusinessException {

        if (xaHelpMemberParameterUpdate.getId() == null) {
            throw new BusinessException("id不能为空！");
        }
        XaHelpMember xaHelpMember = new XaHelpMember();
        BeanUtils.copyProperties(xaHelpMemberParameterUpdate, xaHelpMember);
        xaHelpMenberService.update(xaHelpMember);
        HelpPictureParameter helpPictureParameter = new HelpPictureParameter();
        BeanUtils.copyProperties(xaHelpMemberParameterUpdate,helpPictureParameter);
        helpPicturesService.sertHelpPictures(helpPictureParameter,xaHelpMember.getId());

    }


    @ApiOperation(value = "查询单条救助案例详情")
    @PostMapping("/getXaHelpMemberModelDetail")
    @ResponseBody
    @ApiImplicitParam(name = "id", value = "期数id", required = true, paramType = "query")
    public XaHelpMemberModel getXaHelpMemberModelDetail(@NotNull Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("id不能为空！");
        }
        Map map = new HashMap(2);
        map.put("id", id);
        return xaHelpMenberService.findListByParameter(map).get(0);

    }


}
