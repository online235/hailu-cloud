package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.admin.module.xinan.dao.RescueBackstageMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Rescue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表Service
 * @Date: 18:14 2019/11/12 0012
 */
@Service
public class RescueServiceBackstage {

    @Resource
    private RescueBackstageMapper rescueBackstageMapper;

    @Autowired
    private RescueInfoServiceBackstage rescueInfoServiceBackstage;


    /**
     * 删除救助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(String numberId){
        return rescueBackstageMapper.deleteByPrimaryKey(numberId);
    }

    /**
     * 根据编号查询救助信息
     * @param numberId
     * @return
     */
    public Rescue findRescueById(String numberId){
        if (StringUtils.isBlank(numberId)){
            return null;
        }
        return rescueBackstageMapper.selectByPrimaryKey(numberId);
    }


    /**
     * 查询救助列表
     * @return
     */
    public Object findXaRescueListAll(Integer page, Integer size){
        PageHelper.startPage(page, size);

        List<Rescue> rescue = rescueBackstageMapper.findXaRescueList();
        PageInfo pageInfo = new PageInfo(rescue);
        JSONArray jsonArray = rescueInfoServiceBackstage.rescueJson(rescue);
        JSONObject jsonObject = rescueInfoServiceBackstage.PageAndRescueJson(pageInfo, jsonArray);
        return jsonObject;
    }

    /**
     * 修改救助审核
     * @param numberId
     * @return
     */
    public void updateByPrimaryKeySelective(String numberId, String examine){
        Rescue rescue = new Rescue();
        rescue.setNumberId(numberId);
        rescue.setExamine(examine);
        rescueBackstageMapper.updateByPrimaryKeySelective(rescue);
    }

}
