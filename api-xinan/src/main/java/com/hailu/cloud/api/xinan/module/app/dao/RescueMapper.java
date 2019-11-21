package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.Rescue;
import com.hailu.cloud.api.xinan.module.app.model.RescueVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表Mapper
 * @Date: 18:14 2019/11/12 0012
 */
@Mapper
public interface RescueMapper {
    /**
     *
     * @mbggenerated 2019-11-12
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insert(Rescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int insertSelective(Rescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    Rescue selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeySelective(Rescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKeyWithBLOBs(Rescue record);

    /**
     *
     * @mbggenerated 2019-11-12
     */
    int updateByPrimaryKey(Rescue record);

    /**
     * 查询救助列表
     * @return
     */
    List<RescueVo> findXaRescueVo();

    List<Rescue> findXaRescueList();


}