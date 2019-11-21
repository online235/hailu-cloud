package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.service.impl.ShopMemBerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @Author: QiuFeng:WANG
 * @Description: 后台用户操作接口
 * @Date: 16:33 2019/11/2 0002
 */
@RestController
@RequestMapping("/pc/xinanbackstage")
@Validated
@Api(tags = "心安-会员模块-管理后台")
@Slf4j
public class PcShopMemberController {

    @Autowired
    private ShopMemBerService shopMemBerService;

    @ApiOperation(value = "用户列表", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"total\": 3,    // 记录条数\n" +
            "  \"pages\": 3,    // 页数\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"userId\": \"5075b82803bc4065975fbb545a89a91a\",\n" +
            "      \"loginName\": \"13927555292\",          //登陆名称\n" +
           /* "      \"loginPasswd\": null,\n" +
            "      \"userIcon\": null,\n" +
            "      \"userName\": null,\n" +
            "      \"nickName\": null,\n" +
            "      \"userMobile\": null,\n" +
            "      \"qq\": null,\n" +
            "      \"wechat\": null,\n" +
            "      \"email\": null,\n" +*/
            "      \"memberName\": \"13927555292\",         //会员名称\n" +
            "      \"registTime\": \"2019-10-26\",          //注册时间\n" +
            "      \"memberMobile\": \"13927555292\",       //手机号码\n" +
           /* "      \"profession\": null,\n" +
            "      \"birthday\": null,\n" +
            "      \"createTime\": 0,\n" +
            "      \"integral\": 0,\n" +
            "      \"unReadMsgs\": 0,\n" +
            "      \"inviteCode\": null,\n" +
            "      \"beInviteUser\": null,\n" +
            "      \"unionid\": null,\n" +
            "      \"openId\": null,\n" +
            "      \"createDate\": null,\n" +
            "      \"memberSex\": \"1\",\n" +
            "      \"sourceRegistration\": null,\n" +
            "      \"cid\": null,\n" +
            "      \"systemType\": 1,\n" +*/
            "      \"merchantType\": 0,                         //商户类型 0_无，1_区域代理，2_服务商\n" +
            /*"      \"superiorMember\": null,\n" +
            "      \"cumulativeMerchantsMoney\": null,\n" +
            "      \"freezeWithdrawMerchants\": null,\n" +
            "      \"availableWithdrawMerchants\": null,\n" +
            "      \"totalMerchantsMoney\": null,\n" +*/
            "      \"hlMember\": 0,                             //是否为海露会员（0-否、1-是）\n" +
            "      \"memberId\": 114,                           //用户编号\n" +
            /*"      \"memberTruename\": null,\n" +
            "      \"memberAvatar\": null,\n" +
            "      \"memberBirthday\": null,\n" +
            "      \"memberPasswd\": null,\n" +
            "      \"memberQq\": null,\n" +
            "      \"memberWw\": null,\n" +*/
            "      \"memberLoginNum\": 1,                        //登录次数\n" +/*
            "      \"memberLoginTime\": null,\n" +
            "      \"memberOldLoginTime\": null,\n" +
            "      \"memberLoginIp\": null,\n" +
            "      \"memberOldLoginIp\": null,\n" +
            "      \"memberOpenid\": null,\n" +
            "      \"memberConsumePoints\": null,\n" +
            "      \"memberRankPoints\": null,\n" +
            "      \"availablePredeposit\": 0,\n" +
            "      \"freezePredeposit\": 0,\n" +
            "      \"consumptionPredeposit\": 0,\n" +
            "      \"withdrawPredeposit\": 0,\n" +
            "      \"totalPredeposit\": 0,\n" +
            "      \"redEnvelope\": 0,\n" +
            "      \"informAllow\": 1,\n" +
            "      \"isBuy\": 1,\n" +
            "      \"isAllowtalk\": 1,\n" +
            "      \"memberState\": 1,\n" +
            "      \"memberCredit\": 0,\n" +
            "      \"memberSnsvisitnum\": 0,\n" +
            "      \"memberAreaid\": null,\n" +
            "      \"memberCityid\": null,\n" +
            "      \"memberProvinceid\": null,\n" +
            "      \"memberAreainfo\": null,\n" +
            "      \"isDel\": 0,\n" +
            "      \"signCode\": null,\n" +
            "      \"signCodeState\": null,\n" +
            "      \"memberGradeid\": null,\n" +
            "      \"memberGrow\": 0,\n" +
            "      \"memberGrowGrade\": 0,\n" +
            "      \"isUpgrade\": \"1\",\n" +
            "      \"memberType\": null,\n" +
            "      \"consumptionIntegral\": 0,\n" +
            "      \"totalIntegral\": 0,\n" +
            "      \"isLqlb\": 0,\n" +
            "      \"growthVal\": null,\n" +
            "      \"idcard\": null,\n" +
            "      \"idcardImgx\": null,\n" +
            "      \"idcardImgy\": null,\n" +
            "      \"isSub\": 0,\n" +
            "      \"auditState\": 0,\n" +
            "      \"payPassword\": null,\n" +
            "      \"auditTime\": null,\n" +
            "      \"memberInfo\": null,\n" +
            "      \"memberPrivacy\": null,\n" +
            "      \"wxstate\": null\n" +*/
            "    }\n" +
            "  ],\n" +
            "  \"limit\": 1,            //每页数量\n" +
            "  \"errmsg\": \"成功\",\n" +
            "  \"page\": 1              // 当前页号，外界传入\n" +
            "}")
    @PostMapping("shoplist")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "membername", value = "会员名称", paramType = "query"),
            @ApiImplicitParam(name = "membermobile", value = "手机号码", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第N页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "每页数量", required = true, paramType = "query")
    })
    public Object findShopMemberList( String membername, String membermobile,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        return shopMemBerService.selectFindShopMember(membername, membermobile,page,limit);
    }

    @ApiOperation(value = "用户详情")
    @PostMapping("shopdetails")
    @ResponseBody
    public Object selectByPrimaryKey(@NotBlank(message = "编号不能为空") String memberid) {
        return shopMemBerService.selectByPrimaryKey(memberid);
    }

}

