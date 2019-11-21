package com.hailu.cloud.api.mall.module.customerservice.dao;

import com.hailu.cloud.api.mall.module.customerservice.vo.CsReasonVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface CsReasonDao {
    /**
     * 返回对应的原因
     * 原因类型（0，维修，1换货，2退货）
     *
     * @return
     */
    List<CsReasonVo> findByCsReasonType(int reasonType);

    /**
     * 通过id 查找原因
     *
     * @param csReasonId
     * @return
     */
    CsReasonVo findCsReason(int csReasonId);
}
