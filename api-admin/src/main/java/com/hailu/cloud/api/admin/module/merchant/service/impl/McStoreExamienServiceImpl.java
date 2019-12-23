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
     * 更改审核状态
     * @param id
     * @param storeToExamine
     * @param examineType
     * @throws BusinessException
     */
    @Override
    public void storeToExamine(Long id, Integer storeToExamine, Integer examineType) throws BusinessException {

        McStoreExamine mcStoreExamine = this.findObjectById(id);
        if (mcStoreExamine == null) {
            throw new BusinessException("数据异常");
        }
        McStoreInformation mcStoreInformation = mcStoreInformationAdminService.findMcStoreInformation(mcStoreExamine.getStoreId());
        if (mcStoreInformation == null) {
            throw new BusinessException("数据异常");
        }
        if (examineType == 1) {
            mcStoreExamine.setPhoneToExamine(storeToExamine);
            if (storeToExamine == 2) {
                mcStoreInformation.setPhone(mcStoreExamine.getShopPhone());
            }
        } else if (examineType == 2) {
            mcStoreExamine.setAddressToExamine(storeToExamine);
            if (storeToExamine == 2) {
                mcStoreInformation.setDetailAddress(mcStoreExamine.getShopAddressDetail());
                mcStoreInformation.setAreaCode(mcStoreExamine.getAreaCode());
            }
        } else if (examineType == 3) {
            mcStoreExamine.setStoreNameExamine(storeToExamine);
            if (storeToExamine == 2) {
                mcStoreInformation.setShopName(mcStoreExamine.getStoreName());
            }
        }
        int result = this.updateByPrimaryKey(mcStoreExamine);
        if (result <= 0) {
            throw new BusinessException("更改审核异常");
        }
        mcStoreInformation.setUpdateDateTime(new Date());
        mcStoreInformationAdminService.updateMcStoreInformation(mcStoreInformation);
    }


}
