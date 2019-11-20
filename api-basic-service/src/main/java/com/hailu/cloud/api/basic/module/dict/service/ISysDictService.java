package com.hailu.cloud.api.basic.module.dict.service;

import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.dict.SysDictModel;

import java.util.List;

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
     * 返回所有字典
     *
     * @return
     */
    List<SysDictModel> findAll();

    /**
     * 添加字典项
     *
     * @param dictModel
     * @return
     * @throws BusinessException
     */
    SysDictModel addDict(SysDictModel dictModel) throws BusinessException;

}
