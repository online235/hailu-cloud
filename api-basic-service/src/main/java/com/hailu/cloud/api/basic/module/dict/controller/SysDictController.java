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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 字典管理
 *
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "字典表")
@RequestMapping("/system")
public class SysDictController {

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    private RedisStandAloneClient redisClient;

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
    @PostMapping("/dict/add")
    public SysDictModel add(@Valid SysDictModel dictModel) throws BusinessException {
        return sysDictService.addDict(dictModel);
    }

    @ApiOperation(value = "字典分类列表", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': [{\n" +
            "        'code': 'ENABLE_STATUS',   // 字典code\n" +
            "        'desc': '启用状态',         // 字典描述\n" +
            "    }]\n" +
            "}\n" +
            "</pre>")
    @GetMapping("/dict/category")
    public List<SysDictModel> category() {
        return sysDictService.findCategory();
    }

    @ApiOperation(value = "查询字典列表", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @PostMapping("/dict/list")
    public List<SysDictModel> list(String code) {
        return sysDictService.findList(code);
    }

    @ApiOperation(value = "查询字典详情", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @GetMapping("/dict/detail")
    public SysDictModel detail(@NotNull(message = "请选择要查询的记录") Long id) {
        return sysDictService.findById(id);
    }

    @ApiOperation(value = "更新字典数据", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @PostMapping("/dict/update")
    public void update(
            @NotNull(message = "请选择要查询的记录") Long id,
            @NotBlank(message = "code不能为空") String code,
            @NotBlank(message = "desc不能为空") String desc,
            @NotBlank(message = "name不能为空") String name,
            @NotBlank(message = "value不能为空") String value) {

        sysDictService.update(id, code, desc, name, value);
    }

    @ApiOperation(value = "把所有字典数据重新加载到Redis", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @GetMapping("/dict/re-cache-redis")
    public void reCacheToRedis() {
        sysDictService.findList(null).forEach(item -> {
            String dictKey = Constant.REDIS_KEY_DICT_CACHE + item.getCode();
            redisClient.hashSet(dictKey, item.getValue(), item.getName());
        });
    }

    @ApiOperation(value = "删除字典", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': ''\n" +
            "}\n" +
            "</pre>")
    @DeleteMapping("/dict/delete")
    public void delete(@NotNull(message = "请选择要删除的记录") Long id) {
        sysDictService.deleteDict(id);
    }

}
