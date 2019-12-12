package com.hailu.cloud.api.openapi.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: QiuFeng:WANG
 * @Description: 基础服务对外接口
 * @Date: 2019/12/11 0011
 * @program: cloud
 * @create: 2019-12-11 10:
 */
@FeignClient(name = "service-api-basic")
public interface BasicFeignClient {

    /**
     * 单文件上传
     * @param file
     * @param businessCode
     * @param imageCompress
     * @param compressQuality
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_BASIC + "/upload/single/{businessCode}")
    ApiResponse<String> singleFileUpload(
            @RequestParam("file") MultipartFile file,
            @PathVariable("businessCode") String businessCode,
            @RequestParam(value = "imageCompress", required = false, defaultValue = "false") boolean imageCompress,
            @RequestParam(value = "compressQuality", required = false, defaultValue = "1") double compressQuality);

    /**
     * 删除服务上的文件
     * @param filePath 路径
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_BASIC + "/upload/deleteQictures")
    void deleteServerFile(
            @RequestParam("filePath") String filePath);
}
