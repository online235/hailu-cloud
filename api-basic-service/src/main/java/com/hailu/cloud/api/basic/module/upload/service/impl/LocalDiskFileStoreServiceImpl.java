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

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            if (!isImg(options.getPicName())) {
                IOUtils.copy(in, outputStream);
                return getStoreFileRelativePath(options.getPath(), options.getPicName());
            }
            if (options.isCut() || options.isImageCompress() || options.isZoom()) {
                Img img = Img.from(in);
                if (verifyCompressImageParams(options.isImageCompress(), options.getCompressQuality())) {
                    // 压缩图片
                    log.info("图片压缩");
                    img = img.setQuality(options.getCompressQuality());
                }
                if (verifyCutImageParams(options.isCut(), options.getCutWidth(), options.getCutHeight())) {
                    // 图片裁剪
                    log.info("图片裁剪");
                    img.cut(new Rectangle(0, 0, options.getCutWidth(), options.getCutHeight()));
                }
                if (verifyZoomImageParams(options.isZoom(), options.getZoomWidth(), options.getZoomHeight())) {
                    // 图片缩放
                    log.info("图片缩放");
                    img.scale(options.getZoomWidth(), options.getZoomHeight());
                }
                img.write(outputStream);
                return getStoreFileRelativePath(options.getPath(), options.getPicName());
            }
            IOUtils.copy(in, outputStream);
            return getStoreFileRelativePath(options.getPath(), options.getPicName());
        } catch (IOException e) {
            throw new BusinessException("图片上传失败", e);
        } finally {
            closeStream(in, outputStream);
        }
    }

    /**
     * 验证图片压缩参数
     *
     * @param isImageCompress 是否压缩
     * @param compressQuality 压缩比例 0 ~ 1 之间的小数，如：0.5， 0.66
     * @return
     * @throws BusinessException
     */
    private boolean verifyCompressImageParams(
            boolean isImageCompress,
            Double compressQuality) throws BusinessException {

        if (!isImageCompress) {
            return false;
        }
        if (compressQuality == null || compressQuality <= 0 || compressQuality >= 1) {
            throw new BusinessException("图片输出质量只能在0~1之间，但是不包括0和1");
        }
        return true;
    }

    /**
     * 验证图片缩放参数
     *
     * @param isZoom 是否缩放
     * @param width  缩放宽度
     * @param height 缩放高度
     * @return
     * @throws BusinessException
     */
    private boolean verifyZoomImageParams(
            boolean isZoom,
            int width,
            int height) throws BusinessException {

        if (!isZoom) {
            return false;
        }
        if (width <= 0 || height <= 0) {
            throw new BusinessException("缩放宽高不能为0");
        }
        return true;
    }

    /**
     * 验证图片裁剪参数
     *
     * @param isCut  是否裁剪
     * @param width  裁剪宽度
     * @param height 裁剪高度
     * @return
     * @throws BusinessException
     */
    private boolean verifyCutImageParams(
            boolean isCut,
            int width,
            int height) throws BusinessException {

        if (!isCut) {
            return false;
        }
        if (width <= 0 || height <= 0) {
            throw new BusinessException("裁剪宽高不能为0");
        }
        return true;
    }


    private static final List<String> IMG_SUFFIX = new ArrayList<>();

    static {
        IMG_SUFFIX.add(".jpg");
        IMG_SUFFIX.add(".jpeg");
        IMG_SUFFIX.add(".png");
    }

    /**
     * 判断是否为图片
     *
     * @param picName 文件名
     * @return
     */
    private boolean isImg(String picName) {
        String lowerCase = picName.toLowerCase();
        // 如果非jpg， jpeg则不压缩
        for (String imgSuffix : IMG_SUFFIX) {
            if (lowerCase.endsWith(imgSuffix)) {
                return true;
            }
        }
        return false;
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
