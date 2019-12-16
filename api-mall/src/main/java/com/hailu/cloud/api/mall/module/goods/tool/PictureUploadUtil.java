/**
 * Copyright (c) 2011-2014 FengWoo Network Co.,Ltd.
 * <p>
 * Prohibition is granted, charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software
 */
package com.hailu.cloud.api.mall.module.goods.tool;

import cn.hutool.core.util.RandomUtil;
import com.hailu.cloud.api.mall.util.Const;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Date;

/**
 * @author Administrator
 */
@Slf4j
public class PictureUploadUtil {

    /**
     * 图片上传
     *
     * @param wenjianjia 保持的文件夹每次比如说good
     * @param logo
     * @return
     */
    public static String uploadPicture(String wenjianjia, String logo){
        String picture = null;
        FileOutputStream out = null;
        File file = null;
        try {

            byte[] photoimg = Base64.getMimeDecoder().decode(logo);
            for (int i = 0; i < photoimg.length; ++i) {
                if (photoimg[i] < 0) {
                    // 调整异常数据
                    photoimg[i] += 256;
                }
            }

            Date date = new Date();
            int year = DateUtils.getYear(date);
            int month = DateUtils.getMonth(date);
            int day = DateUtils.getDay(date);

            String image = System.currentTimeMillis() + RandomUtil.randomNumbers(5) + ".png";
            String imagePath = wenjianjia +"/" + year +"/"+month+"/"+day+"/";
            file = new File(Const.IMAGES + File.separator + imagePath);
            if(!file.exists()){
                file.mkdirs();
            }
            out = new FileOutputStream(Const.IMAGES + File.separator + imagePath+image);
            out.write(photoimg);
            out.flush();
            picture = "/" + imagePath +image;
            log.info("上传图片地址：{}",picture);
        } catch (Exception e) {
            log.error("上传图片错误"+e.getMessage(), e);
        }finally {
            try {
                if(null != out){
                    out.close();
                }
            }catch (Exception e){
                log.error("上传图片关闭流错误"+e.getMessage(), e);
            }
        }
        return picture;
    }

    /**
     * 根据图片url 获取到并且保存在本地
     *
     * @param urlString url
     * @param savePath  保存路劲
     * @throws Exception 异常
     */
    public static String saveToService(String urlString, String savePath) {
        String path = "";
        try {
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5 * 1000);
            @Cleanup InputStream is = con.getInputStream();
            String image = System.currentTimeMillis() + RandomUtil.randomNumbers(5) + ".png";
            path = File.separator + savePath + File.separator + image;
            File file = new File(Const.IMAGES + File.separator + savePath + File.separator + image);
            if (file.exists()) {
                if (!file.isDirectory()) {
                    return path;
                }
            }
            @Cleanup OutputStream os = new FileOutputStream(file);
            byte[] bs = new byte[1024];
            int len;
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return path;
    }

    /**
     * 删除单个文件
     *
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(Const.IMAGES + File.separator + path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

}
