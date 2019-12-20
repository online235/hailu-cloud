package com.hailu.cloud.api.auth.module.login.dao;

import com.hailu.cloud.api.auth.module.login.model.MerchantLocalLifeResult;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.merchant.StoreInformationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/11/6 11:05
 */
@Mapper
public interface MerchantMapper {

    /**
     * 根据手机号查找账号
     *
     * @param phone 手机号码
     * @return
     */
    MerchantUserLoginInfoModel findUserByPhone(@Param("phone") String phone);

    /**
     * 根据手机号查找账号
     *
     * @param userId 用户ID
     * @return
     */
    List<StoreInformationModel> findUserStore(@Param("userId") String userId);

    /**
     * 根据手机号查找账号
     *
     * @param account 账号
     * @return
     */
    MerchantUserLoginInfoModel findUserByAccount(@Param("account") String account);


    /**
     * 百货审核状态
     * @param userId
     * @return
     */
    Integer findEntryExamine(@Param("userId") String userId);


    /**
     * 生活圈审核状态
     * @param userId
     * @return
     */
    MerchantLocalLifeResult findLifeExamine(@Param("userId") String userId);


}
