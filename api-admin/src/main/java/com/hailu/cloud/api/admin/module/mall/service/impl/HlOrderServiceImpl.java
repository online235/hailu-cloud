package com.hailu.cloud.api.admin.module.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.mall.dao.HlOrderMapper;
import com.hailu.cloud.api.admin.module.mall.entity.ServiceProviders;
import com.hailu.cloud.api.admin.module.mall.model.HlOrderModel;
import com.hailu.cloud.api.admin.module.mall.service.IHlOrderService;
import com.hailu.cloud.api.admin.module.mall.service.IServiceProvidersService;
import com.hailu.cloud.common.model.mall.hlorder.HlOrder;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 海路订单
 * @author junpei.deng
 */
@Service
public class HlOrderServiceImpl implements IHlOrderService {

    @Resource
    private HlOrderMapper hlOrderMapper;

    @Resource
    private IServiceProvidersService serviceProvidersService;

    @Override
    public Object findList(Integer page, Integer size,Integer orderType,String userName,String goodsName,Integer orderStatus) {
        Page pageData = PageHelper.startPage(page,size);
        List<HlOrderModel> list = hlOrderMapper.findList(orderType,userName,goodsName,orderStatus);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(),list);
    }


    @Override
    public void editLogistics(Long id,String courierCompany, String courierNumber, Integer logisticsStatus, String provinceId, String cityId, String areaId, String address) {
        HlOrder order = hlOrderMapper.selectByPrimaryKey(id);
        //物流名称
        order.setCourierCompany(courierCompany);
        //物流单号
        order.setCourierNumber(courierNumber);
        //物流状态   物流状态（1-已发货、2-待接收、3-已完成）
        if(logisticsStatus == 2){
            //如果物流状态为待接收，则订单状态则成待收货
            order.setOrderStatus(3);
        }else if(logisticsStatus == 3){
            //如果物流状态为待已完成，则订单状态则成已完成
            order.setOrderStatus(4);
        }
        order.setLogisticsStatus(logisticsStatus);

        //TODO 地址  如果地址有变动，则相对于服务商的地址也跟着改动，暂时性的
        if(!StringUtils.equals(order.getProvinceId(),provinceId) ||
                !StringUtils.equals(order.getCityId(),cityId) ||
                !StringUtils.equals(order.getAreaId(),areaId) ||
                !StringUtils.equals(order.getAddress(),address)){
            order.setProvinceId(provinceId);
            order.setCityId(cityId);
            order.setAreaId(areaId);
            order.setAddress(address);

            ServiceProviders serviceProviders = serviceProvidersService.findById(order.getEditId());
            if(serviceProviders != null){
                serviceProviders.setProvinceId(provinceId);
                serviceProviders.setCityId(cityId);
                serviceProviders.setAreaId(areaId);
                serviceProviders.setAddress(address);

                serviceProvidersService.edit(serviceProviders);
            }
        }
        hlOrderMapper.updateByPrimaryKeySelective(order);

    }
}
