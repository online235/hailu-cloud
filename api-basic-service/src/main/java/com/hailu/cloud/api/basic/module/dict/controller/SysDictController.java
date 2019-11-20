package com.hailu.cloud.api.basic.module.dict.controller;

import com.hailu.cloud.api.basic.module.dict.service.ISysDictService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.dict.SysDictModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 字典管理
 *
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "字典表")
@RequestMapping("/sys")
public class SysDictController {

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private RedisStandAloneClient redisClient;

    @ApiOperation(value = "根据code,value查询字典项", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': {\n" +
            "        'id': 9223372036854776000, // 主键ID\n" +
            "        'code': 'aaa',             // 字典code\n" +
            "        'desc': null,              // 字典描述\n" +
            "        'name': 'name',            // 字典名称\n" +
            "        'value': 'cc'              // 字典值\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", required = true, value = "字典分组code", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "value", required = true, value = "字典value值", paramType = "query", dataType = "String")
    })
    @GetMapping("/dicts")
    public SysDictModel find(
            @NotNull(message = "字典code不能为空") String code,
            @NotNull(message = "字典value不能为空") String value) {
        return sysDictService.find(code, value);
    }

    @ApiOperation(value = "新增字典项", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': {\n" +
            "        'id': 9223372036854776000, // 主键ID\n" +
            "        'code': 'aaa',             // 字典code\n" +
            "        'desc': null,              // 字典描述\n" +
            "        'name': 'name',            // 字典名称\n" +
            "        'value': 'cc'              // 字典值\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @PostMapping("/dicts")
    public SysDictModel find(@Valid SysDictModel dictModel) throws BusinessException {
        return sysDictService.addDict(dictModel);
    }

    @ApiOperation(value = "把所有字典数据重新加载到Redis", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @PostMapping("/dicts/re-cache-redis")
    public void reCacheToRedis() {
        sysDictService.findAll().forEach(item -> {
            String dictKey = Constant.REDIS_KEY_DICT_CACHE + item.getCode();
            redisClient.hashSet(dictKey, item.getValue(), item.getName());
        });
    }

}
