package com.hailu.cloud.api.basic.module.upload.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
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
    public String single(
            String businessCode,
            Boolean imageCompress,
            Double compressQuality,
            MultipartFile file) throws BusinessException {
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
                    .append(businessCode)
                    .append(separator)
                    .append(DateUtil.today())
                    .toString();
            return fileStoreService.saveFile(file.getInputStream(), imageCompress, compressQuality, storePath, storeFileName);
        } catch (IOException e) {
            throw new BusinessException("文件上传失败");
        }
    }

    @Override
    public List<String> multi(
            String businessCode,
            Boolean imageCompress,
            Double compressQuality,
            MultipartFile... files) throws BusinessException {

        List<String> result = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            result.add(single(businessCode, imageCompress, compressQuality, file));
        }
        return result;
    }

    @Override
    public void deleteFile(String filePath) {
        fileStoreService.deleteFile(filePath);
    }
}
