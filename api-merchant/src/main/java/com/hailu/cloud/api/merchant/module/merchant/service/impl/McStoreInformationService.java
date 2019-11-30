package com.hailu.cloud.api.merchant.module.merchant.service.impl;


import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McStoreInformationModel;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @Author: zhangmugui
 * @Description: 店铺详情模块
 */
@Service
public class McStoreInformationService {



    @Resource
    private McStoreInformationMapper mcStoreInformationMapper;

    @Autowired
    private BasicFeignClient uuidFeign;

    /**
     * 添加入驻信息
     * @param mcStoreInformation
     * @return
     */
    public void insertSelective(McStoreInformation mcStoreInformation) throws BusinessException {
        if (mcStoreInformation == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(mcStoreInformation.getMcNumberId());
        if (boo){
            throw new BusinessException("入驻信息以填写");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成随机ID
        Long id = uuidFeign.uuid().getData();
        mcStoreInformation.setId(id);
        mcStoreInformation.setCreatedat(new Date());
        mcStoreInformation.setUpdatedat(new Date());
        mcStoreInformation.setToExamine(Mceunm.IN_AUDIT.getKey());
        mcStoreInformation.setBusinessState(1);
        int result = mcStoreInformationMapper.insertSelective(mcStoreInformation);
        if (result <= 0) {
            throw new BusinessException("插入数据失败");
        }

    }


    /**
     * 入驻信息详情
     * @param id
     * @return
     */
    public Object selectByPrimaryKey(Long id){
        return mcStoreInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 更改审核信息
     * @param mcStoreInformation
     * @return
     */
    public void updateMcEntryInformation(McStoreInformation mcStoreInformation){

        mcStoreInformation.setUpdatedat(new Date());
        mcStoreInformation.setToExamine(1);
        mcStoreInformationMapper.updateByPrimaryKeySelective(mcStoreInformation);
    }

    /**
     * 查询入驻信息是否存在
     * @param mcNumberId
     * @return
     */
    public boolean selectMcEntryinFormationById(String mcNumberId){
        if (mcNumberId.isEmpty()){
            return true;
        }
        int result = mcStoreInformationMapper.selectMcEntryinFormationById(mcNumberId);
        if (result == 0) {
            return false;
        }
        return true;
    }

    /**
     * 更改店铺信息
     * @param mcStoreInformationModel
     */
    public void updateBYMcEntryInformation(McStoreInformationModel mcStoreInformationModel, int[] weekDay){
        String week = "";
        if (weekDay != null) {
            int t = 0;
            for (int i : weekDay) {
                week += String.valueOf(i);
                t++;
                if (weekDay.length > t) {
                    week += ",";
                }
            }
            mcStoreInformationModel.setWeekDay(week);
        }
        McStoreInformation mcStoreInformation = new McStoreInformation();
        BeanUtils.copyProperties(mcStoreInformationModel, mcStoreInformation);
        mcStoreInformationMapper.updateByPrimaryKey(mcStoreInformation);

    }

    /**
     * 查看店铺信息
     * @return
     */
    public McStoreInformation findMcStoreInformation(){
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        return mcStoreInformationMapper.findMcStoreInformation(merchantUserLoginInfoModel.getNumberid());
    }


    /**
     * 查看店铺信息
     * @return
     */
    public McStoreInformation findMcStoreInformationById(Long id){

        return mcStoreInformationMapper.selectByPrimaryKey(id);
    }



}
