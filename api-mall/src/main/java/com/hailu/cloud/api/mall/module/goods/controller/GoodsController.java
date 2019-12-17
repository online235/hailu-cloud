package com.hailu.cloud.api.mall.module.goods.controller;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsInfoTo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.SearchGoodsParam;
import com.hailu.cloud.api.mall.module.goods.entity.goods.SpecialOffer;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderGoods;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsService;
import com.hailu.cloud.api.mall.module.goods.vo.AskReplyAnswerVo;
import com.hailu.cloud.api.mall.module.goods.vo.AskReplyQuestionVo;
import com.hailu.cloud.api.mall.module.goods.vo.AskReplyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品接口
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/api/mall")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 添加问题或回答问题
     */
    @PostMapping("/addAskReply")
    public Map<String, Object> addAskReply(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "userImg") String userImg,
            @RequestParam(value = "goodsId") int goodsId,
            @RequestParam(value = "goodsName") String goodsName,
            @RequestParam(value = "goodsImg") String goodsImg,
            @RequestParam(value = "context") String context,
            @RequestParam(value = "pid") int pid) throws Exception {

        AskReplyAnswerVo vo = new AskReplyAnswerVo();
        vo.setUserId(userId);
        vo.setUserName(userName);
        vo.setUserImg(userImg);
        vo.setGoodsId(goodsId);
        vo.setGoodsName(goodsName);
        vo.setGoodsImg(goodsImg);
        vo.setContext(context);
        vo.setPid(pid);
        vo.setCreateTime(System.currentTimeMillis());
        goodsService.addAskReply(vo);
        return new HashMap<>(1);
    }

    /**
     * 我的问题Or我的回答
     * type=0——我的提问 ；type=1——我的回答
     */
    @GetMapping("/myAskReply")
    public List<AskReplyQuestionVo> myAskReply(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "type") int type,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "rows") int rows) throws Exception {

        //Type == 0:我的提问列表；Type== 1：我的回答的问题列表
        return goodsService.myAskReply(userId, type, page <= 1 ? 0 : (page - 1) * rows, rows);
    }

    /**
     * 根据问题id得到对应回答
     */
    @GetMapping("/myQuestAllAnswers")
    public List<AskReplyVo> myQuestAllAnswers(
            @RequestParam(value = "askReplyId") int askReplyId,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "rows") int rows) {

        return goodsService.myQuestAllAnswers(askReplyId, page <= 1 ? 0 : (page - 1) * rows, rows);
    }

    /**
     * 得到该商品下的问答
     */
    @GetMapping("/allQuestionsByGoodsId")
    public List<AskReplyQuestionVo> allQuestionsByGoodsId(
            @RequestParam(value = "goodsId") int goodsId,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "rows") int rows) {

        return goodsService.findAskByGoodsId(goodsId, page <= 1 ? 0 : (page - 1) * rows, rows);
    }

    /**
     * 得到是否可以评价
     */
    @GetMapping("/isBought")
    public Boolean isBought(
            @RequestParam(value = "goodsId") int goodsId,
            @RequestParam(value = "userId") String userId) throws Exception {

        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setGoodsId(goodsId);
        orderGoods.setBuyerId(userId);
        return goodsService.isBought(orderGoods);
    }

    /**
     * 商品评价中使用商品评价列表
     */
    @GetMapping("/goodsAllEevaluate")
    public Map<String, Object> goodsAllEevaluate(
            @RequestParam(value = "goodsId") int goodsId,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "rows") int rows) {

        return goodsService.goodsAllEvaluate(goodsId, page <= 1 ? 0 : (page - 1) * rows, rows);
    }

    /**
     * 商品筛选
     */
    @GetMapping("/findSearchGoods")
    public Map<String, Object> findSearchGoods(@ModelAttribute SearchGoodsParam searchGoodsParam) {
        return goodsService.verifySearchGoods(searchGoodsParam);
    }

    /**
     * 得到商品详情
     */
    @GetMapping("/findGoodsInfo")
    public GoodsInfoTo findGoodsInfo(
            @RequestParam("goodsId") Integer goodsId,
            @RequestParam(value = "goodsSpecId", required = false) Integer goodsSpecId,
            @RequestParam(value = "type", required = false, defaultValue = "0") Integer type,
            @RequestParam(value = "userId", required = false) String userId) {
        return goodsService.verifyGoodsInfo(goodsId, goodsSpecId, type, userId);
    }
}
