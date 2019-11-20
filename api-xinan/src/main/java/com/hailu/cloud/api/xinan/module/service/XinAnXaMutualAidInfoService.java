package com.hailu.cloud.api.xinan.module.service;

import cn.hutool.core.util.IdUtil;
import com.hailu.cloud.api.xinan.module.entity.XaHelppictures;
import com.hailu.cloud.api.xinan.module.entity.XaMutualaid;
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
public class XinAnXaMutualAidInfoService {
    @Autowired
    private XinAnXaMutualAidService xinAnXaMutualAidService;

    @Autowired
    private XinAnXaHelpPicturesService xinAnXaHelpPicturesService;

    public void insHelpANDMtualAid(XaMutualaid xaMutualAid, String[] picture) throws BusinessException {
        if (xaMutualAid == null){
            throw new BusinessException("数据为空");
        }
            //s生成随机ID
            String numberid = IdUtil.simpleUUID();
            Date date = new Date();
            xaMutualAid.setNumberId(numberid);
            xaMutualAid.setCreatedat(date);
            xaMutualAid.setUpdatedat(date);
            xaMutualAid.setExamine("1");
            //添加互助信息
            int result = xinAnXaMutualAidService.insertSelective(xaMutualAid);
                for (String pictureFor: picture) {
                        XaHelppictures xaHelpPictures = new XaHelppictures();
                        xaHelpPictures.setMutualaId(numberid);
                        xaHelpPictures.setNumberId(IdUtil.simpleUUID());
                        xaHelpPictures.setCreatedat(date);
                        xaHelpPictures.setPicture(pictureFor);
                        xinAnXaHelpPicturesService.insertSelective(xaHelpPictures);
                }
    }
}
