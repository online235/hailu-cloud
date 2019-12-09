package com.hailu.cloud.api.mall.module.serviceproviders.controller;

import com.hailu.cloud.api.mall.module.serviceproviders.service.IServiceProvidersService;
import com.hailu.cloud.api.mall.module.serviceproviders.vo.ServiceProvidersDto;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 加入服务商
 * junpei.deng
 * 2019-12-05
 * @author 190726
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/serviceProviders")
@Api(tags = "服务商相关接口")
public class ServiceProvidersController {

    @Resource
    IServiceProvidersService serviceProvidersService;

    @ApiOperation(value = "保存/修改服务商",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": 9148654002742788           // 服务商ID\n" +
            "}")
    @PostMapping("/save")
    public Long save(@Validated @RequestBody ServiceProvidersDto serviceProvidersDto) throws BusinessException {
      log.info("保存服务商数据:{}",serviceProvidersDto.toString());
      return serviceProvidersService.saveServiceProvider(serviceProvidersDto);
    }

    @ApiOperation(value = "查询服务商信息",notes = "\"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"id\": 9148654002742790,      //id\n" +
            "    \"name\": \"test\",            //名称\n" +
            "    \"phone\": \"13800138000\",    //手机号码\n" +
            "    \"provinceId\": 5,             //省ID\n" +
            "    \"cityId\": 20,                //市ID\n" +
            "    \"areaId\": 10,                //区县ID\n" +
            "    \"address\": \"12123\"         //详细地址\n" +
            "  }")
    @GetMapping("/findDetail")
    public ServiceProvidersDto findDetail(){
        log.info("查询服务商数据");
        return serviceProvidersService.findDetail();
    }


    /**
     * 根据userId修改服务商状态
     * @param userId
     * @param isService
     * @return
     */
    @GetMapping("/updateStatusByUserId")
    public Integer updateStatusByUserId(@NotBlank(message = "用户ID不能为空") @RequestParam("userId") String userId, @NotNull(message = "服务商状态不能为空") @RequestParam("isService")int isService){
        log.info("更改服务商状态，用户ID：{}，状态：{}",userId,isService);
        return serviceProvidersService.updateStatusByUserId(userId,isService);
    }


}
