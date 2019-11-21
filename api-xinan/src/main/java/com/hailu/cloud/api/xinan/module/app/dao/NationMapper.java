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
     * @param parentId
     * @return
     */
    List<Nation> findByParentId(@Param("parentId") Long parentId);

    /**
     * 根据省名称查找该数据
     * @param provinceName
     */
    Nation findNationByProvince(String provinceName);

    /**
     * 根据市名称查找该数据
     * @param cityName
     */
    Nation findNationByCityName(String cityName);


    /**
     * 根据区名称查找该数据
     * @param district
     */
    Nation findNationByDistrict(String district);


    /**
     * 根据code的集合查找list数据
     * @param parameter
     * @return
     */
    List<Nation> findListByCodeArray(Object parameter);



}