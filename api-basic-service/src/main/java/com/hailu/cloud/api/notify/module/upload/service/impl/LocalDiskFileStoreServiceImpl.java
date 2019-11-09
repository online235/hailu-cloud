package com.hailu.cloud.api.notify.module.upload.service.impl;

import com.hailu.cloud.api.notify.module.upload.service.IFileStoreService;
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
    @Value("${file.store.path}")
    private String fileStorePath;

    /**
     * @param is
     * @param path    ueditor-config.json里面定义的相对路径
     * @param picName 文件名称
     * @return
     */
    @Override
    public String saveFile(InputStream is, String path, String picName) throws IOException {
        // 目录检查
        FileUtils.forceMkdir(new File(getStorePath(path)));
        // 保存文件
        return save(is, path, picName);
    }

    private String save(InputStream in, String path, String picName) {

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(getStoreFilePath(path, picName));
            IOUtils.copy(in, outputStream);
            return getStoreFileRelativePath(path, picName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(in, outputStream);
        }
        return null;
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
