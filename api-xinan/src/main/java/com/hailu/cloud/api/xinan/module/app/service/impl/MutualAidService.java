package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.hailu.cloud.api.xinan.module.app.dao.MutualaidMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Mutualaid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表Service
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class MutualAidService {
    @Resource
    private MutualaidMapper mutualAidMapper;

    /**
     * 添加互助信息
     * @param mutualAid
     * @return
     */
    public int insertSelective(Mutualaid mutualAid){
        if(mutualAid != null){
            return mutualAidMapper.insertSelective(mutualAid);
        }
        return 0;
    }

    /**
     * 删除互助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(String numberId){
        return mutualAidMapper.deleteByPrimaryKey(numberId);
    }
}
