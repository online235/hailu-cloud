package com.hailu.cloud.api.merchant.module.merchant.service;


import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
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
        mcStoreInformation.setDateTime(new Date());
        mcStoreInformation.setUpdateDateTime(new Date());
        mcStoreInformation.setToExamine(Mceunm.IN_AUDIT.getKey());
        mcStoreInformation.setBusinessState(2);//1-营业中，2-休息中
        mcStoreInformation.setBusinessTime("09:00-12:00");//默认开店时间
        mcStoreInformation.setWeekDay("1");//默认关店时间
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

        mcStoreInformation.setUpdateDateTime(new Date());
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
     * @param storeId
     */
    public void updateByOperatingTime(Long storeId,String businessTime, int[] weekDay){

        McStoreInformation mcStoreInformation = new McStoreInformation();
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
            mcStoreInformation.setWeekDay(week);
        }
        mcStoreInformation.setBusinessTime(businessTime);
        mcStoreInformation.setUpdateDateTime(new Date());
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


    /**
     * 更改店铺信息
     */
    public void updateByPrimaryKey(McStoreInformation mcStoreInformation){

        mcStoreInformation.setUpdateDateTime(new Date());
        mcStoreInformationMapper.updateByPrimaryKey(mcStoreInformation);
    }


}
