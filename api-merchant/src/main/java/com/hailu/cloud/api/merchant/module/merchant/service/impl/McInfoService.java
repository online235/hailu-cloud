package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.LocalCircleEntryMapper;
import com.hailu.cloud.api.merchant.module.merchant.dao.McUserMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.LocalCircleEntry;
import com.hailu.cloud.api.merchant.module.merchant.entity.McManagementType;
import com.hailu.cloud.api.merchant.module.merchant.parameter.MerchantEnteringParameter;
import com.hailu.cloud.api.merchant.module.merchant.result.RegisterShopInformationResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McManagementTypeService;
import com.hailu.cloud.api.merchant.module.merchant.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McUserParameter;
import com.hailu.cloud.api.merchant.module.merchant.parameter.ShopInformationEntryParameter;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
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

    @Resource
    private McManagementTypeService mcManagementTypeService;

    @Resource
    private LocalCircleEntryMapper localCircleEntryMapper;

    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;


    /**
     * 生活圈提交店铺资料
     *
     * @param shopInformationEntryParameter
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitShopInformation(ShopInformationEntryParameter shopInformationEntryParameter) throws BusinessException {

        McStoreInformation mcStoreInformation = new McStoreInformation();
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        String mcNumberId = merchantUserLoginInfoModel.getNumberid();
        //账号类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户
        if (merchantUserLoginInfoModel.getAccounttype() == 1) {
            LocalCircleEntry localCircleEntry = localCircleEntryMapper.findLocalCircleEntryByUserId(mcNumberId);
            if (localCircleEntry == null) {
                throw new BusinessException("数据状态不正确");
            }
            BeanUtils.copyProperties(shopInformationEntryParameter, localCircleEntry);
            localCircleEntry.setToExamine(2);
            mcStoreInformation.setStoreTotalType(shopInformationEntryParameter.getFirstManagementTypeId());
            mcStoreInformation.setStoreSonType(shopInformationEntryParameter.getSecondManagementTypeId());
            localCircleEntryMapper.updateByPrimaryKeySelective(localCircleEntry);
        } else if (merchantUserLoginInfoModel.getAccounttype() == 2) {
            McEntryInformation mcEntryInformation = mcEntryinFormationMapper.findMcEntryInformationByMcNumberId(mcNumberId);
            if (mcEntryInformation == null) {
                throw new BusinessException("数据状态不正确");
            }
            BeanUtils.copyProperties(shopInformationEntryParameter, mcEntryInformation);
            mcEntryInformation.setToExamine(2);
            mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryInformation);
            mcStoreInformation.setStoreTotalType(mcEntryInformation.getFirstManagementTypeId());
        }
        BeanUtils.copyProperties(shopInformationEntryParameter, mcStoreInformation);
        mcStoreInformation.setMcNumberId(mcNumberId);
        mcStoreInformation.setAccountType(merchantUserLoginInfoModel.getAccounttype());
        mcStoreInformationService.insertSelective(mcStoreInformation);

    }



    /**
     * 商家注册以及入驻
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMcUserRegister(McUserParameter mcUserParameter) throws BusinessException {

        String mcNumberId = mcUserService.insertSelective(mcUserParameter.getLandingAccount(), mcUserParameter.getLandingPassword(),
                mcUserParameter.getPhone(), mcUserParameter.getAccountType(),mcUserParameter.getNickName());
        long time = System.currentTimeMillis();
        //生成随机ID
        String numberId = String.valueOf(uuidFeignClient.uuid().getData());
        //百货入驻
        if (mcUserParameter.getAccountType() == 2) {
            McEntryInformation mcEntryInformation = new McEntryInformation();
            BeanUtils.copyProperties(mcUserParameter, mcEntryInformation);
            if (mcEntryInformation == null) {
                throw new BusinessException("数据状态不正确");
            }
            boolean boo = mcEntryinFormationService.selectMcEntryinFormationById(mcNumberId);
            if (boo) {
                throw new BusinessException("信息已存在");
            }
            mcEntryInformation.setNumberId(numberId);
            mcEntryInformation.setDateTime(time);
            mcEntryInformation.setUpdateDateTime(time);
            mcEntryInformation.setToExamine(0);
            mcEntryInformation.setMcNumberId(mcNumberId);
            mcEntryinFormationMapper.insertSelective(mcEntryInformation);
            //生活圈入驻
        } else if (mcUserParameter.getAccountType() == 1) {
            LocalCircleEntry localCircleEntry = new LocalCircleEntry();
            BeanUtils.copyProperties(mcUserParameter, localCircleEntry);
            localCircleEntry.setMcNumberId(mcNumberId);
            localCircleEntry.setNumberId(numberId);
            localCircleEntry.setDateTime(time);
            localCircleEntry.setToExamine(0);
            localCircleEntry.setUpdateDateTime(time);
            Integer result = localCircleEntryMapper.insertSelective(localCircleEntry);
            if (result <= 0) {
                throw new BusinessException("插入数据失败");
            }

        }
    }

    /**
     * 获取当前店铺资料
     */
    public RegisterShopInformationResult getRegisterShopInformationResult() {

        RegisterShopInformationResult registerShopInformationResult = new RegisterShopInformationResult();
        MerchantUserLoginInfoModel merchantUserLoginInfoModel = RequestUtils.getMerchantUserLoginInfo();
        String mcNumberId = merchantUserLoginInfoModel.getNumberid();
        //账号类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户
        if (merchantUserLoginInfoModel.getAccounttype() == 1) {
            LocalCircleEntry localCircleEntry = localCircleEntryMapper.findLocalCircleEntryByUserId(mcNumberId);
            BeanUtils.copyProperties(localCircleEntry, registerShopInformationResult);
        } else if (merchantUserLoginInfoModel.getAccounttype() == 2) {
            McEntryInformation mcEntryInformation = mcEntryinFormationMapper.findMcEntryInformationByMcNumberId(mcNumberId);
            BeanUtils.copyProperties(mcEntryInformation, registerShopInformationResult);
        }
        return registerShopInformationResult;
    }

}
