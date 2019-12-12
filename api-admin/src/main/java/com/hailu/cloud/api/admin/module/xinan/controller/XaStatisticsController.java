package com.hailu.cloud.api.admin.module.xinan.controller;


import com.hailu.cloud.api.admin.module.xinan.entity.XaStatistics;
import com.hailu.cloud.api.admin.module.xinan.model.XaStatisticsModel;
import com.hailu.cloud.api.admin.module.xinan.parameter.XaStatisticsParameter;
import com.hailu.cloud.api.admin.module.xinan.parameter.XaStatisticsParameterUpdate;
import com.hailu.cloud.api.admin.module.xinan.service.XaStatisticsService;
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
import java.beans.Beans;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangmugui
 * @Date 2019-12-11
 */
@RestController
@RequestMapping("/pc/xaStatistics")
@Validated
@Api(tags = "心安-心安期数统计")
@Slf4j
public class XaStatisticsController {


    @Resource
    private XaStatisticsService xaStatisticsService;

    @ApiOperation(value = "查询期数统计列表")
    @PostMapping("/list")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query"),
            @ApiImplicitParam(name = "timeDate", value = "时间（yyyy-MM）", required = false, paramType = "query"),
            @ApiImplicitParam(name = "periodsNumber", value = "期数", required = false, paramType = "query"),

    })
    public PageInfoModel<List<XaStatisticsModel>> findXaStatisticsModel(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size, String timeDate, Integer periodsNumber) {

        Map map = new HashMap();
        map.put("timeDate", timeDate);
        map.put("periodsNumber", periodsNumber);
        return xaStatisticsService.findListByParameterNewPage(page, size, map);
    }


    @ApiOperation(value = "插入期数统计数据")
    @PostMapping("/insertXaStatistics")
    @ResponseBody
    public void insertXaStatistics(@RequestBody XaStatisticsParameter xaStatisticsParameter, BindingResult result) throws BusinessException {

        if (result.hasErrors()) {
            throw new BusinessException("必填信息不能为空！");
        }
        XaStatistics xaStatistics = new XaStatistics();
        BeanUtils.copyProperties(xaStatisticsParameter, xaStatistics);
        xaStatisticsService.insert(xaStatistics);
    }



    @ApiOperation(value = "更新期数统计数据")
    @PostMapping("/updateXaStatistics")
    @ResponseBody
    public void updateXaStatistics(@RequestBody XaStatisticsParameterUpdate xaStatisticsParameterUpdate) throws BusinessException {

        if (xaStatisticsParameterUpdate.getId() == null) {
            throw new BusinessException("id不能为空！");
        }
        XaStatistics xaStatistics = new XaStatistics();
        BeanUtils.copyProperties(xaStatisticsParameterUpdate, xaStatistics);
        xaStatisticsService.update(xaStatistics);
    }



    @ApiOperation(value = "查询单条期数统计数据详情")
    @PostMapping("/getXaStatisticsDetail")
    @ResponseBody
    @ApiImplicitParam(name = "id", value = "期数id", required = true, paramType = "query")
    public XaStatisticsModel getXaStatisticsDetail(@NotNull Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("id不能为空！");
        }
        Map map = new HashMap();
        map.put("id", id);
        return xaStatisticsService.findListByParameter(map).get(0);

    }



}
