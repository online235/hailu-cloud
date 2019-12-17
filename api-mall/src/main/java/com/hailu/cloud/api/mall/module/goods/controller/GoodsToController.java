package com.hailu.cloud.api.mall.module.goods.controller;


import com.google.common.collect.ImmutableMap;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCommentVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsInfoVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsToService;
import com.hailu.cloud.api.mall.module.goods.tool.PictureUploadUtil;
import com.hailu.cloud.api.mall.module.goods.vo.HotVo;
import com.hailu.cloud.api.mall.module.goods.vo.SshtVo;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商城商品信息
 *
 * @author xuzhijie
 */
@Slf4j
@RestController
@RequestMapping("api/mall")
public class GoodsToController {
    @Autowired
    private IGoodsToService goodsToService;

    /**
     * 上传图片评论
     */
    @PostMapping("/saveImage")
    public Map<String, Object> saveImage(
            @RequestParam("images") String images,
            @RequestParam(value = "goodsId", required = false) Integer goodsId) throws BusinessException {

        if (StringUtils.isEmpty(images)) {
            throw new BusinessException("参数为空");
        }
        StringBuilder imgpath = new StringBuilder();
        for (String iu : images.split(",")) {
            String img = PictureUploadUtil.uploadPicture("img", iu);
            if (imgpath.length() > 0) {
                imgpath.append(",");
            }
            imgpath.append(img);
        }
        return ImmutableMap.of("imgpath", imgpath.toString(), "goodsId", goodsId);
    }

    /**
     * 添加商品评价
     */
    @PostMapping("/addGoodsComment")
    public Boolean addGoodsComment(
            @RequestParam String userId,//评价人id
            @RequestParam int orderId,//订单id
            @RequestParam String userImg,//评价人头像
            @RequestParam String userName,//评价人昵称
            @RequestParam int logisticsScore,//物流评分  5分 一分一颗星
            @RequestParam int serviceScore,//服务评分  5分 一分一颗星
            @RequestParam int gevalIsAnonymous,//是否匿名评论   0匿名   1不匿名
            @RequestParam String goodsComments//商品评价list
    ) throws Exception {

        GoodsCommentVo goodsCommentVo = new GoodsCommentVo();
        goodsCommentVo.setUserId(userId);
        goodsCommentVo.setLogisticsScore(logisticsScore);
        goodsCommentVo.setServiceScore(serviceScore);
        goodsCommentVo.setGevalIsAnonymous(gevalIsAnonymous);
        goodsCommentVo.setOrderId(orderId);
        goodsCommentVo.setUserImg(userImg);
        goodsCommentVo.setUserName(userName);
        goodsCommentVo.setGoodsComments(goodsComments);
        Boolean flag = goodsToService.addGoodsComment(goodsCommentVo);
        if (!flag) {
            throw new BusinessException("添加评论失败");
        }
        return flag;
    }

    /**
     * 热门搜索词
     */
    @GetMapping("/getGoodsKeyWords")
    public Map<String, Object> getGoodsKeyWords() throws Exception {
        log.info("获取热门搜索");
        List<HotVo> hotVos = goodsToService.getHots();
        List<Map<String, String>> keys = new ArrayList<>();
        if (hotVos.size() > 0) {
            for (HotVo vo : hotVos) {
                Map<String, String> map = new HashMap<>(1);
                map.put("name", vo.getHotName());
                keys.add(map);
            }
        }
        // 分类商品
        return ImmutableMap.of("names", keys);
    }


    /**
     * 得到商品详情
     */
    @PostMapping("/getGoodsInfo")
    public GoodsInfoVo getGoodsList(
            @RequestParam("goodsId") Integer goodsId, //商品id
            @RequestParam(value = "activityType", required = false) Integer activityType, //活动类型
            @RequestParam(value = "isReserve", required = false) Integer isReserve,//是否预定
            @RequestParam(value = "userId", required = false) String userId) throws Exception {

        return goodsToService.verifyGoodsInfo(goodsId, activityType, isReserve, userId);
    }

    /**
     * 得到实时话题list
     */
    @PostMapping("/findSshtList")
    public List<SshtVo> findSshtList(
            @RequestParam int actId,
            @RequestParam int goodsId,
            @RequestParam int page,
            @RequestParam int rows) {

        List<SshtVo> sshtVos = goodsToService.findSshtList(actId, goodsId, page > 1 ? (page - 1) * rows : 0, rows);
        return sshtVos;
    }

    /**
     * 添加实时话题
     */
    @PostMapping("/addSsht")
    public Boolean addSsht(@ModelAttribute SshtVo ssht) {
        return goodsToService.addSsht(ssht);
    }

    /**
     * 得到购物车推荐 商品/购物车共用
     */
    @PostMapping("/getCartRecommend")
    public List<GoodsListVo> getCartRecommend() {
        return goodsToService.getRecommend("2");
    }

}
