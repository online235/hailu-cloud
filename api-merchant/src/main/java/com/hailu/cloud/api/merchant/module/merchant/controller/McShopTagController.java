package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.result.McSysTagResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McShopTagService;
import com.hailu.cloud.api.merchant.module.merchant.service.McSysTagService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
            @ApiImplicitParam(name = " storeId", value = "店铺编号", required = true, paramType = "query")
    })
    @PostMapping("/addShopTag")
    public void addMcSHopTag(
            @NotNull(message = "标签编号不能为空") @RequestParam Long[] tagId,
            @NotNull(message = "店铺编号不能为空") @RequestParam Long storeId) {

        mcShopTagService.addOrUpdateMcSHopTag(tagId, storeId);
    }


}
