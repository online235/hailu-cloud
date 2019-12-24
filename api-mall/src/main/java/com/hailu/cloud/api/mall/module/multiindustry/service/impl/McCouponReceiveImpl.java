package com.hailu.cloud.api.mall.module.multiindustry.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.module.multiindustry.dao.McCouponReceiveMapper;
import com.hailu.cloud.api.mall.module.multiindustry.entity.McCouponReceive;
import com.hailu.cloud.api.mall.module.multiindustry.model.CouponAndReceiveModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponOtherJsonModel;
import com.hailu.cloud.api.mall.module.multiindustry.model.McCouponReceiveModel;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponReceiveService;
import com.hailu.cloud.api.mall.module.multiindustry.service.McCouponService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import com.hailu.cloud.common.utils.StoreUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 2019/12/23 0023
 * @program: cloud
 * @create: 2019-12-23 11:
 */
@Service
public class McCouponReceiveImpl implements McCouponReceiveService {

    @Resource
    private McCouponReceiveMapper mcCouponReceiveMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    @Autowired
    private McCouponService mcCouponService;


    @Override
    public McCouponReceive insertSelective(McCouponReceiveModel recordModel) throws BusinessException {
        int resultCoupon = mcCouponService.findMcCouponById(recordModel.getCouponId());
        if (resultCoupon > 0){
            throw new BusinessException("该卷不存在，或者已下架");
        }
        int resultReceive = findCountByMemberIdAndCouponId(recordModel.getMemberId(), recordModel.getCouponId());
        if (resultReceive > 4 ) {
            throw new BusinessException("当天领卷不能超过5张！");
        }
        McCouponReceive mcCouponReceive = new McCouponReceive();
        //类转换
        BeanUtils.copyProperties(recordModel,mcCouponReceive);
        mcCouponReceive.setId(basicFeignClient.uuid().getData());
        mcCouponReceive.setAwardCode(RandomUtil.randomNumbers(10));
        mcCouponReceive.setDateTime(new Date());
        mcCouponReceive.setUsageState(1);
        mcCouponReceiveMapper.insertSelective(mcCouponReceive);
        return mcCouponReceive;
    }

    @Override
    public int findCountByMemberIdAndCouponId(Long memberId, Long couponId) {
        return mcCouponReceiveMapper.findCountByMemberIdAndCouponId(memberId,couponId);
    }

    @Override
    public int updUsageStateById(Long id, Long usageState) {
        return mcCouponReceiveMapper.updUsageStateById(id, usageState);
    }

    @Override
    public PageInfoModel<List<CouponAndReceiveModel>> findMcCouponByMemberIdList(Integer usageState, Integer page, Integer size) {
        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        Page pageNum = PageHelper.startPage(page,size);
        List<CouponAndReceiveModel> modelList = mcCouponReceiveMapper.findMcCouponByMemberIdList(memberLoginInfoModel.getUserId(),usageState);
        List<CouponAndReceiveModel> couponAndReceiveModels = new ArrayList<>();

        for (CouponAndReceiveModel model : modelList){
            if (model.getValidPeriodType() == 1){
                //添加设定天数
                model.setEndTime(StoreUtil.datePlus(model.getDateTime(),model.getTimeAfterOrder()));
                model.setStartTime(model.getDateTime());
            }

            //判断时间是否过期
            if (model.getUsageState() != 3){
                int result = StoreUtil.dateCompare(model.getStartTime(), new Date(), 1);
                if (result != -1){
                    //改变状态
                    updUsageStateById(model.getReceiveId(),3L);
                    model.setUsageState(3L);
                }
            }
            couponAndReceiveModels.add(model);
        }

        return  new PageInfoModel<>(pageNum.getPages(), pageNum.getTotal(), couponAndReceiveModels);
    }

    @Override
    public CouponAndReceiveModel<McCouponOtherJsonModel> findMcCouponByReceiveId(Long receiveId) {
        MemberLoginInfoModel memberLoginInfoModel = RequestUtils.getMemberLoginInfo();
        CouponAndReceiveModel<McCouponOtherJsonModel> mcCouponByReceiveId = mcCouponReceiveMapper.findMcCouponByReceiveId(memberLoginInfoModel.getUserId(), receiveId);

        //转成json
        McCouponOtherJsonModel mcCouponOtherJsonModel = JSON.parseObject(mcCouponByReceiveId.getOtherJson(), McCouponOtherJsonModel.class);

        //赋值
        mcCouponByReceiveId.setMcCouponOtherJsonModel(mcCouponOtherJsonModel);
        mcCouponByReceiveId.setOtherJson(null);
        return mcCouponByReceiveId;
    }

}
