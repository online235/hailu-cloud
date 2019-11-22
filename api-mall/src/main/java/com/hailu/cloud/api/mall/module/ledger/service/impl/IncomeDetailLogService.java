package com.hailu.cloud.api.mall.module.ledger.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.constant.DateFormat;
import com.hailu.cloud.api.mall.module.ledger.dao.IncomeDetailLogMapper;
import com.hailu.cloud.api.mall.module.ledger.vo.IncomeDetailLog;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: shopping-mall
 * @BelongsPackage: com.hailu.api.shoppingmall.module.hl.service
 * @Author: junpei.deng
 * @CreateTime: 2019-10-24 15:58
 * @Description: 商户资金明细日志
 */
@Service
@Slf4j
public class IncomeDetailLogService {

    @Resource
    private IncomeDetailLogMapper incomeDetailLogMapper;

    @Resource
    BasicFeignClient basicFeignClient;
    /**
     * 根据ID查找信息
     * @param id
     * @return
     */
    public IncomeDetailLog findById(String id){
        if(StringUtils.isBlank(id)){
            return null;
        }
        return incomeDetailLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增或保存
     * @param incomeDetailLog
     * @return
     */
    public IncomeDetailLog saveEntity(IncomeDetailLog incomeDetailLog){

        long dataNow = System.currentTimeMillis();
        incomeDetailLog.setUpdateDate(dataNow);
        if(incomeDetailLog.getId() == null){
            incomeDetailLog.setId(basicFeignClient.uuid().getData());
            incomeDetailLog.setCreateDate(dataNow);
            incomeDetailLog.setStatus(1);
            incomeDetailLogMapper.insert(incomeDetailLog);
            return incomeDetailLog;
        }
        incomeDetailLogMapper.updateByPrimaryKeySelective(incomeDetailLog);
        return incomeDetailLog;
    }

    /**
     * 增加资金明细
     * @param editId
     * @param money
     * @param beforeMoney
     * @param afterMoney
     * @param from
     * @param accountStatus
     * @param userId
     */
    public void addAccountDetailLog(String editId, BigDecimal money,BigDecimal beforeMoney,BigDecimal afterMoney,String from,int accountStatus,String userId){
        IncomeDetailLog incomeDetailLog = new IncomeDetailLog();
        //操作ID
        incomeDetailLog.setEditId(editId);
        //会员ID
        incomeDetailLog.setUserId(userId);
        //金额
        incomeDetailLog.setMoney(money);
        //使用前金额
        incomeDetailLog.setBeforeMoney(beforeMoney);
        //使用后金额
        incomeDetailLog.setAfterMoney(afterMoney);
        //资金流向
        incomeDetailLog.setFroms(from);
        //资金状态
        incomeDetailLog.setAccountStatus(accountStatus);
        saveEntity(incomeDetailLog);
    }


    public JSONArray findListByMemberIdPage(String memberId,Integer page,Integer size){
        PageHelper.startPage(page,size);
        List<IncomeDetailLog> incomeDetailLogList = incomeDetailLogMapper.findListByMemberId(memberId);

        //封装返回数据
        JSONArray resultJa = new JSONArray();
        for(IncomeDetailLog a : incomeDetailLogList){
            JSONObject js = new JSONObject();
            //金额
            js.put("money",a.getMoney());
            //来源
            js.put("froms",a.getFroms());
            //时间
            js.put("dateTime", DateFormatUtils.format(a.getCreateDate(), DateFormat.YYYY_MM_DD_HH_MM_SS));
            //使用后的余额
            js.put("afterMoney",a.getAfterMoney());
            //状态
            js.put("status",a.getAccountStatus());

            resultJa.add(js);
        }
        return resultJa;
    }
}
