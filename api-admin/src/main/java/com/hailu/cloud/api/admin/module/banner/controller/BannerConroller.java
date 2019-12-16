package com.hailu.cloud.api.admin.module.banner.controller;


import com.hailu.cloud.api.admin.module.banner.entity.SysBanner;
import com.hailu.cloud.api.admin.module.banner.model.SysBannerModel;
import com.hailu.cloud.api.admin.module.banner.parameter.SysBannerParameter;
import com.hailu.cloud.api.admin.module.banner.service.SysBannerService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
 * @Date 2019-12-16
 */
@RestController
@RequestMapping("/banner")
@Validated
@Api(tags = "广告管理——添加广告")
@Slf4j
public class BannerConroller {


    @Resource
    private SysBannerService sysBannerService;


    @ApiOperation(value = "查询广告列表")
    @PostMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", required = false, paramType = "query"),
            @ApiImplicitParam(name = "bannerSpace", value = "广告所在的位置1.心安    2.商城   3、美食", required = false, paramType = "query"),

    })
    public PageInfoModel<List<SysBannerModel>> findBannerModelList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size, String title, Integer bannerSpace) {

        Map map = new HashMap();
        map.put("title", title);
        map.put("bannerSpace", bannerSpace);
        return sysBannerService.findListByParameterNewPage(page, size, map);
    }


    @ApiOperation(value = "查询广告详情")
    @PostMapping("/getBannerDetail")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query")
    public SysBannerModel getBannerDetail(@NotNull Long id) {
        return sysBannerService.findSysBannerModelById(id);
    }

    @ApiOperation(value = "插入广告")
    @PostMapping("/insertBanner")
    @ResponseBody
    public void insertBanner(@RequestBody SysBannerParameter sysBannerParameter) throws BusinessException {

        SysBanner sysBanner = new SysBanner();
        BeanUtils.copyProperties(sysBannerParameter, sysBanner);
        sysBannerService.insertBannerModel(sysBanner);
    }


    @ApiOperation(value = "更新广告")
    @PostMapping("/updateBanner")
    @ResponseBody
    public void updateBanner(@RequestBody SysBannerParameter sysBannerParameter) throws BusinessException {

        if (sysBannerParameter.getId() == null) {
            throw new BusinessException("id不能为空！");
        }
        SysBanner sysBanner = new SysBanner();
        BeanUtils.copyProperties(sysBannerParameter, sysBanner);
        sysBannerService.update(sysBanner);
    }


}
