package com.hailu.cloud.api.mall.module.ledger.dao;

import com.hailu.cloud.api.mall.module.ledger.po.IncomeTransferOut;
import com.hailu.cloud.api.mall.module.ledger.vo.IncomeTransferOutVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author xuzhijie
 * @Date 2019/10/25 9:17
 */
@Mapper
public interface IncomeTransferOutMapper {

    /**
     * 提现
     *
     * @param userId     提现人 shop_member表的user_id
     * @param price      提现金额
     * @param bankCard   银行卡卡号
     * @param cardholder 持卡人名称
     * @return
     */
    boolean transferOut(
            @Param("id") Long id,
            @Param("userId") String userId,
            @Param("price") BigDecimal price,
            @Param("bankCard") String bankCard,
            @Param("cardholder") String cardholder);

    /**
     * 查询提现列表
     *
     * @param transferOut 提现查询条件
     * @param beginTime   开始时间
     * @param endTime     结束时间
     * @return
     */
    List<IncomeTransferOutVo> list(
            @Param("transferOut") IncomeTransferOut transferOut,
            @Param("beginTime") String beginTime,
            @Param("endTime") String endTime);

    /**
     * 审核
     *
     * @param memberId 审核人
     * @param id       提现记录ID
     * @param state    审核状态
     */
    void audit(
            @Param("memberId") String memberId,
            @Param("id") String id,
            @Param("state") Integer state);

}
