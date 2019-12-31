package com.hailu.cloud.api.basic.module.nation.controller;

import com.hailu.cloud.api.basic.module.nation.service.INationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 城市地址接口
 * @author junpei.deng
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/nation")
@Api(tags = "城市地址")
public class NationController {

    @Resource
    private INationService nationService;

    @Autowired
    private RedisStandAloneClient redisClient;

    @ApiOperation(value = "根据名称获取城市ID",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": \"2196\"\n" +
            "}")
    @ApiImplicitParam(name = "name", value = "城市名称", required = true, paramType = "query", dataType = "String")
    @GetMapping("/getIdByName")
    public String getIdByName(@NotBlank(message = "名称不能为空") @RequestParam("name") String name){
        return nationService.findByName(name);
    }

    @ApiOperation(value = "把所有字典数据重新加载到Redis", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @GetMapping("/re-cache-redis")
    public void reCacheToRedis() {
        String dictKey = Constant.REDIS_KEY_DICT_CACHE + Constant.REDIS_KEY_DICT_CACHE_NATION;
        String dictKeyDesc = Constant.REDIS_KEY_DICT_CACHE + Constant.REDIS_KEY_DICT_CACHE_NATION_DESC;
        nationService.findAll().forEach(nation -> {
            redisClient.hashSet(dictKey, nation.getCode(), nation.getAreaName());
            redisClient.hashSet(dictKeyDesc,nation.getAreaName(),nation.getCode());
        });
    }
}
