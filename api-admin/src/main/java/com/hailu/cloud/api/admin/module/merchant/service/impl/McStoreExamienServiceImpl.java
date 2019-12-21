package com.hailu.cloud.api.admin.module.merchant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.merchant.dao.McStoreExamineMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.admin.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.admin.module.merchant.model.McStoreExamineModel;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McStoreExamineListParameter;
import com.hailu.cloud.api.admin.module.merchant.service.McStoreExamienService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class McStoreExamienServiceImpl implements McStoreExamienService {


    @Autowired
    private McStoreExamineMapper mcStoreExamineMapper;

    @Resource
    private BasicFeignClient basicFeignClient;

    @Resource
    private McStoreInformationAdminService mcStoreInformationAdminService;



    @Override
    public int updateByPrimaryKey(McStoreExamine mcStoreExamine) {
        return mcStoreExamineMapper.updateByPrimaryKey(mcStoreExamine);
    }

    @Override
    public List<McStoreExamineModel> findListByParam(Object parameter) {
        return mcStoreExamineMapper.findListByParam(parameter);
    }

    @Override
    public McStoreExamine findObjectById(Long id) {
        return mcStoreExamineMapper.findObjectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return mcStoreExamineMapper.deleteById(id);
    }


    /**
     * 查询店铺审核信息列表
     */
    @Override
    public PageInfoModel<List<McStoreExamineModel>> selectMcStoreExamineModelList(McStoreExamineListParameter mcStoreExamineListParameter) {

        Page pageData = PageHelper.startPage(mcStoreExamineListParameter.getPageNum(), mcStoreExamineListParameter.getPageSize());
        List<McStoreExamineModel> result = mcStoreExamineMapper.findListByParam(mcStoreExamineListParameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }

    /**
     * 电话审核状态
     * @param id
     * @param phoneToExamine
     * @throws BusinessException
     */
    @Override
    public void updatePhoneToExamine(Long id, Integer phoneToExamine) throws BusinessException {

        McStoreExamine mcStoreExamine = this.findObjectById(id);

        if(mcStoreExamine == null){
            throw new BusinessException("数据异常");
        }
        McStoreInformation mcStoreInformation = mcStoreInformationAdminService.findMcStoreInformation(mcStoreExamine.getStoreId());
        if(mcStoreInformation == null){
            throw new BusinessException("数据异常");
        }
        mcStoreExamine.setPhoneToExamine(phoneToExamine);
        int result = this.updateByPrimaryKey(mcStoreExamine);
        if(result <= 0){
            throw new BusinessException("更改审核异常");
        }
        if(phoneToExamine == 2){
            mcStoreInformation.setUpdateDateTime(new Date());
            mcStoreInformation.setPhone(mcStoreExamine.getShopPhone());
            mcStoreInformationAdminService.updateMcStoreInformation(mcStoreInformation);
        }
    }


    /**
     * 地址审核状态
     * @param id
     * @param addressToExamine
     * @throws BusinessException
     */
    @Override
    public void updateAddressToExamine(Long id, Integer addressToExamine) throws BusinessException {

        McStoreExamine mcStoreExamine = this.findObjectById(id);

        if(mcStoreExamine == null){
            throw new BusinessException("数据异常");
        }
        McStoreInformation mcStoreInformation = mcStoreInformationAdminService.findMcStoreInformation(mcStoreExamine.getStoreId());
        if(mcStoreInformation == null){
            throw new BusinessException("数据异常");
        }
        mcStoreExamine.setAddressToExamine(addressToExamine);
        int result = this.updateByPrimaryKey(mcStoreExamine);
        if(result <= 0){
            throw new BusinessException("更改审核异常");
        }
        if(addressToExamine == 2){
            mcStoreInformation.setUpdateDateTime(new Date());
            mcStoreInformation.setDetailAddress(mcStoreExamine.getShopAddressDetail());
            mcStoreInformation.setAreaCode(mcStoreExamine.getAreaCode());
            mcStoreInformationAdminService.updateMcStoreInformation(mcStoreInformation);
        }
    }


}
