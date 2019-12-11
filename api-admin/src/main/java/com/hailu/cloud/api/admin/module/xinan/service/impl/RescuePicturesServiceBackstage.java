package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.hailu.cloud.api.admin.module.xinan.dao.RescuePicturesBackstageMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.RescuePictures;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表图片Service
 * @Date: 18:14 2019/11/12 0012
 */
@Service
public class RescuePicturesServiceBackstage {

    @Resource
    private RescuePicturesBackstageMapper rescuePicturesBackstageMapper;
    /**
     * 查询图片列表
     * @param MutualAid
     * @return
     */
    public List<RescuePictures> findRescuePicturesList(Long MutualAid){
        return rescuePicturesBackstageMapper.selectByPrimaryMutualAid(MutualAid);
    }
}
