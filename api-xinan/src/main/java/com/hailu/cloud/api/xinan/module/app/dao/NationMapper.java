package com.hailu.cloud.api.xinan.module.app.dao;


import com.hailu.cloud.api.xinan.module.app.entity.Nation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NationMapper {
    /**
     *
     * @mbggenerated 2019-10-30
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-10-30
     */
    int insert(Nation record);

    /**
     *
     * @mbggenerated 2019-10-30
     */
    int insertSelective(Nation record);

    /**
     *
     * @mbggenerated 2019-10-30
     */
    Nation selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-10-30
     */
    int updateByPrimaryKeySelective(Nation record);

    /**
     *
     * @mbggenerated 2019-10-30
     */
    int updateByPrimaryKey(Nation record);

    /**
     * 根据父级ID查询下面的地区，如果不传则查询省信息
     * @param parentCode
     * @return
     */
    List<Nation> findByParentId(@Param("parentCode") String parentCode);

    /**
     * 根据名称查找该数据
     * @param areaName
     */
    Nation findNationByAreaName(String areaName);



    /**
     * 根据code的集合查找list数据
     * @param parameter
     * @return
     */
    List<Nation> findListByCodeArray(Object parameter);


    /**
     * 根据区code查询父code
     * @param Code
     * @return
     */
    String findCodeBySonCode(@Param("Code") String Code);

    /**
     * 根据code查询城市名称
     * @param code
     * @return
     */
    String findCityNameByCode(@Param("code") String code);

}