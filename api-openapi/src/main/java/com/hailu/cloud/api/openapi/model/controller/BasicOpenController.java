package com.hailu.cloud.api.openapi.model.controller;

import com.hailu.cloud.api.openapi.feigns.BasicFeignClient;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: QiuFeng:WANG
 * @Description: 上传图片开放接口
 * @Date: 2019/12/11 0011
 * @program: cloud
 * @create: 2019-12-11 18:
 */
@Slf4j
@Validated
@RestController
@Api(tags = "上传图片开放接口")
@RequestMapping("/open/upload")
public class BasicOpenController {

    @Autowired
    private BasicFeignClient basicFeignClient;

    @ApiOperation(value = "单文件上传", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': null,\n" +
            "    'data': '/业务编码/年月日/xxxx-时分秒.png'\n" +
            "}" +
            "</pre>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessCode", required = true, value = "业务标识", paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "imageCompress", value = "是否启用图片压缩", paramType = "query", dataType = "Boolean"),
            @ApiImplicitParam(name = "compressQuality", value = "图片输出质量，只有启用图片压缩后该参数才生效", paramType = "query", dataType = "Double")
    })
    @PostMapping(value = "/singleFile/{businessCode}")
    public String singleFileUpload(
            @RequestParam("file")
            @NotNull(message = "请选择文件") MultipartFile file,
            @Pattern(regexp = "^[a-zA-Z\\-]{5,20}$", message = "业务编码只允许大小写字母和“-”,5-20位")
            @NotBlank(message = "业务标识不能为空")
            @PathVariable("businessCode") String businessCode,
            @RequestParam(value = "imageCompress", required = false, defaultValue = "false") boolean imageCompress,
            @RequestParam(value = "compressQuality", required = false, defaultValue = "1") double compressQuality) {

        return basicFeignClient.singleFileUpload(file, businessCode, imageCompress, compressQuality).getData();
    }

    /**
     * 删除服务上的文件
     * @param filePath 路径
     * @return
     */
    @ApiOperation( value = "删除图片")
    @ResponseBody
    @PostMapping(value = "/deletePictures")
    @ApiImplicitParam(name = "filePath", value = "图片路径" ,required = true)
    public void deleteServerFile(String filePath){
        basicFeignClient.deleteServerFile(filePath);
    }
}
