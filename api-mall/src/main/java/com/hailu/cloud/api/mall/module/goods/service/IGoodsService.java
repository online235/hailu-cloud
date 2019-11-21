package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.HomeRecommend;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsInfoTo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.SearchGoodsParam;
import com.hailu.cloud.api.mall.module.goods.entity.goods.SpecialOffer;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderGoods;
import com.hailu.cloud.api.mall.module.goods.vo.AskReplyAnswerVo;
import com.hailu.cloud.api.mall.module.goods.vo.AskReplyQuestionVo;
import com.hailu.cloud.api.mall.module.goods.vo.AskReplyVo;

import java.util.List;
import java.util.Map;

/**
 * 商品
 */
public interface IGoodsService {
    /**
     * 我的提问
     *
     * @param
     * @return
     */
    void addAskReply(AskReplyAnswerVo askReplyAnswerVo) throws Exception;


    /**
     * 我的提问Or我的回答
     *
     * @param
     * @return
     */
    List<AskReplyQuestionVo> myAskReply(String userId, int type, int page, int rows) throws Exception;

    /**
     * 我的问答——是否购买
     *
     * @param
     * @return
     */
    Boolean isBought(OrderGoods orderGoods) throws Exception;

    /**
     * @return
     * @author huangl
     * @date 4:25 PM 9/4/2019
     */
    Map<String, Object> goodsAllEvaluate(int goodsId, int page, int rows);

    /**
     * @author 黄亮
     * 根据问题id得到所有的回答
     */
    List<AskReplyVo> myQuestAllAnswers(int askReplyId, int page, int rows);

    /**
     * @param goodsId
     * @param page
     * @param rows
     * @return
     * @author 黄亮
     * 根据商品id得到对应的列表
     */
    List<AskReplyQuestionVo> findAskByGoodsId(int goodsId, int page, int rows);

    /**
     * 得到小程序首页
     *
     * @return 返回小程序首页数据
     * @author huangl
     * @date 4:15 PM 9/4/2019
     */
    Map<String, Object> findXHome();

    /**
     * 获取首页推荐
     *
     * @param page
     * @param rows
     * @return
     */
    List<HomeRecommend> findHomeRecommend(int page, int rows);

    /**
     * 获取会员特惠商品
     *
     * @return 返回特惠商品
     */
    SpecialOffer findSpecialOfferGoods();



    /**
     * 商品搜索
     *
     * @param searchGoodsParam 筛选条件
     * @return 返回
     */
    Map<String, Object> verifySearchGoods(SearchGoodsParam searchGoodsParam);

    /**
     * 得到商品详情
     *
     * @param goodsId     商品id
     * @param goodsSpecId 规格id
     * @param type        类型 0_正常,1_秒杀,2_周
     * @param userId      用户id
     * @return
     */
    GoodsInfoTo verifyGoodsInfo(Integer goodsId, Integer goodsSpecId, Integer type, String userId);

//    /**
//     * @Author: QiuFeng:WANG
//     * @Description: 查询推广产品
//     * @Date: 18:01 2019/11/4 0004
//     */
//    ResponseData findGoodsList(String userid, Integer page, Integer size);


}
