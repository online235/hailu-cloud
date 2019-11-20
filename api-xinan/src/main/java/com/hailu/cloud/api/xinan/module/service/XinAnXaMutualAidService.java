package com.hailu.cloud.api.xinan.module.service;

import com.hailu.cloud.api.xinan.module.dao.XaMutualaidMapper;
import com.hailu.cloud.api.xinan.module.entity.XaMutualaid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表Service
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class XinAnXaMutualAidService {
    @Resource
    private XaMutualaidMapper xaMutualAidMapper;

    /**
     * 添加互助信息
     * @param xaMutualAid
     * @return
     */
    public int insertSelective(XaMutualaid xaMutualAid){
        if(xaMutualAid != null){
            return xaMutualAidMapper.insertSelective(xaMutualAid);
        }
        return 0;
    }

    /**
     * 删除互助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(String numberId){
        return xaMutualAidMapper.deleteByPrimaryKey(numberId);
    }
}
