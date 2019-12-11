package com.hailu.cloud.api.basic.module.upload.service.impl;

import cn.hutool.core.img.Img;
import com.hailu.cloud.api.basic.module.upload.model.UploadOptions;
import com.hailu.cloud.api.basic.module.upload.service.IFileStoreService;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 本地磁盘文件存储
 *
 * @Author xuzhijie
 * @Date 2019/10/25 16:56
 */
@Slf4j
@Service
public class LocalDiskFileStoreServiceImpl implements IFileStoreService {

    private String separator = "/";

    /**
     * 外部存储的绝对路径
     */
    @Value("${file.store.path:/opt/filestore}")
    private String fileStorePath;

    /**
     * @param is
     * @return
     */
    @Override
    public String saveFile(InputStream is, UploadOptions options) throws BusinessException {

        try {
            // 目录检查
            FileUtils.forceMkdir(new File(getStorePath(options.getPath())));
            // 保存文件
            return save(is, options);
        } catch (IOException e) {
            throw new BusinessException("图片上传失败", e);
        }
    }

    @Override
    public void deleteFile(String filePath) {
        File file = new File(fileStorePath + filePath);
        file.deleteOnExit();
    }

    private String save(InputStream in, UploadOptions options) throws BusinessException {

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(getStoreFilePath(options.getPath(), options.getPicName()));
            if (isCompressImage(options.getIsImageCompress(), options.getCompressQuality(), options.getPicName())) {
                // 压缩图片存储
                Img.from(in).setQuality(options.getCompressQuality()).write(outputStream);
            } else {
                IOUtils.copy(in, outputStream);
            }
            return getStoreFileRelativePath(options.getPath(), options.getPicName());
        } catch (IOException e) {
            throw new BusinessException("图片上传失败", e);
        } finally {
            closeStream(in, outputStream);
        }
    }

    private boolean isCompressImage(
            Boolean imageCompress,
            Double compressQuality,
            String picName) throws BusinessException {

        String jpgSuffix = ".jpg";
        String jpegSuffix = ".jpeg";
        if (!picName.endsWith(jpgSuffix) && !picName.endsWith(jpegSuffix)) {
            // 如果非jpg， jpeg则不压缩
            return false;
        }
        if (imageCompress == null || !imageCompress) {
            return false;
        }
        if (compressQuality == null || compressQuality <= 0 || compressQuality >= 1) {
            throw new BusinessException("图片输出质量只能在0~1之间，但是不包括0和1");
        }
        return true;
    }

    private void closeStream(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 获取文件存储根目录
     *
     * @param path
     * @return
     */
    private String getStorePath(String path) {
        return fileStorePath + separator + path;
    }

    /**
     * 获取完整文件目录
     *
     * @param path
     * @param picName
     * @return
     */
    private String getStoreFilePath(String path, String picName) {
        return getStorePath(path) + separator + picName;
    }

    /**
     * 获取完整文件目录
     *
     * @param path
     * @param picName
     * @return
     */
    private String getStoreFileRelativePath(String path, String picName) {
        return separator + path + "/" + picName;
    }

}
