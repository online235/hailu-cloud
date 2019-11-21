package com.hailu.cloud.api.mall.module.ledger.service.impl;

import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.mall.constant.DateFormat;
import com.hailu.cloud.api.mall.module.ledger.dao.IncomeTransferOutMapper;
import com.hailu.cloud.api.mall.module.ledger.po.IncomeTransferOut;
import com.hailu.cloud.api.mall.module.ledger.service.IAuditService;
import com.hailu.cloud.api.mall.module.ledger.vo.IncomeTransferOutVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ValidationException;
import java.text.ParseException;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @Author xuzhijie
 * @Date 2019/10/28 9:09
 */
@Service
public class AuditServiceImpl implements IAuditService {

    @Resource
    private IncomeTransferOutMapper transferOutMapper;

    @Override
    public List<IncomeTransferOutVo> list(
            IncomeTransferOut transferOut,
            String beginTimeStr,
            String endTimeStr,
            int pageNo,
            int pageSize) {

        // 日期格式校验
        PageHelper.startPage(pageNo, pageSize);
        return dateFormatValidate(
                beginTimeStr,
                endTimeStr,
                (beginDate, endDate) -> transferOutMapper.list(transferOut, beginDate, endDate));
    }

    @Override
    public void audit(String memberId, String id, Integer state) {
        transferOutMapper.audit(memberId, id, state);
    }

    /**
     * 日期格式校验
     *
     * @param beginTime
     * @param endTime
     */
    private List<IncomeTransferOutVo> dateFormatValidate(String beginTime, String endTime, BiFunction<String, String, List<IncomeTransferOutVo>> consumer) {
        // 校验开始日期
        String beginDate = null;
        try {
            if (StringUtils.isNotBlank(beginTime)) {
                DateUtils.parseDate(beginTime, DateFormat.YYYY_MM_DD_HH_MM_SS);
                beginDate = beginTime;
            }
        } catch (ParseException e) {
            throw new ValidationException("开始日期格式不正确：" + DateFormat.YYYY_MM_DD_HH_MM_SS);
        }
        // 校验结束日期
        String endDate = null;
        try {
            if (StringUtils.isNotBlank(endTime)) {
                DateUtils.parseDate(endTime, DateFormat.YYYY_MM_DD_HH_MM_SS);
                endDate = endTime;
            }
        } catch (ParseException e) {
            throw new ValidationException("结束日期格式不正确：" + DateFormat.YYYY_MM_DD_HH_MM_SS);
        }
        return consumer.apply(beginDate, endDate);
    }
}
