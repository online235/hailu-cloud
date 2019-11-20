package com.hailu.cloud.api.xinan.module.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.xinan.module.entity.XaRescue;
import com.hailu.cloud.api.xinan.module.entity.XaRescuePictures;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表ServiceInfo
 * @Date: 18:14 2019/11/12 0012
 */
@Service
@Slf4j
public class XinAnRescueInfoService {

    @Autowired
    private XinAnRescueService xinAnRescueService;

    @Autowired
    private XinAnRescuePicturesService xinAnRescuePicturesService;

    /**
     * 保存救助信息和图片路径
     * @param xaRescue
     * @param picture
     * @return
     */
    public void insRescueAndRictures(XaRescue xaRescue, String[] picture) throws BusinessException {
        Date date = new Date();
        String numberId = IdUtil.simpleUUID();
        xaRescue.setNumberId(numberId);
        xaRescue.setCreatedat(date);
        xaRescue.setUpdatedat(date);
        xaRescue.setExamine("1");
        int result = xinAnRescueService.insertSelective(xaRescue);
        if (result > 0){
            for (String pictureFor : picture){
                try {
                    XaRescuePictures xaRescuePictures = new XaRescuePictures();
                    xaRescuePictures.setMutualaId(numberId);
                    xaRescuePictures.setCreatedat(date);
                    xaRescuePictures.setNumberId(IdUtil.simpleUUID());
                    xaRescuePictures.setPicture(pictureFor);
                    xinAnRescuePicturesService.insertSelective(xaRescuePictures);
                }catch (Exception e){
                    xinAnRescueService.deleteByPrimaryKey(numberId);
                    log.error(e.getMessage(), e);
                }
            }
        }else{
            throw new BusinessException("救助信息保存失败");
        }
    }

    /**
     * 查看救助详情
     * @param numberId
     * @return
     */
    public Object findRescue(String numberId){
        JSONObject jsonObject = new JSONObject();
        XaRescue xaRescue = xinAnRescueService.findRescueById(numberId);

        String createDat = DateUtil.formatDateTime(xaRescue.getCreatedat());
        String updatedat = DateUtil.formatDateTime(xaRescue.getUpdatedat());
        //救助编号
        jsonObject.put("numberId", xaRescue.getNumberId());
        //发起用户编号
        jsonObject.put("memberId", xaRescue.getMemberId());
        //目标金额
        jsonObject.put("targetAmount", xaRescue.getTargetAmount());
        //救助标题
        jsonObject.put("title", xaRescue.getTitle());
        //帮助次数
        jsonObject.put("helpTimes", xaRescue.getHelpTimes());
        //现金额
        jsonObject.put("cash", xaRescue.getCash());
        //救助类型
        jsonObject.put("rescueType", xaRescue.getRescueType());
        //省份Id
        jsonObject.put("provinceId", xaRescue.getProvinceId());
        //城市Id
        jsonObject.put("cityId", xaRescue.getCityId());
        //审核
        jsonObject.put("examine", xaRescue.getExamine());
        //救助详细说明
        jsonObject.put("instructions", xaRescue.getInstructions());
        //创建时间
        jsonObject.put("createdat", createDat);
        //更新时间
        jsonObject.put("updatedat", updatedat);
        List<XaRescuePictures> xaRescuePictures = xinAnRescuePicturesService.findRescuePicturesList(numberId);
        JSONArray jsonArray = new JSONArray();

        for (XaRescuePictures xaRescuePictures1 : xaRescuePictures){
            jsonArray.add(xaRescuePictures1.getPicture());
        }
        JSONArray jsonArrayList = new JSONArray();
        jsonObject.put("imageList", jsonArray);
        jsonArrayList.add(jsonObject);
        return jsonArrayList;
    }

    /**
     * 转换Json列表
     * @param xaRescues
     * @return
     */
    public JSONArray rescueJson(List<XaRescue> xaRescues){
        JSONArray objects = new JSONArray();
        for (XaRescue rescue : xaRescues) {
            JSONObject json = new JSONObject();
            String createDat = DateUtil.formatDateTime(rescue.getCreatedat());
            String updatedat = DateUtil.formatDateTime(rescue.getUpdatedat());
            //救助编号
            json.put("numberId", rescue.getNumberId());
            //发起用户编号
            json.put("memberId", rescue.getMemberId());
            //目标金额
            json.put("targetAmount", rescue.getTargetAmount());
            //救助标题
            json.put("title", rescue.getTitle());
            //帮助次数
            json.put("helpTimes", rescue.getHelpTimes());
            //现金额
            json.put("cash", rescue.getCash());
            //救助类型
            json.put("rescueType", rescue.getRescueType());
            //省份Id
            json.put("provinceId", rescue.getProvinceId());
            //城市Id
            json.put("cityId", rescue.getCityId());
            //审核
            json.put("examine", rescue.getExamine());
            //救助详细说明
            json.put("instructions", rescue.getInstructions());
            //创建时间
            json.put("createdat", createDat);
            //更新时间
            json.put("updatedat", updatedat);
            objects.add(json);
        }
        return objects;
    }

    public JSONObject PageAndRescueJson(PageInfo pageInfo,JSONArray objects){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",objects);
        //当前页
        jsonObject.put("pageNum",pageInfo.getPageNum());
        //每页数量
        jsonObject.put("pageSize", pageInfo.getPageSize());
        //当前页数量
        jsonObject.put("size",pageInfo.getSize());
        //总条数
        jsonObject.put("total",pageInfo.getTotal());
        //总页数
        jsonObject.put("pages",pageInfo.getPages());
        //前一页
        jsonObject.put("prePage",pageInfo.getPrePage());
        //第一页
        jsonObject.put("nextPage", pageInfo.getNextPage());
        //导航页码数
        jsonObject.put("navigatePages",pageInfo.getNavigatePages());
        //所有导航页号
        jsonObject.put("navigatepageNums",pageInfo.getNavigatepageNums());
        return jsonObject;
    }

}
