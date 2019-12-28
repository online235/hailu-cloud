package com.hailu.cloud.api.admin.module.partnermanagement.controller;

import com.hailu.cloud.api.admin.module.partnermanagement.model.HlIncomeTransferOutListModel;
import com.hailu.cloud.api.admin.module.partnermanagement.model.HlIncomeTransferOutModel;
import com.hailu.cloud.api.admin.module.partnermanagement.service.impl.HlIncomeTransferOutService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangmugui
 * @Description: 合伙人后台——提现管理
 * @program: cloud
 */
@RestController
@RequestMapping("/pc/withdrawal")
@Validated
@Api(tags = "合伙人后台——提现管理")
@Slf4j
public class WithdrawalController {


    @Resource
    private HlIncomeTransferOutService hlIncomeTransferOutService;


    @ApiOperation(value = "查询提现列表")
    @PostMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", defaultValue = "1", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小", defaultValue = "20", required = true, paramType = "query"),
            @ApiImplicitParam(name = "createTimeStar", value = "申请提现时间，开始（yyyy-MM-dd）", paramType = "query"),
            @ApiImplicitParam(name = "createTimeEnd", value = "申请提现时间，结束（yyyy-MM-dd）", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "提现状态 0申请中 1申请成功 2已拒绝", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "用户手机号码", paramType = "query"),
            @ApiImplicitParam(name = "memberName", value = "用户名", paramType = "query"),
            @ApiImplicitParam(name = "examineTime", value = "审核时间，（yyyy-MM-dd）", paramType = "query")
    })
    public PageInfoModel<List<HlIncomeTransferOutListModel>> findHlIncomeTransferOutListModel(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size,
            String createTimeStar, String createTimeEnd, String examineTime, Integer state, String phone, String memberName) {

        Map map = new HashMap(8);
        map.put("createTimeStar", createTimeStar);
        map.put("createTimeEnd", createTimeEnd);
        map.put("examineTime", examineTime);
        map.put("state", state);
        map.put("phone", phone);
        map.put("memberName", memberName);
        return hlIncomeTransferOutService.findListByParameterNewPage(page, size, map);

    }


    @ApiOperation(value = "查询提现详情")
    @PostMapping("/getHlIncomeTransferOutModelDetail")
    @ApiImplicitParam(name = "id", value = "提现数据id", required = true, paramType = "query")
    public HlIncomeTransferOutModel getHlIncomeTransferOutModelDetail(@NotNull Long id) throws BusinessException {

        if (id == null) {
            throw new BusinessException("id不能为空！");
        }
        return hlIncomeTransferOutService.findById(id);
    }


    @ApiOperation(value = "修改审核状态")
    @PostMapping("/updateToExamine")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提现数据id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "state", value = "审核状态：0申请中 1申请成功 2已拒绝", required = true, paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "备注", required = true, paramType = "query")
    })
    public void updateToExamineString(Long id, Integer state, String remark) throws BusinessException {

        if (id == null) {
            throw new BusinessException("id不能为空！");
        }
        hlIncomeTransferOutService.update(id, state, remark);

    }


    @ApiOperation(value = "到处Excel")
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {

        hlIncomeTransferOutService.excelWriter(response);
    }


}
