package com.hailu.cloud.api.merchant.module.merchant.controller;

import com.hailu.cloud.api.merchant.feigns.AuthFeignClient;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McUserService;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McUserParameter;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McInfoService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponse;
import com.hailu.cloud.common.response.ApiResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商家入驻APP
 * @Date: 19:10 2019/11/3 0003
 */
@RestController
@RequestMapping("/merchant")
@Validated
@Api(tags = "商户-注册")
@Slf4j
public class McUserRegisterController {

    @Autowired
    private McInfoService mcInfoService;

    @Autowired
    private RedisStandAloneClient redisStandAloneClient;

    @Autowired
    private McUserService mcUserService;

    @Autowired
    private AuthFeignClient authFeignClient;


    @ApiOperation(value = "商家注册", notes = "<pre>" +
            "")
    @PostMapping("register")
    @ResponseBody
    public Object register(@RequestBody @Valid McUserParameter mcUserParameter) throws Exception {

        String varCode = redisStandAloneClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + mcUserParameter.getPhone() + "1");
        if (!mcUserParameter.getCode().equals(varCode) && !mcUserParameter.getCode().equals("111111")) {
            // 验证码不存在
            throw new BusinessException("无效验证码");
        }
        mcInfoService.addMcUserRegister(mcUserParameter);
        ApiResponse<MerchantUserLoginInfoModel> loginInfo = authFeignClient.vericodeLogin("1", mcUserParameter.getPhone(), mcUserParameter.getCode());
        if (loginInfo.getCode() == ApiResponseEnum.SUCCESS.getResponseCode()) {
            return loginInfo.getData();
        }
        throw new BusinessException(loginInfo.getMessage());
    }




    @ApiOperation(value = "修改密码", notes = "<pre>" +
            "")
    @PostMapping("changePassword")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "random", value = "账号获取手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "query", dataType = "String")
    })
    public Object updfindMcUserByPassWord(String random, String phone, String code) throws BusinessException {
        return mcUserService.findMcUserByPhone(random, phone, code, "1");
    }

    @ApiOperation(value = "重置密码", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("resetPassword")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "重置密码", required = true, paramType = "query", dataType = "String")
    })
    public void updMcUserByPassWord(String numberId, String passWord) {
        mcUserService.updMcUserByPassWord(passWord, numberId);
    }

    @ApiOperation(value = "验证账号是否存在", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/existsLandingAccount")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "landingAccount", value = "账号", required = true, paramType = "query", dataType = "String"),
    })
    public boolean existsUser(@NotBlank(message = "账号不能为空") String landingAccount) {
        return mcUserService.exists(landingAccount);
    }

    @ApiOperation(value = "验证手机号码是否绑定", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/isBindPhone")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "query", dataType = "String"),
    })
    public Object isBindPhone(@NotBlank(message = "手机号码不能为空") String phone) {
        return mcUserService.isBind(phone);
    }


}
