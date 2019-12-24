package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreFunctionMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreFunction;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreFunctionModel;
import com.hailu.cloud.api.merchant.module.merchant.service.McStoreFunctionService;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class McStoreFunctionServiceImpl implements McStoreFunctionService {


    @Resource
    private McStoreFunctionMapper mcStoreFunctionMapper;
    @Resource
    private BasicFeignClient basicFeignClient;

    @Override
    public int insertSelective(McStoreFunction mcStoreFunction) {

        mcStoreFunction.setDateTime(new Date());
        mcStoreFunction.setId(basicFeignClient.uuid().getData());
        mcStoreFunction.setUpdateDateTime(new Date());
        return mcStoreFunctionMapper.insertSelective(mcStoreFunction);
    }

    @Override
    public int updateByObject(McStoreFunction mcStoreFunction) {

        mcStoreFunction.setUpdateDateTime(new Date());
        return mcStoreFunctionMapper.updateByObject(mcStoreFunction);
    }

    @Override
    public List<McStoreFunctionModel> findListByParameter(Object parameter) {

        return mcStoreFunctionMapper.findListByParameter(parameter);
    }

    @Override
    public McStoreFunction findObjectByStoreId(Long storeId) {

        return mcStoreFunctionMapper.findObjectByStoreId(storeId);
    }


    /**
     * 更新保存店铺预定管理
     */
    @Override
    public int saveUpdateStoreFunction(McStoreFunctionModel mcStoreFunctionModel) {

        int result = 1;
        McStoreFunction mcStoreFunction = mcStoreFunctionMapper.findObjectByStoreId(mcStoreFunctionModel.getStoreId());
        //空则去保存
        if(mcStoreFunction == null){
            BeanUtils.copyProperties(mcStoreFunctionModel,mcStoreFunction);
            result = this.insertSelective(mcStoreFunction);
        }else{
            mcStoreFunction.setAppointmentSaveTime(mcStoreFunctionModel.getAppointmentSaveTime());
            mcStoreFunction.setAutomaticStatus(mcStoreFunctionModel.getAutomaticStatus());
            mcStoreFunction.setReserveStatus(mcStoreFunctionModel.getReserveStatus());
            mcStoreFunction.setMaxOrderNum(mcStoreFunctionModel.getMaxOrderNum());
            result = this.updateByObject(mcStoreFunction);
        }
        return result;

    }

}
