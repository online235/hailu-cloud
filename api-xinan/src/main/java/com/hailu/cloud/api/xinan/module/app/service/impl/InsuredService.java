package com.hailu.cloud.api.xinan.module.app.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hailu.cloud.api.xinan.module.app.dao.InsuredMapper;
import com.hailu.cloud.api.xinan.module.app.dao.OrderMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Insured;
import com.hailu.cloud.api.xinan.module.app.entity.Order;
import com.hailu.cloud.api.xinan.module.app.enums.MemberShopEnum;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: litemall
 * @BelongsPackage: org.linlinjava.litemall.db.service.xinAn
 * @Author: junpei.deng
 * @CreateTime: 2019-10-18 09:19
 * @Description: 参保人
 */
@Slf4j
@Service
public class InsuredService {

    @Resource
    private InsuredMapper xaInsuredMapper;

    @Autowired
    private OrderService orderService;

    @Resource
    private OrderMapper xaOrderMapper;

    /**
     * 首次充值
     */
    private final double FIRSTMONEY = 0.01;

    /**
     * 续费
     */
    private final double NEXTMONEY = 26;

    public Object addOrEditInsured(String name, String idCard, String memberId, Integer memberRelation, Integer type, Integer isYearEnjoy, String photoUrl,String deviceType) throws BusinessException {

        //返回信息
        JSONObject resultParam = new JSONObject();
        try {
            //是否续费
            boolean flag = false;

            BigDecimal money = null;

            //校验是否存在数据
            Insured insured = xaInsuredMapper.findByNameAndValueAndType(name, idCard, type);
            if (insured == null) {
                //新建一条信息
                insured = new Insured();
                insured.setMemberStatus(1);
                //是否享受年费机制
                if (isYearEnjoy == 1) {
                    //校验图片是否上传
                    if (StringUtils.isBlank(photoUrl)) {
                        return null;
                    }
                    insured.setPhotoUrl(photoUrl);
                    money = BigDecimal.valueOf(0.0);
                }
                insured.setIsYearEnjoy(isYearEnjoy);
                //名称
                insured.setName(name);
                //证件类型
                insured.setType(type);
                //证件数据
                insured.setValue(idCard);
                //关系
                insured.setMemberRelation(memberRelation);
                //会员ID
                insured.setMemberId(memberId);

                //保存数据
                insured = saveEntity(insured);

            } else if (insured.getMemberStatus() == 1) {
                //未支付状态 直接返回，无法继续添加
                throw new BusinessException("该用户处于未支付状态，无法继续添加！");
            } else {
                flag = true;
            }
            if(deviceType != null && StringUtils.equals("ios",deviceType)){
                money = BigDecimal.valueOf(0.0);
            }
            //创建订单
            Order order = orderService.buildOrder(insured.getName(), insured.getValue(), memberId, MemberShopEnum.INSURD.getDesc(), "XA", "购买心安", null, money == null ? new BigDecimal(flag ? NEXTMONEY : FIRSTMONEY) : money);


            //订单ID 用于删除
            resultParam.put("orderId", order.getId());
            //支付使用的ID
            resultParam.put("insuredId", insured.getId());
            //证件参数
            String value = insured.getValue();
            resultParam.put("value", value.substring(0, 3) + "********" + value.substring(value.length() - 3));
            //是否享受年费机制(1-是、2-否)
            resultParam.put("isYearEnjoy ", insured.getIsYearEnjoy());
            //参保人关系(0-本人、1-配偶、2-父母、3-子女、4-朋友)
            resultParam.put("memberRelation", insured.getMemberRelation());
            //金额
            resultParam.put("money",order.getMoney().setScale(2, RoundingMode.HALF_UP));
            //名称
            resultParam.put("name", insured.getName());
            //参保人证件类型(1-身份证、2-护照、3-军官证、4-士兵证、5-港澳台居民往来通行证、6-临时身份证、7-户口簿、8-警官证、9-其他、10-外国人永久居留证、11-边民出入通行证)
            resultParam.put("type", insured.getType());
            //是否为首年 1-首年2-续费3-免费
            BigDecimal orderMoney = order.getMoney().setScale(2, RoundingMode.HALF_UP);
            resultParam.put("isType", orderMoney.compareTo(new BigDecimal("0.01")) == 0 ? 1 : orderMoney.compareTo(new BigDecimal("26")) == 0 ? 2 : 3);
            return resultParam;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }


    /**
     * 新增或编辑
     *
     * @param insured
     * @return
     */
    private Insured saveEntity(Insured insured) {
        Date dateNow = new Date();
        //如果ID为空，则新增
        if (StringUtils.isBlank(insured.getId())) {
            insured.setId(IdUtil.simpleUUID());
            insured.setCreateDate(dateNow);
            insured.setCreateBy(insured.getMemberId());
            insured.setStatus(1);
            xaInsuredMapper.insert(insured);
            return insured;
        }
        insured.setUpdateDate(dateNow);
        insured.setUpdateBy(insured.getMemberId());
        xaInsuredMapper.updateByPrimaryKeySelective(insured);
        return insured;
    }

    /**
     * 根据ID查找信息
     *
     * @param id
     * @return
     */
    public Insured findById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return xaInsuredMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据会员ID和状态查询
     * @param memberId
     * @param memberStatus
     * @return
     */
//    public JSONObject findListByMemberIdAndMemberStatus(String memberId, Integer memberStatus){
//
//        //返回参数
//        JSONObject result = new JSONObject();
//        JSONArray ja = new JSONArray();
//
//        //金额
//        BigDecimal money = new BigDecimal("0");
//        List<Insured> insuredList = xaInsuredMapper.findListByMemberIdAndMemberStatus(memberId,memberStatus);
//        JSONObject jsonObject = null;
//        for (Insured i : insuredList){
//            jsonObject = new JSONObject();
//            //名称
//            jsonObject.put("name",i.getName());
//            //关系
//            jsonObject.put("memberRelation",i.getMemberRelation());
//            //证件号码
//            String value = i.getValue();
//            jsonObject.put("value",value.substring(0,3)+"********"+value.substring(value.length()-3,value.length()));
//            //是否为首年 1-首年2-续费3-免费
//
//        }
//        return null;
//    }


    /**
     * 根据ID查询信息
     *
     * @param id
     * @return
     */
    public JSONObject findDetailById(String id) {

        JSONObject result = new JSONObject();
        //根据ID获取信息
        Insured insured = findById(id);

        //名称
        result.put("name", insured.getName());
        //参保人数据
        result.put("value", insured.getValue());
        //参保人证件类型
        result.put("type", insured.getType());
        //是否i享受年费机制
        result.put("isYearEnjoy", insured.getIsYearEnjoy());
        //图片地址
        result.put("photoUrl", insured.getRemark());
        //备注
        result.put("remark", insured.getRemark());
        //会员有效日
        result.put("memberValidity", insured.getMemberValidity());
        //参保人关系
        result.put("memberRelation", insured.getMemberRelation());

        return result;
    }


    /**
     * 修改参保人信息
     *
     * @param
     * @return
     */
    public Object editInsurd(String name, String idCard, String id, Integer memberRelation,
                             Integer type, Integer isYearEnjoy, String photoUrl) throws BusinessException {

        Insured insured = findById(id);
        if (insured.getMemberStatus() != 4) {
            throw new BusinessException("状态不为驳回，不可修改！");
        }

        //姓名
        insured.setName(name);
        //值
        insured.setValue(idCard);
        //参保人证件类型
        insured.setType(type);
        //是否享受年费机制
        insured.setIsYearEnjoy(isYearEnjoy);
        //图片地址
        insured.setPhotoUrl(photoUrl);
        //参保人关系
        insured.setMemberRelation(memberRelation);
        //将状态改成待审核
        insured.setMemberStatus(2);

        return saveEntity(insured);
    }

    /**
     * 修改支付成功后的状态
     *
     * @param name
     * @param value
     */
    public void editInsurdPayStatus(String name, String value) {

        Date dataNow = new Date();
        try {
            //获取参保人信息
            Insured insured = xaInsuredMapper.findByNameAndValueAndType(name, value, null);
            if (insured == null) {
                log.warn("支付更改状态   查询不到名称为：{}，证件信息为：{}的信息", name, value);
                return;
            }
            //判断状态是否为会员
            if (insured.getMemberStatus() == 1 || dataNow.after(insured.getMemberValidity())) {
                //修改会员状态为待审核
                insured.setMemberStatus(3);
                //修改过期时间
                insured.setMemberValidity(DateUtil.offset(new Date(), DateField.YEAR, 1));
            } else {
                //会员，则在当前的会员时间上再加一年
                insured.setMemberValidity(DateUtil.offset(insured.getMemberValidity(), DateField.YEAR, 1));
            }

            //处理初始化时间
            if (insured.getFirstJoinInsured() == null) {
                insured.setFirstJoinInsured(dataNow);
            }
            saveEntity(insured);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 根据会员ID获取名下参保人信息
     *
     * @param memberId
     * @return
     */
    public Object findListByMemberId(String memberId) {
        //返回参数
        JSONArray resultJa = new JSONArray();

        List<Insured> insuredList = xaInsuredMapper.findListByMemberId(memberId);
        for (Insured i : insuredList) {
            JSONObject jsonObject = new JSONObject();
            //名称
            jsonObject.put("name", i.getName());
            //证件类型
            jsonObject.put("type", i.getType());
            //证件值
            String value = i.getValue();
            jsonObject.put("value", value.substring(0, 3) + "********" + value.substring(value.length() - 3, value.length()));
            //续费日期
            Date memberValidity = i.getMemberValidity();
            jsonObject.put("memberValidity", memberValidity != null ? DateUtil.format(memberValidity, DatePattern.CHINESE_DATE_PATTERN) : "");
            //会员ID
            jsonObject.put("insuredMemberId", i.getInsuredMemberId() == null ? "" : i.getInsuredMemberId());
            //状态
            jsonObject.put("memberStatus", i.getMemberStatus());
            //参保人ID
            jsonObject.put("insuredId", i.getId());
            //校验状态是否支付
            if (i.getMemberStatus() == 1) {
                Order order = xaOrderMapper.findByNameAndValue(i.getName(), i.getValue());
                BigDecimal orderMoney = order.getMoney();
                jsonObject.put("isType", orderMoney.compareTo(new BigDecimal("0.01")) == 0 ? 1 : orderMoney.compareTo(new BigDecimal("26")) == 0 ? 2 : 3);
                //金额
                jsonObject.put("money", orderMoney);
                //关系
                jsonObject.put("memberRelation", i.getMemberRelation());
            } else if (i.getMemberStatus() == 3) {
                //如果为观察期则显示剩余多少天 90 天
                jsonObject.put("remainingDay", DateUtil.between(new Date(),DateUtil.offset(i.getFirstJoinInsured(), DateField.DAY_OF_MONTH, 90),DateUnit.DAY));
            }
            resultJa.add(jsonObject);
        }
        return resultJa;
    }
}
