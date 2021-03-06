package com.hailu.cloud.api.merchant.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.system.InvitedetailModel;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 商城服务
 */
@FeignClient(name = "service-api-mall")
public interface MallFeignClient {

    /**
     *  添加汇总信息
     * @param userId                会员ID
     * @param totalConsumption      累计消费
     * @param inviteMemberNum       邀请会员数量
     * @param inviteMerchatNum      邀请商家数量
     * @param invitePartnersNum     邀请合伙人数量
     * @param salesPerformance      销售业绩
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/memberDetail/addTotal")
    void addTotal(@RequestParam("userId") String userId,
                  @RequestParam("totalConsumption") BigDecimal totalConsumption,
                  @RequestParam("inviteMemberNum") Integer inviteMemberNum,
                  @RequestParam("inviteMerchatNum") Integer inviteMerchatNum,
                  @RequestParam("invitePartnersNum") Integer invitePartnersNum,
                  @RequestParam("salesPerformance") BigDecimal salesPerformance);


    /**
     * 保存邀请信息
     * @param invitedetailModel
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/invite/add")
    ApiResponse addInvitedetail(@RequestBody InvitedetailModel invitedetailModel);
}
