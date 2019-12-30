package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreExamineMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreExamine;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreExamineModel;
import com.hailu.cloud.api.merchant.module.merchant.parameter.McStoreExamineParameter;
import com.hailu.cloud.api.merchant.module.merchant.result.ExamineResult;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreExamienService;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.BeanUtils;
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
    private McStoreInformationService mcStoreInformationService;


    @Override
    public int insertSelective(McStoreExamine mcStoreExamine) {

        mcStoreExamine.setId(basicFeignClient.uuid().getData());
        mcStoreExamine.setDateTime(new Date());
        return mcStoreExamineMapper.insertSelective(mcStoreExamine);
    }

    @Override
    public int updateByPrimaryKey(McStoreExamine mcStoreExamine) {
        return mcStoreExamineMapper.updateByPrimaryKey(mcStoreExamine);
    }

    @Override
    public List<McStoreExamineModel> findListByParam(Object parameter) {
        return mcStoreExamineMapper.findListByParam(parameter);
    }

    @Override
    public McStoreExamineModel findObjectById(Long id) {
        return mcStoreExamineMapper.findObjectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return mcStoreExamineMapper.deleteById(id);
    }

    @Override
    public McStoreExamine findObjectByStoreId(Long storeId) {
        return mcStoreExamineMapper.findObjectByStoreId(storeId);
    }


    /**
     * 提交更新审核信息
     *
     * @param mcStoreExamineParameter
     * @throws BusinessException
     */
    @Override
    public void submitStoreExamine(McStoreExamineParameter mcStoreExamineParameter) throws BusinessException {

        if (mcStoreExamineParameter.getStoreId() == null) {
            throw new BusinessException("店铺id不能为空！");
        }
        Long storeId = mcStoreExamineParameter.getStoreId();
        McStoreExamine mcStoreExamine = this.findObjectByStoreId(storeId);
        //审核表是否存在该数据，存在就更新，不存在就插入
        if (mcStoreExamine != null) {
            BeanUtils.copyProperties(mcStoreExamineParameter, mcStoreExamine);
            if (mcStoreExamineParameter.getShopPhone() != null) {
                mcStoreExamine.setPhoneToExamine(1);
            } else if (mcStoreExamineParameter.getShopAddressDetail() != null) {
                mcStoreExamine.setAddressToExamine(1);
            }
            this.updateByPrimaryKey(mcStoreExamine);
        } else {
            mcStoreExamine = new McStoreExamine();
            BeanUtils.copyProperties(mcStoreExamineParameter, mcStoreExamine);
            if (mcStoreExamineParameter.getShopPhone() != null) {
                mcStoreExamine.setPhoneToExamine(1);
            } else if (mcStoreExamineParameter.getShopAddressDetail() != null) {
                mcStoreExamine.setAddressToExamine(1);
            }
            this.insertSelective(mcStoreExamine);
        }
    }


    /**
     * @param storeId     店铺id
     * @param examineType 提交类型:电话-1,地址审核-2,店铺名审核-3
     * @return
     * @throws BusinessException
     */
    @Override
    public ExamineResult getExamineResult(Long storeId, Integer examineType) throws BusinessException {

        ExamineResult examineResult = new ExamineResult();
        if (storeId == null) {
            throw new BusinessException("店铺id不能为空！");
        }
        McStoreInformation mcStoreInformation = mcStoreInformationService.findMcStoreInformationById(storeId);
        McStoreExamine mcStoreExamine = this.findObjectByStoreId(storeId);
        //电话-1 更新原来数据返回
        if (examineType == 1) {
            examineResult.setOriginalShopPhone(mcStoreInformation.getPhone());

            if (mcStoreExamine != null) {
                BeanUtils.copyProperties(mcStoreExamine, examineResult);
                //还在审核中
                if (mcStoreExamine.getPhoneToExamine() != null && mcStoreExamine.getPhoneToExamine() == 1) {
                    examineResult.setToExamine(1);
                    return examineResult;
                } else if (mcStoreExamine.getPhoneToExamine() != null && mcStoreExamine.getPhoneToExamine() == 2) {
                    examineResult.setToExamine(2);
                    return examineResult;
                } else if (mcStoreExamine.getPhoneToExamine() != null && mcStoreExamine.getPhoneToExamine() == 3) {
                    examineResult.setToExamine(3);
                    return examineResult;
                }
            }
        } else if (examineType == 2) {
            examineResult.setOriginalProvinceCode(mcStoreInformation.getProvinceCode());
            examineResult.setOriginalCityCode(mcStoreInformation.getCityCode());
            examineResult.setOriginalAreaCode(mcStoreInformation.getAreaCode());
            examineResult.setOriginalShopAddressDetail(mcStoreInformation.getDetailAddress());
            if (mcStoreExamine != null) {
                BeanUtils.copyProperties(mcStoreExamine, examineResult);
                //还在审核中
                if (mcStoreExamine.getAddressToExamine() != null && mcStoreExamine.getAddressToExamine() == 1) {
                    examineResult.setToExamine(1);
                    return examineResult;
                } else if (mcStoreExamine.getAddressToExamine() != null && mcStoreExamine.getAddressToExamine() == 2) {
                    examineResult.setToExamine(2);
                    return examineResult;
                } else if (mcStoreExamine.getAddressToExamine() != null && mcStoreExamine.getAddressToExamine() == 3) {
                    examineResult.setToExamine(3);
                    return examineResult;
                }
            }
        } else if (examineType == 3) {
            examineResult.setOriginalShopName(mcStoreInformation.getShopName());
            if (mcStoreExamine != null) {
                BeanUtils.copyProperties(mcStoreExamine, examineResult);
                //还在审核中
                if (mcStoreExamine.getStoreNameExamine() != null && mcStoreExamine.getStoreNameExamine() == 1) {
                    examineResult.setToExamine(1);
                    return examineResult;
                } else if (mcStoreExamine.getStoreNameExamine() != null && mcStoreExamine.getStoreNameExamine() == 2) {
                    examineResult.setToExamine(2);
                    return examineResult;
                } else if (mcStoreExamine.getStoreNameExamine() != null && mcStoreExamine.getStoreNameExamine() == 3) {
                    examineResult.setToExamine(3);
                    return examineResult;
                }
            }
        }
        examineResult.setToExamine(0);
        return examineResult;
    }


}
