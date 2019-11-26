package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.Insured;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuredMapper {
    /**
     *
     * @mbggenerated 2019-10-17
     */
    Insured selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKeySelective(Insured record);


    /**
     * 分页查询
     * @return
     */
    List<Insured> findListPage();
}