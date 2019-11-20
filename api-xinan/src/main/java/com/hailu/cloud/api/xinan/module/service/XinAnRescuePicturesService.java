package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.module.dao.XaRescuePicturesMapper;
import com.hailu.cloud.api.xinan.module.entity.XaRescuePictures;
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
public class XinAnRescuePicturesService {

    @Resource
    private XaRescuePicturesMapper xaRescuePicturesMapper;

    /**
     * 添加救助图片路径
     * @param xaRescuePictures
     * @return
     */
    public int insertSelective(XaRescuePictures xaRescuePictures){
        if (xaRescuePictures == null){
            return 0;
        }
        return xaRescuePicturesMapper.insertSelective(xaRescuePictures);
    }

    /**
     * 查询图片列表
     * @param MutualAid
     * @return
     */
    public List<XaRescuePictures> findRescuePicturesList(String MutualAid){
        if (StringUtils.isBlank(MutualAid)) {
            return null;
        }
        return xaRescuePicturesMapper.selectByPrimaryMutualAid(MutualAid);
    }
}
