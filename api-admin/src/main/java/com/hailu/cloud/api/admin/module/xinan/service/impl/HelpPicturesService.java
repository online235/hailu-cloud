package com.hailu.cloud.api.admin.module.xinan.service.impl;


import com.hailu.cloud.api.admin.module.xinan.dao.HelppicturesMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Helppictures;
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

    /**
     * 查询互助者图片
     * @param mutualAid
     * @return
     */
    public List<Helppictures> findHelpPicturesList(Long mutualAid){
        return helpPicturesMapper.findHelppicturesList(mutualAid);
    }
}
