package com.hailu.cloud.api.xinan.module.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.xinan.module.dao.InsuredMapper;
import com.hailu.cloud.api.xinan.module.dao.OrderMapper;
import com.hailu.cloud.api.xinan.module.entity.Insured;
import com.hailu.cloud.api.xinan.module.entity.Order;
import com.hailu.cloud.api.xinan.module.model.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.db.service.xinAn
 * @Author: junpei.deng
 * @CreateTime: 2019-10-18 09:31
 * @Description: 心安订单信息
 */
@Slf4j
@Service
public class OrderService {

    @Resource
    private OrderMapper xaOrderMapper;

    @Resource
    private InsuredMapper xaInsuredMapper;


    /**
     * 保存或编辑
     * @param order
     * @return
     */
    public Order saveEntity(Order order){
        Date dateNow = new Date();
        //如果ID为空，则新增
        if(StringUtils.isBlank(order.getId())){
            order.setId(IdUtil.simpleUUID());
            order.setCreateDate(dateNow);
            order.setCreateBy(order.getMemberId());
            order.setStatus(1);
            xaOrderMapper.insert(order);
            return order;
        }
        order.setUpdateDate(dateNow);
        order.setUpdateBy(order.getMemberId());
        xaOrderMapper.updateByPrimaryKeySelective(order);
        return order;
    }

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public Order findById(String id){
        if(StringUtils.isBlank(id)){
            return null;
        }
        return xaOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 创建订单
     * @param name
     * @param value
     * @param memberId
     * @param itemType
     * @param from
     * @param itemName
     * @param invitationMember
     * @param money
     * @return
     */
    public Order buildOrder(String name, String value, String memberId, String itemType, String from, String itemName, String invitationMember, BigDecimal money){
        return buildOrder(name, value, memberId, itemType, from, itemName, invitationMember, money,null,null,null,null,null);
    }

    /**
     * 创建订单
     * @param name
     * @param value
     * @param memberId
     * @return
     */
    public Order buildOrder(String name, String value, String memberId, String itemType, String from, String itemName, String invitationMember, BigDecimal money, Long provinceId, Long cityId, Long areaId, String address, Long chooseCityId){
        //添加订单
        Order order = new Order();
        //商品名称
        order.setItemName(itemName);
        //订单号
        order.setOrderNo(IdUtil.simpleUUID());
        //参保人名称
        order.setInsuredName(name);
        //参保人数据
        order.setInsuredValue(value);
        //金额
        order.setMoney(money);
        //订单状态 订单状态（1-未付款2-已付款3-已取消）
        order.setOrderStatus(1);
        //会员ID
        order.setMemberId(memberId);
        //订单状态 对应MemberShopEnum枚举
        order.setItemType(itemType);
        //来源
        order.setFroms(from);
        //邀请人
        order.setInvitationMember(invitationMember);
        //省市区+详细地址
        order.setProvinceId(provinceId);
        order.setCityId(cityId);
        order.setAreaId(areaId);
        order.setAddress(address);
        //服务商选择的城市
        order.setChooseCityId(chooseCityId);

        return saveEntity(order);
    }


    /**
     * 查询添加人的信息
     * @param memberId
     * @return
     */
    public JSONObject findListByMemberIdAndMemberStatus(String memberId){

        //返回参数
        JSONObject result = new JSONObject();
        JSONArray ja = new JSONArray();

        //金额
        BigDecimal money = new BigDecimal("0");
        List<OrderVo> orderVoList = xaOrderMapper.findByMemberIdAndOrderStatus(memberId,1,null);
        JSONObject jsonObject = null;
        for (OrderVo orderVo : orderVoList){
            jsonObject = new JSONObject();
            //ID
            jsonObject.put("orderId",orderVo.getId());
            //参保人ID
            jsonObject.put("insuredId",orderVo.getInsuredId());
            //名称
            jsonObject.put("name",orderVo.getInsuredName());
            //关系
            jsonObject.put("memberRelation",orderVo.getMemberRelation());
            //证件号码
            String value = orderVo.getInsuredValue();
            jsonObject.put("value",value.substring(0,3)+"********"+value.substring(value.length()-3,value.length()));
            //是否为首年 1-首年2-续费3-免费
//            BigDecimal orderMoney = NumberUtil.bigDecimalScale(i.getMoney());
            jsonObject.put("isType",orderVo.getMoney().compareTo(new BigDecimal("0.01")) == 0 ? 1 : orderVo.getMoney().compareTo(new BigDecimal("26")) == 0 ? 2 : 3);
            money = money.add(orderVo.getMoney());
            //金额
            jsonObject.put("money",orderVo.getMoney());
            ja.add(jsonObject);
        }
        result.put("list",ja);
        result.put("money",money);

        return result;
    }




    public void deleteById(String id){
           //根据ID获取信息
           Order order = findById(id);
           //将状态改成删除
           order.setStatus(2);
           saveEntity(order);
           //去参保库里面查询参保人的信息
           Insured insured = xaInsuredMapper.findByNameAndValueAndType(order.getInsuredName(),order.getInsuredValue(),null);

           //判断状态 如果为未付款，则删除
           if(insured.getMemberStatus() == 1){
               insured.setStatus(2);
           }
           xaInsuredMapper.updateByPrimaryKey(insured);
    }
}
