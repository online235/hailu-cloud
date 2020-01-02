package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.Nation;
import com.hailu.cloud.api.xinan.module.app.service.INationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.pay.service.Impl
 * @Author: junpei.deng
 * @CreateTime: 2019-10-30 11:15
 * @Description: 省市区接口
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/nation")
@Api(tags = "心安-省市区地址相关接口")
public class NationController {

    @Autowired
    private INationService nationService;


    @GetMapping("/findListByParentCode")
    @ApiOperation(value = "获取省市区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentCode", value = "父级ID(查询省信息父类ID为1)",required = true, dataType = "Long", paramType = "query"),
    })
    public List<Nation> findListByParentId(@NotNull(message = "父级ID不能为空") String parentCode) {

        log.info("查询城市地址ID为：{}",parentCode);
        return nationService.findListByParentCode(parentCode);
    }

    @ApiImplicitParam(name = "code", value = "城市代码(如果不传默认查询省)", required = false, paramType = "query", dataType = "String")
    @GetMapping("/findParentListByCode")
    public List<Nation> findParentListByCode( String code){
        log.info("查询城市地址Code为：{}",code);
        return nationService.findParentListByCode(code);
    }

}
