package com.hailu.cloud.api.basic.module.upload.service;

import com.hailu.cloud.common.exception.BusinessException;

import java.io.InputStream;

/**
 * @Author xuzhijie
 * @Date 2019/10/25 16:51
 */
public interface IFileStoreService {

    /**
     * 文件保存，保存成功后返回文件相对路径
     *
     * @param is
     * @param imageCompress   true开启图片压缩(只有图片为jpg时才会压缩)
     * @param compressQuality 压缩比例
     * @param filePath        文件保存相对路径
     * @param picName
     * @return
     * @throws BusinessException
     */
    String saveFile(
            InputStream is,
            Boolean imageCompress,
            Double compressQuality,
            String filePath,
            String picName) throws BusinessException;

}
