package com.hailu.cloud.api.merchant.module.Backstage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.merchant.module.Backstage.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.Backstage.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.Backstage.eunms.Mceunm;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.UuidFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户入驻模块
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class McEntryinFormationService {

    @Resource
    private McEntryInformationMapper mcEntryinFormationMapper;

    @Autowired
    private UuidFeignClient uuidFeign;


    /**
     * 商家后台审核列表
     * @return
     */
    public Object selectMcEntryinFormationList(String shopname, String phone,Integer page, Integer szie){
        PageHelper.startPage(page, szie);
        PageInfo pageInfo = new PageInfo(mcEntryinFormationMapper.selectMcEntryinFormationList(shopname,phone));
        return pageInfo;
    }

    /**
     * 入驻信息详情
     * @param numberId
     * @return
     */
    public Object selectByPrimaryKey(String numberId){
        return mcEntryinFormationMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 更改审核状态
     * @param numberId
     * @param toExamine
     * @return
     */
    public void updateToExamineByNumberId(String numberId, String toExamine){
        McEntryInformation mcEntryinFormation = new McEntryInformation();
        mcEntryinFormation.setNumberId(numberId);
        mcEntryinFormation.setToExamine(toExamine);
        mcEntryinFormation.setUpdatedat(System.currentTimeMillis());
        mcEntryinFormation.setMcNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }

    /**
     * 更改审核信息
     * @param mcEntryinFormation
     * @return
     */
    public void updateMcEntryInformation(McEntryInformation mcEntryinFormation){
        mcEntryinFormation.setUpdatedat(System.currentTimeMillis());
        mcEntryinFormation.setToExamine("1");
        mcEntryinFormation.setNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }



    /**
     * 删除信息
     * @param numberId
     * @return
     */
    public void deleteByPrimaryKey(String numberId){
        mcEntryinFormationMapper.deleteByPrimaryKey(numberId);
    }


}
