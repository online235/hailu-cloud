package com.hailu.cloud.api.basic.module.dict.dao;

import com.hailu.cloud.api.basic.module.dict.model.SysDictModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author xuzhijie
 * @Date 2019/11/18 23:14
 */
@Mapper
public interface SysDictMapper {

    /**
     * 根据code和value查询字典项
     *
     * @param code
     * @param value
     * @return
     */
    SysDictModel find(@Param("code") String code, @Param("value") String value);

    /**
     * 添加字典项
     *
     * @param dictModel
     */
    void addDict(SysDictModel dictModel);
}
