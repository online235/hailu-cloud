package com.hailu.cloud.api.notify.module.sms.controller;

import com.hailu.cloud.api.notify.module.sms.model.SmsModel;
import com.hailu.cloud.api.notify.module.sms.service.ISmsService;
import com.hailu.cloud.common.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "短信")
@RequestMapping("/sms")
public class SmsController {

    @Resource(name = "freeSmsServiceImpl")
    private ISmsService freeSmsService;

    @ApiOperation(value = "第三方免费短信接口-超过免费次数会限制发送", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': null,\n" +
            "  'data': null\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "message", value = "短信内容", paramType = "query", dataType = "String"),
    })
    @GetMapping("/send/free")
    public ApiResponse send(
            @NotBlank(message = "手机号码不能为空") String phone,
            @NotBlank(message = "短信内容不能为空") String message) {

        SmsModel smsModel = freeSmsService.send(phone, message);
        return smsModel.getState() == 0 ? ApiResponse.result() : ApiResponse.abnormalParameter(smsModel.getMsgState());
    }

}
