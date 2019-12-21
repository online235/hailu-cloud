package com.hailu.cloud.api.merchant.module.merchant.controller;


import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McStoreExamineParameter;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.api.merchant.module.merchant.result.ExamineResult;
import com.hailu.cloud.api.merchant.module.merchant.result.ShopExamineResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreExamienService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/app/store_exanime")
@Validated
@Api(tags = "商户-物联云商-审核接口")
@Slf4j
public class McStoreExamineController {


    @Resource
    private McStoreExamienService mcStoreExamienService;


    @ApiOperation(value = "提交审批资料")
    @PostMapping("/submitStoreExamine")
    public void submitStoreExamine(@RequestBody @Valid McStoreExamineParameter mcStoreExamineParameter) throws BusinessException {

        mcStoreExamienService.submitStoreExamine(mcStoreExamineParameter);
    }


    @ApiOperation(value = "获取地址电话是否在审核中状态,同时返回原来数据")
    @PostMapping("/getExamineResult")
    @ApiImplicitParam(name = "storeId", value = "店铺id", allowMultiple = true, paramType = "query", dataType = "Long")
    public ExamineResult getExamineResult(Long storeId) throws BusinessException {

        return mcStoreExamienService.getExamineResult(storeId);
    }


}
