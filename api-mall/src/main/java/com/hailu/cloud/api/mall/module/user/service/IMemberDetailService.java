package com.hailu.cloud.api.mall.module.user.service;

import com.hailu.cloud.common.exception.BusinessException;

import java.math.BigDecimal;

public interface IMemberDetailService {

    /**
     *
     * @param userId                会员ID
     * @param totalConsumption      累计消费
     * @param inviteMemberNum       邀请会员数量
     * @param inviteMerchatNum      邀请商家数量
     * @param invitePartnersNum     邀请合伙人数量
     * @param salesPerformance      销售业绩
     */
   void addTotal(String userId, BigDecimal totalConsumption,Integer inviteMemberNum,Integer inviteMerchatNum,
                 Integer invitePartnersNum,BigDecimal salesPerformance);

    /**
     *  增加服务区域
     * @param userId        会员ID
     * @param provinceId    省code
     * @param cityId        市code
     * @param areaId        区县code
     * @param address       详细地址
     * @param wantBuyType   购买的类型（1-区代、2-城市合伙人、3-销售）
     * @return
     */
    Long addAddress(String userId,String provinceId,String cityId,String areaId,String address,Integer wantBuyType,String name,String phone);

    Object findServiceProvidersList();

    /**
     * 分页查询会员邀请信息
     * @param page
     * @param size
     * @param value
     * @param type
     * @return
     */
    Object findMemberDetail(int page,int size,String value,int type,String userId,Integer memberAll) throws BusinessException;

}
