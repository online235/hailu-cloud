package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCommentVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsInfoVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import com.hailu.cloud.api.mall.module.goods.entity.order.CartVo;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderAmount;
import com.hailu.cloud.api.mall.module.goods.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 商品信息 评价
 *
 * @author leiqi
 */
public interface IGoodsToService {
    /**
     * 添加商品评价
     *
     * @param goodsCommentVo
     * @throws Exception
     * @author 黄兴
     */
    Boolean addGoodsComment(GoodsCommentVo goodsCommentVo) throws Exception;

    /**
     * 热门词
     *
     * @return
     * @throws Exception
     */
    List<HotVo> getHots() throws Exception;

    /**
     * @return
     * @author 黄亮 得到商品分类
     */
    Map<String, Object> getListGoodsClass();

    /**
     * @param page
     * @param row
     * @param gcId
     * @param oby
     * @param goodsName
     * @param conditions
     * @param isBigClass
     * @return
     * @author 黄亮 根据分类id得到这个分类下的所有商品
     */
    List<GoodsListVo> verifyByGcIdQueryGoods(Integer page, Integer row, Integer gcId, Integer conditions,
                                             String goodsName, String oby, Integer isBigClass) throws Exception;

    /**
     * @return
     * @author 黄亮 得到首页活动
     */
    List<RecommendVo> verifyHomeList(String sessionID, Integer merchantType);

    /**
     * @param goodsId
     * @param activityType
     * @return
     * @author 黄亮 得到商品详情
     */
    GoodsInfoVo verifyGoodsInfo(Integer goodsId, Integer activityType, Integer isReserve, String userId) throws Exception;

    /**
     * @param goodsId
     * @return
     * @author 黄亮 得到根據商品id得到贈品
     */
    List<GoodsCompl> getGoodsCompl(Integer goodsId);

    /**
     * @return
     * @author 黄亮 根據贈品id得到某個屬性
     */
    Map<String, Object> findGoodsByGoodsId(Integer complGoodsId);

    /**
     * @return
     * @throws Exception
     * @author 黄亮
     * 得到推薦分類
     */
    List<Map<String, Object>> findClassifyRecommend() throws Exception;


    /**
     * @param couAndGoAmount
     * @param cityName
     * @param goodsId
     * @param goodsNum
     * @param couponId
     * @return
     * @AUTHOR HUANGL
     * 直接下单的运费
     */
    Map<String, Object> getFreight(double couAndGoAmount, String cityName, Integer goodsId, Integer goodsNum, Integer specId, Integer type, Integer couponId);

    /**
     * 购物车下单的运费
     *
     * @param cartIds
     * @param couponId
     * @param cartVos
     * @param orderAmounts
     * @return
     */
    Map<String, Object> getGoodsFreight(String cityName, String cartIds, Integer couponId, List<CartVo> cartVos, List<OrderAmount> orderAmounts);

    /**
     * @param goodsId
     * @param cityName
     * @return
     * @throws Exception
     * @AUTHOR HUANGL
     * 得到商品详情中的运费
     */
    Map<String, Object> findGoodsFreight(int goodsId, String cityName) throws Exception;

    /**
     * 得到实时话题 ,
     *
     * @param actId
     * @param goodsId
     * @param start
     * @param rows
     * @return
     */
    List<SshtVo> findSshtList(int actId, int goodsId, int start, int rows);

    /**
     * 添加实时话题
     *
     * @param ssht
     * @return
     */
    Boolean addSsht(SshtVo ssht);

    /**
     * 得到对应推荐商品
     */
    List<GoodsListVo> getRecommend(String type);

    /**
     * 根据父类id获取分类
     *
     * @Author HuangL
     * @Email huangl96@163.com
     * @Date 11:21 AM 9/4/2019
     */
    List<GoodsListVo> getParentClassList(int parentId);


    /**
     * 得到分类/分类推荐
     *
     * @Author HuangL
     * @Email huangl96@163.com
     * @Date 12:00 PM 9/4/2019
     */
    Map<String, Object> getGoodsClass();

}
