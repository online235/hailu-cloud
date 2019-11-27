package com.hailu.cloud.api.merchant.module.merchant.dao;


import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.model.bak.McStoreInformationModel;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangmugui
 */
public interface McStoreInformationMapper {

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
    McStoreInformation selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int updateByPrimaryKeySelective(McStoreInformation record);

    /**
     *
     * @mbggenerated 2019-11-19
     * @param record
     */
    int updateByPrimaryKey(McStoreInformationModel record);

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


    McStoreInformation findMcStoreInformation(String mcNumberId);
}
