package com.hailu.cloud.api.basic.module.dict.dao;

import com.hailu.cloud.common.model.dict.SysDictModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 返回所有字典
     *
     * @return
     */
    List<SysDictModel> findList(@Param("code") String code);

    /**
     * 根据ID查询字典
     *
     * @return
     */
    SysDictModel findById(@Param("id") Long id);

    /**
     * 更新字典
     *
     * @param id
     * @param code
     * @param desc
     * @param name
     * @param value
     */
    void update(
            @Param("id") Long id,
            @Param("code") String code,
            @Param("desc") String desc,
            @Param("name") String name,
            @Param("value") String value);

    /**
     * 添加字典项
     *
     * @param dictModel
     */
    void addDict(SysDictModel dictModel);

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
    void deleteDict(@Param("id") Long id);
}
