package com.hailu.cloud.api.basic.module.upload.service;

import com.hailu.cloud.api.basic.module.upload.model.UploadOptions;
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
     * @param options
     * @return
     * @throws BusinessException
     */
    String saveFile(InputStream is, UploadOptions options) throws BusinessException;

    /**
     * 删除文件
     *
     * @param filePath
     */
    void deleteFile(String filePath);
}
