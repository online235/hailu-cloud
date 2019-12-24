package com.hailu.cloud.api.mall.module.message.controller;

import com.hailu.cloud.api.mall.module.message.service.IHlMessageService;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Max;

/**
 * 用户消息
 * @author junpei.deng
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/hlMessage")
@Api(tags = "用户消息")
public class HlMessageController {

    @Resource
    private IHlMessageService hlMessageService;


    @ApiOperation(value = "获取用户消息列表",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"totalPage\": 1,\n" +
            "    \"total\": 1,\n" +
            "    \"datas\": [\n" +
            "      {\n" +
            "        \"id\": \"165156\",                            //消息ID\n" +
            "        \"createTime\": \"2019-12-27 14:37:29\",       //创建时间\n" +
            "        \"title\": \"测试\",                            //标题\n" +
            "        \"subtitle\": \"测试副标题\",                   //副标题\n" +
            "        \"messageStatus\": 0,                          //信息状态（0-未读、1-已读）\n" +
            "        \"type\": 1,                                   //类型（1-文本显示、2-跳转、3-物流信息）\n" +
            "        \"jumpUrl\": \"www.baidu.com\",                //跳转地址\n" +
            "        \"iconUrl\": \"31231321\"                      //图片地址\n" +
            "        \"value\": \"31231321\"                       //其他消息需要用到的value值\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageStatus", value = "信息状态（0-未读、1-已读、不传默认查全部）", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", dataType = "int", paramType = "query")
    })
    @GetMapping("/findList")
    public Object findList(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @Max(value = 200, message = "每页最多显示200条数据")
            @RequestParam(value = "size", defaultValue = "20", required = false)Integer size,
            @RequestParam(value = "messageStatus",required = false) Integer messageStatus
    ){
        log.info("获取用户消息列表");
        return hlMessageService.findListPage(RequestUtils.getMemberLoginInfo().getUserId(),page,size,messageStatus);
    }

    @ApiOperation(value = "将消息改成已读状态")
    @ApiImplicitParam(name = "id", value = "id", dataType = "int", paramType = "query")
    @GetMapping("/readMessage")
    public void readMessage(@RequestParam("id")Long id){
        log.info("将消息改成已读状态：{}",id);
        hlMessageService.readMessage(id);
    }

    /**
     * 获取未读消息的条数
     * @return
     */
    @ApiOperation(value = "获取未读消息的条数",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": 1          //未读条数\n" +
            "}")
    @GetMapping("/findNoReadCount")
    public Integer findNoReadCount(){
        return hlMessageService.findNoReadCount(RequestUtils.getMemberLoginInfo().getUserId());
    }

}
