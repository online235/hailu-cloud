package com.hailu.cloud.api.mall.module.goods.dao;

import com.hailu.cloud.api.mall.module.goods.entity.goods.*;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsParameterVo;
import com.hailu.cloud.api.mall.module.goods.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品信息
 *
 * @author Administrator
 */
@Mapper
public interface GoodsToMapper {

    /**
     * 添加商品评价
     *
     * @param goodsCommentVo
     * @throws Exception
     * @author 黄兴
     */
    void addGoodsComment(GoodsCommentVo goodsCommentVo) throws Exception;

    /**
     * 根据id获取评价
     *
     * @param gevalId 评价id
     * @return
     * @throws Exception
     * @author 黄兴
     */
    GoodsCommentVo getGoodsCommentById(int gevalId) throws Exception;

    /**
     * 评价点赞
     *
     * @param goodsCommentVo
     * @throws Exception
     */
    void upGoodsComment(GoodsCommentVo goodsCommentVo) throws Exception;

    /**
     * 根据商品id获取商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    GoodsListVo getGoods2(int id);

    /**
     * 获取商品的价格
     *
     * @param goodsPriceVo
     * @return
     * @throws Exception
     */
    GoodsPriceVo getGoodsPrice(GoodsPriceVo goodsPriceVo) throws Exception;

    /**
     * 商品参数
     *
     * @param goodsId
     * @return
     * @throws Exception
     */
    List<GoodsParameterVo> goodsParameters(int goodsId);

    /**
     * 包装售后
     *
     * @param goodsId
     * @return
     * @throws Exception
     */
    List<PackagingVo> findPackagings(int goodsId);

    /**
     * 项目进展
     *
     * @param goodsId
     * @return
     * @throws Exception
     */
    List<ProgressVo> getProgressVos(@Param("goodsId") Integer goodsId, @Param("reserveId") int reserveId) throws Exception;

    /**
     * 实时话题
     *
     * @return
     * @throws Exception
     */
    List<SshtVo> getSshtVo(@Param("goodsId") Integer goodsId, @Param("reserveId") int reserveId) throws Exception;

    /**
     * 热门词
     *
     * @return
     * @throws Exception
     */
    List<HotVo> getHots() throws Exception;

    /**
     * 查询热门词
     *
     * @return
     * @throws Exception
     */
    HotVo getHotByName(HotVo hotVo) throws Exception;

    /**
     * 更新热门词数
     *
     * @throws Exception
     */
    void updateHot(HotVo hotVo) throws Exception;

    /**
     * 添加热门词
     *
     * @param hotVo
     * @throws Exception
     */
    void addHot(HotVo hotVo) throws Exception;

    /**
     * @param parentId
     * @return
     * @author 黄亮
     * 得到商品分类列表
     */
    List<GoodsClassVo> getListGoodsClass(@Param("parentId") Integer parentId);

    /**
     * @param page
     * @param row
     * @param gcId
     * @param oby
     * @param goodsName
     * @param conditions
     * @param isBigClass
     * @return
     * @author 黄亮
     * 根据分类id得到下面的所有商品属性
     */
    List<GoodsListVo> findByGcIdQueryGoods(
            @Param("page") Integer page,
            @Param("row") Integer row,
            @Param("gcId") Integer gcId,
            @Param("conditions") Integer conditions,
            @Param("goodsName") String goodsName,
            @Param("oby") String oby,
            @Param("isBigClass") Integer isBigClass);

    /**
     * @param specId
     * @return
     * @author 黄亮
     * 根据规格id得到商品价格
     */
    GoodsListVo findBySpecId(Integer specId);

    /**
     * @return
     * @author 黄亮
     * 得到首页推荐活动
     */
    List<RecommendVo> getHomeList();

    /**
     * @param gcBigId
     * @param showNum
     * @return
     * @author 黄亮
     * 根据分类大id得到推荐的商品id和商品详情
     */
    List<GoodsListVo> getGcRecommend(
            @Param("gcBigId") Integer gcBigId,
            @Param("showNum") Integer showNum,
            @Param("recommendId") Integer recommendId);

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     */
    GoodsListVo getCouponsGoods(int goodsId);


    /**
     * @return
     * @author 黄亮
     * 得到新品首发
     */
    List<NewGoodsVo> getNewGoods();

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     * 得到商品详情
     */
    GoodsInfoVo getGoodsInfo(Integer goodsId);

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     * 根据商品id得到所有的规格
     */
    List<SpecVo> getSpecList(Integer goodsId);

    /**
     * @param goodsId
     * @param actType
     * @return
     * @author 黄亮
     * 得到活动详情
     */
    List<ActGoodsPriceVo> getReserveInfo(@Param("goodsId") Integer goodsId, @Param("actType") Integer actType);

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     * 得到赠品
     */
    List<GoodsCompl> getGoodsCompl(Integer goodsId);

    /**
     * 马宗旭
     *
     * @param goodsRuleId 规格ID
     * @return 规格名称
     */
    String getGoodeSpecName(@Param("goodsRuleId") Integer goodsRuleId);

    /**
     * @param goodsId
     * @param specId
     * @return
     * @author 黄亮
     * 得到商品售出数量和库存
     */
    ActPriceVo getGoodsNum(@Param("goodsId") int goodsId, @Param("specId") int specId);

    /**
     * @param goodsId
     * @param specId
     * @param goodsStorage
     * @param specSalenum
     * @author 黄亮
     * 更新商品库存
     */
    void updateGoodsStorage(
            @Param("goodsId") int goodsId,
            @Param("specId") int specId,
            @Param("goodsStorage") int goodsStorage,
            @Param("specSalenum") int specSalenum);

    /**
     * @param specId
     * @return
     * @author 黄亮
     * 根据规格id得到价格和售出数量
     */
    Map<String, Object> getPriceAndNum(Integer specId);

    /**
     * @param goodsId
     * @param specId
     * @return
     * @throws Exception
     * @author 黄亮
     * 根据商品id和规格id得到活动
     */
    NormalActVo findByGoodsIdSpecId(
            @Param("goodsId") int goodsId,
            @Param("specId") int specId) throws Exception;

    /**
     * @param goodsId
     * @param specId
     * @return
     * @author 黄亮
     * 得到购物车使用活动详情
     */
    ActPriceVo getByGoodsIdAndSpecId(@Param("goodsId") int goodsId, @Param("specId") int specId);

    /**
     * @param id
     * @return
     * @author 黄亮
     * 根据pid得到对应的回复
     */
    List<SshtVo> findBySsPid(int id);

    /**
     * @param goodsId
     * @param actType
     * @param goodsSpecId
     * @return
     * @author 黄亮
     */
    ActPriceVo findByGoodsIdAndActType(
            @Param("goodsId") int goodsId,
            @Param("actType") int actType,
            @Param("goodsSpecId") int goodsSpecId);


    /**
     * @param activityType 查询众筹页面数据
     * @author刘信
     */
    public List<ActGoodsPriceVo> selectShow(int activityType) throws Exception;

    /**
     * @param goodsId 查询众筹页面数据2
     * @author刘信
     */
    public ActGoodsPriceVo selectShow2(int goodsId) throws Exception;

    /**
     * @param complGoodsId
     * @return
     * @author 黄亮
     * 得到贈品的屬性
     */
    Map<String, Object> findGoodsByGoodsId(Integer complGoodsId);

    /**
     * @return
     * @author 黄亮
     * 得到分类推荐
     */
    List<GoodsClassVo> findClassifyRecommend();

    /**
     * @param parentId
     * @return
     * @author 黄亮
     * 得到分类推荐
     */
    GoodsClassVo getGoodsClass(Integer parentId);

    /**
     * @param goodsId
     * @param newSalenum
     * @author 黄亮
     * 根据商品id更新销量
     */
    void updateGoodsSalenum(@Param("goodsId") int goodsId, @Param("newSalenum") int newSalenum);

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     * 根据商品id得到该商品以前的销售量
     */
    Integer getGoodsSalenum(int goodsId);

    /**
     * @param goodsId
     * @return
     * @AUTHOR HUANGL
     * 得到邮费id
     */
    int getTransport(int goodsId);

    /**
     * @param cityName
     * @param transportId
     * @return
     * @AUTHOR HUANGL
     * 得到运费
     */
    FreightVo findTransport(@Param("transportId") Integer transportId, @Param("cityName") String cityName);

    /**
     * @param time
     * @param freAm
     * @return
     * @AUTHORHUANGL 得到是否免邮
     */
    int getTransportAct(@Param("time") long time, @Param("freAm") BigDecimal freAm);

    /**
     * 得到对应的活动 ,同一商品,同一规格 ,在同一时间不能同时参加多个活动 否则会报错
     *
     * @param goodsId
     * @param goodsSpecId
     * @param time
     * @return
     */
    ActPriceVo findActivity(
            @Param("goodsId") int goodsId,
            @Param("goodsSpecId") int goodsSpecId,
            @Param("time") long time);

    /**
     * 得到实时话题
     *
     * @param actId
     * @param goodsId
     * @param start
     * @param rows
     * @return
     */
    List<SshtVo> findSshtList(
            @Param("actId") int actId,
            @Param("goodsId") int goodsId,
            @Param("start") int start,
            @Param("rows") int rows);

    /**
     * 添加ssht
     *
     * @param ssht
     */
    void addSsht(SshtVo ssht);

    GoodsWVo findGoodsByIdWT(int goodsId);

    List<GoodsListVo> findByGcIdQueryGoods1(
            @Param("page") Integer page,
            @Param("row") Integer row,
            @Param("gcId") Integer gcId,
            @Param("conditions") Integer conditions,
            @Param("goodsName") String goodsName,
            @Param("oby") String oby,
            @Param("isBigClass") int isBigClass,
            @Param("goodsId") Integer goodsId);

    List<GoodsListVo> getParentClassList(int parentId);

    /**
     * 根據預定活動ID得到對應的活動開始結束時間
     *
     * @param reserveId
     * @return
     * @Author huangl
     */
    ActGoodsPriceVo getReserveAct(int reserveId);

    /**
     * 得到新品首发和众筹预定封面
     *
     * @return
     * @Author huangl
     */
    List<Map<String, Object>> getReserveAndNew();

    String getSpecGoodsSpec(Integer goodsSpecId);

    /**
     * 得到规格详情
     *
     * @Author HuangL
     * @Description
     * @Date 2018-09-25_16:45
     */
    SpecVo findSpec(Integer specId);

    /**
     * 根据购物车ids 得到对应的邮费
     *
     * @Author HuangL
     * @Description
     * @Date 2018-10-10_17:28
     */
    List<FreightVo> findTransportByCardId(
            @Param("cardIds") String[] cartIds,
            @Param("cityName") String cityName,
            @Param("type") String type);

    /**
     * 得到购物车推荐商品
     *
     * @Author HuangL
     * @Description
     * @Date 2018-10-17_16:44
     */
    List<GoodsListVo> getRecommend(String type);


    /**
     * 得到商品推荐
     *
     * @Author HuangL
     * @Email huangl96@163.com
     * @Date 3:20 PM 9/4/2019
     */
    List<GoodsListVo> getGoodsRecommend(
            @Param("type") String type,
            @Param("page") int page,
            @Param("rows") int rows);

    /**
     * 得到商品详情
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 3:26 PM 9/10/2019
     */
    GoodsInfoTo findGoodsInfo(Integer goodsId);

    /**
     * 得到活动
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 4:28 PM 9/10/2019
     */
    List<ActGoodsPriceVo> getActivity(@Param("goodsId") Integer goodsId, @Param("actType") Integer[] actType);

    /**
     * 根据规格id获取规格
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 4:40 PM 9/10/2019
     */
    List<SpecVo> getSpecListById(@Param("goodsId") Integer goodsId, @Param("goodsSpecId") Integer goodsSpecId);

    /**
     * 更新健康豆商品
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 7:11 PM 9/17/2019
     */
    void updatePointsMarketRepertory(
            @Param("actPriceId") int actPriceId,
            @Param("repertory") int repertory,
            @Param("hasBeen") int hasBeen);
}
