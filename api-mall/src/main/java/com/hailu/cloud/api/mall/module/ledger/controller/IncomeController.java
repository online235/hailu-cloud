package com.hailu.cloud.api.mall.module.ledger.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.mall.module.ledger.service.IIncomeService;
import com.hailu.cloud.api.mall.module.ledger.service.impl.IncomeDetailLogService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.math.BigDecimal;

/**
 * 收入明细相关接口
 *
 * @Author xuzhijie
 * @Date 2019/10/24 17:28
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/income")
@Api(tags = "商城-个人中心-分账-收入接口")
public class IncomeController {

    @Autowired
    private IIncomeService incomeService;

    @Autowired
    private IncomeDetailLogService incomeDetailLogService;

    @ApiOperation(value = "提现接口", notes = "<pre>" +
            "{\n" +
            "   'code':'0/-1',\n" +
            "   'msg':'成功/错误',\n" +
            "   'serverTime':1571640858311,\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "price", value = "提现金额", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "bankCard", value = "银行卡卡号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "cardholder", value = "持卡人名称", dataType = "String", paramType = "query"),
    })
    @PostMapping("/transfer-out")
    public void transferOut(
            HttpServletRequest request,
            BigDecimal price,
            String bankCard,
            String cardholder) throws BusinessException {


        if(StringUtils.isBlank(bankCard) || bankCard.length() <15 || bankCard.length() >19){
            throw new BusinessException("银行卡号16-19位纯数字");
        }
        boolean flag = incomeService.transferOut(RequestUtils.getMemberLoginInfo().getUserId(), price, bankCard, cardholder);
        if (!flag) {
            throw new BusinessException("申请提现失败");

        }
    }


    @PostMapping(value = "/findAccountDetailList")
    @ApiOperation(notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': [{\n" +
            "            'dateTime': '2018-08-16 17:37:31',\n" +
            "            'money': -3,\n" +
            "            'froms': '提现',\n" +
            "            'afterMoney': 7\n" +
            "        }, {\n" +
            "            'dateTime': '2018-08-16 17:37:31',\n" +
            "            'money': 10,\n" +
            "            'froms': '分享收益',\n" +
            "            'afterMoney': 10\n" +
            "        }, {\n" +
            "            'dateTime': '2018-08-16 17:37:31',\n" +
            "            'money': 4,\n" +
            "            'froms': '名下服务商分享收益',\n" +
            "            'afterMoney': 5\n" +
            "        }\n" +
            "    ],\n" +
            "    'serverTime': 1571983782885\n" +
            "}\n" +
            "</pre>", value = "获取账户流向明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示数量", required = true, paramType = "query", dataType = "int")
    })
    public JSONArray findAccountDetailList(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
        MemberLoginInfoModel model = RequestUtils.getMemberLoginInfo();
        log.info("获取账户流向明细：{}", model.getUserId());
        return incomeDetailLogService.findListByMemberIdPage(model.getUserId(), page, size);
    }


    @PostMapping(value = "/findIncome")
    @ApiOperation(notes = "<pre>\n" +
            "{\n" +
            "    'code': 0,\n" +
            "    'msg': '成功',\n" +
            "    'data': {\n" +
            "        'totalPrice': 219.8,               // 累计收入\n" +
            "        'price': 19.8,                     // 余额\n" +
            "        'availableWithdrawMerchants': 0    // 可提现金额, 可提现金额=\n" +
            "    },\n" +
            "    'serverTime': 1571990545440\n" +
            "}\n" +
            "</pre>", value = "获取账户信息")
    public JSONObject findIncome(HttpServletRequest request) {
        MemberLoginInfoModel model = RequestUtils.getMemberLoginInfo();
        log.info("获取账户信息 ：{}", model.getUserId());
        return incomeService.findIncome(model.getUserId());
    }
}
