package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.Insured;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsuredMapper {
    /**
     *
     * @mbggenerated 2019-10-17
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insert(Insured record);

    /**
     *
     * @mbggenerated 2019-10-17
     */
    int insertSelective(Insured record);

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
     *
     * @mbggenerated 2019-10-17
     */
    int updateByPrimaryKey(Insured record);

    /**
     * 根据会员ID和状态获取信息
     * @param memberId
     * @param memberStatus
     * @return
     */
    List<Insured> findListByMemberIdAndMemberStatus(@Param("memberId") String memberId, @Param("memberStatus") Integer memberStatus);

    /**
     * 根据名称和证件号码和证件类型查询
     * @param name
     * @param value
     * @param type
     * @return
     */
    Insured findByNameAndValueAndType(@Param("name") String name, @Param("value") String value, @Param("type") Integer type);

    /**
     * 根据会员ID查询信息
     * @param memberId
     * @return
     */
    List<Insured> findListByMemberId(@Param("memberId") String memberId);

    /**
     * 分页查询
     * @return
     */
    List<Insured> findListPage();
}