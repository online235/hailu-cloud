package com.hailu.cloud.api.admin.module.partnermanagement.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.partnermanagement.dao.HlIncomeTransferOutMapper;
import com.hailu.cloud.api.admin.module.partnermanagement.model.HlIncomeTransferOutListModel;
import com.hailu.cloud.api.admin.module.partnermanagement.model.HlIncomeTransferOutModel;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangmugui
 */
@Service
public class HlIncomeTransferOutService {


    @Autowired
    private HlIncomeTransferOutMapper hlIncomeTransferOutMapper;

    @Resource
    private RedisStandAloneClient redisStandAloneClient;


    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    public List<HlIncomeTransferOutListModel> findListByParameter(Map<String, Object> map) {
        return hlIncomeTransferOutMapper.findListByParameter(map);
    }


    /**
     * id查询数据
     *
     * @param id
     * @return
     */
    public HlIncomeTransferOutModel findById(Long id) {
        return hlIncomeTransferOutMapper.findById(id);

    }


    /**
     * 分页操作
     *
     * @param pageNum
     * @param size
     * @param map
     * @return
     */
    public PageInfoModel<List<HlIncomeTransferOutListModel>> findListByParameterNewPage(Integer pageNum, Integer size, Map<String, Object> map) {
        Page pageData = PageHelper.startPage(pageNum, size);
        List<HlIncomeTransferOutListModel> result = hlIncomeTransferOutMapper.findListByParameter(map);
        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        //将搜索数据放到redis
        String resultString = JSON.toJSONString(result);
        redisStandAloneClient.stringSet(adminLoginInfoModel.getId()+"hlIncomeTransferOutList",resultString);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


    /**
     * 审核
     *
     * @param id       提现记录ID
     * @param state    审核状态
     * @param remark   原因
     */
    public void update( Long id, Integer state,String remark) {

        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        hlIncomeTransferOutMapper.update(id, adminLoginInfoModel.getId().toString(), state, remark);

    }


    /**
     * 获取redis存放的审核列表数据，同时导出数据
     */
    public void excelWriter(HttpServletResponse response) throws IOException {

        AdminLoginInfoModel adminLoginInfoModel = RequestUtils.getAdminLoginInfo();
        String resultString =  redisStandAloneClient.stringGet(adminLoginInfoModel.getId()+"hlIncomeTransferOutList");
        List<HlIncomeTransferOutListModel> result = JSON.parseArray(resultString,HlIncomeTransferOutListModel.class);
        response.setHeader("content-Type","application/vnd.ms-excel");
        response.setHeader("Content-disposition","attachment;filename="+ new String("withdrawalsList.xls".getBytes("UTF-8"),"ISO-8859-1"));
        List<HlIncomeTransferOutListModel> rows =  CollUtil.newArrayList(result);
        //通过工具类创建writer
        BigExcelWriter writer = (BigExcelWriter) ExcelUtil.getBigWriter();
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(15, "会员提现列表");
        //自定义标题别名
        writer.addHeaderAlias("id", "提现申请ID");
        writer.addHeaderAlias("memberId", "会员表的user_id");
        writer.addHeaderAlias("memberName", "会员名称");
        writer.addHeaderAlias("price", "提现金额");
        writer.addHeaderAlias("openAccountBank", "开户行名称");
        writer.addHeaderAlias("bankName", "银行名称");
        writer.addHeaderAlias("bankCard", "银行卡卡号");
        writer.addHeaderAlias("cardholder", "持卡人名称");
        writer.addHeaderAlias("state", "审核状态");
        writer.addHeaderAlias("remark", "拒绝原因");
        writer.addHeaderAlias("createTime", "提现时间");
        writer.addHeaderAlias("updateBy", "审核人");
        writer.addHeaderAlias("nickName", "审核人姓名");
        writer.addHeaderAlias("examineTime", "审核时间");
        writer.addHeaderAlias("updateTime", "更新时间");
        writer.addHeaderAlias("createTime", "提现时间");
        writer.addHeaderAlias("memberMobile", "用户手机号码");
        writer.write(rows, true);
        writer.flush(response.getOutputStream());
        writer.close();

    }




}
