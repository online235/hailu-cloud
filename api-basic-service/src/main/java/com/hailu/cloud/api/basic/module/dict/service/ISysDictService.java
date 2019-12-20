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
    List<SysDictModel> findList(String code);

    /**
     * 返回所有字典
     *
     * @return
     */
    SysDictModel findById(Long id);

    /**
     * 返回所有字典
     *
     * @return
     */
    void update(Long id, String code, String desc, String name, String value);

    /**
     * 添加字典项
     *
     * @param dictModel
     * @return
     * @throws BusinessException
     */
    SysDictModel addDict(SysDictModel dictModel) throws BusinessException;

    /**
     * 查询字典分类列表
     *
     * @return
     */
    List<SysDictModel> findCategory();

    /**
     * 删除字典
     *
     * @return
     */
    void deleteDict(Long id);
}
