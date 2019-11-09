package com.hailu.cloud.api.notify.module.upload.service;

import java.io.IOException;
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
     * @param filePath 文件保存相对路径
     * @param picName
     * @return
     * @throws IOException
     */
    String saveFile(InputStream is, String filePath, String picName) throws IOException;

}
