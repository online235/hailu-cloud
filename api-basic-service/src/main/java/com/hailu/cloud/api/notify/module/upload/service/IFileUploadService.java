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
     * @param businessCode
     * @param file
     * @return
     * @throws BusinessException
     */
    String single(String businessCode, MultipartFile file) throws BusinessException;

    /**
     * 处理多文件
     *
     * @param businessCode
     * @param files
     * @return
     * @throws BusinessException
     */
    List<String> multi(String businessCode, MultipartFile... files) throws BusinessException;

}
