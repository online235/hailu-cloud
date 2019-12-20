package com.hailu.cloud.api.admin.module.xinan.service.impl;


import com.hailu.cloud.api.admin.module.xinan.dao.HelppicturesMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Helppictures;
import com.hailu.cloud.api.admin.module.xinan.parameter.HelpPictureParameter;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表图片Service
 * @Date: 18:16 2019/11/12 0012
 */
@Service
public class HelpPicturesService {

    @Resource
    private HelppicturesMapper helpPicturesMapper;
    @Autowired
    private BasicFeignClient uuidFeign;


    /**
     * 查询互助者图片
     *
     * @param mutualaId
     * @return
     */
    public List<Helppictures> findHelpPicturesList(Long mutualaId) {
        return helpPicturesMapper.findHelppicturesList(mutualaId);
    }


    public void sertHelpPictures(HelpPictureParameter helpPictureParameter, Long xaHelpMemberId) {

        helpPicturesMapper.deleteHelppictures(xaHelpMemberId);
        if (!StringUtils.isBlank(helpPictureParameter.getPictureImage())) {
            String[] pictureImages = helpPictureParameter.getPictureImage().split(",");
            for (int i = 0; i < pictureImages.length; i++) {
                Helppictures helppictures = new Helppictures();
                helppictures.setNumberId(uuidFeign.uuid().getData());
                helppictures.setDateTime(new Date());
                helppictures.setMutualaId(xaHelpMemberId);
                helppictures.setPicture(pictureImages[i]);
                helppictures.setPictureName("病历图片路径");
                helppictures.setPictureType(1);
                helpPicturesMapper.insertSelective(helppictures);
            }
        }
        if (!StringUtils.isBlank(helpPictureParameter.getPictureHelpImage())) {
            String[] pictureHelpImages = helpPictureParameter.getPictureHelpImage().split(",");
            for (int i = 0; i < pictureHelpImages.length; i++) {
                Helppictures helppictures = new Helppictures();
                helppictures.setNumberId(uuidFeign.uuid().getData());
                helppictures.setDateTime(new Date());
                helppictures.setMutualaId(xaHelpMemberId);
                helppictures.setPicture(pictureHelpImages[i]);
                helppictures.setPictureName("互助者图片");
                helppictures.setPictureType(2);
                helpPicturesMapper.insertSelective(helppictures);
            }
        }
        if (!StringUtils.isBlank(helpPictureParameter.getPictureHelpVideo())) {
            String[] pictureHelpVideos = helpPictureParameter.getPictureHelpImage().split(",");
            for (int i = 0; i < pictureHelpVideos.length; i++) {
                Helppictures helppictures = new Helppictures();
                helppictures.setNumberId(uuidFeign.uuid().getData());
                helppictures.setDateTime(new Date());
                helppictures.setMutualaId(xaHelpMemberId);
                helppictures.setPicture(pictureHelpVideos[i]);
                helppictures.setPictureName("视频");
                helppictures.setPictureType(3);
                helpPicturesMapper.insertSelective(helppictures);
            }
        }

    }


}
