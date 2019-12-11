package com.hailu.cloud.api.basic.module.upload.service;

import com.hailu.cloud.api.basic.module.upload.model.UploadOptions;
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
     * @param options
     * @param file
     * @return
     * @throws BusinessException
     */
    String single(UploadOptions options, MultipartFile file) throws BusinessException;

    /**
     * 处理多文件
     *
     * @param options
     * @param files
     * @return
     * @throws BusinessException
     */
    List<String> multi(UploadOptions options, MultipartFile... files) throws BusinessException;

    /**
     * 删除文件
     *
     * @param filePath
     */
    void deleteFile(String filePath);

}
