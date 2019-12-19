package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.merchant.dao.McEntryInformationMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private BasicFeignClient basicFeignClient;


    /**
     * 商家后台审核列表
     * @return
     */
    public PageInfoModel<List<McEntryInformation>> selectMcEntryinFormationList(String shopName, String phone, Integer page, Integer szie) {
        Page pageData = PageHelper.startPage(page, szie);
        List<McEntryInformation> datas = mcEntryinFormationMapper.selectMcEntryinFormationList(shopName, phone);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), datas);
    }

    /**
     * 入驻信息详情
     * @param numberId
     * @return
     */
    public McEntryInformation selectByPrimaryKey(String numberId) {
        return mcEntryinFormationMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 更改审核状态
     * @param numberId
     * @param toExamine
     * @return
     */
    public void updateToExamineByNumberId(String numberId, Integer toExamine){
        McEntryInformation mcEntryinFormation = new McEntryInformation();
        mcEntryinFormation.setNumberId(numberId);
        mcEntryinFormation.setToExamine(toExamine);
        mcEntryinFormation.setUpdateDateTime(System.currentTimeMillis());
        mcEntryinFormation.setMcNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }

    /**
     * 更改审核信息
     * @param mcEntryinFormation
     * @return
     */
    public void updateMcEntryInformation(McEntryInformation mcEntryinFormation) {
        mcEntryinFormation.setUpdateDateTime(System.currentTimeMillis());
        mcEntryinFormation.setToExamine(1);
        mcEntryinFormation.setNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }



    /**
     * 删除信息
     * @param numberId
     * @return
     */
    public void deleteByPrimaryKey(String numberId) {
        mcEntryinFormationMapper.deleteByPrimaryKey(numberId);
    }


}
