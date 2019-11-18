package com.hailu.cloud.api.notify.module.upload.service;

import com.hailu.cloud.common.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhijie
 */
public interface IFileUploadService {

    /**
     * 处理单文件
     *
     * @param businessCode    业务代码
     * @param imageCompress   true开启图片压缩(只有图片为jpg时才会压缩)
     * @param compressQuality 压缩比例
     * @param file
     * @return
     * @throws BusinessException
     */
    String single(
            String businessCode,
            Boolean imageCompress,
            Double compressQuality,
            MultipartFile file) throws BusinessException;

    /**
     * 处理多文件
     *
     * @param businessCode    业务代码
     * @param imageCompress   true开启图片压缩(只有图片为jpg时才会压缩)
     * @param compressQuality 压缩比例
     * @param files
     * @return
     * @throws BusinessException
     */
    List<String> multi(
            String businessCode,
            Boolean imageCompress,
            Double compressQuality,
            MultipartFile... files) throws BusinessException;

}
