package com.hailu.cloud.api.xinan.module.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.xinan.module.dao.XaRescueMapper;
import com.hailu.cloud.api.xinan.module.entity.XaRescue;
import com.hailu.cloud.api.xinan.module.model.XaRescueVo;
import com.hailu.cloud.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表Service
 * @Date: 18:14 2019/11/12 0012
 */
@Service
public class XinAnRescueService {

    @Resource
    private XaRescueMapper xaRescueMapper;

    @Autowired
    private XinAnRescueInfoService xinAnRescueInfoService;

    /**
     * 添加救助
     * @param xaRescue
     * @return
     */
    public int insertSelective(XaRescue xaRescue){
        if (xaRescue == null){
            return 0;
        }
        return xaRescueMapper.insertSelective(xaRescue);
    }

    /**
     * 删除救助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(String numberId){
        return xaRescueMapper.deleteByPrimaryKey(numberId);
    }

    /**
     * 根据编号查询救助信息
     * @param numberId
     * @return
     */
    public XaRescue findRescueById(String numberId){
        if (StringUtils.isBlank(numberId)){
            return null;
        }
        return xaRescueMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 查询救助列表
     * @return
     */
    public Object findXaRescueList(Integer page, Integer size) throws BusinessException {
        PageHelper.startPage(page, size);
        List<XaRescueVo> xaRescueVos = xaRescueMapper.findXaRescueVo();
        PageInfo pageInfo = new PageInfo(xaRescueVos);
        if (xaRescueVos == null ){
            throw new BusinessException("未获取任何信息");
        }
        JSONArray arrayList = new JSONArray();
        for (XaRescueVo xaRescueVo : xaRescueVos){
            JSONObject json = new JSONObject();
            //救助编号
            json.put("numberId", xaRescueVo.getNumberId());
            //救助标题
            json.put("title", xaRescueVo.getTitle());
            //救助类型
            json.put("rescueType", xaRescueVo.getRescueType());
            //帮助次数
            json.put("helpTimes", xaRescueVo.getHelpTimes());
            //用户编号
            json.put("memberId", xaRescueVo.getMemberId());
            //封面
            json.put("picture", xaRescueVo.getPicture());
            arrayList.add(json);
        }
        JSONObject jsonObject = xinAnRescueInfoService.PageAndRescueJson(pageInfo, arrayList);
        return jsonObject;
    }
    /**
     * 查询救助列表
     * @return
     */
    public Object findXaRescueListAll(Integer page, Integer size){
        PageHelper.startPage(page, size);

        List<XaRescue> xaRescue = xaRescueMapper.findXaRescueList();
        PageInfo pageInfo = new PageInfo(xaRescue);
        JSONArray jsonArray = xinAnRescueInfoService.rescueJson(xaRescue);
        JSONObject jsonObject = xinAnRescueInfoService.PageAndRescueJson(pageInfo, jsonArray);
        return jsonObject;
    }

    /**
     * 修改救助审核
     * @param numberId
     * @return
     */
    public void updateByPrimaryKeySelective(String numberId, String examine){
        XaRescue xaRescue = new XaRescue();
        xaRescue.setNumberId(numberId);
        xaRescue.setExamine(examine);
        xaRescueMapper.updateByPrimaryKeySelective(xaRescue);
    }

}
