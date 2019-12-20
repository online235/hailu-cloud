package com.hailu.cloud.api.admin.module.xinan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.xinan.dao.RescueBackstageMapper;
import com.hailu.cloud.api.admin.module.xinan.entity.Rescue;
import com.hailu.cloud.common.model.page.PageInfoModel;
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
    public Rescue findRescueById(Long numberId){

        return rescueBackstageMapper.selectByPrimaryKey(numberId);
    }


    /**
     * 查询救助列表
     * @return
     */
    public Object findXaRescueListAll(Integer page, Integer size){
        Page pageData = PageHelper.startPage(page, size);

        List<Rescue> rescue = rescueBackstageMapper.findXaRescueList();
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), rescue);
    }

    /**
     * 修改救助审核
     * @param numberId
     * @return
     */
    public void updateByPrimaryKeySelective(Long numberId, String toExamine){
        Rescue rescue = new Rescue();
        rescue.setNumberId(numberId);
        rescue.setToExamine(toExamine);
        rescueBackstageMapper.updateByPrimaryKeySelective(rescue);
    }

}
