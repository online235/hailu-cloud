package com.hailu.cloud.api.xinan.feigns;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import com.hailu.cloud.common.model.serviceproviders.ServiceProvidersDto;
import com.hailu.cloud.common.model.system.InvitedetailModel;
import com.hailu.cloud.common.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author junpei.deng
 */
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

    /**
     * 保存/修改服务商
     * @param serviceProvidersDto
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/serviceProviders/save")
    ApiResponse<Long> save(@RequestBody ServiceProvidersDto serviceProvidersDto);

    /**
     * 查询服务商信息
     * @param userId
     * @return
     */
    @GetMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/serviceProviders/findDetail")
    ApiResponse<ServiceProvidersDto> findDetail(@RequestParam(value = "userId") String userId);

    /**
     * 创建海露订单
     * @param userId
     * @param editId
     * @param goodsName
     * @param remark
     * @param recipient
     * @param courierCompany
     * @param courierNumber
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     * @param orderType
     * @param money
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/hlOrder/buildOrder")
    ApiResponse<HlOrder> buildOrder(@RequestParam("userId") String userId, @RequestParam("editId")Long editId,
                              @RequestParam("goodsName")String goodsName,@RequestParam("money") BigDecimal money,
                              @RequestParam("remark")String remark, @RequestParam("recipient")String recipient,
                              @RequestParam("courierCompany")String courierCompany, @RequestParam("courierNumber") String courierNumber,
                              @RequestParam("provinceId")String provinceId, @RequestParam("cityId")String cityId,
                              @RequestParam("areaId")String areaId, @RequestParam("address")String address,
                              @RequestParam("orderType")Integer orderType,@RequestParam("invitationMember")String invitationMember,
                              @RequestParam("payType")Integer payType);


    /**
     * 根据订单号获取订单
     * @param orderNo
     * @return
     */
    @GetMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/hlOrder/findByOrderNo/{orderNo}")
    ApiResponse<HlOrder> findByOrderNo(@PathVariable("orderNo") String orderNo);

    /**
     * 保存/修改订单
     * @param hlOrder
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/hlOrder/saveHlOrder")
    ApiResponse<HlOrder> saveHlOrder(@RequestBody HlOrder hlOrder);

    /**
     * 保存邀请信息
     * @param invitedetailModel
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/invite/add")
    ApiResponse addInvitedetail(@RequestBody InvitedetailModel invitedetailModel);

    /**
     * 添加服务区域
     * @param userId
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     * @param address
     * @param wantBuyType   购买的类型（1-区代、2-城市合伙人、3-销售）
     * @return
     */
    @PostMapping(Constant.API_VERSION + Constant.API_NAME_MALL+"/memberDetail/addAddress")
    ApiResponse<Long> addAddress(
            @RequestParam("userId") String userId,
            @RequestParam("provinceId") String provinceId,
            @RequestParam("cityId") String cityId,
            @RequestParam("areaId") String areaId,
            @RequestParam("address") String address,
            @RequestParam("wantBuyType") Integer wantBuyType,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone);

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
}
