package com.hailu.cloud.api.admin.module.xinan.dao;

import com.hailu.cloud.api.admin.module.xinan.entity.Government;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description  ：
 * @author       : QiuFeng:WANG
 * @date         : 2019/12/3 0003 15:05
 */
@Mapper
public interface GovernmentMapper {

    /**
     * 添加政府账号
     * @mbggenerated 2019-12-02
     */
    int insertSelective(Government record);

    /**
     * 根据编号查询账号详细信息
     * @mbggenerated 2019-12-02
     */
    Government selectByPrimaryKey(Long adminId);

    /**
     * 根据政府编号根据信息
     * @mbggenerated 2019-12-02
     */
    int updateByPrimaryKeySelective(Government record);

    /**
     * 根据城市查询文章
     * @param cityCode
     * @return
     */
    Government findGovernmentUsersByCityCode(String cityCode);

    /**
     * 管理员查询文章列表
     * @return
     */
    List<Government> findGovernmentList();
}