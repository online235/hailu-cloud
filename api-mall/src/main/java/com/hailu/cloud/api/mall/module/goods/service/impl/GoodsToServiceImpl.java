package com.hailu.cloud.api.mall.module.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hailu.cloud.api.mall.module.common.enums.BusinessCode;
import com.hailu.cloud.api.mall.module.goods.dao.CollectGoodsMapper;
import com.hailu.cloud.api.mall.module.goods.dao.GoodsToMapper;
import com.hailu.cloud.api.mall.module.goods.dao.OrderMapper;
import com.hailu.cloud.api.mall.module.goods.entity.goods.*;
import com.hailu.cloud.api.mall.module.goods.entity.order.CartVo;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderAmount;
import com.hailu.cloud.api.mall.module.goods.service.IComputeCommission;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsToService;
import com.hailu.cloud.api.mall.module.goods.service.IOrderService;
import com.hailu.cloud.api.mall.module.goods.tool.HtmlReplace;
import com.hailu.cloud.api.mall.module.goods.tool.StringUtil;
import com.hailu.cloud.api.mall.module.goods.vo.*;
import com.hailu.cloud.api.mall.module.sys.service.ISysAttributeService;
import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsParameterVo;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GoodsToServiceImpl implements IGoodsToService {

    @Resource
    private GoodsToMapper goodsToDao;
    @Resource
    private OrderMapper orderDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ISysAttributeService sysAttributeService;
    @Resource
    private UserInfoMapper userInfoDao;
    @Resource
    private CollectGoodsMapper collectGoodsDao;

    @Autowired
    private IComputeCommission computeCommission;
    /**
     * 添加商品评价
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addGoodsComment(GoodsCommentVo goodsComment) {
        try {
            JSONObject object = JSONObject.parseObject(goodsComment.getGoodsComments());
            String type = object.get("evaluate").toString();
            type = type.replace("[", "").replace("]", "");
            String[] sType = type.split("\\}\\,");
            int item = 0;
            boolean flag = true;
            for (String str : sType) {
                StringBuffer sb = new StringBuffer(str);
                sb.insert(sb.length(), "}");
                JSONObject st = JSONObject.parseObject(sb.toString());
                //获取解析出来的键
                //商品id
                int goodsId = st.getIntValue("goodsId");
                //商品名称
                String goodsName = st.getString("goodsName");
                //评价内容
                String gevalContent = st.getString("gevalContent");
                //商品价格
                Double goodsPrice = st.getDouble("goodsPrice");
                //描述评分 5分 一分一颗星
                int gevalScore = st.getIntValue("gevalScore");
                //好评1   中评 2   差评3
                int comment = st.getIntValue("comment");
                //晒单图片 多张图片,分隔
                String gevalImage = st.getString("gevalImage");
                //规格名
                String specInfo = st.getString("specInfo");
                if (gevalContent.length() < 10) {
                    flag = false;
                }
                goodsComment.setGoodsId(goodsId);
                goodsComment.setGoodsName(goodsName);
                goodsComment.setGevalContent(gevalContent);
                goodsComment.setGoodsPrice(goodsPrice);
                goodsComment.setGevalScore(gevalScore);
                goodsComment.setComment(comment);
                goodsComment.setSpecInfo(specInfo);
                goodsComment.setCreateTime(System.currentTimeMillis());
                goodsComment.setCreateTime(System.currentTimeMillis());
                goodsComment.setGevalImage(gevalImage);
                //默认为0
                goodsComment.setIsDel(0);
                goodsToDao.addGoodsComment(goodsComment);
                item = item + 1;
            }
            //如果这个相等就更新当前订单状态
            if (item == sType.length) {
                orderService.updatEvaluate(goodsComment.getOrderId());
                if (flag) {
                    //根据订单id得到确认收货的时间
                    long time = orderService.getOrderAccomplishTime(goodsComment.getOrderId());
                    long time1 = System.currentTimeMillis();
                    long date = 15 * 24;
                    date = date * 3600000;
                    if ((time1 - time) > date) {
                        return false;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public List<HotVo> getHots() throws Exception {
        return goodsToDao.getHots();
    }

    public void verifyHotByName(String hotName) throws Exception {
        HotVo hotVo = new HotVo();
        hotVo.setHotName(hotName);
        hotVo = goodsToDao.getHotByName(hotVo);
        if (hotVo != null) {
            hotVo.setNumber(Integer.parseInt(hotVo.getNumber()) + 1 + "");
            goodsToDao.updateHot(hotVo);
        } else {
            hotVo = new HotVo();
            hotVo.setHotName(hotName);
            hotVo.setNumber(1 + "");
            goodsToDao.addHot(hotVo);
        }
    }

    /**
     * @return
     * @author 黄亮
     * 得到商品分类
     */
    @Override
    public Map<String, Object> getListGoodsClass() {
        Map<String, Object> map = Maps.newHashMap();
        Integer parentId = 0;
        List<GoodsClassVo> goodsClassList = goodsToDao.getListGoodsClass(parentId);
        List<GoodsClassVo> classList = new ArrayList<>();
        if (goodsClassList.size() > 0) {
            goodsClassList.forEach(goodsClassVo -> {
                if (0 == goodsClassVo.getParentId()) {
                    List<GoodsClassVo> classListTwo = new ArrayList<>();
                    goodsClassList.forEach(goodsClassVo1 -> {
                        if (goodsClassVo.getGcId().equals(goodsClassVo1.getParentId())) {
                            classListTwo.add(goodsClassVo1);
                        }
                    });
                    goodsClassVo.setGoodsList(classListTwo);
                    classList.add(goodsClassVo);
                }
            });
        }
        map.put("goodsClassList", classList);
        return map;
    }

    /**
     * 商品搜索接口
     *
     * @Author HuangL
     * @Email huangl96@163.com
     * @Date 11:23 AM 9/4/2019
     */
    @Override
    public List<GoodsListVo> verifyByGcIdQueryGoods(Integer page, Integer row, Integer gcId, Integer conditions, String goodsName, String oby, Integer isBigClass) throws Exception {
        if (!StringUtil.isNotEmpty(page)) {
            throw new BusinessException(BusinessCode.BASE_PARAM_EMPTY.getDescription());
        }
        if (!StringUtil.isNotEmpty(row)) {
            throw new BusinessException(BusinessCode.BASE_PARAM_EMPTY.getDescription());
        }
        if (StringUtils.isNotBlank(goodsName)) {
            this.verifyHotByName(goodsName.trim());
        }
        page = (page - 1) * row;
        //根据分类id得到商品属性
        List<GoodsListVo> goodsList = goodsToDao.findByGcIdQueryGoods(page, row, gcId, conditions, goodsName, oby, isBigClass);
        if(goodsList.size() > 0){
            breakDown(goodsList);
        }
        return goodsList;
    }
    private void breakDown(List<GoodsListVo> goodsList) {
        for(GoodsListVo goodsListVo : goodsList) {
            goodsListVo.setIsActivity(0);
            goodsListVo.setActivltyType(2);
            //根据商品默认规格id得到价格
            GoodsListVo price = goodsToDao.findBySpecId(goodsListVo.getSpecId());
            goodsListVo.setSpecGoodsPrice(price.getSpecGoodsPrice());
            goodsListVo.setSpecGoodsVipPrice(price.getSpecGoodsVipPrice());
            goodsListVo.setSpecGoodsPurchasePrice(price.getSpecGoodsPurchasePrice());
        }
    }


    /**
     * @AUTHORHUANGL 得到首页下的列表
     */
    @Override
    public List<RecommendVo> verifyHomeList(String sessionID, Integer merchantType) {
        userUseRecord(sessionID);

        //得到首页推荐活动
        List<RecommendVo> homelist = goodsToDao.getHomeList();
        if (homelist.size() > 0) {
            for (RecommendVo recomm : homelist) {
                recomm.setTitleColor(RecommendVo.color(recomm.getTitleColor()));
                String recommImg = recomm.getRecommImg();
                if (StringUtil.isNotEmpty(recommImg) && !("http").equals(recommImg.substring(0, 4))) {
                    recomm.setRecommImg(Const.PRO_URL + recommImg);
                }
                if (StringUtil.isNotEmpty(recomm.getCoverImg()) && !("http").equals(recomm.getCoverImg().substring(0, 4))) {
                    recomm.setCoverImg(Const.PRO_URL + recomm.getCoverImg());
                }

                //是否首发
                Integer isPublish = recomm.getIsPublish();
                //显示数量
                Integer showNum = recomm.getShowNum();
                if (isPublish == 1) {
                    //新品首发
                    List<NewGoodsVo> newGoods = goodsToDao.getNewGoods();
                    if (newGoods.size() > 0) {
                        for (NewGoodsVo newGoodsVo : newGoods) {
                            //根据商品规格id得到相应的价钱
                            Map<String, Object> price = goodsToDao.getPriceAndNum(newGoodsVo.getSpecId());
                            if( price != null ){
                                newGoodsVo.setGoodsPrice(((BigDecimal) price.get("gprice")).doubleValue());
                                newGoodsVo.setHasBeen((Integer) price.get("hasBeen"));
                            }
                            //拼接商品路劲
                            if (StringUtil.isNotEmpty(newGoodsVo.getShowImg()) && !("http").equals(newGoodsVo.getShowImg().substring(0, 4))) {
                                newGoodsVo.setShowImg(Const.PRO_URL + newGoodsVo.getShowImg());
                            }
                        }
                    }
                    recomm.setNewGoods(newGoods);
                } else {
                    Integer gcBigId = recomm.getGcBigId();
                    List<GoodsListVo> goods = goodsToDao.getGcRecommend(gcBigId, showNum, recomm.getRecommendId());
                    if (goods.size() > 0) {
                        for (GoodsListVo goodsList : goods) {
                            goodsList.setIsActivity(0);
                            goodsList.setActivltyType(2);
                            //商品价格
                            GoodsListVo price = goodsToDao.findBySpecId(goodsList.getSpecId());
                            goodsList.setSpecGoodsPrice(price.getSpecGoodsPrice());
                            goodsList.setSpecGoodsVipPrice(price.getSpecGoodsVipPrice());
                            goodsList.setSpecGoodsPurchasePrice(price.getSpecGoodsPurchasePrice());
                            goodsList.setCommission(computeCommission.compute(price.getCommission()));
                        }
                    }
                    recomm.setGoodsList(goods);
                    //map.put("goods",goods);
                }
            }
        }
        return homelist;
    }

    /**
     * @Author HuangL
     * @Description 得到对应的方法用来判断存活率的
     * @Date 2018-10-25_16:50
     */
    private void userUseRecord(String sessionID) {
        if (StringUtils.isNotEmpty(sessionID)) {
            int count = userInfoDao.findUseRecord(sessionID);
            if (count == 0) {
                userInfoDao.saveUseRecord(sessionID);
            }
        }
    }

    /**
     * @throws Exception
     * @author 黄亮
     * 得到商品详情
     */
    @SuppressWarnings("unchecked")
    @Override
    public GoodsInfoVo verifyGoodsInfo(Integer goodsId, Integer activityType, Integer isReserve, String userId) throws Exception {

        if (!StringUtil.isNotEmpty(goodsId)) {
            throw new BusinessException(BusinessCode.BASE_PARAM_EMPTY.getDescription());
        }

        GoodsInfoVo goodsInfo = goodsToDao.getGoodsInfo(goodsId);
        List<CollectGoodsVo> collectGoodsList = collectGoodsDao.verificationIsCollect(userId, goodsId);
        if (collectGoodsList.size() > 0) {
            goodsInfo.setIsCollect(1);
        }
        //活动使用
        List<Map<String, Object>> maplist2 = new LinkedList<>();
        List<Map<String, Object>> maplist5 = new LinkedList<>();
        //规格参数 , 包装售后
        List<GoodsParameterVo> goodsParameterVos = goodsToDao.goodsParameters(goodsId);
        List<PackagingVo> packagingVos = goodsToDao.findPackagings(goodsId);
        if (packagingVos.size() > 0) {
            goodsInfo.setGoodsPack(packagingVos);
        }
        if (goodsParameterVos.size() > 0) {
            for (int i = 0; i < goodsParameterVos.size(); i++) {
                String a = goodsParameterVos.get(i).getParameterValue();
                if (a.indexOf("&le;") != -1 || a.indexOf("&ge;") != -1 || a.indexOf("&plusmn;") != -1) {
                    String parameterValue = HtmlReplace.htmlReplace(a);
                    goodsParameterVos.get(i).setParameterValue(parameterValue);
                    goodsInfo.setGoodsParameter(goodsParameterVos);
                } else {
                    goodsInfo.setGoodsParameter(goodsParameterVos);
                }
            }
        }
        if (goodsInfo != null) {
            //得到商品规格
            List<SpecVo> spec = goodsToDao.getSpecList(goodsId);
            if (spec.size() > 0) {
                Map<String, Object> map = analysisJSON(spec);
                maplist2 = (List<Map<String, Object>>) map.get("spec");
                maplist5 = (List<Map<String, Object>>) map.get("goodsSpec");
            }
            //拼接图片路径
            if (StringUtil.isNotEmpty(goodsInfo.getGoodsImage()) && !("http").equals(goodsInfo.getGoodsImage().substring(0, 4))) {
                goodsInfo.setGoodsImage(Const.PRO_URL + goodsInfo.getGoodsImage());
            }
            //拼接商品多图路径图片路径
            if (StringUtil.isNotEmpty(goodsInfo.getGoodsImageMore())) {
                StringBuilder imgPath = new StringBuilder();
                for (String str : goodsInfo.getGoodsImageMore().split(",")) {
                    if (imgPath.length() > 0) {
                        imgPath.append(",");
                    }
                    imgPath.append(Const.PRO_URL).append(str);
                }
                goodsInfo.setGoodsImageMore(imgPath.toString());
            }
            //商品详情路径
            if (StringUtil.isNotEmpty(goodsInfo.getGoodsBody()) && !goodsInfo.getGoodsBody().contains("http")) {
                StringBuilder imgPath = new StringBuilder();
                for (String str : goodsInfo.getGoodsBody().split(",")) {
                    if (imgPath.length() > 0) {
                        imgPath.append(",");
                    }
                    imgPath.append(Const.PRO_URL).append(str);
                }
                goodsInfo.setGoodsBody(imgPath.toString());
            }
        }
        Integer actType = 3;


        //页面显示的规格
        goodsInfo.setSpec(maplist2);
        //真实规格
        goodsInfo.setSpecGoods(maplist5);
        //得到赠品
        List<GoodsCompl> goodsCompl = goodsToDao.getGoodsCompl(goodsId);
        if (goodsCompl.size() > 0) {
            goodsInfo.setCompl(goodsCompl);
            goodsInfo.setIsCompl(1);
        } else {
            goodsInfo.setIsCompl(0);
        }
        return goodsInfo;
    }


    @Override
    public ActPriceVo getGoodsNum(int goodsId, int specId) {
        return goodsToDao.getGoodsNum(goodsId, specId);
    }

    @Override
    public void updateGoodsStorage(int goodsId, int specId, int goodsStorage, int specSalenum) {
        goodsToDao.updateGoodsStorage(goodsId, specId, goodsStorage, specSalenum);

    }


    /**
     * 马宗旭
     *
     * @param goodsRuleId 规格ID
     * @return 规格名称
     */
    @Override
    public String getGoodeSpecName(Integer goodsRuleId) {
        return goodsToDao.getGoodeSpecName(goodsRuleId);
    }

    /**
     * 根据商品id获取商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public GoodsListVo getGoods2(int id) throws Exception {
        return goodsToDao.getGoods2(id);
    }

    /**
     * @author 黄亮
     * 根据商品id和规格id得到常规活动
     */
    @Override
    public NormalActVo findByGoodsIdSpecId(int goodsId, int specId) throws Exception {

        return goodsToDao.findByGoodsIdSpecId(goodsId, specId);
    }

    /**
     * @author 黄亮
     * 得到活动详情
     */
    @Override
    public ActPriceVo getByGoodsIdAndSpecId(int goodsId, int specId) {

        return goodsToDao.getByGoodsIdAndSpecId(goodsId, specId);
    }


    /**
     * @author 刘信
     * 获取众筹预定列表
     */
    @Override
    public List<ActGoodsPriceVo> getshowActivity(int activityType, String sessionID) throws Exception {

        List<ActGoodsPriceVo> list = goodsToDao.selectShow(activityType);
        if (list.size() > 0) {
            for (ActGoodsPriceVo l : list) {
                l.setHasBeen(l.getHasBeen() + l.getVirtual());
                int goodsId = l.getGoodsId();
                ActGoodsPriceVo act = goodsToDao.selectShow2(goodsId);
                l.setGoodsName(act.getGoodsName());
                l.setGoodsDescription(act.getGoodsDescription());
            }
        }

        return list;
    }

    /**
     * @author 黄亮
     * 根据规格id和商品id商品类型id得到活动
     */
    @Override
    public ActPriceVo findByGoodsIdAndActType(int goodsId, int actType, int goodsSpecId) {

        return goodsToDao.findByGoodsIdAndActType(goodsId, actType, goodsSpecId);
    }



    /**
     * @author 黄亮
     * 得到新品首发
     */
    @Override
    public List<NewGoodsVo> getNewGoods() {
        List<NewGoodsVo> newGoods = goodsToDao.getNewGoods();
        if (newGoods.size() > 0) {
            for (NewGoodsVo newGoodsVo : newGoods) {
                //根据商品规格id得到相应的价钱
                Map<String, Object> price = goodsToDao.getPriceAndNum(newGoodsVo.getSpecId());
                newGoodsVo.setGoodsPrice(((BigDecimal) price.get("gprice")).doubleValue());
                newGoodsVo.setHasBeen((Integer) price.get("hasBeen"));
                //拼接商品路劲
                if (StringUtil.isNotEmpty(newGoodsVo.getShowImg()) && !("http").equals(newGoodsVo.getShowImg().substring(0, 4))) {
                    newGoodsVo.setShowImg(Const.PRO_URL + newGoodsVo.getShowImg());
                }
            }
        }
        return newGoods;
    }

    /**
     * @author 黄亮
     * 根據訂單id得到贈品
     */
    @Override
    public List<GoodsCompl> getGoodsCompl(Integer goodsId) {
        return goodsToDao.getGoodsCompl(goodsId);
    }

    @Override
    public Map<String, Object> findGoodsByGoodsId(Integer complGoodsId) {

        return goodsToDao.findGoodsByGoodsId(complGoodsId);
    }

    /**
     * @author 黄亮
     * 得到商品分类推荐
     */
    @Override
    public List<Map<String, Object>> findClassifyRecommend() throws Exception {
        List<GoodsClassVo> goodsClassList = goodsToDao.findClassifyRecommend();
        List<Map<String, Object>> map = new ArrayList<>();
        if (goodsClassList != null && goodsClassList.size() > 0) {
            for (int i = 0; i < goodsClassList.size(); ) {
                int pid = 0;
                Map<String, Object> map1 = new HashMap<String, Object>();
                List<GoodsClassVo> gg = new ArrayList<>();
                pid = goodsClassList.get(0).getParentId();
                Iterator<GoodsClassVo> it = goodsClassList.iterator();
                while (it.hasNext()) {
                    GoodsClassVo x = it.next();
                    if (pid == x.getParentId()) {
                        if (!StringUtil.isNotEmpty(map1.get("name"))) {
                            //得到父类id
                            GoodsClassVo gc = goodsToDao.getGoodsClass(x.getParentId());
                            map1.put("name", gc.getGcName());
                        }
                        gg.add(x);
                        it.remove();
                    }
                }
                map1.put("twoGoodsClass", gg);
                map.add(map1);
            }
        }
        return map;
    }



    /**
     * @param couAndGoAmount
     * @param cityName
     * @param goodsId
     * @param goodsNum
     * @param couponId
     * @return
     * @AUTHOR HUANGL
     * type 1_免费领取
     * 得到运费
     */
    @Override
    public Map<String, Object> getFreight(double couAndGoAmount, String cityName, Integer goodsId, Integer goodsNum, Integer specId, Integer type, Integer couponId) {

        couAndGoAmount = (couAndGoAmount < 0) ? 0 : couAndGoAmount;
        Map<String, Object> map = new HashMap<>();
        //运费
        BigDecimal freight = BigDecimal.valueOf(0);

        GoodsInfoVo goodsInfo = goodsToDao.getGoodsInfo(goodsId);
        if (goodsInfo.getFullFreeMail() != null && goodsInfo.getFullFreeMail() != 0) {
            if (couAndGoAmount >= goodsInfo.getFullFreeMail()) {
                map.put("freight", freight.doubleValue());
                return map;
            }
        }
        if (goodsInfo.getTransportId() != null && goodsInfo.getTransportId() != 0) {
            freight = buyFreight(goodsInfo.getTransportId(), specId, cityName, goodsNum);
        }
        map.put("freight", freight.doubleValue());
        return map;
    }

    private BigDecimal buyFreight(Integer transport, Integer specId, String cityName, Integer goodsNum) {
        if (transport != 0) {
            try {
                SpecVo specVo = goodsToDao.findSpec(specId);
                if (specVo.getWeight() != 0.0) {
                    //根据模板id得到对应的和省名运费
                    FreightVo freightVo = goodsToDao.findTransport(transport, cityName);
                    if (freightVo == null) {
                        freightVo = goodsToDao.findTransport(transport, "全国");
                    }
                    if (freightVo != null) {
                        Double weight = null;
                        if ("1".equals(freightVo.getShippingType())) {
                            weight = specVo.getWeight() * goodsNum;
                        } else {
                            weight = specVo.getVolume() * goodsNum;
                        }
                        if (weight - freightVo.getSnum() > 0) {
                            BigDecimal weight1 = BigDecimal.valueOf(weight).subtract(BigDecimal.valueOf(freightVo.getSnum()));
                            BigDecimal weight2 = weight1.divide(BigDecimal.valueOf(freightVo.getXnum()));
                            weight2 = weight2.setScale(0, BigDecimal.ROUND_UP);
                            return BigDecimal.valueOf(freightVo.getSprice() + weight2.doubleValue() * freightVo.getXprice());
                        } else {
                            return BigDecimal.valueOf(freightVo.getSprice());
                        }
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return BigDecimal.valueOf(0.0);
    }

    /**
     * 获取订单中的运费
     *
     * @param cityName
     * @param cartIds
     * @param cGoodsId
     * @param cPrice
     * @param orderAmounts
     * @param goodsAmount
     * @param cartVoList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal getOrderFreight(
            String cityName,
            String cartIds,
            Integer cGoodsId,
            Double cPrice,
            List<OrderAmount> orderAmounts,
            BigDecimal goodsAmount,
            List<CartVo> cartVoList) {

        return getFreight(cartIds, cityName, cartVoList, orderAmounts, cGoodsId, cPrice, goodsAmount);
    }

    /**
     * @param cartIds
     * @param couponId
     * @param cartVos
     * @param orderAmounts
     * @return
     * @throws Exception
     * @throws
     * @AUTHOR HUANGL
     * 得到购物车的运费
     */
    @Override
    public Map<String, Object> getGoodsFreight(
            String cityName,
            String cartIds,
            Integer couponId,
            List<CartVo> cartVos,
            List<OrderAmount> orderAmounts) {

        Integer cGoodsId = null;
        Double cPrice = null;

        BigDecimal goodsAmount = BigDecimal.valueOf(0);
        for (OrderAmount oa : orderAmounts) {
            goodsAmount = goodsAmount.add(oa.getGoodsPrice());
        }
        BigDecimal freight = getFreight(cartIds, cityName, cartVos, orderAmounts, cGoodsId, cPrice, goodsAmount);
        Map<String, Object> map = new HashMap<>();
        map.put("freight", freight.doubleValue());
        return map;
    }

    /**
     * 计算购物车运费
     *
     * @param cartIds
     * @param cityName
     * @param cartVoList
     * @param oaList
     * @param cGoodsId
     * @param cPrice
     * @param goodsAmount
     * @return
     */
    private BigDecimal getFreight(
            String cartIds,
            String cityName,
            List<CartVo> cartVoList,
            List<OrderAmount> oaList,
            Integer cGoodsId,
            Double cPrice,
            BigDecimal goodsAmount) {

        BigDecimal freight = BigDecimal.valueOf(0);
        String type = "1";
        List<FreightVo> freightVos = goodsToDao.findTransportByCardId(cartIds.split(","), cityName, type);
        if (freightVos.size() == 0) {
            freightVos = goodsToDao.findTransportByCardId(cartIds.split(","), "全国", type);
        }
        type = "2";
        List<FreightVo> freightVos1 = goodsToDao.findTransportByCardId(cartIds.split(","), cityName, type);
        if (freightVos1.size() == 0) {
            freightVos1 = goodsToDao.findTransportByCardId(cartIds.split(","), "全国", type);
        }
        double weight = 0.0;
        double TWeight = 0.0;
        double volume = 0.0;
        double TVolume = 0.0;
        for (CartVo goodinfo : cartVoList) {
            for (OrderAmount oa : oaList) {
                if (goodinfo.getGoodsId() == oa.getGoodsId()) {
                    BigDecimal gp = oa.getGoodsPrice();

                    if (cGoodsId != null && cPrice != null) {
                        goodsAmount = goodsAmount.subtract(BigDecimal.valueOf(cPrice));
                        if (cGoodsId.equals(goodinfo.getGoodsId())) {
                            gp = gp.subtract(BigDecimal.valueOf(cPrice));
                        }
                    }
                    BigDecimal gPrice = goodsAmount.multiply(gp).divide(goodsAmount, 5, BigDecimal.ROUND_HALF_DOWN);
                    if (goodinfo.getFullFreeMail() != null && goodinfo.getFullFreeMail() != 0) {
                        if (gPrice.doubleValue() >= goodinfo.getFullFreeMail()) {
                            continue;
                        }
                    }
                    if (goodinfo.getTransportId() == 0 || goodinfo.getTransportId() == null) {
                        continue;
                    }
                    FreightVo freightVo = goodsToDao.findTransport(goodinfo.getTransportId(), cityName);
                    if (freightVo == null) {
                        freightVo = goodsToDao.findTransport(goodinfo.getTransportId(), "全国");
                    }
                    if (freightVo != null) {
                        if ("1".equals(freightVo.getShippingType())) {
                            weight += goodinfo.getWeight() * goodinfo.getGoodsNum();
                        } else {
                            volume += goodinfo.getVolume() * goodinfo.getGoodsNum();
                        }
                    }
                }
            }
        }
        freight = freight(freightVos, weight, volume, freight);
        freight = freight(freightVos1, TWeight, TVolume, freight);
        return freight;
    }

    /**
     * 计算运费
     *
     * @param freightVos
     * @param weight
     * @param volume
     * @param freight
     * @return
     */
    private BigDecimal freight(List<FreightVo> freightVos, double weight, double volume, BigDecimal freight) {
        for (FreightVo f : freightVos) {
            Double hg = 0.0;
            if (("1").equals(f.getShippingType())) {
                hg = weight;
            } else {
                hg = volume;
            }
            if (hg != 0.0) {
                if (hg - f.getSnum() > 0) {
                    BigDecimal weight1 = BigDecimal.valueOf(hg).subtract(BigDecimal.valueOf(f.getSnum()));
                    BigDecimal weight2 = weight1.divide(BigDecimal.valueOf(f.getXnum()));
                    weight2 = weight2.setScale(0, BigDecimal.ROUND_UP);
                    freight = freight.add(BigDecimal.valueOf(f.getSprice() + weight2.doubleValue() * f.getXprice()));
                } else {
                    freight = freight.add(BigDecimal.valueOf(f.getSprice()));
                }
            }
        }
        return freight;
    }

    /**
     * @AUTHOR HUANGL
     * 得到商品详情的运费
     */
    @Override
    public Map<String, Object> findGoodsFreight(int goodsId, String cityName) {
        BigDecimal freight = BigDecimal.valueOf(0);
        //根据商品id得到字段是否免邮
        //		  int transport=goodsToDao.getTransport(goodsId);
        GoodsInfoVo goodsInfo = goodsToDao.getGoodsInfo(goodsId);
        if (goodsInfo.getTransportId() != 0) {
            //根据模板id得到对应的和省名运费
            FreightVo freightVo = goodsToDao.findTransport(goodsInfo.getTransportId(), cityName);
            if (freightVo == null) {
                freightVo = goodsToDao.findTransport(goodsInfo.getTransportId(), "全国");
            }
            if (freightVo != null) {
                freight = BigDecimal.valueOf(freightVo.getSprice());
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("freight", freight.doubleValue());
        return result;
    }

    /**
     * @return TODO
     * @AUTHOR HUANGL
     * 规格专用 APP or PC
     */
    public Map<String, Object> analysisJSON(List<SpecVo> list) {
        List<Map<String, Object>> mapList = new LinkedList<>();
        List<Map<String, Object>> maplist1 = new LinkedList<>();
        List<Map<String, Object>> maplist2 = new LinkedList<>();
        List<Map<String, Object>> maplist4 = new LinkedList<>();
        List<Map<String, Object>> maplist5 = new LinkedList<>();
        int sl = 0;

        for (SpecVo specVo : list) {
            Map<String, Object> specGoodsId = new HashMap<String, Object>();
            //加入购物车的规格id
            specGoodsId.put("specGoodsId", specVo.getSpecGoodsId());
            String s = specVo.getSpecName();
            s = s.replace("{", "").replace("}", "");
            String sp = specVo.getSpecGoodsSpec();
            sp = sp.replace("{", "").replace("}", "");
            String[] ss = s.split(",");
            sl = ss.length;
            String[] sps = sp.split(",");
            String pid = "0";
            StringBuilder gsId = new StringBuilder();
            for (int j = 0; j < sl; j++) {
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> map1 = new HashMap<>();
                String[] sStr = ss[j].split(":");
                String[] spStr = sps[j].split(":");
                map.put("name", sStr[1].substring(1, sStr[1].length() - 1));
                map.put("leven", j);
                map1.put("specName", spStr[1].substring(1, spStr[1].length() - 1));
                map1.put("specId", spStr[0].substring(1, spStr[0].length() - 1));

                map1.put("specGoodsPrice", specVo.getSpecGoodsPrice());
                map1.put("specGoodsVipPrice", specVo.getSpecGoodsVipPrice());
                map1.put("specGoodsPurchasePrice", specVo.getSpecGoodsPurchasePrice());

                // 计算提成
                map1.put("commission", computeCommission.compute(specVo.getCommission()));

                map1.put("specSalenum", specVo.getSpecSalenum());
                map1.put("specGoodsStorage", specVo.getSpecGoodsStorage());
                map1.put("pid", pid);
                map1.put("isActivity", 0);
                map1.put("spName", sStr[1].substring(1, sStr[1].length() - 1));
                pid = spStr[0].substring(1, spStr[0].length() - 1);
                mapList.add(map);
                if (gsId.length() > 0) {
                    gsId.append(",");
                }
                gsId.append(spStr[0], 1, spStr[0].length() - 1);
                boolean flag = true;
                if (j != sl - 1) {
                    for (Map<String, Object> oMap : maplist4) {
                        if (!oMap.isEmpty()) {
                            if (oMap.get("specId").equals(map1.get("specId"))) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                if (!flag) {
                    map1 = new HashMap<>();
                }
                maplist4.add(map1);
            }
            specGoodsId.put("gsId", gsId);
            maplist5.add(specGoodsId);
        }
        int item = 0;
        for (int l = 0; l < sl; l++) {
            Map<String, Object> map3 = new HashMap<>();
            map3.put("leven", item);
            map3.put("name", "");
            maplist2 = new ArrayList<>();
            int size = 0;
            for (int h = 0; h < mapList.size(); h++) {
                Integer leven = (int) mapList.get(h).get("leven");
                if (leven == item) {
                    if (!maplist4.get(h).isEmpty()) {
                        maplist2.add(maplist4.get(h));
                        String name = (String) map3.get("name");
                        if (name == "") {
                            map3.put("name", mapList.get(h).get("name"));
                        }
                    }
                }
                if (size == (mapList.size() - 1)) {
                    item++;
                }
                size++;
            }
            map3.put("specList", maplist2);
            maplist1.add(map3);
        }

        Map<String, Object> map10 = new HashMap<>(2);
        map10.put("spec", maplist1);
        map10.put("goodsSpec", maplist5);
        return map10;
    }

    /**
     * 优化过后的规格使用
     *
     * @return
     * @Author huangl
     */
    public static String specIndex(List<SpecVo> list) {
        String jsonStr1 = "{\"1\":\"颜色\",\"2\":\"内存\",\"3\":\"CPU\"}";
        String jsonStr2 = "{\"319\":\"red\",\"315\":\"32G\",\"317\":\"1000CPU\"}";
        try {
            List<String> superArray = getValueArray(jsonStr1);
            List<String> childArray = getValueArray(jsonStr2);
            for (int i = 0; i < superArray.size(); i++) {
                System.out.println(superArray.get(i) + " - " + childArray.get(i));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    public static List<String> getValueArray(String jsonStr) throws Exception {
        List<String> result = new ArrayList<String>();
        Iterator<Entry<String, JsonNode>> tmp = trans(jsonStr);
        while (tmp.hasNext()) {
            Entry<String, JsonNode> node = tmp.next();
            result.add(node.getValue().asText());
        }
        return result;
    }

    public static Iterator<Entry<String, JsonNode>> trans(String jsonStr) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonStr).fields();
    }

    /**
     * 拼接服务申请
     *
     * @return
     */
    @Override
    public String getApplyForOdd() {
        String returnStr = "";
        try {
            /*
             * 返回数据组装
             */
            String tempStr = "";

            System.out.println("最大订单号" + tempStr);
            String yd = "";
            /*
             * 当天日期组合
             */
            Calendar c = Calendar.getInstance();
            // 年
            yd = String.valueOf(c.get(Calendar.YEAR));
            // 月
            String month = String.valueOf(c.get(Calendar.MONTH) + 1);
            // 日
            String date = String.valueOf(c.get(Calendar.DATE));

            yd = month.length() >= 2 ? yd + month : yd + "0" + month;
            yd = date.length() >= 2 ? yd + date : yd + "0" + date;

            if (tempStr.length() > 0) {
                String code = tempStr;

                // 年月日
                String d = code.substring(2, 10);
                if (d.equals(yd)) {
                    // 当天已有过编码
                    String s = code.substring(10);
                    int i = Integer.parseInt(s);
                    s = String.valueOf(++i);

                    if (s.length() == 1) {
                        s = "0000" + s;
                    } else if (s.length() == 2) {
                        s = "000" + s;
                    } else if (s.length() == 3) {
                        s = "00" + s;
                    } else if (s.length() == 4) {
                        s = "0" + s;
                    }
                    returnStr = d + s;
                } else {
                    returnStr = yd + "00001";
                }
            } else {
                returnStr = yd + "00001";
            }

            System.out.println(" ****** orderCode ******   " + returnStr);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        // returnStr = "ZU" + returnStr; //测试环境
        // 生产环境
        returnStr = "SQ" + returnStr;
        return returnStr;
    }

    @Override
    public List<SshtVo> findSshtList(int actId, int goodsId, int start, int rows) {
        List<SshtVo> sshtVos = goodsToDao.findSshtList(actId, goodsId, start, rows);
        if (sshtVos.size() > 0) {
            for (SshtVo sshtVo : sshtVos) {
                if (StringUtils.isNotEmpty(sshtVo.getUserIcon())) {
                    sshtVo.setUserIcon(Const.PRO_URL + sshtVo.getUserIcon());
                }
                List<SshtVo> huifu = goodsToDao.findBySsPid(sshtVo.getId());
                sshtVo.setHtReply(huifu);
            }
        }
        return sshtVos;
    }

    @Override
    public Boolean addSsht(SshtVo ssht) {
        ssht.setTime(System.currentTimeMillis());
        goodsToDao.addSsht(ssht);
        return true;
    }

    @Override
    public GoodsWVo findGoodsByIdWT(int goodsId) {
        return goodsToDao.findGoodsByIdWT(goodsId);
    }



    @Override
    public Map<String, Object> getReserveAndNew() {
        List<Map<String, Object>> maps = goodsToDao.getReserveAndNew();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < maps.size(); i++) {
            if ((long) maps.get(i).get("apId") == 19) {
                map.put("reserveImg", Const.PRO_URL + maps.get(i).get("resUrl"));
                continue;
            } else {
                map.put("newGoodsImg", Const.PRO_URL + maps.get(i).get("resUrl"));
                continue;
            }
        }
        return map;
    }

    @Override
    public String getSpecGoodsSpec(Integer goodsSpecId) {
        String json = goodsToDao.getSpecGoodsSpec(goodsSpecId);
        Map<String, String> map = (Map<String, String>) JSON.parse(json);
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        StringBuilder s = new StringBuilder();
        boolean b = false;
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            if (b) {
                s.append(":").append(entry.getValue());
                continue;
            }
            s.append(entry.getValue());
            b = true;
        }
        return s.toString();
    }


    @Override
    public GoodsInfoVo getGoodsInfo(Integer goodsId) {
        return goodsToDao.getGoodsInfo(goodsId);
    }

    @Override
    public List<GoodsListVo> getRecommend(String type) {
        List<GoodsListVo> goodsListVos = goodsToDao.getRecommend(type);
        return goodsListVos;
    }

    @Override
    public List<GoodsListVo> getParentClassList(int parentId) {
        List<GoodsListVo> parentClassList = goodsToDao.getParentClassList(parentId);
        return parentClassList;
    }


    @Override
    public Map<String, Object> getGoodsClass() {
        Map<String, Object> result = Maps.newHashMap();
        List<ClassGoodsRecommend> classGoodsRecommend = new ArrayList<>();
        List<GoodsListVo> recommend = goodsToDao.getRecommend("1");
        if (recommend != null) {
            recommend.forEach(goodsListVo -> {
                ClassGoodsRecommend cg = new ClassGoodsRecommend();
                cg.setGoodsId(goodsListVo.getGoodsId());
                cg.setGoodsName(goodsListVo.getGoodsName());
                cg.setGoodsImage(goodsListVo.getGoodsImage());
                classGoodsRecommend.add(cg);
            });
        }
        result.put("classRecommend", classGoodsRecommend);
        Map<String, Object> listGoodsClass = this.getListGoodsClass();
        result.put("classList", listGoodsClass.get("goodsClassList"));
        return result;
    }

    @Override
    public List<GoodsListVo> getGoodsRecommend(String type, int page, int rows) {
        return goodsToDao.getGoodsRecommend(type, page, rows);
    }
}