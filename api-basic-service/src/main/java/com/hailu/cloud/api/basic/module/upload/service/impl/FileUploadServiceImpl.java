package com.hailu.cloud.api.basic.module.upload.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.hailu.cloud.api.basic.module.upload.model.UploadOptions;
import com.hailu.cloud.api.basic.module.upload.service.IFileStoreService;
import com.hailu.cloud.api.basic.module.upload.service.IFileUploadService;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhijie
 */
@Slf4j
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private IFileStoreService fileStoreService;

    private String separator = "/";

    @Override
    public String single(UploadOptions options, MultipartFile file) throws BusinessException {
        try {
            // 获取文件后缀
            String fileSuffix = "";
            String point = ".";
            String horizontalLine = "-";
            if (file.getOriginalFilename().contains(point)) {
                fileSuffix = file.getOriginalFilename()
                        .substring(file.getOriginalFilename().lastIndexOf(point))
                        .toLowerCase();
            }

            String storeFileName = new StringBuilder()
                    .append(IdUtil.simpleUUID())
                    .append(horizontalLine)
                    .append(System.currentTimeMillis())
                    .append(fileSuffix)
                    .toString();

            String storePath = new StringBuilder()
                    .append(options.getBusinessCode())
                    .append(separator)
                    .append(DateUtil.today())
                    .toString();
            options.setPath(storePath);
            options.setPicName(storeFileName);
            return fileStoreService.saveFile(file.getInputStream(), options);
        } catch (IOException e) {
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public List<String> multi(UploadOptions options, MultipartFile... files) throws BusinessException {

        List<String> result = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            result.add(single(options, file));
        }
        return result;
    }

    @Override
    public void deleteFile(String filePath) {
        fileStoreService.deleteFile(filePath);
    }
}
