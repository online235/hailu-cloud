package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.CharitableCommonweal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/3 0003 14:56
 */
@Mapper
public interface CharitableCommonwealMapper {
    /**
     *
     * @mbggenerated 2019-12-02
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int insert(CharitableCommonweal record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int insertSelective(CharitableCommonweal record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    CharitableCommonweal selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeySelective(CharitableCommonweal record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeyWithBLOBs(CharitableCommonweal record);

    /**
     *
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKey(CharitableCommonweal record);

    /**
     * 根据政府编号查询公益列表
     * @param usersId
     * @return
     */
    List<CharitableCommonweal> findCharitableCommonwealByUsersId(@Param("usersId") Long usersId);
}