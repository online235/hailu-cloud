package com.hailu.cloud.api.xinan.module.dao;


import com.hailu.cloud.api.xinan.module.entity.Pay;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XaPayMapper {
    /**
     *
     * @mbggenerated 2019-10-17
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insert(Pay record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insertSelective(Pay record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    Pay selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKeySelective(Pay record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKey(Pay record);

    Pay findByPayOrderNo(@Param("payOrderNo") String payOrderNo);
}