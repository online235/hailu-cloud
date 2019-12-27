package com.hailu.cloud.api.xinan.feigns;

import com.hailu.cloud.api.xinan.module.app.entity.ShopMember;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "service-api-mall")
public interface MallFeignClient {


    /**
     * 根据UserId获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping(Constant.API_VERSION + Constant.API_NAME_MALL + "/api/user/findById")
    ApiResponse<ShopMember> findById(@RequestParam(value = "userId") String userId);

    /**
     * 更改会员信息的商户状态
     *
     * @param userId
     * @param merchantType
     * @param superiorMember
     * @param cityId
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL + "/api/user/editMerchantTypeAndSuperiorMember")
    void editMerchantTypeAndSuperiorMember(
            @RequestParam("userId") String userId,
            @RequestParam("merchantType") int merchantType,
            @RequestParam("superiorMember") String superiorMember,
            @RequestParam("cityId") Long cityId
    );

    /**
     * 服务商分销
     *
     * @param userInfo
     * @param money
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL + "/ledger/editInvitationProvider")
    void editInvitationProvider(@RequestBody ShopMember userInfo,
                                @RequestParam(value = "money") BigDecimal money);

    /**
     * 获取购买服务商价格
     *
     * @param chooseCityId
     * @return
     */
    @GetMapping(Constant.API_VERSION + Constant.API_NAME_MALL + "/api/user/findPoviderPrice")
    ApiResponse<Integer> findPoviderPrice(@RequestParam("chooseCityId") Long chooseCityId);

    /**
     * 根据userId修改服务商状态
     * @param userId
     * @param isService
     * @return
     */
    @GetMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/serviceProviders/updateStatusByUserId")
    ApiResponse<Integer> updateStatusByUserId(@RequestParam("userId") String userId, @RequestParam("isService")int isService);
}
