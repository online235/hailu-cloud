package com.hailu.cloud.api.xinan.module.app.controller;

import cn.hutool.core.date.DateUtil;
import com.hailu.cloud.api.xinan.module.app.model.HomeDataListModel;
import com.hailu.cloud.api.xinan.module.app.model.XaStatisticsModel;
import com.hailu.cloud.api.xinan.module.app.service.impl.HomeDateService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author zhangmugui
 * @Date 2019-12-11
 */
@RestController
@RequestMapping("/app/home")
@Validated
@Api(tags = "心安-首页")
@Slf4j
public class HomeController {


    @Resource
    private HomeDateService homeDateService;


    @ApiOperation(value = "首页数据")
    @PostMapping("/homeData")
    @ResponseBody
    public HomeDataListModel findHelpPicturesList(){

        return homeDateService.getHomeDateListModel();
    }


    @ApiOperation(value = "查看更多接口")
    @PostMapping("/getXaHelpMemberList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timeDate", value = "时间（yyyy-MM）", required = true, paramType = "query"),
            @ApiImplicitParam(name = "periodsNumber", value = "期数", required = true, paramType = "query")
    })
    public XaStatisticsModel getXaHelpMemberList( String timeDate, @NotNull Integer periodsNumber) throws BusinessException {

        if(timeDate == null || periodsNumber == null){
            throw new BusinessException("参数不能为空！");
        }
        return homeDateService.getXaHelpMemberList(timeDate,periodsNumber);
    }





}
