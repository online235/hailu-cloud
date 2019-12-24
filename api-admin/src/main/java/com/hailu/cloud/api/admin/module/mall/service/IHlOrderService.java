package com.hailu.cloud.api.admin.module.mall.service;

public interface IHlOrderService {

    /**
     * 分页查询数据
     * @param page
     * @param size
     * @return
     */
    Object findList(Integer page,Integer size,Integer orderType,String userName,String goodsName,Integer orderStatus);

    /**
     * 修改物流信息
     * @param courierCompany
     * @param courierNumber
     * @param logisticsStatus
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     */
    void editLogistics(Long id,String courierCompany,String courierNumber,Integer logisticsStatus,String provinceId,String cityId,String areaId,String address);
    
}
