package com.hailu.cloud.api.admin.module.merchant.dao;


import com.hailu.cloud.api.admin.module.merchant.entity.LocalCircleEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface LocalCircleEntryAdminMapper {

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int insert(LocalCircleEntry record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int insertSelective(LocalCircleEntry record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    LocalCircleEntry selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int updateByPrimaryKeySelective(LocalCircleEntry record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int updateByPrimaryKey(LocalCircleEntry record);

    /**
     * 查询审核是否通过
     * @param memberid
     * @param toexamine
     * @return
     */
    int selectByMcnumberidAndToexamine(@Param("memberid") String memberid, @Param("toexamine") Integer toexamine);


    /**
     * 查询列表
     * @param parameter
     * @return
     */
    List<LocalCircleEntry> selectMcEntryinFormationList(Object parameter);

    /**
     * 根据商户id查询入驻信息是否存在
     * @param mcnumberid
     * @return
     */
    int selectMcEntryinFormationById(@Param("mcnumberid") String mcnumberid);

}
