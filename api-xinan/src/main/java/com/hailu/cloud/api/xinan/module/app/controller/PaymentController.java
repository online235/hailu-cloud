package com.hailu.cloud.api.xinan.module.app.controller;


import com.hailu.cloud.api.xinan.module.app.service.IPaymentService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @BelongsProject: xinan
 * @Author: junpei.deng
 * @CreateTime: 2019-11-22 18:05
 * @Description: 心安支付
 */
@Slf4j
@RestController
@RequestMapping("/payment")
@Api(tags ="心安支付")
public class PaymentController {

    @Resource
    private IPaymentService paymentService;


    @ApiOperation(notes = "", value = "心安支付接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="payType", value = "支付类型（1-支付宝、2-微信、3-微信H5）", required = true, paramType="query",dataType = "int"),
            @ApiImplicitParam(name="money", value = "金额", required = true, paramType="query",dataType = "double"),
            @ApiImplicitParam(name="insuredIds", value = "参保人(心安支付传)", required = true, paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/orderPay",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> orderPay(@NotNull(message = "支付类型不能为空") Integer payType,
                                       @NotNull(message = "金额不能为空") Double money,
                                       @NotNull(message = "参保人不能为空") String insuredIds)throws BusinessException {
        log.info("心安支付 支付类型：{},金额：{}, 参保人ID：{}",payType,money,insuredIds);
        return paymentService.createOrder(payType,money,insuredIds);
    }

    @RequestMapping(value = "/callbackWechat",method = {RequestMethod.POST,RequestMethod.GET})
    public String callbackWechat(){
        log.info("微信回调开始");
        try {
            paymentService.chinaumsCallback();
            return "SUCCESS";
        }catch (Exception e){
            log.error("微信回调异常{}",e);
            return "FAILED";
        }

    }

    /**
     * 海露订单支付
     */
    @ApiOperation(notes = "", value = "海露订单支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name="payType", value = "支付类型（1-支付宝、2-微信、3-微信H5）", required = true, paramType="query",dataType = "int"),
            @ApiImplicitParam(name="money", value = "金额", required = true, paramType="query",dataType = "double"),
            @ApiImplicitParam(name="address", value = "详细地址", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="provinceId", value = "省ID", required = true, paramType="query",dataType = "Long"),
            @ApiImplicitParam(name="cityId", value = "市ID", required = true, paramType="query",dataType = "Long"),
            @ApiImplicitParam(name="areaId", value = "区县ID", required = true, paramType="query",dataType = "Long"),
            @ApiImplicitParam(name="itemName", value = "商品名称", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="name", value = "名称", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="phone", value = "手机号码", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="chooseCityId", value = "服务商城市（购买服务商时必传）", required = false, paramType="query",dataType = "long"),
            @ApiImplicitParam(name="openid", value = "openId(公众号支付)", required = false, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="remark", value = "备注信息", required = false, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="buyType", value = "购买类型（2-城市合伙人、3-销售）", paramType="query",dataType = "String")
    })
    @PostMapping("/hlOrderPay")
    public Map<String,Object> hlOrderPay(
            @NotNull(message = "支付类型不能为空")Integer payType,
            @NotNull(message = "金额不能为空")Double money,
            @NotBlank(message = "详细地址不能为空") String address,
            @NotNull(message = "省ID不能为空")String provinceId,
            @NotNull(message = "市ID不能为空")String cityId,
            @NotNull(message = "区县ID不能为空")String areaId,
            @NotBlank(message = "商品名称不能为空")String itemName,
            @NotBlank(message = "名称不能为空")String name,
            @NotBlank(message = "手机号码不能为空")String phone,
            @NotNull(message = "服务商城市不能为空")Long chooseCityId,
            String remark,
            String openid,
            @NotNull(message = "购买类型不能为空") Integer buyType,
            @NotBlank(message = "邀请码不能为空") String inviteNum)throws BusinessException {
        log.info("海露支付 支付类型：{},金额：{}",payType,money);
        return paymentService.createHlOrder(payType,money,address,provinceId,cityId,areaId,itemName,name,phone,chooseCityId,openid,remark,buyType,inviteNum);
    }

    @RequestMapping(value = "/callbackWechatHl",method = {RequestMethod.POST,RequestMethod.GET})
    public String callbackWechatHl() throws Exception {
        log.info("海露微信回调开始");
        try {
            paymentService.chinaumsCallbackHl();
            return "SUCCESS";
        }catch (Exception e){
            log.error("微信回调异常{}",e);
            return "FAILED";
        }
    }

    @ApiOperation(notes = "", value = "心安捐赠支付接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="payType", value = "支付类型（1-支付宝、2-微信、3-微信H5）", required = true, paramType="query",dataType = "int"),
            @ApiImplicitParam(name="money", value = "金额", required = true, paramType="query",dataType = "double"),
            @ApiImplicitParam(name="orderId", value = "订单编号", required = true, paramType="query",dataType = "String"),
    })
    @RequestMapping(value = "/donationOrder",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> donationOrder(@NotNull(message = "支付类型不能为空") Integer payType,
                                       @NotNull(message = "金额不能为空") Double money,
                                       @NotNull(message = "订单编号不能为空") String orderId)throws BusinessException {
        log.info("心安支付 支付类型：{},金额：{}, 参保人ID：{}",payType,money,orderId);
        return paymentService.donationOrder(payType,money,orderId);
    }

    @RequestMapping(value = "/callbackWeChatDonation",method = {RequestMethod.POST,RequestMethod.GET})
    public String callbackWeChatDonation() throws Exception {

        log.info("捐赠微信回调开始");
        try {
            paymentService.chinaumsCallbackDonation();
            return "SUCCESS";
        }catch (Exception e){
            log.error("捐赠回调异常{}",e);
            return "FAILED";
        }
    }


}
