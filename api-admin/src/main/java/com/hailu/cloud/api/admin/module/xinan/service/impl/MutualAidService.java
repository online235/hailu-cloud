package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.xinan.dao.MutualaidMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.MutualAid;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * 删除互助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(Long numberId){
        return mutualAidMapper.deleteByPrimaryKey(numberId);
    }

    /**
     * 互助列表
     * @param page
     * @param size
     * @return
     */
    public PageInfoModel<List<MutualAid>> findMutualAidList(Integer page, Integer size, Integer rescueType, String diseaseName){
        Page objects = PageHelper.startPage(page, size);
        List<MutualAid> data = mutualAidMapper.findMutualaidList(rescueType,diseaseName);
        return new PageInfoModel<>(objects.getPages(), objects.getTotal(), data);
    }


    /**
     * 互助详细信息
     * @mbggenerated 2019-11-12
     */
    public MutualAid findMutualAid(Long numberId){
        return mutualAidMapper.findMutualAid(numberId);
    }

    /**
     * 更改审核状态
     * @param toExamine
     * @param numberId
     * @return
     */
    public void updateMutualAidOfExamine(Integer toExamine, Long numberId){
        mutualAidMapper.updateMutualAidOfExamine(toExamine, numberId);
    }



}
