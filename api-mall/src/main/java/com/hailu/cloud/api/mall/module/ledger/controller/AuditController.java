package com.hailu.cloud.api.mall.module.ledger.controller;

import com.hailu.cloud.api.mall.module.ledger.po.IncomeTransferOut;
import com.hailu.cloud.api.mall.module.ledger.service.IAuditService;
import com.hailu.cloud.api.mall.module.ledger.vo.IncomeTransferOutVo;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/10/26 16:14
 */
@RestController
@Api(tags = "商城-个人中心-分账-提现审核")
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private IAuditService auditService;

    @ApiOperation(value = "查询提现审核列表", notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': [{\n" +
            "            'id': 'e6f15956-a153-1224-3c1c-3ad399a7e685',  // 收入明细ID\n" +
            "            'memberId': null,                              // 会员表的user_id\n" +
            "            'memberName': null,                            // 会员名称\n" +
            "            'price': 99,                                   // 提现金额\n" +
            "            'bankCard': 'a123456',                         // 银行卡卡号\n" +
            "            'cardholder': '许',                            // 持卡人名称\n" +
            "            'remark': null,                                // 拒绝原因\n" +
            "            'state': 0,                                    // 审核状态：0申请中 1申请成功 2已拒绝\n" +
            "            'createTime': '2019-10-25 22:28:01'            // 提现时间\n" +
            "        }\n" +
            "    ],\n" +
            "    'serverTime': 1572230226134\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transferOut", dataType = "IncomeTransferOut", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "当前页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", dataType = "String", paramType = "query")
    })
    @GetMapping("/list")
    public List<IncomeTransferOutVo> list(
            IncomeTransferOut transferOut,
            @Pattern(regexp = "^$|^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "开始日期格式不正确：yyyy-MM-dd HH:mm:ss") String beginTime,
            @Pattern(regexp = "^$|^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "结束日期格式不正确：yyyy-MM-dd HH:mm:ss") String endTime,
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(defaultValue = "10", required = false) Integer size) {

        return auditService.list(transferOut, beginTime, endTime, page, size);
    }

    @ApiOperation(value = "审核", notes = "<pre>" +
            "{\n" +
            "   'code':'0/-1',\n" +
            "   'msg':'成功/错误',\n" +
            "   'serverTime':1571640858311,\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "审核记录ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "审核状态 1通过，2拒绝", dataType = "String", paramType = "query"),
    })
    @PostMapping("/")
    public void audit(
            HttpServletRequest request,
            @NotNull(message = "审核记录ID不能为空") String id,
            @NotNull(message = "审核状态不能为空")
            @Pattern(regexp = "^[1,2]$", message = "审核状态值不正确：1通过，2拒绝") Integer state) {

        auditService.audit(RequestUtils.getMemberLoginInfo().getUserId(), id, state);
    }


}
