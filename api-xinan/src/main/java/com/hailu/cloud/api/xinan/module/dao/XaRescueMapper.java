package com.hailu.cloud.api.xinan.module.dao;

import com.hailu.cloud.api.xinan.module.entity.XaRescue;
import com.hailu.cloud.api.xinan.module.model.XaRescueVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表Mapper
 * @Date: 18:14 2019/11/12 0012
 */
@Mapper
public interface XaRescueMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insert(XaRescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(XaRescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    XaRescue selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(XaRescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeyWithBLOBs(XaRescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(XaRescue record);

    /**
     * 查询救助列表
     * @return
     */
    List<XaRescueVo> findXaRescueVo();

    List<XaRescue> findXaRescueList();


}