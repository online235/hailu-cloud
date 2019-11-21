package com.hailu.cloud.api.mall.module.goods.service;


import com.hailu.cloud.api.mall.module.goods.entity.goods.*;
import com.hailu.cloud.api.mall.module.goods.entity.order.CartVo;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderAmount;
import com.hailu.cloud.api.mall.module.goods.vo.*;

import java.math.BigDecimal;
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
     * 根据商品id获取商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    GoodsListVo getGoods2(int id) throws Exception;

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
     * 马宗旭
     *
     * @param goodsRuleId 规格ID
     * @return 规格名称
     */
    String getGoodeSpecName(Integer goodsRuleId);

    /**
     * @param goodsId
     * @param specId
     * @return
     * @author 黄亮 根据商品id得到商品库存和售出数量
     */
    ActPriceVo getGoodsNum(int goodsId, int specId);

    /**
     * @param goodsId
     * @param specId
     * @param goodsStorage
     * @param specSalenum
     * @author 黄亮 更改商品库存
     */
    void updateGoodsStorage(int goodsId, int specId, int goodsStorage, int specSalenum);

    /**
     * @param activityType
     * @return
     * @throws Exception
     * @author 刘信 查询众筹页面数据
     */
    List<ActGoodsPriceVo> getshowActivity(int activityType, String sessionID) throws Exception;

    /**
     * @param goodsId
     * @param specId
     * @return
     * @author 黄亮 根据商品id和规格id得到活动
     */
    NormalActVo findByGoodsIdSpecId(int goodsId, int specId) throws Exception;

    /**
     * @param goodsId
     * @param specId
     * @return
     * @author 黄亮 得到购物车活动详情by商品id和规格id
     */
    ActPriceVo getByGoodsIdAndSpecId(int goodsId, int specId);


    /**
     * @param goodsId
     * @param actType
     * @param goodsSpecId
     * @return
     * @author 黄亮 得到
     */
    ActPriceVo findByGoodsIdAndActType(int goodsId, int actType, int goodsSpecId);


    /**
     * 新品首發
     *
     * @return
     */
    List<NewGoodsVo> getNewGoods();

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
     * 得到订单中的运费
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 7:03 PM 9/18/2019
     */
    BigDecimal getOrderFreight(String cityName, String cartIds, Integer cGoodsId, Double cPrice, List<OrderAmount> orderAmounts, BigDecimal goodsAmount, List<CartVo> cartVoList);

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


    String getApplyForOdd();

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

    GoodsWVo findGoodsByIdWT(int goodsId);

    /**
     * 得到新品首发和
     *
     * @return
     * @Author huangl
     */
    Map<String, Object> getReserveAndNew();

    String getSpecGoodsSpec(Integer goodsSpecId);



    /**
     * @Author HuangL
     * @Description 得到商品详情
     * @Date 2018-10-10_15:32
     */
    GoodsInfoVo getGoodsInfo(Integer goodsId);

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

    /**
     * 获取推荐商品
     *
     * @param type 1_分类,2_商品
     * @param page 页数
     * @param rows 条数
     * @return 返回推荐的商品
     */
    List<GoodsListVo> getGoodsRecommend(String type, int page, int rows);

}
