package com.hailu.cloud.api.xinan.module.app.controller;

import com.hailu.cloud.api.xinan.module.app.entity.Government;
import com.hailu.cloud.api.xinan.module.app.model.GovernmentModel;
import com.hailu.cloud.api.xinan.module.app.service.GovernmentService;
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

import javax.validation.constraints.NotBlank;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/2 0002
 */
@RestController
@RequestMapping("/app/xinan")
@Validated
@Api(tags = "心安-政府慈善公益")
@Slf4j
public class GovernmentController {

    @Autowired
    private GovernmentService governmentUsersService;

    @ApiOperation(value = "根据城市查询文章", notes = "<pre>" +
            "" +
            "</pre>")
    @PostMapping("/CommonwealArticle")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityCode", value = "城市Id", required = true, paramType = "query")
    })
    public GovernmentModel findCommonwealArticle(@NotBlank(message = "城市Id不能为空") String cityCode){

        return governmentUsersService.findGovernmentUsersByCityCode(cityCode);
    }
}
