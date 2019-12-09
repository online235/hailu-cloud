package com.hailu.cloud.api.admin.module.system.controller;

import com.hailu.cloud.api.admin.module.system.model.VersionManagementModel;
import com.hailu.cloud.api.admin.module.system.service.IVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;

/**
 * 版本管理
 * @author 190726
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/appManagement/version")
@Api(tags = "版本管理")
public class VersionController {

    @Resource
    IVersionService versionService;

    @ApiOperation(value = "获取版本信息",notes = "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\",\n" +
            "  \"data\": {\n" +
            "    \"totalPage\": 1,\n" +
            "    \"total\": 2,\n" +
            "    \"datas\": [\n" +
            "      {\n" +
            "        \"id\": \"65465456\",      id\n" +
            "        \"createTime\": \"2019-12-09 22:12:14\",       //创建时间\n" +
            "        \"version\": \"1.0.0\",                        //版本号\n" +
            "        \"build\": \"165\",                            //构建号\n" +
            "        \"title\": \"6151\",                           //标题\n" +
            "        \"content\": \"32123\",                        //内容\n" +
            "        \"mandatory\": \"6211\",                       //更新标识\n" +
            "        \"renewal\": 2,                                //更新状态\n" +
            "        \"url\": \"54516\",                            //地址\n" +
            "        \"type\": 2                                    //类型\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"456456465\",\n" +
            "        \"createTime\": \"2019-12-08 02:44:51\",\n" +
            "        \"version\": \"1.0.0\",\n" +
            "        \"build\": \"1212\",\n" +
            "        \"title\": \"2312\",\n" +
            "        \"content\": \"123123\",\n" +
            "        \"mandatory\": \"21321\",\n" +
            "        \"renewal\": 2,\n" +
            "        \"url\": \"12312\",\n" +
            "        \"type\": 1\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}")
    @GetMapping("findList")
    public Object findList(@RequestParam(value = "version",required = false) String version,
                           @RequestParam(value = "type",required = false) Integer type,
                           @RequestParam(value = "page", defaultValue = "1", required = false)Integer page,
                           @Max(value = 200, message = "每页最多显示200条数据")
                               @RequestParam(value = "size", defaultValue = "20", required = false)Integer size){
        log.info("查询版本信息分页：版本号{}，来源：{}",version,type);
        return versionService.findPage(version,type,page,size);
    }

    @ApiOperation(value = "添加/修改版本信息")
    @PostMapping("save")
    public void save(@Validated @RequestBody VersionManagementModel versionManagementModel){
        log.info("保存/修改版本信息：",versionManagementModel.toString());
        versionService.save(versionManagementModel);
    }

    @ApiOperation(value = "删除版本信息")
    @DeleteMapping("/version/{id}")
    public boolean delete(@PathVariable Long id){
        log.info("删除版本信息。ID：{}",id);
        return versionService.delete(id);
    }

}
