package com.hailu.cloud.api.mall.module.hl.controller;

import com.hailu.cloud.api.mall.module.hl.service.IHlOrderService;
import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 海路订单
 * @author junpei.deng
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/hlOrder")
public class HlOrderController {

    @Resource
    private IHlOrderService hlOrderService;


    /**
     * 创建订单
     * @param userId
     * @param editId
     * @param goodsName
     * @param money
     * @param remark
     * @param recipient
     * @param courierCompany
     * @param courierNumber
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     * @param orderType
     * @Param invitationMember
     * @return
     */
    @PostMapping("/buildOrder")
    public HlOrder buildOrder(@RequestParam("userId") String userId, @RequestParam("editId")Long editId,
                              @RequestParam("goodsName")String goodsName,@RequestParam("money") BigDecimal money,
                              @RequestParam("remark")String remark, @RequestParam("recipient")String recipient,
                              @RequestParam(value = "courierCompany",required = false)String courierCompany, @RequestParam(value = "courierNumber",required = false) String courierNumber,
                              @RequestParam("provinceId")String provinceId, @RequestParam("cityId")String cityId,
                              @RequestParam("areaId")String areaId, @RequestParam("address")String address,
                              @RequestParam("orderType")Integer orderType, @RequestParam(value = "invitationMember",required = false)String invitationMember,
                              @RequestParam("payType")Integer payType){
       return hlOrderService.buildOrder(userId, editId, goodsName,
               remark, recipient, courierCompany, courierNumber, provinceId,
               cityId, areaId, address, orderType,money,invitationMember,payType);
   }

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
   @GetMapping("findByOrderNo/{orderNo}")
   public HlOrder findByOrderNo(@PathVariable("orderNo") String orderNo){
        return hlOrderService.findByOrderNo(orderNo);
   }

    /**
     * 保存
     * @param hlOrder
     * @return
     */
    @PostMapping("/saveHlOrder")
    public HlOrder saveHlOrder(@RequestBody HlOrder hlOrder){
       return hlOrderService.saveEntity(hlOrder);
    }


}
