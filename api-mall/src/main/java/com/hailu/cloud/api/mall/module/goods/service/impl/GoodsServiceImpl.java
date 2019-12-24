package com.hailu.cloud.api.mall.module.goods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.hailu.cloud.api.mall.module.goods.dao.GoodsMapper;
import com.hailu.cloud.api.mall.module.goods.dao.GoodsToMapper;
import com.hailu.cloud.api.mall.module.goods.entity.Brand;
import com.hailu.cloud.api.mall.module.goods.entity.Spec;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsParameterVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.*;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderGoods;
import com.hailu.cloud.api.mall.module.goods.service.IComputeCommission;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsService;
import com.hailu.cloud.api.mall.module.goods.tool.HtmlReplace;
import com.hailu.cloud.api.mall.module.goods.vo.*;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Resource
    private GoodsMapper goodsDao;
    @Resource
    private GoodsToMapper goodsToDao;
    @Autowired
    private IComputeCommission computeCommission;

    /**
     * @author Administrator 购买商品提问题
     */
    @Override
    public void addAskReply(AskReplyAnswerVo askReplyAnswerVo) throws Exception {
        goodsDao.addAskReply(askReplyAnswerVo);
    }

    /**
     * @author 黄亮
     * 我的问题列表
     * //Type == 0:我的提问列表；Type== 1：我的回答的问题列表
     */
    @Override
    public List<AskReplyQuestionVo> myAskReply(String userId, int type, int page, int rows) throws Exception {
        List<AskReplyQuestionVo> myQuestionList = null;
        if (type == 0) {
            // 得到问题及商品信息
            myQuestionList = goodsDao.myQuestion(userId, page, rows);
        }
        if (type == 1) {
            // 得到我的回答问题列表并进行时间排序
            //订单的购买时间只要大于time 就可以查询出来他购买的商品相应的问题
            long time = System.currentTimeMillis();
            long stime = 90 * 24;
            stime = stime * 3600000;
            time = time - stime;
            myQuestionList = goodsDao.allQuestionBymyAnswer(userId, page, rows, time);
        }
        if (myQuestionList.size() > 0) {
            for (AskReplyQuestionVo ask : myQuestionList) {
                if (StringUtils.isNotEmpty(ask.getGoodsImg()) && !("http").equals(ask.getGoodsImg().substring(0, 4))) {
                    ask.setGoodsImg(ask.getGoodsImg());
                }
            }
        }
        return myQuestionList;
    }

    /**
     * @author 黄亮
     * 商品评论列表
     * @see
     */
    @Override
    public Map<String, Object> goodsAllEvaluate(int goodsId, int page, int rows) {
        // 得到问题及商品信息
        List<GoodsEvaluateTO> list = goodsDao.goodsAllEevaluate(goodsId, page, rows);
        int count = goodsDao.getCountEvaluate(goodsId);
        return ImmutableMap.of("goodsEvaluate", list, "evaluate", count);
    }

    /**
     * @author 黄亮
     * 判断是否可以评价
     * (non-Javadoc)
     * @see IGoodsService#isBought(OrderGoods)
     */
    @Override
    public Boolean isBought(OrderGoods orderGoods) throws Exception {
        int list = goodsDao.isBought(orderGoods);
        if (list == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @author 黄亮
     * 根据问题id得到对应的回答
     */
    @Override
    public List<AskReplyVo> myQuestAllAnswers(int askReplyId, int page, int rows) {
        List<AskReplyVo> askReply = goodsDao.myQuestAllAnswers(askReplyId, page, rows);
        if (askReply.size() > 0) {
            for (AskReplyVo askReplyVo : askReply) {
                if (StringUtils.isNotEmpty(askReplyVo.getUserImg()) && !("http").equals(askReplyVo.getUserImg().substring(0, 4))) {
                    askReplyVo.setUserImg(askReplyVo.getUserImg());
                }
            }
        }
        return askReply;
    }

    /**
     * @author 黄亮
     * 根据商品id得到对应的问答
     */
    @Override
    public List<AskReplyQuestionVo> findAskByGoodsId(int goodsId, int page, int rows) {
        //先得到问题
        List<AskReplyQuestionVo> askreply = goodsDao.getIssue(goodsId, page, rows);
        if (askreply.size() > 0) {
            for (AskReplyQuestionVo ask : askreply) {
                if (StringUtils.isNotEmpty(ask.getGoodsImg()) && !("http").equals(ask.getGoodsImg().substring(0, 4))) {
                    ask.setGoodsImg(ask.getGoodsImg());
                }
                if (StringUtils.isNotEmpty(ask.getUserImg()) && !("http").equals(ask.getUserImg().substring(0, 4))) {
                    ask.setUserImg(ask.getUserImg());
                }
                //得到问题的最新一条回答
                List<AskReplyVo> askReply = goodsDao.myQuestAllAnswers(ask.getAskReplyId(), 0, 1);
                if (askReply.size() > 0) {
                    for (AskReplyVo askReplyVo : askReply) {
                        if (StringUtils.isNotEmpty(askReplyVo.getUserImg()) && !("http").equals(askReplyVo.getUserImg().substring(0, 4))) {
                            askReplyVo.setUserImg(askReplyVo.getUserImg());
                        }
                    }
                }
                ask.setAskReply(askReply);
            }
        }
        return askreply;
    }

    @Override
    public Map<String, Object> verifySearchGoods(SearchGoodsParam searchGoodsParam) {
        Map<String, Object> result = Maps.newHashMap();
        if (StringUtils.isNotBlank(searchGoodsParam.getName())) {
            this.verifyHotByName(searchGoodsParam.getName().trim());
        }
        List<GoodsListVo> goodsListVos = goodsDao.findSearchGoods(searchGoodsParam);
        result.put("goodsList", goodsListVos);
        List<Integer> integers = goodsDao.findGroupGcId(searchGoodsParam);
        List<Brand> brands = new ArrayList<>();
        List<Spec> specs = new ArrayList<>();
        if (integers != null && integers.size() > 0) {
            brands = goodsDao.findBrandByGcIds(integers);
            specs = goodsDao.findSpecByGcIds(integers);
        }
        result.put("brands", brands);
        result.put("specs", specs);
        return result;
    }

    /**
     * 添加搜索记录
     *
     * @param hotName
     */
    @Transactional(rollbackFor = Exception.class)
    protected void verifyHotByName(String hotName) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public GoodsInfoTo verifyGoodsInfo(Integer goodsId, Integer goodsSpecId, Integer type, String userId) {
        GoodsInfoTo goodsInfo = goodsToDao.findGoodsInfo(goodsId);
        if (goodsInfo != null) {
            Integer[] actType = new Integer[2];
            actType[0] = 2;
            //得到商品规格
            List<SpecVo> spec = goodsToDao.getSpecList(goodsId);
            if (1 == type) {
                actType[1] = 1;
            }
            List<ActGoodsPriceVo> res = goodsToDao.getActivity(goodsId, actType);
            if (spec.size() > 0) {
                Map<String, Object> map = analysisJson(spec, res);
                goodsInfo.setSpec((List<Map<String, Object>>) map.get("spec"));
                goodsInfo.setSpecGoods((List<Map<String, Object>>) map.get("goodsSpec"));
            }
            goodsInfo.setEvaluateTOS(goodsDao.goodsAllEevaluate(goodsInfo.getGoodsId(), 0, 1));
            List<GoodsParameterVo> goodsParameterVos = goodsToDao.goodsParameters(goodsId);
            goodsParameterVos.forEach(item -> {
                boolean containsHtml = item.getParameterValue().contains("&le;") ||
                        item.getParameterValue().contains("&ge;") ||
                        item.getParameterValue().contains("&plusmn;");
                if (containsHtml) {
                    String parameterValue = HtmlReplace.htmlReplace(item.getParameterValue());
                    item.setParameterValue(parameterValue);
                    goodsInfo.setGoodsParameter(goodsParameterVos);
                } else {
                    goodsInfo.setGoodsParameter(goodsParameterVos);
                }
            });
        }
        return goodsInfo;
    }

    /**
     * 计算规格
     * TODO 这块可能是个重构的大坑
     *
     * @param list    规格list
     * @param actList 活动list
     * @return
     */
    private static Map<String, Object> analysisJson(List<SpecVo> list, List<ActGoodsPriceVo> actList) {
        try {
            List<Map<String, Object>> mapList = new LinkedList<>();
            List<Map<String, Object>> maplist1 = new LinkedList<>();
            List<Map<String, Object>> maplist2;
            List<Map<String, Object>> maplist4 = new LinkedList<>();
            List<Map<String, Object>> maplist5 = new LinkedList<>();
            int sl = 0;
            for (SpecVo specVo : list) {
                Map<String, Object> specGoodsId = new HashMap<>(10);
                specGoodsId.put("goodsSpecId", specVo.getSpecGoodsId());
                BigDecimal specGoodsPrice = specVo.getSpecGoodsPrice();
                Integer specSalenum = specVo.getSpecSalenum();
                Integer specStorage = specVo.getSpecGoodsStorage();
                Long actRemainingTime = null;

                if (StringUtils.isNotEmpty(specVo.getSpecName())) {
                    String s = specVo.getSpecName();
                    s = s.replace("{", "").replace("}", "");
                    String sp = specVo.getSpecGoodsSpec();
                    sp = sp.replace("{", "").replace("}", "");
                    String[] ss = s.split(",");
                    sl = ss.length;
                    String[] sps = sp.split(",");
                    StringBuilder gsId = new StringBuilder();
                    String specInfo = "";
                    for (int j = 0; j < sl; j++) {
                        Map<String, Object> map = new HashMap<>(10);
                        Map<String, Object> map1 = new HashMap<>(10);
                        String[] sStr = ss[j].split(":");
                        String[] spStr = sps[j].split(":");
                        String specId = spStr[0].substring(1, spStr[0].length() - 1);
                        map.put("name", sStr[1].substring(1, sStr[1].length() - 1));
                        map.put("leven", j);
                        String name = spStr[1].substring(1, spStr[1].length() - 1);
                        map1.put("specName", name);
                        map1.put("specId", specId);
                        map1.put("specGoodsStorage", specVo.getSpecGoodsStorage());
                        mapList.add(map);
                        if (StringUtils.isEmpty(gsId.toString())) {
                            gsId = new StringBuilder(specId);
                        } else {
                            gsId.append(",").append(specId);
                        }
                        if (StringUtils.isEmpty(specInfo)) {
                            specInfo = name;
                        } else {
                            specInfo = specInfo + "," + name;
                        }
                        boolean flag = true;
                        for (Map<String, Object> oMap : maplist4) {
                            if (oMap.isEmpty()) {
                                continue;
                            }
                            if (oMap.get("specId").equals(map1.get("specId"))) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            map1 = new HashMap<>(10);
                        }
                        maplist4.add(map1);
                    }
                    specGoodsId.put("specInfo", specInfo);
                    specGoodsId.put("actRemainingTime", actRemainingTime);
                    specGoodsId.put("specGoodsPrice", specGoodsPrice);
                    specGoodsId.put("originalPrice", specGoodsPrice);
                    specGoodsId.put("specSalenum", specSalenum);
                    specGoodsId.put("specGoodsStorage", specStorage);
                    specGoodsId.put("gsId", gsId.toString());
                    specGoodsId.put("specGoodsSerial", specVo.getSpecGoodsSerial());
                    maplist5.add(specGoodsId);
                }
            }
            int item = 0;
            for (int l = 0; l < sl; l++) {
                Map<String, Object> map3 = new HashMap<>(10);
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
                            if (StringUtils.isEmpty(name)) {
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
            return ImmutableMap.of("spec", maplist1, "goodsSpec", maplist5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询推广产品
     *
     * @return
     */
    @Override
    public PageInfoModel<List<GoodsAndGoodsSpecVo>> findGoodsList(String userid, Integer page, Integer size) {
        MemberLoginInfoModel loginInfoModel = null;
        if (RequestUtils.getAuthInfo().getLoginType() == 0) {
            loginInfoModel = RequestUtils.getMemberLoginInfo();
            if (loginInfoModel.getMerchantType() == 0) {
                return null;
            }
        }
        //添加分页
        Page pageData = PageHelper.startPage(page, size);
        List<GoodsAndGoodsSpecVo> goods = goodsDao.findGoodsList();
        MemberLoginInfoModel finalLoginInfoModel = loginInfoModel;
        goods.forEach(vo -> vo.setCommission(computeCommission.compute(vo.getCommission(), finalLoginInfoModel.getMerchantType())));
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), goods);
    }
}
