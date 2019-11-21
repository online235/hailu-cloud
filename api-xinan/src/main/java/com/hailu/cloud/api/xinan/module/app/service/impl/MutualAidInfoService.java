package com.hailu.cloud.api.xinan.module.app.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hailu.cloud.api.xinan.module.app.entity.Helppictures;
import com.hailu.cloud.api.xinan.module.app.entity.Mutualaid;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助助ServiceInfo
 * @Date: 16:32 2019/11/2 0002
 */
@Service
@Slf4j
public class MutualAidInfoService {
    @Autowired
    private MutualAidService mutualAidService;

    @Autowired
    private HelpPicturesService helpPicturesService;

    public void insHelpANDMtualAid(Mutualaid mutualAid, String[] picture) throws BusinessException {
        if (mutualAid == null){
            throw new BusinessException("数据为空");
        }
            //s生成随机ID
            String numberid = IdUtil.simpleUUID();
            Date date = new Date();
            mutualAid.setNumberId(numberid);
            mutualAid.setCreatedat(date);
            mutualAid.setUpdatedat(date);
            mutualAid.setExamine("1");
            //添加互助信息
            int result = mutualAidService.insertSelective(mutualAid);
                for (String pictureFor: picture) {
                        Helppictures helpPictures = new Helppictures();
                        helpPictures.setMutualaId(numberid);
                        helpPictures.setNumberId(IdUtil.simpleUUID());
                        helpPictures.setCreatedat(date);
                        helpPictures.setPicture(pictureFor);
                        helpPicturesService.insertSelective(helpPictures);
                }
    }
}
