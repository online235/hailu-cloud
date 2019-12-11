package com.hailu.cloud.api.xinan.module.app.service.impl;


import com.hailu.cloud.api.xinan.module.app.dao.HelppicturesMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Helppictures;
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
     * 添加互助信息图片
     * @param helpPictures
     * @return
     */
    public int insertSelective(Helppictures helpPictures){
        if (helpPictures != null){
            return helpPicturesMapper.insertSelective(helpPictures);
        }
        return 0;
    }

    /**
     * 查询互助者图片
     * @param numberId
     * @return
     */
    public List<Helppictures> findHelpPicturesList(Long numberId){
        return helpPicturesMapper.findHelppicturesList(numberId);
    }
}
