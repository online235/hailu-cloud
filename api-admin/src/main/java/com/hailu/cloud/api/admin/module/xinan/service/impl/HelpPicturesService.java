package com.hailu.cloud.api.admin.module.xinan.service.impl;


import com.hailu.cloud.api.admin.module.xinan.dao.HelppicturesMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Helppictures;
import com.hailu.cloud.api.admin.module.xinan.parameter.HelpPictureParameter;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param mutualAid
     * @return
     */
    public List<Helppictures> findHelpPicturesList(Long mutualAid){
        return helpPicturesMapper.findHelppicturesList(mutualAid);
    }


    public void sertHelpPictures(HelpPictureParameter helpPictureParameter){



    }


}
