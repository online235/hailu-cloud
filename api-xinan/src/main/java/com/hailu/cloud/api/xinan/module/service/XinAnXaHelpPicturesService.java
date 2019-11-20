package com.hailu.cloud.api.xinan.module.service;


import com.hailu.cloud.api.xinan.module.dao.XaHelppicturesMapper;
import com.hailu.cloud.api.xinan.module.entity.XaHelppictures;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表图片Service
 * @Date: 18:16 2019/11/12 0012
 */
@Service
public class XinAnXaHelpPicturesService {

    @Resource
    private XaHelppicturesMapper xaHelpPicturesMapper;

    /**
     * 添加互助信息图片
     * @param xaHelpPictures
     * @return
     */
    public int insertSelective(XaHelppictures xaHelpPictures){
        if (xaHelpPictures != null){
            return xaHelpPicturesMapper.insertSelective(xaHelpPictures);
        }
        return 0;
    }
}
