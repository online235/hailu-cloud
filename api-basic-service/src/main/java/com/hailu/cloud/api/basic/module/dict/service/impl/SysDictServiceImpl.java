package com.hailu.cloud.api.basic.module.dict.service.impl;

import com.hailu.cloud.api.basic.module.dict.dao.SysDictMapper;
import com.hailu.cloud.api.basic.module.dict.model.SysDictModel;
import com.hailu.cloud.api.basic.module.dict.service.ISysDictService;
import com.hailu.cloud.api.basic.module.uid.component.UidGenerator;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuzhijie
 * @date 2019/11/18 23:41
 */
@Slf4j
@Service
public class SysDictServiceImpl implements ISysDictService {

    @Resource
    private SysDictMapper dictMapper;

    @Autowired
    private UidGenerator uidGenerator;

    @Cacheable(value = "dict", key = "#code + '-' + #value")
    @Override
    public SysDictModel find(String code, String value) {
        return dictMapper.find(code, value);
    }

    @Override
    public SysDictModel addDict(SysDictModel dictModel) throws BusinessException {
        SysDictModel exists = dictMapper.find(dictModel.getCode(), dictModel.getValue());
        if( exists != null ){
            throw new BusinessException("字典项已存在");
        }
        dictModel.setId(uidGenerator.uuid());
        dictMapper.addDict(dictModel);
        return dictModel;
    }

}
