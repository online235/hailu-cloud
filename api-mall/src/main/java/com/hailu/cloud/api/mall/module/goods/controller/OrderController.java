package com.hailu.cloud.api.mall.module.goods.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.hailu.cloud.api.mall.module.goods.entity.order.*;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsToService;
import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import com.hailu.cloud.api.mall.module.goods.service.IOrderService;
import com.hailu.cloud.api.mall.module.goods.vo.RegionVo;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单模块
 *
 * @author xuzhijie
 */
@Slf4j
@RestController
@RequestMapping("api/mall")
@Api(tags = "商城-订单")
public class OrderController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IGoodsToService goodsToService;
    @Autowired
    private IOSSOrderService ossOrderService;

    /**
     * 微信公众号签名认证接口
     *
     * @throws
     * @Title: test
     * @Description: TODO
     * @param: @param signature
     * @param: @param timestamp
     * @param: @param nonce
     * @param: @param echostr
     * @param: @return
     * @return: String
     */
    @RequestMapping("/wxToken")
    public String test(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }

    /**
     * 获取省市县
     */
    @GetMapping("/regionList")
    public Map<String, Object> regionList(@RequestParam int pid) throws Exception {
        List<RegionVo> regionList = orderService.regionList(pid);
        return ImmutableMap.of("regionList", regionList);
    }

    /**
     * 添加购物车
     */
    @PostMapping("/addShoppingCart")
    public Map<String, Object> addShoppingCart(@ModelAttribute ShoppingCartVo shoppingCart) throws Exception {
        shoppingCart.setCreateTime(System.currentTimeMillis());
        shoppingCart.setIsSelected(0);
        //是否购物车存在相同规格商品
        ShoppingCartVo shoppingCart1 = orderService.getShoppingCartByVo(shoppingCart);
        if (shoppingCart1 != null) {
            shoppingCart1.setGoodsNum(shoppingCart1.getGoodsNum() + shoppingCart.getGoodsNum());
            shoppingCart1.setUpdateTime(System.currentTimeMillis());
            shoppingCart1.setIsSelected(1);
            orderService.updateShoppingCart(shoppingCart1);
        } else {
            orderService.addShoppingCart(shoppingCart);
        }
        return ImmutableMap.of("msg", "添加成功");
    }

    /**
     * 获取购物车列表
     */
    @GetMapping("/shoppingCartList")
    public Map<String, Object> shoppingCartList() throws Exception {
        MemberLoginInfoModel loginInfoModel = RequestUtils.getMemberLoginInfo();
        List<ShoppingCartVo> shoppingCartList = orderService.shoppingList(loginInfoModel.getUserId());
        return ImmutableMap.of("shoppingCartList", shoppingCartList);
    }

    /**
     * 清除失效宝贝
     */
    @GetMapping("/deleteFailure")
    public Map<String, Object> deleteFailure(@RequestParam String userId) {
        orderService.deleteFailure(userId);
        return null;
    }

    /**
     * 修改购物车商品数量 规格
     */
    @GetMapping("/upShoppingCart")
    public void updateShoppingCart(
            @RequestParam(value = "cartId") int cartId,
            @RequestParam(value = "specId", required = false) Integer specId,
            @RequestParam(value = "goodsNum", required = false) Integer goodsNum,
            @RequestParam(value = "isSelected", required = false) Integer isSelected) {

        ShoppingCartVo shoppingCart = new ShoppingCartVo();
        shoppingCart.setGoodsNum(goodsNum);
        shoppingCart.setSpecId(specId);
        shoppingCart.setCartId(cartId);
        shoppingCart.setIsSelected(isSelected);
        try {
            orderService.updateShoppingCart(shoppingCart);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 删除购物车商品
     */
    @GetMapping("/delShoppingCart")
    public void delShoppingCart(@RequestParam String cartIds) {
        orderService.deleteCarts(cartIds);
    }


    /**
     * 获取收货地址列表
     */
    @GetMapping("/shoppingAddressList")
    public Map<String, Object> shoppingAddressList(@RequestParam String userId) throws Exception {
        List<ShoppingAddressVo> shoppingAddressList = orderService.shoppingAddressVoList(userId);
        return ImmutableMap.of("shoppingAddressList", shoppingAddressList);
    }

    /**
     * 根据地址id得到地址详情
     */
    @PostMapping("/findAddressInfoById")
    public Map<String, Object> findAddressInfoById(@RequestParam("addressId") int addressId) {
        return orderService.findAddressInfoById(addressId);

    }

    /**
     * 添加收货地址
     */
    @PostMapping("/addShoppingAddress")
    public Map<String, Object> addShoppingAddress(@ModelAttribute ShoppingAddressVo shoppingAddress) {
        return orderService.addShoppingAddress(shoppingAddress);
    }

    /**
     * 修改收货地址
     */
    @GetMapping("/upShoppingAddress")
    public Map<String, Object> updataShoppingAddress(@ModelAttribute ShoppingAddressVo shoppingAddress) {
        return orderService.updataShoppingAddress(shoppingAddress);
    }

    /**
     * 删除收货地址
     */
    @GetMapping("/delShoppingAddress")
    public void delShoppingAddress(@RequestParam String idStr) throws Exception {
        for (String id : idStr.split(",")) {
            orderService.delShoppingAddress(Integer.parseInt(id));
        }
    }

    /**
     * 得到下单价钱 并验证
     */
    @PostMapping("/getOrderPrice")
    public Map<String, Object> getOrderPrice(
            @RequestParam(value = "userId") String userId, //用户id
            @RequestParam(value = "goodsId", required = false) Integer goodsId, //商品id
            @RequestParam(value = "goodsSpecId", required = false) Integer goodsSpecId, //商品规格id
            @RequestParam(value = "goodsNum", required = false) Integer goodsNum, //商品数量
            @RequestParam(value = "cartIds", required = false) String cartIds, //购物车id
            @RequestParam(value = "isActivity", required = false, defaultValue = "0") Integer isActivity, //是否常规活动 1_是 , 0_否
            @RequestParam(value = "isLimitTime", required = false, defaultValue = "0") Integer isLimitTime, //是否限时抢购 1_是 , 0_否
            @RequestParam(value = "isReserve", required = false, defaultValue = "0") Integer isReserve, //是否预定 1_是 , 0_否
            @RequestParam(value = "type", required = false, defaultValue = "0") Integer type //0_正常，2_会员周,3_健康豆
    ) throws Exception {

        return orderService.getOrderPrice(userId, goodsId, goodsSpecId, goodsNum, cartIds, isActivity, isLimitTime, isReserve, type);
    }

    /**
     * 直接下单
     */
    @PostMapping("/buyGoods")
    public Map<String, Object> buyGoods(@ModelAttribute OrderParam orderParam) {
        return orderService.addBuyOrder(orderParam);
    }

    /**
     * 通过购物车下单
     */
    @PostMapping("/cartBuyGoods")
    public Map<String, Object> cartBuyGoods(@ModelAttribute OrderParam orderParam) {
        return orderService.addCartOrder(orderParam);
    }


    /**
     * 订单支付验证
     */
    @PostMapping("/orderVerification")
    public Map<String, Object> orderVerification(@RequestParam("orderId") int orderId) {
        return orderService.orderVerification(orderId);
    }


    /**
     * 订单支付
     */
    @ApiOperation(notes = "微信支付：{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",\n" +
            "  \"data\": {\n" +
            "    \"appid\": \"wx28a66e8b774bd3f2\",\n" +
            "    \"noncestr\": \"w0krzHiS4I5wavgi\",\n" +
            "    \"packageValue\": \"Sign=WXPay\",\n" +
            "    \"partnerid\": \"1520607751\",\n" +
            "    \"prepayid\": \"wx29103143274592adf0da736a1639062400\",\n" +
            "    \"timestamp\": \"1572316303\",\n" +
            "    \"sign\": \"D2A5F98AD2A6B0697AD76E0FC843503C\"\n" +
            "  },\n" +
            "  \"serverTime\": 1572316303756\n" +
            "}", value = "支付接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderSn", value = "订单号(商城订单传)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "payType", value = "支付类型（1-支付宝、2-微信、3-微信H5）", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "openid", value = "openId(公众号支付)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "returnUrl", value = "支付成功后跳转地址", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/orderPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> orderPay(@NotBlank(message = "订单号不能为空") String orderSn, Integer payType, String openid,String returnUrl) throws BusinessException {
        return ossOrderService.createOrder(orderSn, payType, openid,returnUrl);
    }


    /**
     * 查看所有订单   或查看相应状态订单 得到订单列表
     */
    @GetMapping("/getOrders")
    public Map<String, Object> getOrders(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "evaluateState") int evaluateState, //评价状态 0_未评价 , 1_已评价
            @RequestParam("page") int page, @RequestParam("row") int row) {

        page = (page - 1) * row;
        List<OrderListVo> orders = orderService.getOrdersList(userId, orderStatus, evaluateState, page, row);
        return ImmutableMap.of("orders", orders);
    }

    /**
     * 查看伙人所有订单   查看城市合伙人订单
     * 是否查看分享订单（1-是、2-否）
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "evaluateState", value = "评价状态", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "row", value = "条数", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "isShare", value = "是否查看分享订单（1-是、2-否）", required = false, paramType = "query", dataType = "String"),
    })
    @ApiOperation(value = "查看城市合伙人订单")
    @GetMapping("/getOrdersAsPartner")
    public Map<String, Object> getOrdersAsPartner(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "evaluateState") int evaluateState, //评价状态 0_未评价 , 1_已评价
            @RequestParam("page") int page, @RequestParam("row") int row,
            @RequestParam(value = "isShare") Integer isShare
    ) {

        page = (page - 1) * row;
        List<OrderListVo> orders = orderService.getOrdersList(userId, orderStatus, evaluateState, page, row,isShare);
        return ImmutableMap.of("orders", orders);
    }

    /**
     * 根据用户id得到该用户的所有角标
     * 其中有 未支付 ,未发货 , 未收货 , 未评价
     */
    @GetMapping("/getOrderCount")
    public Map<String, Object> getOrderCount(@RequestParam(value = "userId") String userId) {
        return orderService.getOrderCount(userId);
    }

    /**
     * 获取单个订单信息
     */
    @PostMapping("/getOrderInfo")
    public Map<String, Object> getOrderInfo(@RequestParam(value = "orderId") Integer orderId) throws BusinessException {

        return orderService.getOrderInfo(orderId);
    }

    /**
     * 改变订单状态
     */
    @PostMapping("/upOrderStatus")
    public void getOrders(
            @RequestParam(value = "orderId") int orderId, //订单id
            @RequestParam(value = "orderStatus") Integer orderStatus //订单状态
    ) throws Exception {

        orderService.updateOrderStatus(orderId, orderStatus);

    }


    /**
     * 删除订单(逻辑删除)
     */
    @PostMapping("/delOrder")
    public void deleteOrder(@RequestParam(value = "orderId") int orderId) throws Exception {
        orderService.deleteOrder(orderId);
    }

    /**
     * 获取申请记录列表
     */
    @GetMapping("/getOrderServices")
    public Map<String, Object> getOrderServices(@RequestParam(value = "userId") String userId) throws Exception {
        OrderServiceVo os = new OrderServiceVo();
        os.setUserId(userId);
        List<OrderServiceVo> orderServices = orderService.getOrderServices(os);
        if (orderServices.size() > 0) {
            for (OrderServiceVo orderSevice : orderServices) {
                switch (orderSevice.getState()) {
                    case "1":
                        orderSevice.setResultremark("您的服务单已申请成功，待售后审核中..");
                        break;
                    case "2":
                        orderSevice.setResultremark("您的服务单已审核成功，等待收货中..");
                        break;
                    case "3":
                        orderSevice.setResultremark("您的服务单已收货，等待处理中..");
                        break;
                    case "4":
                        orderSevice.setResultremark("您的服务单正在处理请等待");
                        break;
                    default:
                        break;
                }
                if (orderSevice.getApplyForTime() != null && orderSevice.getApplyForTime().length() > 16) {
                    orderSevice.setApplyForTime(orderSevice.getApplyForTime().substring(0, 16));
                }
                if (orderSevice.getSmallImg().length() > 4) {
                    if (!orderSevice.getSmallImg().contains("http")) {
                        orderSevice.setSmallImg(orderSevice.getSmallImg());
                    }
                }
                String imgStr = orderSevice.getPicture();
                StringBuilder imgPath = new StringBuilder();
                if (imgStr != null) {
                    for (String ig : imgStr.split(",")) {
                        if (imgPath.length() > 0) {
                            imgPath.append(",");
                        }
                        imgPath.append(ig);
                    }
                }
                if (orderSevice.getTime() != null) {
                    List<Map<String, String>> times = new ArrayList<>();
                    String[] strs = orderSevice.getTime().split(",");
                    for (int j = 0; j < strs.length; j++) {
                        Map<String, String> map = new HashMap<>(10);
                        if (j == 0 && "0".equals(orderSevice.getState())) {
                            map.put("state", j + "");
                            map.put("time", strs[j]);
                            times.add(map);
                            continue;
                        }
                        if (j == 0) {
                            continue;
                        }
                        map.put("state", j + "");
                        map.put("time", strs[j]);
                        times.add(map);
                    }
                    orderSevice.setProgressTime(times);
                }
                orderSevice.setPicture(imgPath.toString());
            }
        }
        return ImmutableMap.of("orderServices", orderServices);
    }


    /**
     * 获取单个进度详情
     */
    @GetMapping("/getOrderService")
    public Map<String, Object> getOrderService(@RequestParam(value = "id") int id) throws Exception {
        OrderServiceVo orderSevice = new OrderServiceVo();
        orderSevice.setId(id);
        List<OrderServiceVo> orderServices = orderService.getOrderServices(orderSevice);
        if (orderServices.size() > 0) {
            orderSevice = orderServices.get(0);
            switch (orderSevice.getState()) {
                case "1":
                    orderSevice.setResultremark("您的服务单已申请成功，待售后审核中..");
                    break;
                case "2":
                    orderSevice.setResultremark("您的服务单已审核成功，等待收货中..");
                    break;
                case "3":
                    orderSevice.setResultremark("您的服务单已收货，等待处理中..");
                    break;
                case "4":
                    orderSevice.setResultremark("您的服务单正在处理请等待");
                    break;
                default:
                    break;
            }
            if (orderSevice.getApplyForTime() != null && orderSevice.getApplyForTime().length() > 16) {
                orderSevice.setApplyForTime(orderSevice.getApplyForTime().substring(0, 16));
            }
            if (orderSevice.getSmallImg().length() > 4) {
                if (!orderSevice.getSmallImg().contains("http")) {
                    orderSevice.setSmallImg(orderSevice.getSmallImg());
                }
            }
            String imgStr = orderSevice.getPicture();
            StringBuilder imgPath = new StringBuilder();
            if (imgStr != null) {
                for (String ig : imgStr.split(",")) {
                    if (imgPath.length() > 0) {
                        imgPath.append(",");
                    }
                    imgPath.append(ig);
                }
            }
            if (orderSevice.getTime() != null) {
                List<Map<String, String>> times = new ArrayList<>();
                String[] strs = orderSevice.getTime().split(",");
                for (int j = 0; j < strs.length; j++) {
                    Map<String, String> map = new HashMap<>();
                    if (j == 0 && "0".equals(orderSevice.getState())) {
                        map.put("state", j + "");
                        map.put("time", strs[j]);
                        times.add(map);
                        continue;
                    }
                    if (j == 0) {
                        continue;
                    }
                    map.put("state", j + "");
                    map.put("time", strs[j]);
                    times.add(map);
                }
                orderSevice.setProgressTime(times);
            }
            orderSevice.setPicture(imgPath.toString());
        }
        return ImmutableMap.of("orderService", orderSevice);
    }

    /**
     * 提交售后申请
     */
    @PostMapping("/addOrderServices")
    public void addOrderServices(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "orderNumber") String orderNumber,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam(value = "reason", required = false) String reason,
            @RequestParam(value = "serviceType") String serviceType,
            @RequestParam(value = "orderDetailId") String orderDetailId,
            @RequestParam(value = "addressId") String addressId) throws Exception {

        OrderServiceVo os = new OrderServiceVo();
        os.setPicture(picture);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String random = (Math.random() * 10000000 + "").substring(0, 2);
        String lastRandom = (Math.random() * 10000000 + "").substring(0, 4);
        String serviceNumber = random.concat(random).concat(lastRandom);
        System.out.println("serviceNumber:" + serviceNumber);
        os.setServiceNumber(serviceNumber);
        os.setUserId(userId);
        os.setOrderNumber(orderNumber);
        if ("3".equals(serviceType)) {
            os.setReason("");
        } else {
            os.setReason(reason);
        }
        os.setServiceType(serviceType);
        os.setOrderDetailId(orderDetailId);
        os.setAddressId(addressId);
        os.setApplyForTime(sdf.format(new Date()));
        os.setUserId(userId);
        os.setState("1");
        os.setTime(sdf.format(new Date()) + "," + sdf.format(new Date()));
        orderService.addOrderService(os);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setId(Integer.parseInt(orderDetailId));
        orderDetailVo.setIsApplyFor(1);
        orderService.upOrderDetail(orderDetailVo);
    }

    /**
     * 取消申请
     */
    @PostMapping("/upOrderService")
    public Map<String, Object> updateOrderService(@RequestParam(value = "orderServiceId") int orderServiceId) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderServiceVo os = new OrderServiceVo();
        os.setId(orderServiceId);
        List<OrderServiceVo> orderServices = orderService.getOrderServices(os);
        if (orderServices.size() > 0) {
            os = orderServices.get(0);
        }
        os.setState("0");
        os.setResultremark("您的服务单已取消申请..");
        if (os.getApplyForTime() != null && os.getApplyForTime().length() > 19) {
            os.setApplyForTime(os.getApplyForTime().substring(0, 19));
        }
        os.setTime(sdf.format(new Date()) + "," + os.getApplyForTime());
        orderService.updateOrderService(os);
        return new HashMap<>(1);
    }


    /**
     * 物流信息
     */
    @GetMapping("/logistics")
    public Object logistics(@RequestParam(value = "orderNumber") String orderNumber) throws Exception {
        Object result = new Object();
        Map<String, Object> order = orderService.getExpress(orderNumber);
        if (order != null) {
            String shippingCode = (String) order.get("shippingCode");
            Integer shippingExpressId = (Integer) order.get("shippingExpressId");
            String eCode = (String) order.get("eCode");
            String eName = (String) order.get("eName");
            String eMobile = (String) order.get("eMobile");
            if (shippingExpressId == null || shippingCode == null || "".equals(shippingCode) || "".equals(shippingExpressId)) {
                throw new BusinessException("等待揽件");
            } else {

                JSONObject jsStr = new JSONObject();
                jsStr.put("ShipperCode", eName);
                jsStr.put("ShipperMobile", eMobile);
                System.out.println("我是物流信息:" + jsStr);
                return jsStr;
            }
        } else {
            throw new BusinessException("等待揽件");
        }
    }


    /**
     * 查询运费
     */
    @GetMapping("/getOrderFreight")
    public Map<String, Object> getOrderFreight(
            @RequestParam(value = "cityName", required = false, defaultValue = "北京") String cityName, //省级名
            @RequestParam(value = "goodsId", required = false) Integer goodsId, //商品id
            @RequestParam(value = "goodsNum", required = false) Integer goodsNum, //商品数量
            @RequestParam(value = "goodsAmount", required = false) Double goodsAmount, //商品总价
            @RequestParam(value = "specId", required = false) Integer specId, //规格id
            @RequestParam(value = "cartIds", required = false) String cartIds,  //购物车ids
            @RequestParam(value = "couponId", required = false) Integer couponId,  //优惠卷金额
            @RequestParam(value = "type", required = false) Integer type  //1_免费领取
    ) throws Exception {

        Map<String, Object> freight;
        if (cartIds != null) {
            List<CartVo> cartVos = orderService.getShoppingCartByIds(cartIds.split(","));
            if (cartVos != null && cartVos.size() > 0) {
                Map<String, Object> countCartAmount = orderService.getOrderPrice(cartVos.get(0).getUserId(), null, null, null, cartIds, null, null, null, null);
                List<OrderAmount> orderAmounts = (List<OrderAmount>) (countCartAmount.get("listMap"));
                freight = goodsToService.getGoodsFreight(cityName, cartIds, couponId, cartVos, orderAmounts);
            } else {
                freight = Maps.newHashMap();
                freight.put("freight", 0.00);
            }
        } else {
            freight = goodsToService.getFreight(goodsAmount, cityName, goodsId, goodsNum, specId, type, couponId);
        }
        return freight;
    }


    /**
     * 得到商品详情中的运费
     */
    @PostMapping("/findGoodsFreight")
    public Map<String, Object> findGoodsFreight(
            @RequestParam("goodsId") int goodsId,
            @RequestParam("cityName") String cityName) throws Exception {

        return goodsToService.findGoodsFreight(goodsId, cityName);

    }

    /**
     * 再来一单其中的商品加入到购物车
     */
    @PostMapping("/addRecourToCart")
    public Boolean addRecourToCart(@RequestParam(value = "orderId") int orderId) throws BusinessException {
        return orderService.addRecourToCart(orderId);

    }

    /**
     * 得到购物车中的角标
     */
    @PostMapping("/getCartGoodsNum")
    public Map<String, Object> getCartGoodsNum(@RequestParam("userId") String userId) {
        return orderService.getCartGoodsNum(userId);
    }

    /**
     * 获取购买服务商的价格
     *
     * @return
     */
    @ApiOperation(notes = "{\n" +
            "  \"code\": 0,\n" +
            "  \"msg\": \"成功\",      \n" +
            "  \"data\": {\n" +
            "    \"price\": 8888      //价格\n" +
            "  },\n" +
            "  \"serverTime\": 1572688241220\n" +
            "}", value = "获取购买服务商的价格")
    @PostMapping("/findPoviderPrice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantCityId", value = "服务商购买的城市", required = true, paramType = "query", dataType = "String"),
    })
    public Object findPoviderPrice(Long merchantCityId) {
        log.info("获取购买服务商的价格，城市ID为：{}", merchantCityId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("price", userInfoService.findPoviderPrice(merchantCityId));
        return jsonObject;
    }
}
