package com.hailu.cloud.api.admin.module.merchant.dao;


import com.hailu.cloud.api.admin.module.merchant.entity.McStoreInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface McStoreInformationAdminMapper {

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int insert(McStoreInformation record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int insertSelective(McStoreInformation record);



    /**
     *
     * @mbggenerated 2019-11-19
     */
    int updateByPrimaryKeySelective(McStoreInformation record);


    /**
     * 查询审核是否通过
     * @param memberid
     * @param toExamine
     * @return
     */
    int selectByMcnumberidAndToexamine(@Param("mcNumberId") String memberid, @Param("toExamine") Integer toExamine);


    /**
     * 根据商户id查询入驻信息是否存在
     * @param mcNumberId
     * @return
     */
    Integer selectMcEntryinFormationById(@Param("mcNumberId") String mcNumberId);


    McStoreInformation findMcStoreInformation(Long id);



    /**
     * 查询列表
     * @param parameter
     * @return
     */
    List<McStoreInformation> selectMcStoreInformationList(Object parameter);



}
