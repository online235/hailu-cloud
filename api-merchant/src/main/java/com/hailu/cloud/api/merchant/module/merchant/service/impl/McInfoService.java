package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.hailu.cloud.api.merchant.module.lifecircle.dao.McUserMapper;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.McUser;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.McEntryinFormationService;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.McUserService;
import com.hailu.cloud.api.merchant.module.merchant.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 19:10 2019/11/3 0003
 */
@Service
public class McInfoService {

    @Resource
    private McUserMapper mcUserMapper;

    @Autowired
    private McUserService mcUserService;

    @Autowired
    private McEntryinFormationService mcEntryinFormationService;

    @Resource
    private McEntryInformationMapper mcEntryinFormationMapper;

    @Autowired
    private BasicFeignClient uuidFeignClient;

    @Resource
    private McStoreInformationService mcStoreInformationService;

    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;

    /**
     * 商家注册以及入驻
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMcUserAndEntry(ShopInformationEntryParameter shopInformationEntryParameter, Integer accountType) throws BusinessException {

        String mcNumberId = mcUserService.insertSelective(shopInformationEntryParameter.getLandingAccount(), shopInformationEntryParameter.getLandingPassword(), shopInformationEntryParameter.getMoli(), shopInformationEntryParameter.getCode(), accountType);

        McEntryInformation mcEntryInformation = new McEntryInformation();
        BeanUtils.copyProperties(shopInformationEntryParameter, mcEntryInformation);
        if (mcEntryInformation == null) {
            throw new BusinessException("数据状态不正确");
        }
        boolean boo = mcEntryinFormationService.selectMcEntryinFormationById(mcNumberId);
        if (boo) {
            throw new BusinessException("信息已存在");
        }
        long time = System.currentTimeMillis();
        //生成随机ID
        String mcnumberid = String.valueOf(uuidFeignClient.uuid().getData());
        mcEntryInformation.setNumberId(mcnumberid);
        mcEntryInformation.setCreatedat(time);
        mcEntryInformation.setUpdatedat(time);
        mcEntryInformation.setToExamine(Mceunm.IN_AUDIT.getKey());
        mcEntryInformation.setMcNumberId(mcNumberId);
        mcEntryinFormationMapper.insertSelective(mcEntryInformation);
        McStoreInformation mcStoreInformation = new McStoreInformation();
        BeanUtils.copyProperties(mcEntryInformation, mcStoreInformation);
        mcStoreInformation.setStoreTotalType(mcEntryInformation.getFirstManagementTypeId());
        mcStoreInformation.setStoreSonType(mcEntryInformation.getSecondManagementTypeId());
        mcStoreInformationService.insertSelective(mcStoreInformation);

    }


}
