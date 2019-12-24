package com.hailu.cloud.api.mall.module.goods.dao;

import com.hailu.cloud.api.mall.module.goods.entity.Brand;
import com.hailu.cloud.api.mall.module.goods.entity.Spec;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsEvaluateTO;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.SearchGoodsParam;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderGoods;
import com.hailu.cloud.api.mall.module.goods.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品
 *
 * @author Administrator
 */
@Mapper
public interface GoodsMapper {
    /**
     * 购买商品提新问题
     *
     * @return
     */
    void addAskReply(AskReplyAnswerVo askReplyAnswerVo) throws Exception;

    /**
     * 我的提问
     *
     * @param userId
     * @param rows
     * @param page
     */
    List<AskReplyQuestionVo> myQuestion(
            @Param("userId") String userId,
            @Param("page") int page,
            @Param("rows") int rows) throws Exception;

    /**
     * 我的回答 , 显示提问的问题
     *
     * @param rows
     * @param page
     */
    List<AskReplyQuestionVo> allQuestionBymyAnswer(
            @Param("userId") String userId,
            @Param("page") int page,
            @Param("rows") int rows
            , @Param("time") long time) throws Exception;

    /**
     * 我的问答——是否购买
     *
     * @param
     * @return
     */
    int isBought(OrderGoods orderGoods) throws Exception;

    /**
     * @param goodsId
     * @param page
     * @param rows
     * @return
     * @author 黄亮
     * 得到商品评价列表
     */
    List<GoodsEvaluateTO> goodsAllEevaluate(
            @Param("goodsId") int goodsId,
            @Param("page") int page,
            @Param("rows") int rows);

    /**
     * 根据问题id得到对应回答
     *
     * @param askReplyId
     * @param page
     * @param rows
     * @return
     */
    List<AskReplyVo> myQuestAllAnswers(
            @Param("askReplyId") int askReplyId,
            @Param("page") int page,
            @Param("rows") int rows);

    /**
     * @param goodsId
     * @param page
     * @param rows
     * @return
     * @author 黄亮
     * 得到所有问题
     */
    List<AskReplyQuestionVo> getIssue(
            @Param("goodsId") int goodsId,
            @Param("page") int page,
            @Param("rows") int rows);

    /**
     * 得到对应商品评价数
     *
     * @param goodsId
     * @return
     * @Author huangl
     */
    int getCountEvaluate(int goodsId);

    /**
     * 商品搜索
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 10:53 AM 9/10/2019
     */
    List<GoodsListVo> findSearchGoods(SearchGoodsParam searchGoodsParam);

    /**
     * 得到搜索的分类
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 11:19 AM 9/10/2019
     */
    List<Integer> findGroupGcId(SearchGoodsParam searchGoodsParam);

    /**
     * 根据分类id获取对应的品牌
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 11:28 AM 9/10/2019
     */
    List<Brand> findBrandByGcIds(List<Integer> integers);

    /**
     * 得到规格名
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 11:38 AM 9/10/2019
     */
    List<Spec> findSpecByGcIds(List<Integer> integers);


    /**
     * 根据ID查询商品信息
     *
     * @param id
     * @return
     */
    Goods findById(@Param("id") int id);

    /**
     * @Author: QiuFeng:WANG
     * @Description: 查询推广产品
     * @Date: 18:01 2019/11/4 0004
     */
    List<GoodsAndGoodsSpecVo> findGoodsList();

}
