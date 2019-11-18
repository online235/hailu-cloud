package com.hailu.cloud.api.basic.module.uid.controller;

import com.hailu.cloud.api.basic.module.uid.component.UidGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 获取分布式ID
 *
 * @author zhijie
 */
@Slf4j
@Validated
@RestController
@Api(tags = "分布式ID")
@RequestMapping("/uuid")
public class UuidController {

    @Autowired
    private UidGenerator uidGenerator;

    @ApiOperation(value = "生成一个分布式uuid", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': 387113992347148\n" +
            "}" +
            "</pre>")
    @GetMapping("/create")
    public long uuid() {
        return uidGenerator.uuid();
    }

    @ApiOperation(value = "解析分布式uuid构成", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': {\n" +
            "        'uid': 388428252340224,                // uuid\n" +
            "        'sequence': 0,                         // 自增序列\n" +
            "        'workerId': 48,                        // 主机节点ID\n" +
            "        'timestamp': '2019-11-12 01:07:18'     // 时间戳\n" +
            "    }\n" +
            "}\n" +
            "</pre>")
    @ApiImplicitParam(name = "uuid", required = true, value = "分布式UUID", paramType = "path", dataType = "String")
    @GetMapping("/parse/{uuid}")
    public Map<String, Object> parse(@PathVariable long uuid) {
        return uidGenerator.parseUuid(uuid);
    }

}
