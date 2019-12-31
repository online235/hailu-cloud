package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McSysTag;
import com.hailu.cloud.api.merchant.module.merchant.result.McSysTagResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McShopTagService;
import com.hailu.cloud.api.merchant.module.merchant.service.McSysTagService;
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

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 店铺标签
 * @Date: 2019/12/16 0016
 * @program: cloud
 * @create: 2019-12-16 16:
 */
@RestController
@RequestMapping("/app/tag")
@Validated
@Api(tags = "标签")
@Slf4j
public class McShopTagController {

    @Autowired
    private McShopTagService mcShopTagService;
    @Resource
    private McSysTagService mcSysTagService;

    @ApiOperation(value = "获取标签详情")
    @GetMapping("/findAllTagByStore")
    @ApiImplicitParam(name = "id", value = "店铺id", paramType = "query", dataType = "Long", required = true)
    public List<McSysTagResult> findAllTagByStore(@NotNull @RequestParam(value = "id") Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("参数不能为空！");
        }
        return mcSysTagService.findAllTagByStore(id);
    }

    @ApiOperation(value = "根据店铺编号添加标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", value = "标签编号", allowMultiple = true, required = true, paramType = "query"),
            @ApiImplicitParam(name = "storeId", value = "店铺编号", required = true, paramType = "query")
    })
    @PostMapping("/addShopTag")
    public void addMcSHopTag(@RequestParam(value = "tagId") Long[] tagId,@NotNull @RequestParam(value = "storeId") Long storeId) throws BusinessException {
        if(storeId == null){
            throw new BusinessException("店铺id不能为空！");
        }
        mcShopTagService.addOrUpdateMcSHopTag(tagId, storeId);
    }


    @ApiOperation(value = "标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query", defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "页面大小", required = true, paramType = "query", defaultValue = "1", dataType = "Integer")
    })
    @GetMapping("/findMcSysTagList")
    public PageInfoModel<List<McSysTag>> findMcSysTagList(
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据") @Min(value = 10, message = "每页最少显示10条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {

        return mcSysTagService.findMcSysTagList(page, size);
    }


}
