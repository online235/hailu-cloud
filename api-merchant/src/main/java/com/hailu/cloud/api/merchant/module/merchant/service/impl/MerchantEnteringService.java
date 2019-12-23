package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户入驻模块
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class MerchantEnteringService {

    @Resource
    private McEntryInformationMapper mcEntryinFormationMapper;

    @Autowired
    private BasicFeignClient uuidFeign;

    @Resource
    private McStoreInformationService mcStoreInformationService;

    /**
     * 添加入驻信息
     *
     * @param mcEntryinFormation
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(McEntryInformation mcEntryinFormation) throws BusinessException {
        if (mcEntryinFormation == null) {
            throw new BusinessException("入驻信息为空");
        }
        MerchantUserLoginInfoModel loginInfo = RequestUtils.getMerchantUserLoginInfo();
        McEntryInformation mcEntryInformation1 = mcEntryinFormationMapper.findMcEntryInformationByMcNumberId(loginInfo.getNumberid());
        mcEntryinFormation.setNumberId(mcEntryInformation1.getNumberId());
        //生成时间戳
        mcEntryinFormation.setToExamine(2);
        int result = mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
        if (result <= 0) {
            throw new BusinessException("插入数据失败");
        }
        McStoreInformation mcStoreInformation = new McStoreInformation();
        BeanUtils.copyProperties(mcEntryinFormation, mcStoreInformation);
        mcStoreInformation.setMcNumberId(loginInfo.getNumberid());
        mcStoreInformationService.insertSelective(mcStoreInformation);

    }


    /**
     * 入驻信息详情
     *
     * @param numberId
     * @return
     */
    public Object selectByPrimaryKey(String numberId) {
        return mcEntryinFormationMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 更改审核信息
     *
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
     * 查询入驻信息是否存在
     *
     * @param mcnumberid
     * @return
     */
    public boolean selectMcEntryinFormationById(String mcnumberid) {
        if (mcnumberid.isEmpty()) {
            return true;
        }
        int result = mcEntryinFormationMapper.selectMcEntryinFormationById(mcnumberid);
        if (result == 0) {
            return false;
        }
        return true;
    }


}
