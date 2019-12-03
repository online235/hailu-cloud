package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.CharitableCommonweal;
import com.hailu.cloud.api.xinan.module.app.entity.GovernmentUsers;
import com.hailu.cloud.api.xinan.module.app.service.CharitableCommonwealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 心安-公益
 * @Date: 2019/12/3 0003
 * @program: cloud
 * @create: 2019-12-03 15:
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-政府慈善公益")
@Slf4j
public class CharitableCommonwealController {

    @Autowired
    private CharitableCommonwealService charitableCommonwealService;

    @ApiOperation(value = "根据政府编号查询公益列表", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/publicInterestList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "UsersId", value = "政府编号", required = true, paramType = "query")
    })
    public List<CharitableCommonweal> findCommonwealArticle(@NotNull(message = "政府编号不能为空") Long UsersId){

        return charitableCommonwealService.findCharitableCommonwealByUsersId(UsersId);
    }
}
