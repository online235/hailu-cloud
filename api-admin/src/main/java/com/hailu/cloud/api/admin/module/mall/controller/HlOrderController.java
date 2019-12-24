package com.hailu.cloud.api.admin.module.mall.controller;

import com.hailu.cloud.api.admin.module.mall.model.HlOrderModel;
import com.hailu.cloud.api.admin.module.mall.service.IHlOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author junpei.deng
 */
@RestController
@Slf4j
@RequestMapping("hlOrder")
@Validated
@Api(tags = "海露订单")
public class HlOrderController {

    @Resource
    private IHlOrderService hlOrderService;

    /**
     * 海露订单集合
     * @param page
     * @param size
     * @param orderType
     * @return
     */
    @ApiOperation(notes = "", value = "获取海露订单列表信息")
    @GetMapping("/findList")
    public Object findList(@RequestParam(value = "page", defaultValue = "1", required = false)Integer page,
                           @Max(value = 200, message = "每页最多显示200条数据")
                           @RequestParam(value = "size", defaultValue = "20", required = false)Integer size,
                           @NotNull(message = "订单类型不能为空") @RequestParam("orderType") Integer orderType,
                            String userName,
                           String goodsName,
                           Integer orderStatus){
        log.info("查询海露订单，订单类型：{}",orderType);
        return hlOrderService.findList(page,size,orderType,userName,goodsName,orderStatus);
    }

    @ApiOperation(notes = "", value = "编辑")
    @PostMapping("/edit")
    public void edit(
                     @NotNull(message = "id不能为空") @RequestParam("id") Long id,
                     @NotBlank(message = "快递公司不能为空") @RequestParam("courierCompany") String courierCompany,
                     @NotBlank(message = "快递单号不能为空") @RequestParam("courierNumber") String courierNumber,
                     @NotNull(message = "物流状态不能为空") @RequestParam("logisticsStatus") Integer logisticsStatus,
                     @NotNull(message = "省ID不能为空") @RequestParam("provinceId")String provinceId,
                     @NotNull(message = "市ID不能为空") @RequestParam("cityId") String cityId,
                     @NotNull(message = "市ID不能为空") @RequestParam("areaId")String areaId,
                     @NotBlank(message = "详细地址不能为空") @RequestParam("address")String address){
        hlOrderService.editLogistics(id, courierCompany, courierNumber, logisticsStatus, provinceId, cityId, areaId, address);
    }

}
