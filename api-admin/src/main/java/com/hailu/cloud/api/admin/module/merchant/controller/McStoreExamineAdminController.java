package com.hailu.cloud.api.admin.module.merchant.controller;


import com.hailu.cloud.api.admin.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.admin.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.admin.module.merchant.model.McStoreExamineModel;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McStoreExamineListParameter;
import com.hailu.cloud.api.admin.module.merchant.service.McStoreExamienService;
import com.hailu.cloud.api.admin.module.merchant.service.impl.McStoreInformationAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: zhangmugui
 * @Description: 店铺管理后台
 */
@RestController
@RequestMapping("/pc/store-exanime")
@Validated
@Api(tags = "商户-店铺管理-店铺信息审批")
@Slf4j
public class McStoreExamineAdminController {


    @Resource
    private McStoreExamienService mcStoreExamienService;

    @ApiOperation(value = "显示店铺审核列表")
    @PostMapping("selectMcStoreExamineList")
    public PageInfoModel<List<McStoreExamineModel>> selectMcStoreExamineList(@ModelAttribute McStoreExamineListParameter mcStoreExamineListParameter) {

        return mcStoreExamienService.selectMcStoreExamineModelList(mcStoreExamineListParameter);

    }


    @ApiOperation(value = "更改电话审核状态")
    @PostMapping("updatePhoneToExamine")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "审核表id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phoneToExamine", value = "状态:审核中-1'''',''''审核通过-2'''',''''审核不通过-3", required = true, paramType = "query")
    })
    public void updatePhoneToExamine(@NotNull(message = "编号不能为空") Long id, @NotNull(message = "更改的状态不能为空") Integer phoneToExamine) throws BusinessException {

        mcStoreExamienService.updatePhoneToExamine(id, phoneToExamine);

    }

    @ApiOperation(value = "更改地址审核状态")
    @PostMapping("updateAddressToExamine")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "审核表id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "phoneToExamine", value = "状态:审核中-1'''',''''审核通过-2'''',''''审核不通过-3", required = true, paramType = "query")
    })
    public void updateAddressToExamine(@NotNull(message = "编号不能为空") Long id, @NotNull(message = "更改的状态不能为空") Integer addressToExamine) throws BusinessException {

        mcStoreExamienService.updateAddressToExamine(id,addressToExamine);

    }


    @ApiOperation(value = "获取审批详情")
    @PostMapping("selectMcStoreExamineDetail")
    @ApiImplicitParam(name = "id", value = "审核表id", required = true, paramType = "query")
    public McStoreExamineModel selectMcStoreExamineDetail(@NotNull(message = "编号不能为空") Long id) {

        McStoreExamine mcStoreExamine = mcStoreExamienService.findObjectById(id);
        McStoreExamineModel mcStoreExamineModel = new McStoreExamineModel();
        BeanUtils.copyProperties(mcStoreExamine,mcStoreExamineModel);
        return  mcStoreExamineModel;
    }




}
