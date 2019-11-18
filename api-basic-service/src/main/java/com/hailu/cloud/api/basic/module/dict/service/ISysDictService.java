package com.hailu.cloud.api.basic.module.dict.service;

import com.hailu.cloud.api.basic.module.dict.model.SysDictModel;
import com.hailu.cloud.common.exception.BusinessException;

/**
 * @Author xuzhijie
 * @Date 2019/11/18 23:40
 */
public interface ISysDictService {

    /**
     * 根据code和value查询字典项
     *
     * @param code
     * @param value
     * @return
     */
    SysDictModel find(String code, String value);

    /**
     * 添加字典项
     *
     * @param dictModel
     */
    SysDictModel addDict(SysDictModel dictModel) throws BusinessException;

}
