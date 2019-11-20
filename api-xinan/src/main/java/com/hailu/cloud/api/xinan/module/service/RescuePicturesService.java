package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.module.dao.RescuePicturesMapper;
import com.hailu.cloud.api.xinan.module.entity.RescuePictures;
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
public class RescuePicturesService {

    @Resource
    private RescuePicturesMapper rescuePicturesMapper;

    /**
     * 添加救助图片路径
     * @param rescuePictures
     * @return
     */
    public int insertSelective(RescuePictures rescuePictures){
        if (rescuePictures == null){
            return 0;
        }
        return rescuePicturesMapper.insertSelective(rescuePictures);
    }

    /**
     * 查询图片列表
     * @param MutualAid
     * @return
     */
    public List<RescuePictures> findRescuePicturesList(String MutualAid){
        if (StringUtils.isBlank(MutualAid)) {
            return null;
        }
        return rescuePicturesMapper.selectByPrimaryMutualAid(MutualAid);
    }
}
