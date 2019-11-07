package com.hailu.cloud.api.auth.module.login.dao;

import com.hailu.cloud.common.model.MemberModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author xuzhijie
 * @Date 2019/11/6 11:05
 */
@Mapper
public interface MemberMapper {

    /**
     * 根据手机号查找账号
     *
     * @param phone 手机号码
     * @return
     */
    MemberModel findMember(@Param("phone") String phone);

}
