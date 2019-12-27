package com.hailu.cloud.api.mall.module.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.hailu.cloud.api.mall.module.goods.dao.GoodsMapper;
import com.hailu.cloud.api.mall.module.goods.dao.GoodsToMapper;
import com.hailu.cloud.api.mall.module.goods.dao.OrderMapper;
import com.hailu.cloud.api.mall.module.goods.entity.SerialNumber;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsInfoVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsListVo;
import com.hailu.cloud.api.mall.module.goods.entity.order.*;
import com.hailu.cloud.api.mall.module.goods.enums.SerialEnum;
import com.hailu.cloud.api.mall.module.goods.service.IComputeCommission;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsToService;
import com.hailu.cloud.api.mall.module.goods.service.IOrderService;
import com.hailu.cloud.api.mall.module.goods.service.ISerialNumberService;
import com.hailu.cloud.api.mall.module.goods.vo.*;
import com.hailu.cloud.api.mall.module.payment.vo.OrderPay;
import com.hailu.cloud.api.mall.module.payment.vo.OrderToPay;
import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import com.hailu.cloud.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 黄亮  E-mail 1428516543@qq.com
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderMapper orderDao;
    @Autowired
    private IGoodsToService goodsToService;
    @Resource
    private GoodsToMapper goodsToDao;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserInfoMapper userInfoDao;
    @Autowired
    private ISerialNumberService serialNumberService;
    @Autowired
    private RedisStandAloneClient redisKit;

    @Autowired
    private IComputeCommission computeCommission;

    @Autowired
    private RedisStandAloneClient redisClient;

    private Gson gson = new Gson();

    @Override
    public void addShoppingCart(ShoppingCartVo shoppingCartVo) {
        orderDao.addShoppingCart(shoppingCartVo);
    }

    @Override
    public List<ShoppingCartVo> shoppingList(String userId) throws Exception {
        List<ShoppingCartVo> shoppingCarts = orderDao.shoppingList(userId);
        if (shoppingCarts.size() > 0) {
            Set<Integer> goodsIds = new HashSet<>(shoppingCarts.size());
            Set<Integer> specIds = new HashSet<>(shoppingCarts.size());
            shoppingCarts.forEach(vo -> {
                goodsIds.add(vo.getGoodsId());
                specIds.add(vo.getSpecId());
            });
            List<GoodsSpec> specs = orderDao.shoppingListPrice(goodsIds, specIds);
            specs.forEach(spec -> {
                for (ShoppingCartVo shoppingCart : shoppingCarts) {
                    boolean eq = shoppingCart.getGoodsId() == spec.getGoodsId() &&
                            shoppingCart.getSpecId() == spec.getGoodsSpecId();
                    if (eq) {
                        shoppingCart.setPrice(spec.getSpecGoodsPrice());
                        shoppingCart.setSpecGoodsVipPrice(spec.getSpecGoodsVipPrice());
                        shoppingCart.setSpecGoodsPurchasePrice(spec.getSpecGoodsPurchasePrice());
                        // 计算提成
                        shoppingCart.setCommission(computeCommission.compute(spec.getCommission(), RequestUtils.getMemberLoginInfo().getMerchantType()));
                        shoppingCart.setIsPopularize(spec.getIsPopularize());
                        break;
                    }
                }
            });
        }
        shoppingCarts.stream()
                .filter(item -> StringUtils.isBlank(item.getShopName()))
                .forEach(item -> item.setShopName("海露物联云商"));
        return shoppingCarts;
    }

    @Override
    public void updateShoppingCart(ShoppingCartVo shoppingCartVo) throws Exception {
        orderDao.updateShoppingCart(shoppingCartVo);
    }

    @Override
    public List<ShoppingAddressVo> shoppingAddressVoList(String userId) throws Exception {
        return orderDao.shoppingAddressVoList(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addShoppingAddress(ShoppingAddressVo savo) {
        Map<String, Object> responseData = new HashMap<>(10);
        try {
            responseData = updateShoppingAddress(responseData, savo);

            orderDao.addShoppingAddress(savo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return responseData;
    }

    @Transactional(rollbackFor = Exception.class)
    protected Map<String, Object> updateShoppingAddress(Map<String, Object> responseData, ShoppingAddressVo savo) throws BusinessException {
        if (savo.getArea().length() > 100) {
            throw new BusinessException("详细地址填写字数不能超过100字！");
        }
        if (savo.getAddress().length() > 100) {
            throw new BusinessException("地址填写字数不能超过100字！");
        }
        if (0 == savo.getStatus()) {
            ShoppingAddressVo shoppingAddress1 = orderDao.shoppingAddressVo(savo.getUserId());
            if (shoppingAddress1 != null) {
                shoppingAddress1.setStatus(1);
                orderDao.updataShoppingAddress(shoppingAddress1);
            }
        }
        return responseData;
    }

    @Override
    public Map<String, Object> updataShoppingAddress(ShoppingAddressVo shoppingAddressVo) {
        Map<String, Object> responseData = new HashMap<>(10);
        try {
            responseData = updateShoppingAddress(responseData, shoppingAddressVo);

            orderDao.updataShoppingAddress(shoppingAddressVo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return responseData;

    }

    @Override
    public void delShoppingAddress(int id) throws Exception {
        orderDao.delShoppingAddress(id);
    }


    @Override
    public List<RegionVo> regionList(int pid) throws Exception {
        String region = redisKit.stringGet(Constant.REDIS_NATION_CACHE_OLD + pid);
        if (StringUtils.isBlank(region)) {
            List<RegionVo> list = orderDao.regionList(pid);
            redisKit.stringSet(Constant.REDIS_NATION_CACHE_OLD + pid, JSONArray.toJSONString(list));
            return list;
        }
        return JSON.parseArray(region, RegionVo.class);
    }



    @Override
    public List<OrderListVo> getOrdersList(String userId, Integer orderStatus, Integer evaluateState, int page, int row) {
        return getOrdersList(userId, orderStatus, evaluateState, page, row,null);
    }

    @Override
    public List<OrderListVo> getOrdersList(String userId, Integer orderStatus, Integer evaluateState, int page, int row, Integer isShare) {
        List<OrderListVo> orders = orderDao.getOrdersList(userId, orderStatus, evaluateState, page, row,isShare);
        if (orders.size() > 0) {
            for (int i = 0; i < orders.size(); i++) {
                OrderListVo ol = orders.get(i);
                List<OrderGoods> og = this.findByOrderId(ol.getOrderId());
                if (og.size() > 0) {
                    for (OrderGoods orderGoods : og) {
                        if (StringUtils.isNotEmpty(orderGoods.getGoodsImage()) && !("http").equals(orderGoods.getGoodsImage().substring(0, 4))) {
                            orderGoods.setGoodsImage(orderGoods.getGoodsImage());
                        }
                        List<GoodsCompl> goodsCompl = this.getGoodsCompl(orderGoods.getRecId());
                        orderGoods.setGoodsClmpl(goodsCompl);
                    }
                }
                ol.setOrderGoods(og);
            }
        }
        return orders;
    }

    @Override
    public ShoppingCartVo getShoppingCartByVo(ShoppingCartVo shoppingCartVo) throws Exception {
        return orderDao.getShoppingCartByVo(shoppingCartVo);
    }

    @Override
    public ShoppingCartVo getShoppingCartById(int cartId) throws Exception {
        return orderDao.getShoppingCartById(cartId);
    }

    @Override
    public void deleteOrder(int orderId) throws Exception {
        orderDao.deleteOrder(orderId);
    }

    @Override
    public List<OrderServiceVo> getOrderServices(OrderServiceVo orderServiceVo) throws Exception {

        return orderDao.getOrderServices(orderServiceVo);
    }

    @Override
    public void addOrderService(OrderServiceVo orderServiceVo) throws Exception {
        orderDao.addOrderService(orderServiceVo);
    }

    @Override
    public void updateOrderService(OrderServiceVo orderServiceVo) throws Exception {
        orderDao.updateOrderService(orderServiceVo);
    }

    @Override
    public void upOrderDetail(OrderDetailVo orderDetailVo) throws Exception {
        orderDao.upOrderDetail(orderDetailVo);
    }

    @Override
    public FreightVo getFreight(FreightVo freightVo) throws Exception {
        return orderDao.getFreight(freightVo);
    }


    /**
     * 得到金额
     *
     * @param listMap 返回金额list
     * @param oa      金额
     * @param actp    金额实体类
     */
    private void calculationAmount(List<OrderAmount> listMap, OrderAmount oa, ActPriceVo actp) {
        OrderAmount amount = oa;
        amount.setPledgePrice(actp.getPledgePrice());
        amount.setReserveType(actp.getReserveType());
        amount.setOnePayPrice(actp.getOnePayPrice());
        amount.setTwoPayPrice(actp.getTwoPayPrice());
        listMap.add(amount);
    }

    /**
     * 不限购活动,正常商品
     *
     * @param result   返回值
     * @param actp     活动金额
     * @param listMap  返回的金额
     * @param oa       金额对象
     * @param goodsNum 商品数量
     * @param dis      优惠
     * @return 返回结果
     */
    private Map<String, Object> calculationAmount(Map<String, Object> result, ActPriceVo actp, List<OrderAmount> listMap, OrderAmount oa, Integer goodsNum, BigDecimal dis) throws BusinessException {
        //TODO
        int goodsStorage = actp.getRepertory() - goodsNum;
        if (goodsStorage < 0) {
            throw new BusinessException("该商品库存不足");
        }
        calculationAmount(actp, listMap, oa, goodsNum, dis);
        return result;
    }

    /**
     * 得到价格
     *
     * @param actp     价格
     * @param listMap  list
     * @param goodsNum 商品数量
     * @param dis      优惠
     */
    private void calculationAmount(ActPriceVo actp, List<OrderAmount> listMap, OrderAmount oa, Integer goodsNum, BigDecimal dis) {
        //TODO
        BigDecimal num = new BigDecimal(goodsNum);
        BigDecimal actPrice = new BigDecimal(actp.getActivityPrice());
        actPrice = actPrice.multiply(num);
        if (dis != null) {
            actPrice = actPrice.multiply(dis);
            actPrice = actPrice.divide(new BigDecimal(100), BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP);
        }
        if (actp.getReserveType() != null && actp.getReserveType() == 2) {
            BigDecimal onePay = new BigDecimal(actp.getOnePayPrice());
            actp.setOnePayPrice((onePay.multiply(num)).doubleValue());
            actp.setTwoPayPrice((actPrice.subtract(onePay)).doubleValue());
        }
        oa.setGoodsPrice(actPrice);
        calculationAmount(listMap, oa, actp);
    }


    /**
     * 计算购物车金额
     *
     * @param userId  用户id
     * @param cartIds 购物车ids
     * @return 返回对应状态
     */
    private Map<String, Object> calculationAmount(String userId, String cartIds) throws BusinessException {

        Map<String, Object> data = Maps.newHashMap();
        List<OrderAmount> listMap = new ArrayList<>();
        //用户信息
        ShopMember userInfo = userInfoDao.byIdFindUser(userId);
        BigDecimal availableRedEnvelope = BigDecimal.ZERO;
        BigDecimal availableIntegral = BigDecimal.ZERO;
        List<CartVo> shoppingCartVos = orderDao.getShoppingCartByIds(cartIds.split(","));
        for (CartVo cartVo : shoppingCartVos) {
            int goodsId = cartVo.getGoodsId();
            Goods goods = goodsMapper.findById(goodsId);
            //判断用户是否为服务商且改商品是否参与推销
            if (userInfo != null && goods != null && userInfo.getMerchantType() == 2 && goods.getIsPopularize() == 1) {
                //如果是服务商且改商品参与推销，则以供货价下单
                Map<String, Object> responseData = calculationAmount(goodsId, cartVo.getSpecId(), cartVo.getGoodsNum(), false);
                List<OrderAmount> orderAmounts = gson.fromJson(gson.toJson(responseData.get("listMap")), new TypeToken<List<OrderAmount>>() {
                }.getType());
                listMap.addAll(orderAmounts);
            } else {
                Map<String, Object> responseData = calculationAmount(goodsId, cartVo.getSpecId(), cartVo.getGoodsNum(), true);
                List<OrderAmount> orderAmounts = gson.fromJson(gson.toJson(responseData.get("listMap")), new TypeToken<List<OrderAmount>>() {
                }.getType());
                listMap.addAll(orderAmounts);
            }

        }
        data.put("listMap", listMap);
        data.put("availableRedEnvelope", availableRedEnvelope);
        data.put("availableIntegral", availableIntegral);
        redisKit.stringSet(userId + "ORDER", JSONObject.toJSON(listMap).toString(), 5 * Constant.HOUR);
        return data;
    }

    /**
     * 直接下单接口
     *
     * @param orderParam 订单参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addBuyOrder(OrderParam orderParam) {
        Map<String, Object> result = new HashMap<>(10);
        try {
            Map<String, Object> calculationAmount;

            //判断用户是否为服务商且改商品是否参与推销
            ShopMember userInfo = userInfoDao.byIdFindUser(orderParam.getUserId());
            Goods goods = goodsMapper.findById(orderParam.getGoodsId());
            if (userInfo != null && goods != null && userInfo.getMerchantType() == 2 && goods.getIsPopularize() == 1) {
                //如果是服务商且改商品参与推销，则以供货价下单
                calculationAmount = calculationAmount(orderParam.getGoodsId(), orderParam.getGoodsSpecId(), orderParam.getGoodsNum(), false);
            } else {
                calculationAmount = calculationAmount(orderParam.getGoodsId(), orderParam.getGoodsSpecId(), orderParam.getGoodsNum(), true);
            }

            OrderToVo order = new OrderToVo();
            List<OrderAmount> orderAmounts = gson.fromJson(gson.toJson(calculationAmount.get("listMap")), new TypeToken<List<OrderAmount>>() {
            }.getType());
            OrderAmount oa = orderAmounts.get(0);
            BigDecimal orderAmount = oa.getGoodsPrice();
            BigDecimal disAmount = BigDecimal.ZERO;

            result = updateGoodsRepertory(result, orderAmounts, orderParam);

            Map<String, Object> freight = goodsToService.getFreight(orderAmount.doubleValue(), orderParam.getCityName(), orderParam.getGoodsId(), orderParam.getGoodsNum(), orderParam.getGoodsSpecId(), orderParam.getType(), null);
            BigDecimal f = new BigDecimal(freight.get("freight").toString());
            if (oa.getOnePayPrice() > 0 && oa.getTwoPayPrice() > 0) {
                //第二阶段实付金额= 第二阶段应付金额+运费-优惠卷-健康豆
                oa.setTwoPayPrice(f.add(new BigDecimal(oa.getTwoPayPrice())).subtract(disAmount).doubleValue());
            }
            orderAmount = orderAmount.add(f);
            order.setOrderAmount(orderAmount.doubleValue());
            //店铺ID
            order.setStoreId(goods.getStoreId());
            //邀请人用户ID
            order.setInviterUserId(redisClient.stringGet(RedisEnum.DB_2.ordinal(), Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE + orderParam.getUserId()));
            addOrder(order, orderParam, oa);
            order.setAmount(order.getIsReserve() == 1 ? order.getReserveOneAmount() : order.getAmount());

            return ImmutableMap.of("orderId", order.getOrderId(),
                    "orderSn", order.getOrderSn(),
                    "orderAmount", order.getAmount());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(JSONObject.toJSONString(result));
        }
    }

    /**
     * 已服务商价格计算金额
     *
     * @param goodsSpecId 商品规格ID
     */
    public Map<String, Object> calculationAmount(int goodsId, int goodsSpecId, int goodsNum, boolean isMember) throws BusinessException {
        Map<String, Object> result = new HashMap<>();
        OrderAmount oa = new OrderAmount(goodsId, goodsSpecId, goodsNum);
        List<OrderAmount> listMap = new ArrayList<>();
        //获取到商品的库存
        ActPriceVo actp = goodsToDao.getGoodsNum(goodsId, goodsSpecId);
        //将价格进货价放到优惠价格中去
        if (isMember) {
            actp.setActivityPrice(actp.getSpecGoodsVipPrice().doubleValue());
        } else {
            actp.setActivityPrice(actp.getSpecGoodsPurchasePrice().doubleValue());
        }

        calculationAmount(result, actp, listMap, oa, goodsNum, null);

        return ImmutableMap.of("listMap", listMap);
    }

    /**
     * 更新库存
     *
     * @param result       返回结果
     * @param orderAmounts 订单中的
     * @param orderParam   订单中的参数
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    protected Map<String, Object> updateGoodsRepertory(Map<String, Object> result, List<OrderAmount> orderAmounts, OrderParam orderParam) throws BusinessException {
        for (OrderAmount oa : orderAmounts) {
            ActPriceVo actp = null;

            if (orderParam.getType() != null && orderParam.getType() != 0) {
                String status = "1";
                if (actp != null) {
                    result = updateWeekPoints(result, actp, orderParam, status);
                    result = updateGoodsStorage(result, orderParam.getGoodsNum(), orderParam.getGoodsId(), orderParam.getGoodsSpecId());

                } else {
                    throw new BusinessException("商品不存在");
                }
            } else {
                result = updateGoodsStorage(result, oa.getGoodsNum(), orderParam.getGoodsId(), orderParam.getGoodsSpecId());
            }

        }
        return result;
    }


    /**
     * 更新周，兑换库存
     *
     * @param result
     * @param actp
     * @param orderParam
     * @param status
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    protected Map<String, Object> updateWeekPoints(Map<String, Object> result, ActPriceVo actp, OrderParam orderParam, String status) throws BusinessException {
        long startTime = actp.getStartTime();
        long endTime = actp.getEndTime();
        Integer buyNum = orderDao.getBuyNum(actp.getGoodsId(), actp.getGoodsSpecId(), startTime, endTime, orderParam.getUserId(), actp.getActId());
        if (buyNum > orderParam.getGoodsNum()) {
            throw new BusinessException("超出限购数量");
        }
        int repertory = actp.getRepertory() - orderParam.getGoodsNum();
        if (repertory < 0) {
            throw new BusinessException("商品库存不足");
        }
        return result;
    }

    /**
     * 更新商品规格库存
     *
     * @param result      返回结果
     * @param goodsNum    商品数量
     * @param goodsId     商品id
     * @param goodsSpecId 商品规格id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    protected Map<String, Object> updateGoodsStorage(Map<String, Object> result, Integer goodsNum, Integer goodsId, Integer goodsSpecId) throws BusinessException {
        ActPriceVo actp = goodsToDao.getGoodsNum(goodsId, goodsSpecId);
        int repertory = actp.getRepertory() - goodsNum;
        if (repertory < 0) {
            throw new BusinessException("该商品库存不足");
        }
        int hasBeen = actp.getHasBeen() + goodsNum;
        goodsToDao.updateGoodsStorage(actp.getGoodsId(), actp.getGoodsSpecId(), repertory, hasBeen);
        return result;
    }

    /**
     * 添加订单
     *
     * @param order
     * @param orderParam
     * @param oa
     */
    @Transactional(rollbackFor = Exception.class)
    protected void addOrder(OrderToVo order, OrderParam orderParam, OrderAmount oa) {

        //先添加订单的收货地址 ,然后在添加订单
        ShoppingAddressVo sa = new ShoppingAddressVo();
        sa.setObject(orderParam.getAddressId());
        this.addOrderAddress(sa);
        SerialNumber serialNumber = serialNumberService.verifySerialNumber(SerialEnum.ZT.getCode());
        order.setOrderSn(SerialEnum.ZT.getValue() + serialNumber.getSerialNumber());
        order.setUserId(orderParam.getUserId());
        order.setCreateTime(System.currentTimeMillis());
        order.setRemark(orderParam.getRemark());
        order.setGoodsAmount(oa.getGoodsPrice());
        order.setAddressId(sa.getAddressId());
        order.setOrderState(1);
        order.setUserName(orderParam.getUserName());
        order.setReserveOneAmount(oa.getOnePayPrice());
        order.setReserveTwoAmount(oa.getTwoPayPrice());
        order.setOneIsPay(0);
        order.setTwoIsPay(0);

        orderDao.addOrder(order);
        serialNumberService.updateSerialNumber(serialNumber);
        OrderGoods og = new OrderGoods();
        og.setOrderId(order.getOrderId());
        og.setGoodsPrice(oa.getGoodsPrice());
        this.addOrderGoods(orderParam, og);
        this.addCompl(orderParam, order, og);
    }

    /**
     * 添加订单商品
     *
     * @param orderParam
     * @param og
     */
    @Transactional(rollbackFor = Exception.class)
    protected void addOrderGoods(OrderParam orderParam, OrderGoods og) {
        GoodsInfoVo goods = goodsToDao.getGoodsInfo(orderParam.getGoodsId());
        og.setBuyerId(orderParam.getUserId());
        og.setEvaluationStatus(0);
        og.setEvaluationTime(System.currentTimeMillis());
        og.setGcId(goods.getGcId());
        og.setGoodsId(orderParam.getGoodsId());
        og.setGoodsImage(goods.getGoodsImage());
        og.setGoodsName(goods.getGoodsName());
        og.setGoodsNum(orderParam.getGoodsNum());
        og.setSpecId(orderParam.getGoodsSpecId());
        og.setSpecName(orderParam.getGoodsSpecName());
        Integer type = orderParam.getType() == 2 ? 5 : orderParam.getType() == 3 ? 6 : orderParam.getType();
        og.setType(type);
        og.setParentId(0);
        og.setWeight(goods.getWeight());
        og.setVolume(goods.getVolume());
        og.setFullFreeMail(goods.getFullFreeMail());
        orderDao.addOrderGoods(og);
    }

    /**
     * 添加赠品
     *
     * @param orderParam
     * @param order
     * @param og
     */
    @Transactional(rollbackFor = Exception.class)
    protected void addCompl(OrderParam orderParam, OrderToVo order, OrderGoods og) {
        //根據商品id德達所有贈品
        List<GoodsCompl> goodsCompl = goodsToService.getGoodsCompl(orderParam.getGoodsId());
        if (goodsCompl.size() > 0) {
            for (GoodsCompl goodsCompl2 : goodsCompl) {
                //根據商品id得到某個屬性
                Map<String, Object> goodsmap = goodsToService.findGoodsByGoodsId(goodsCompl2.getComplGoodsId());
                String goodsIm = (String) goodsmap.get("goodsImage");
                String gn = (String) goodsmap.get("goodsName");
                long gcid = (long) goodsmap.get("gcId");
                OrderGoods orderg = new OrderGoods();
                orderg.setBuyerId(orderParam.getUserId());
                orderg.setEvaluationStatus(0);
                orderg.setEvaluationTime(System.currentTimeMillis());
                orderg.setGcId((int) gcid);
                orderg.setGoodsId(goodsCompl2.getGoodsId());
                orderg.setGoodsImage(goodsIm);
                orderg.setGoodsName(gn);
                orderg.setGoodsNum(goodsCompl2.getComplNumber() * orderParam.getGoodsNum());
                orderg.setOrderId(order.getOrderId());
                orderg.setSpecId(goodsCompl2.getComplSpecId());
                orderg.setSpecName(goodsCompl2.getComplSpecName());
                orderg.setGoodsPrice(BigDecimal.ZERO);
                orderg.setType(4);
                orderg.setParentId(og.getRecId());
                orderDao.addOrderGoods(orderg);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addCartOrder(OrderParam orderParam) {
        Map<String, Object> result = new HashMap<>(10);
        try {
            Map<String, Object> calculationAmount = calculationAmount(orderParam.getUserId(), orderParam.getCartIdStr());

            OrderToVo order = new OrderToVo();
            List<OrderAmount> orderAmounts = gson.fromJson(gson.toJson(calculationAmount.get("listMap")), new TypeToken<List<OrderAmount>>() {
            }.getType());
            OrderAmount oa = new OrderAmount(true);
            orderAmounts.forEach(orderAmount -> {
                oa.setGoodsPrice(oa.getGoodsPrice().add(orderAmount.getGoodsPrice()));
                if (orderAmount.getPledgePrice() != null) {
                    oa.setPledgePrice(oa.getPledgePrice() + orderAmount.getPledgePrice());
                }
            });
            BigDecimal disAmount = BigDecimal.ZERO;
            order.setOrderAmount(oa.getGoodsPrice().doubleValue());
            order.setCouponId(orderParam.getCouponId());
            order.setCouponPrice(disAmount.doubleValue());
            List<CartVo> cartVos = orderDao.getShoppingCartByIds(orderParam.getCartIdStr().split(","));
            result = updateGoodsRepertory(result, cartVos);

            //邀请人用户ID
            order.setInviterUserId(redisClient.stringGet(RedisEnum.DB_2.ordinal(), Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE + orderParam.getUserId()));
            addCartOrder(order, orderParam, oa, cartVos, orderAmounts);
            return ImmutableMap.of("orderId", order.getOrderId(),
                    "orderSn", order.getOrderSn(),
                    "orderAmount", order.getOrderAmount());
        } catch (Exception e) {
            log.error("购物车异常：" + e.getMessage(), e);
            throw new RuntimeException(JSONObject.toJSONString(result));
        }
    }

    /**
     * 更新购物车库存
     *
     * @param result  返回接轨
     * @param
     * @param cartVos 购物车
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    protected Map<String, Object> updateGoodsRepertory(Map<String, Object> result, List<CartVo> cartVos) throws BusinessException {
        for (CartVo cart : cartVos) {
            ActPriceVo actPriceVo = goodsToDao.getByGoodsIdAndSpecId(cart.getGoodsId(), cart.getSpecId());

            actPriceVo = goodsToDao.getGoodsNum(cart.getGoodsId(), cart.getSpecId());
            if (actPriceVo == null) {
                throw new BusinessException("商品不存在");
            }
            result = updateGoodsStorage(result, cart.getGoodsNum(), cart.getGoodsId(), cart.getSpecId());
        }
        return result;
    }

    /**
     * 添加购物车
     *
     * @param order
     * @param orderParam
     * @param oa
     * @param cartVos
     * @param orderAmounts
     */
    @Transactional(rollbackFor = Exception.class)
    protected void addCartOrder(
            OrderToVo order,
            OrderParam orderParam,
            OrderAmount oa,
            List<CartVo> cartVos,
            List<OrderAmount> orderAmounts) {

        //先添加订单的收货地址 ,然后在添加订单
        ShoppingAddressVo sa = new ShoppingAddressVo();
        sa.setObject(orderParam.getAddressId());
        this.addOrderAddress(sa);
        SerialNumber serialNumber = serialNumberService.verifySerialNumber(SerialEnum.ZT.getCode());
        order.setOrderSn(SerialEnum.ZT.getValue() + serialNumber.getSerialNumber());
        order.setUserId(orderParam.getUserId());
        order.setCreateTime(System.currentTimeMillis());
        order.setRemark(orderParam.getRemark());
        order.setAddressId(sa.getAddressId());
        order.setOrderState(1);
        order.setGoodsAmount(oa.getGoodsPrice());
        order.setUserName(orderParam.getUserName());
        order.setReserveOneAmount(oa.getOnePayPrice());
        order.setReserveTwoAmount(oa.getTwoPayPrice());
        order.setOneIsPay(0);
        order.setTwoIsPay(0);
        orderDao.addOrder(order);
        serialNumberService.updateSerialNumber(serialNumber);
        this.addCartOrderGoods(order, cartVos, orderAmounts);
        //删除添加成功之后删除购物车
        this.deleteCarts(orderParam.getCartIdStr());
    }

    /**
     * 添加购物车商品订单
     *
     * @param order        订单
     * @param cartVos      购物车vos
     * @param orderAmounts 订单金额list
     */
    @Transactional(rollbackFor = Exception.class)
    protected void addCartOrderGoods(OrderToVo order, List<CartVo> cartVos, List<OrderAmount> orderAmounts) {
        for (CartVo goodinfo : cartVos) {
            for (OrderAmount oa : orderAmounts) {
                if (goodinfo.getGoodsId() == oa.getGoodsId()) {
                    OrderGoods og = new OrderGoods();
                    GoodsInfoVo goods = goodsToDao.getGoodsInfo(goodinfo.getGoodsId());
                    og.setBuyerId(order.getUserId());
                    og.setEvaluationStatus(0);
                    og.setEvaluationTime(System.currentTimeMillis());
                    og.setGcId(goodinfo.getFirstGcId());
                    og.setGoodsId(goodinfo.getGoodsId());
                    og.setGoodsImage(goodinfo.getGoodsImages());
                    og.setGoodsName(goodinfo.getGoodsName());
                    og.setGoodsNum(goodinfo.getGoodsNum());
                    og.setOrderId(order.getOrderId());
                    og.setSpecId(goodinfo.getSpecId());
                    og.setSpecName(goodinfo.getSpecName());
                    og.setGoodsPrice(oa.getGoodsPrice());
                    og.setType(goodinfo.getType());
                    og.setParentId(0);
                    og.setWeight(goods.getWeight());
                    og.setVolume(goods.getVolume());
                    og.setFullFreeMail(goods.getFullFreeMail());

                    orderDao.addOrderGoods(og);
                    this.addCompl(new OrderParam() {{
                        setGoodsId(goodinfo.getGoodsId());
                        setUserId(order.getUserId());
                        setGoodsNum(goodinfo.getGoodsNum());
                    }}, order, og);
                }
            }
        }
    }

    /**
     * @author 黄亮
     * 更改订单状态
     * 如果是4  完成订单 就对商品销量增加一个
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderStatus(int orderId, Integer orderStatus) {
        try {
            Map<String, Object> resultMap = Maps.newHashMap();
            resultMap.put("coupon", "0");
            //查询订单得到订单明细
            OrderInfo orderInfo = orderDao.getOrderInfo(orderId);
            if (orderStatus.equals(orderInfo.getOrderState())) {
                throw new BusinessException("请勿重复操作");
            }
            //得到订单中的售出
            List<OrderGoods> ogList = orderDao.findByOrderId(orderInfo.getOrderId());
            //取消订单
            if (orderStatus == 0) {
                updateReductionStorage(ogList, orderInfo);
            }
            //完成订单
            if (orderStatus == 4) {
                for (OrderGoods orderGoods : ogList) {
                    //得到这件商品以前的销售情况
                    Integer salenum = goodsToDao.getGoodsSalenum(orderGoods.getGoodsId());
                    int newSalenum = salenum + orderGoods.getGoodsNum();
                    goodsToDao.updateGoodsSalenum(orderGoods.getGoodsId(), newSalenum);
                }
            }
            orderDao.updateOrderStatus(orderId, orderStatus, System.currentTimeMillis());
        } catch (Exception e) {
            log.error("确认收货异常：" + e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 取消订单还原库存
     *
     * @param ogList    订单商品list
     * @param orderInfo 订单详情
     */
    @Transactional(rollbackFor = Exception.class)
    protected void updateReductionStorage(List<OrderGoods> ogList, OrderInfo orderInfo) {
        for (OrderGoods orderGoods : ogList) {
            updateGoodsStorage(orderGoods);
        }
    }

    /**
     * 还原库存
     *
     * @param orderGoods 订单商品bean
     */
    @Transactional(rollbackFor = Exception.class)
    protected void updateGoodsStorage(OrderGoods orderGoods) {
        ActPriceVo map = goodsToDao.getGoodsNum(orderGoods.getGoodsId(), orderGoods.getSpecId());
        if (map != null) {
            int goodsStorage = map.getRepertory() + orderGoods.getGoodsNum();
            int specSalenum = map.getHasBeen() - orderGoods.getGoodsNum();
            goodsToDao.updateGoodsStorage(orderGoods.getGoodsId(), orderGoods.getSpecId(), goodsStorage, specSalenum);
        }
    }


    /**
     * @author 黄亮
     * 根据订单id得到该订单下的所有商品
     */
    @Override
    public List<OrderGoods> findByOrderId(int orderId) {

        return orderDao.findByOrderId(orderId);
    }

    /**
     * @author 黄亮
     * 根据订单id得到订单详情
     */
    @Override
    public Map<String, Object> getOrderInfo(Integer orderId) throws BusinessException {
        if (orderId == null) {
            throw new BusinessException("参数为空");
        }

        //得到订单详情
        OrderInfo orderInfo = orderDao.getOrderInfo(orderId);
        try {
            orderInfo.setNewTime(System.currentTimeMillis());
            if (orderInfo != null) {
                //得到订单下面的商品列表
                List<OrderGoods> ogList = orderDao.findByOrderId(orderInfo.getOrderId());
                if (ogList.size() > 0) {
                    for (OrderGoods orderGoods : ogList) {
                        if (StringUtils.isNotEmpty(orderGoods.getGoodsImage()) && !("http").equals(orderGoods.getGoodsImage().substring(0, 4))) {
                            orderGoods.setGoodsImage(orderGoods.getGoodsImage());
                        }
                        List<GoodsCompl> goodsCompl = orderDao.getGoodsCompl(orderGoods.getRecId());
                        orderGoods.setGoodsClmpl(goodsCompl);
                    }
                }
                orderInfo.setOrderGoods(ogList);
                //得到收货地址详情
                AddressVo addres = orderDao.getAddressById(orderInfo.getAddressId());
                orderInfo.setAddres(addres);
                //得到判断订单支付结束时间
                if (orderInfo.getOrderState() == 1) {
                    Long cancelTime;
                    if ("1".equals(orderInfo.getIsLimit())) {
                        BigDecimal ft = new BigDecimal(String.valueOf(1000 * 60 * 60));
                        cancelTime = ft.longValue();
                    } else {
                        BigDecimal ft = new BigDecimal(String.valueOf(24 * 1000 * 60 * 60));
                        cancelTime = ft.longValue();
                    }
                    cancelTime = orderInfo.getCreateTime() + cancelTime;
                    long newTime = System.currentTimeMillis();
                    orderInfo.setCancelOrderTime(cancelTime - newTime);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("orderInfo", orderInfo);
        return map;
    }

    /**
     * @author 黄亮
     * 根据订单编号得的订单明细
     */
    @Override
    public OrderToVo findByOrderSn(String orderSn) {
        return orderDao.findByOrderSn(orderSn);
    }

    /**
     * @author 黄亮
     * 添加訂單詳情
     */
    @Override
    public void addOrderPay(OrderPay op) {
        orderDao.addOrderPay(op);

    }

    /**
     * @author 黄亮
     * 更改支付
     */
    @Override
    public void updateOrderByPay(OrderToPay otp) {
        orderDao.updateOrderByPay(otp);

    }

    /**
     * @author 黄亮
     * 根据订单商品id得到该商品下的所有赠品
     */
    @Override
    public List<GoodsCompl> getGoodsCompl(int goodsId) {
        return orderDao.getGoodsCompl(goodsId);
    }

    /**
     * @author 黄亮
     * 根据用户id得到该用户的所有订单对应状态的条数
     */
    @Override
    public Map<String, Object> getOrderCount(String userId) {
        Map<String, Object> map = Maps.newHashMap();

        List<Map<String, Object>> orderCount = orderDao.findCountStatusByUserId(userId);
        for (Map m : orderCount) {
            if (m.get("orderStatus") != null) {
                int orderStatus = (int) m.get("orderStatus");
                if (orderStatus == 1) {
                    map.put("notPaymentNum", m.get("count"));
                } else if (orderStatus == 2) {
                    map.put("notDeliverGoods", m.get("count"));
                } else if (orderStatus == 3) {
                    map.put("notSignFor", m.get("count"));
                } else if (orderStatus == 4) {
                    map.put("notEvaluate", m.get("count"));
                }
            }
        }
//        //未付款
//        int os = 1;
//        Integer notPaymentNum = orderDao.findByUserIdAndOs(userId, os);
//        if (notPaymentNum == null) {
//            notPaymentNum = 0;
//        }
//        map.put("notPaymentNum", notPaymentNum);
//        //未发货
//        os = 2;
//        Integer notDeliverGoods = orderDao.findByUserIdAndOs(userId, os);
//        if (notDeliverGoods == null) {
//            notDeliverGoods = 0;
//        }
//        map.put("notDeliverGoods", notDeliverGoods);
//        //未签收
//        os = 3;
//        Integer notSignFor = orderDao.findByUserIdAndOs(userId, os);
//        if (notSignFor == null) {
//            notSignFor = 0;
//        }
//        map.put("notSignFor", notSignFor);
//        //未评价
//        os = 4;
//        Integer notEvaluate = orderDao.findByUserIdAndEvaluate(userId, os);
//        if (notEvaluate == null) {
//            notEvaluate = 0;
//        }
//        map.put("notEvaluate", notEvaluate);
        return map;
    }

    /**
     * @author 黄亮
     * 订单支付验证
     * 验证项目一 会员可使用健康豆
     * 验证项目二 会员的优惠卷是否过期
     * 验证项目三 活动是否结束
     * 定义code 码 0 代表正常支付,400 代表取消订单 401 代表订单金额重新计算 取我返给你的orderAmount
     * TODO
     */
    @Override
    public Map<String, Object> orderVerification(int orderId) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            //先得到订单详情
            OrderInfo orderInfo = orderDao.getOrderInfo(orderId);
            BigDecimal newOrderAmount = new BigDecimal(orderInfo.getOrderAmount());

            if (orderInfo.getIsRecour() == 1) {
                return result;
            }
            //得到订单中的所有商品
            List<OrderGoods> ogList = orderDao.findByOrderId(orderInfo.getOrderId());

            Map<String, Object> map = Maps.newHashMap();

            //正常更新订单应付金额
            if (newOrderAmount.doubleValue() == orderInfo.getOrderAmount()) {

                return result;
            } else {
                orderDao.updateActOrderAmount(orderInfo.getOrderId(), newOrderAmount);
                map.put("orderAmount", newOrderAmount.doubleValue());
                return map;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    /**
     * @author 黄亮
     * 更新订单评价状态
     */
    @Override
    public void updatEvaluate(int orderId) {
        orderDao.updatEvaluate(orderId);

    }

    /**
     * @author 黄亮
     * 根据收货地址id得到地址详情
     */
    @Override
    public Map<String, Object> findAddressInfoById(int addressId) {
        ShoppingAddressVo add = orderDao.findAddressInfoById(addressId);
        Map<String, Object> map = Maps.newHashMap();
        map.put("addressInfo", add);
        return map;
    }

    /**
     * @AUTHOR HUANGL
     * 得到快递单号
     */
    @Override
    public Map<String, Object> getExpress(String orderNumber) {

        return orderDao.getExpress(orderNumber);
    }

    /**
     * 清除失效宝贝
     *
     * @param userId
     */
    @Override
    public void deleteFailure(String userId) {
        orderDao.deleteFailureUId(userId);
    }

    /**
     * @AUTHORHUANGL 删除购物车 中的商品
     */
    @Override
    public void deleteCarts(String cartIds) {
        orderDao.deleteShoppingCarts(cartIds.split(","));
    }

    /**
     * 添加訂單收貨地址
     *
     * @AUTHORHUANGL
     */
    @Override
    public void addOrderAddress(ShoppingAddressVo sa) {
        orderDao.addOrderAddress(sa);
    }

    /**
     * 拼成下单需要的规格
     *
     * @param specName 规格名
     * @param specInfo 规格详情
     * @return
     * @author 黄亮
     */
    public String specIndex(String specName, String specInfo) {
        String spec = "";
        if (StringUtils.isNotEmpty(specName) && StringUtils.isNotEmpty(specInfo)) {
            String[] sn = specName.split(",");
            String[] si = specInfo.split(",");

            for (int i = 0; i < sn.length; i++) {
                String sni = sn[i].replace("}", "");
                String sii = si[i].replace("}", "");
                String[] sns = sni.split(":");
                String[] sis = sii.split(":");
                String spec1 = sns[1].substring(1, sns[1].length() - 1);
                String spec2 = sis[1].substring(1, sis[1].length() - 1);
                if ("".equals(spec)) {
                    spec = spec1 + ":" + spec2;
                } else {
                    spec = spec + " " + spec1 + ":" + spec2;
                }
            }
        }
        return spec;
    }

    @Override
    public long getOrderAccomplishTime(int orderId) {

        return orderDao.getOrderAccomplishTime(orderId);
    }

    @Override
    public int getOrderPayCount(String out_trade_no) {
        return orderDao.getOrderPayCount(out_trade_no);
    }

    @Override
    public Boolean addRecourToCart(int orderId) throws BusinessException {

        boolean flag = true;
        try {
            List<OrderGoods> orderGoods = orderDao.findByOrderId(orderId);
            for (int i = 0; i < orderGoods.size(); i++) {
                OrderGoods og = orderGoods.get(i);
                if (og.getType() == 1 || og.getType() == 2) {
                    throw new BusinessException("您选择的订单有商品下架,请重新下单");
                }
                og.setCreateTime(System.currentTimeMillis());
                GoodsListVo goods2 = goodsToDao.getGoods2(og.getGoodsId());
                if (goods2 == null) {
                    throw new BusinessException("您选择的订单有商品下架,请重新下单");
                }
                og.setGcBigId(goods2.getGcBigId());
            }
            for (int i = 0; i < orderGoods.size(); i++) {
                OrderGoods og = orderGoods.get(i);
                if (og.getType() != 3) {
                    ShoppingCartVo shoppingCart = new ShoppingCartVo();
                    shoppingCart.setGoodsId(og.getGoodsId());
                    shoppingCart.setSpecId(og.getSpecId());
                    shoppingCart.setPrice(og.getGoodsPrice());
                    shoppingCart.setUserId(og.getBuyerId());
                    shoppingCart.setGoodsNum(og.getGoodsNum());
                    shoppingCart.setFirstGcbigId(og.getGcBigId());
                    shoppingCart.setFirstGcId(og.getGcId());
                    shoppingCart.setGoodsName(og.getGoodsName());
                    shoppingCart.setSpecName(og.getSpecName());
                    shoppingCart.setGoodsImages(og.getGoodsImage());
                    shoppingCart.setType(0);
                    shoppingCart.setIsActivity(0);
                    shoppingCart.setCreateTime(System.currentTimeMillis());
                    shoppingCart.setIsCompl(0);
                    shoppingCart.setIsSelected(1);
                    //是否购物车存在相同规格商品
                    ShoppingCartVo shoppingCart1 = orderDao.getShoppingCartByVo(shoppingCart);
                    if (shoppingCart1 != null) {
                        shoppingCart1.setGoodsNum(shoppingCart1.getGoodsNum() + og.getGoodsNum());
                        shoppingCart1.setUpdateTime(System.currentTimeMillis());
                        shoppingCart1.setIsSelected(1);
                        orderDao.updateShoppingCart(shoppingCart1);
                    } else {
                        orderDao.addShoppingCart(shoppingCart);
                    }
                }
            }
        } catch (Exception e) {
            throw new BusinessException("未知错误,请稍后重试");
        }
        return flag;
    }

    @Override
    public Map<String, Object> getCartGoodsNum(String userId) {

        Map<String, Object> map = orderDao.getCartGoodsNum(userId);
        return map;
    }

    /**
     * @throws Exception
     * @throws NumberFormatException
     * @author 黄亮
     * 得到订单的价格 , 并验证是否可以下单
     */
    @Override
    public Map<String, Object> getOrderPrice(String userId, Integer goodsId, Integer goodsSpecId, Integer goodsNum, String cartIds, Integer isActivity, Integer isLimitTime, Integer isReserve, Integer type) throws BusinessException {
        Map<String, Object> result;
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException("参数为空");
        }
        //购物车
        if (StringUtils.isNotEmpty(cartIds)) {
            //result=countCartAmount(userId , cartIds);
            result = calculationAmount(userId, cartIds);
        } else {
            ShopMember userInfo = userInfoDao.byIdFindUser(userId);
            Goods goods = goodsMapper.findById(goodsId);
            if (userInfo != null && goods != null && userInfo.getMerchantType() == 2 && goods.getIsPopularize() == 1) {
                //如果是服务商且改商品参与推销，则以供货价下单
                result = calculationAmount(goodsId, goodsSpecId, goodsNum, false);
            } else {
                result = calculationAmount(goodsId, goodsSpecId, goodsNum, true);
            }
        }
        return result;
    }


    @Override
    public List<CartVo> getShoppingCartByIds(String[] split) {
        return orderDao.getShoppingCartByIds(split);
    }

}
