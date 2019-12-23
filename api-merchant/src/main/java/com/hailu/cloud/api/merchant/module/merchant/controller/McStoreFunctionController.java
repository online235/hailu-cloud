package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreFunction;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreFunctionModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreFunctionService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author zhangmugui
 */
@RestController
@RequestMapping("/app/store-function")
@Validated
@Api(tags = "商户-物联云商-APP店铺预定管理")
@Slf4j
public class McStoreFunctionController {


    @Resource
    private McStoreFunctionService mcStoreFunctionService;

    @ApiOperation(value = "获取店铺预定管理详情")
    @PostMapping("/getStoreFunctionDetail")
    @ApiImplicitParam(name = "storeId", value = "店铺id", paramType = "query", dataType = "Long", required = true)
    public McStoreFunctionModel getStoreFunctionDetail(@RequestParam(value = "storeId") Long storeId) {

        McStoreFunction mcStoreFunction = mcStoreFunctionService.findObjectByStoreId(storeId);
        McStoreFunctionModel mcStoreFunctionModel = new McStoreFunctionModel();
        BeanUtils.copyProperties(mcStoreFunction,mcStoreFunctionModel);
        return mcStoreFunctionModel;
    }


    @ApiOperation(value = "更新保存店铺预定管理")
    @PostMapping("/saveUpdateStoreFunction")
    public void saveUpdateStoreFunction(@RequestBody McStoreFunctionModel mcStoreFunctionModel) throws BusinessException {

        int result = mcStoreFunctionService.saveUpdateStoreFunction(mcStoreFunctionModel);
        if(result <= 0){
            throw new BusinessException("更新异常");
        }

    }



}
