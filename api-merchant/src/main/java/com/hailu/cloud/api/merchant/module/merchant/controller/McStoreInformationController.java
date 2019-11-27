package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.McStoreInformationModel;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McStoreInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/store")
@Validated
@Api(tags = "商户-物联云商-APP")
@Slf4j
public class McStoreInformationController {

    @Autowired
    private McStoreInformationService mcStoreInformationService;

    @ApiOperation(value = "更改信息店铺信息")
    @PostMapping("/changeInformation")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wee", value = "每周营业日用，“；”隔开（例1；2；3；4:）", allowMultiple = true, paramType = "query", dataType = "int"),
    })
    public void updateBYMcEntryInformation(McStoreInformationModel mcStoreInformationModel, int[] wee) {
        mcStoreInformationService.updateBYMcEntryInformation(mcStoreInformationModel, wee);
    }

    @ApiOperation(value = "店铺信息")
    @PostMapping("storeInformation")
    @ResponseBody
    public McStoreInformation findMcStoreInformation() {
        return mcStoreInformationService.findMcStoreInformation();
    }



}
