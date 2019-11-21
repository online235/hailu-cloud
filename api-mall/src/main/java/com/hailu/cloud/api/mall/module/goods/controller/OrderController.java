package com.hailu.cloud.api.mall.module.goods.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hailu.cloud.api.mall.constant.Constant;
import com.hailu.cloud.api.mall.module.common.enums.BusinessCode;
import com.hailu.cloud.api.mall.module.goods.entity.order.*;
import com.hailu.cloud.api.mall.module.goods.service.IGoodsToService;
import com.hailu.cloud.api.mall.module.goods.service.IOSSOrderService;
import com.hailu.cloud.api.mall.module.goods.service.IOrderService;
import com.hailu.cloud.api.mall.module.goods.tool.PictureUploadUtil;
import com.hailu.cloud.api.mall.module.goods.vo.RegionVo;
import com.hailu.cloud.api.mall.module.pay.service.Impl.BuyMemberServiceImpl;
import com.hailu.cloud.api.mall.module.pay.service.Impl.MallPayServiceImpl;
import com.hailu.cloud.api.mall.module.pay.service.Impl.XinanPayServiceImpl;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.redis.enums.RedisEnum;
import com.hailu.cloud.common.utils.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Api(tags = "心安-订单")
public class OrderController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IGoodsToService goodsToService;
    @Autowired
    private IOSSOrderService ossOrderService;
    @Autowired
    private MallPayServiceImpl mallPayService;
    @Autowired
    private XinanPayServiceImpl xinanPayService;
    @Autowired
    private BuyMemberServiceImpl buyMemberService;
    @Autowired
    private RedisStandAloneClient redisClient;


    /**
     * 微信公众号签名认证接口
     * @Title: test
     * @Description: TODO
     * @param: @param signature
     * @param: @param timestamp
     * @param: @param nonce
     * @param: @param echostr
     * @param: @return
     * @return: String
     * @throws
     */
    @RequestMapping("/wxToken")
    public String test(String signature,String timestamp,String nonce,String echostr) {
        return echostr;
    }

    /**
     * 获取省市县
     */
    @GetMapping("/regionList")
    public Map<String, Object> regionList(@RequestParam int pid) throws Exception {
        List<RegionVo> regionList = orderService.regionList(pid);
        Map<String, Object> data = new HashMap<>();
        data.put("regionList", regionList);
        return data;
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
        Map<String, Object> data = new HashMap<>();
        data.put("msg", "添加成功");
        return data;
    }

    /**
     * 获取购物车列表
     */
    @GetMapping("/shoppingCartList")
    public Map<String, Object> shoppingCartList(
            @RequestParam String userId) throws Exception {

        if (userId == null || "".equals(userId)) {
            List<ShoppingCartVo> shoppingCartList = new ArrayList<>();
            Map<String, Object> data = new HashMap<>();
            data.put("shoppingCartList", shoppingCartList);
            return data;
        }
        List<ShoppingCartVo> shoppingCartList = orderService.shoppingList(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("shoppingCartList", shoppingCartList);
        return data;
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
            @RequestParam(value = "isSelected", required = false) Integer isSelected){

        ShoppingCartVo shoppingCart = new ShoppingCartVo();
        shoppingCart.setGoodsNum(goodsNum);
        shoppingCart.setSpecId(specId);
        shoppingCart.setCartId(cartId);
        shoppingCart.setIsSelected(isSelected);
        try {
            orderService.updateShoppingCart(shoppingCart);
        }catch (Exception e){
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
        Map<String, Object> data = new HashMap<>();
        data.put("shoppingAddressList", shoppingAddressList);
        return data;
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
     * 海露订单支付
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
            "}", value = "海露订单支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name="payType", value = "支付类型（1-支付宝、2-微信、3-微信H5）", required = true, paramType="query",dataType = "int"),
            @ApiImplicitParam(name="money", value = "金额", required = true, paramType="query",dataType = "double"),
            @ApiImplicitParam(name="invitationMember", value = "邀请人(海露会员)", required = false, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="address", value = "详细地址", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="provinceId", value = "省ID", required = true, paramType="query",dataType = "Long"),
            @ApiImplicitParam(name="cityId", value = "市ID", required = true, paramType="query",dataType = "Long"),
            @ApiImplicitParam(name="areaId", value = "区县ID", required = true, paramType="query",dataType = "Long"),
            @ApiImplicitParam(name="itemName", value = "商品名称", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="name", value = "名称", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="phone", value = "手机号码", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="itemType", value = "商品类型（1-购买海露会员、2-购买服务商）", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="chooseCityId", value = "服务商城市（购买服务商时必传）", required = false, paramType="query",dataType = "long"),
            @ApiImplicitParam(name="openid", value = "openId(公众号支付)", required = false, paramType="query",dataType = "String")
    })
    @PostMapping("/hlOrderPay")
    @ResponseBody
    public Object hlOrderPay(HttpServletRequest httpRequest,
                                   Integer payType,
                                   Double money,
                                   String invitationMember,
                                   String address,
                                   Long provinceId,
                                   Long cityId,
                                   Long areaId,
                                   String itemName,
                                   String name,
                                   String phone,
                                   Integer itemType,
                                   Long chooseCityId,
                                   String openid){
        return null;
//        PayVo payVo = new PayVo();
//        payVo.setPayType(payType);
//        payVo.setMoney(money);
//        payVo.setInvitationMember(invitationMember);
//        payVo.setProvinceId(provinceId);
//        payVo.setCityId(cityId);
//        payVo.setAreaId(areaId);
//        payVo.setAddress(address);
//        payVo.setItemName(itemName);
//        payVo.setName(name);
//        payVo.setPhone(phone);
//        payVo.setItemType(itemType);
//        payVo.setChooseCityId(chooseCityId);
//        payVo.setMemberId(loginInfo.getId());
//        payVo.setOpenId(openid);
//        payVo.setPayFrom("HL");
//        log.info("海露订单支付|参数:{}", payVo.toString());
//        return buyMemberService.pay(payVo,httpRequest);
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
            @ApiImplicitParam(name="orderSn", value = "订单号(商城订单传)", required = false, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="payFrom", value = "支付来源（XA-心安、mall-商城）", required = true, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="payType", value = "支付类型（1-支付宝、2-微信、3-微信H5）", required = true, paramType="query",dataType = "int"),
            @ApiImplicitParam(name="money", value = "金额", required = false, paramType="query",dataType = "double"),
            @ApiImplicitParam(name="insuredIds", value = "参保人(心安支付传)", required = false, paramType="query",dataType = "String"),
            @ApiImplicitParam(name="openid", value = "openId(公众号支付)", required = false, paramType="query",dataType = "String")
    })
    @RequestMapping(value = "/orderPay",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> orderPay(
            HttpServletRequest httpRequest,
            String orderSn,
            String payFrom,
            Integer payType,
            Double money,
            String insuredIds,
            String openid
    ){
        return null;
//        PayVo payVo = new PayVo();
//        payVo.setOrderNo(orderSn);
//        payVo.setPayFrom(payFrom);
//        payVo.setPayType(payType);
//        payVo.setMoney(money);
//        payVo.setInsuredIds(insuredIds);
//        payVo.setOpenId(openid);
//        log.info("订单支付|参数:{}", payVo.toString());
//        LoginInfo loginInfo = (LoginInfo) httpRequest.getAttribute(Constant.USER_INFORMATION);
//        payVo.setMemberId(loginInfo.getId());
//        //判断是商城的支付订单还是心安的支付
//        if(StringUtils.equals(payVo.getPayFrom(),"XA")){
//            return xinanPayService.pay(payVo,httpRequest);
//        }  else{
//            return mallPayService.pay(payVo,httpRequest);
//        }
//        Map<String, Object> data = new HashMap<>();
//        //获取根据订单编号得到订单的信息
//        OrderToVo ol = orderService.findByOrderSn(orderSn);
//        if (ol != null) {
//            //订单取金额逻辑 , 如果是预定2
//            double orderAmount;
//            String isReserve = "0";
//            if (ol.getIsReserve() == 1) {
//                //第一阶段支付了,取第二阶段的金额
//                if (ol.getOneIsPay() == 1) {
//                    orderSn = orderSn + "s";
//                    orderAmount = ol.getReserveTwoAmount();
//                    isReserve = "2";
//                } else {
//                    orderAmount = ol.getReserveOneAmount();
//                    isReserve = "1";
//                }
//            } else {
//                orderAmount = ol.getOrderAmount();
//            }
//            if (orderAmount <= 0) {
//                ossOrderService.addZeroPay(ol);
//                return new ResponseData<Map<String, Object>>() {{
//                    setCode(10);
//                }};
//            }
//            if (payType == 1) {
//                // 支付宝
//                data.put("orderInfo", payForZhiFuBao(orderSn, httpRequest, ol, orderAmount));
//            } else if (payType == 2) {
//                // 微信
//                data = payForWeiXin(orderSn, httpRequest, ol, orderAmount, isReserve);
//            } else if (payType == 3) {
//                // 微信h5
//                data = payForH5(orderSn, httpRequest, orderAmount);
//            }
//        } else {
//            data.put("msg", "不能重复支付");
//        }
//
//        return new ResponseData<>(data);
    }

//    /**
//     * 支付宝支付
//     */
//    private String payForZhiFuBao(
//            String orderSn,
//            HttpServletRequest httpRequest,
//            OrderToVo ol,
//            double orderAmount) throws Exception {
//
//        Map<String, String> parameterMap = new HashMap<>();
//        // 订单号
//        parameterMap.put("outTradeNo", orderSn);
//        // 总金额
//        parameterMap.put("totalFee", orderAmount + "");
//        parameterMap.put("goodsName", "德达康健商品");
//        parameterMap.put("backUrl", "/api/sys/alipayBack");
//        parameterMap.put("userId", ol.getUserId());
//        return ossOrderService.aliPayUnifiedorder(httpRequest, parameterMap);
//    }
//
//    /**
//     * 微信支付
//     */
//    private Map<String, Object> payForWeiXin(
//            String orderSn,
//            HttpServletRequest httpRequest,
//            OrderToVo ol,
//            double orderAmount,
//            String isReserve) throws Exception {
//
//        Map<String, Object> data;
//        String openid = httpRequest.getParameter("openid");
//        if (StringUtil.isNotEmpty(openid)) {
//            UserInfoVo userinfo = userInfoService.userInfoQueryByUserId(ol.getUserId());
//            data = WechatUtil.wxpay(userinfo, openid, orderSn, "JSAPI", orderAmount, httpRequest);
//        } else {
//            Map<String, String> parameterMap = new HashMap<>();
//            parameterMap.put("body", java.net.URLDecoder.decode("德达康健商品", "UTF-8"));
//            parameterMap.put("nonce_str", RandomUtil.captcha(10));
//            parameterMap.put("out_trade_no", orderSn);
//            // ip地址
//            parameterMap.put("spbill_create_ip", IPUtil.getRemoteHost(httpRequest));
//            // 本系统总金额单位为元，但是微信需要的单位为分
//            parameterMap.put("total_fee", (int) (orderAmount * 100) + "");
//            parameterMap.put("trade_type", "APP");
//            parameterMap.put("isReserve", isReserve);
//            // 生成预付信息
//            data = ossOrderService.unifiedorder(parameterMap);
//        }
//        return data;
//    }
//
//    /**
//     * H5支付
//     */
//    private Map<String, Object> payForH5(
//            String orderSn,
//            HttpServletRequest httpRequest,
//            double orderAmount) throws UnsupportedEncodingException {
//
//        Map<String, String> parameterMap = new HashMap<>();
//        parameterMap.put("body", java.net.URLDecoder.decode("德达康健商品", "UTF-8"));
//        parameterMap.put("nonce_str", RandomUtil.captcha(10));
//        parameterMap.put("out_trade_no", orderSn);
//        // ip地址
//        parameterMap.put("spbill_create_ip", IPUtil.getRemoteHost(httpRequest));
//        // 本系统总金额单位为元，但是微信需要的单位为分
//        parameterMap.put("total_fee", (int) (orderAmount * 100) + "");
//        parameterMap.put("trade_type", "MWEB");
//        // 生成预付信息
//        return ossOrderService.gzunifiedorder(parameterMap);
//    }

    /**
     * 余额下单
     */
    @PostMapping("/balancePay")
    public Boolean addBalancePay(
            @RequestParam("orderSn") String orderSn,
            @RequestParam("payPwd") String payPwd) throws Exception {

        return ossOrderService.addBalancePay(orderSn, payPwd);
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
        Map<String, Object> data = new HashMap<>();
        data.put("orders", orders);
        return data;
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
    public Map<String, Object> getOrders(
            @RequestParam(value = "orderId") int orderId, //订单id
            @RequestParam(value = "orderStatus") Integer orderStatus //订单状态
    ) throws Exception {

        return orderService.updateOrderStatus(orderId, orderStatus);

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
                        orderSevice.setSmallImg(Const.PRO_URL + orderSevice.getSmallImg());
                    }
                }
                String imgStr = orderSevice.getPicture();
                StringBuilder imgPath = new StringBuilder();
                if (imgStr != null) {
                    for (String ig : imgStr.split(",")) {
                        if (imgPath.length() > 0) {
                            imgPath.append(",");
                        }
                        imgPath.append(Const.PRO_URL).append(ig);
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
        }
        Map<String, Object> data = new HashMap<>();
        data.put("orderServices", orderServices);
        return data;
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
                    orderSevice.setSmallImg(Const.PRO_URL + orderSevice.getSmallImg());
                }
            }
            String imgStr = orderSevice.getPicture();
            StringBuilder imgPath = new StringBuilder();
            if (imgStr != null) {
                for (String ig : imgStr.split(",")) {
                    if (imgPath.length() > 0) {
                        imgPath.append(",");
                    }
                    imgPath.append(Const.PRO_URL).append(ig);
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
        Map<String, Object> data = new HashMap<>();
        data.put("orderService", orderSevice);
        return data;
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
        if (picture != null) {
            StringBuilder imgpath = new StringBuilder();
            for (String iu : picture.split(",")) {
                String img = PictureUploadUtil.uploadPicture("img", iu);
                if (imgpath.length() > 0) {
                    imgpath.append(",");
                }
                imgpath.append(img);
            }
            os.setPicture(imgpath.toString());
        }
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
        //		log.info("物流查询|orderNumber={}", orderNumber);
        //Map<String, Object> data = new HashMap<>();
       Object result = new Object();
        //OrderVo order=orderService.orderVo(orderNumber);
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
                //tracesByJsonStr=tracesByJsonStr.replace("\"", "");
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
            if(cartVos != null && cartVos.size() >0){
                Map<String, Object> countCartAmount = orderService.getOrderPrice(cartVos.get(0).getUserId(), null, null, null, cartIds, null, null, null, null);
                List<OrderAmount> orderAmounts = (List<OrderAmount>) (countCartAmount.get("listMap"));
                freight = goodsToService.getGoodsFreight(cityName, cartIds, couponId, cartVos, orderAmounts);
            }else {
                freight = Maps.newHashMap();
                freight.put("freight",0.00);
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
     * H5支付宝
     */
    @GetMapping("/phoneH5AiPayPage")
    public void phoneH5AiPayPage(HttpServletResponse httpResponse, @RequestParam String orderSn) {
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", Alipayh5Contents.partner, Alipayh5Contents.privatekey, "json", "utf-8", Alipayh5Contents.alipublickey, "RSA");
//        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//        try {
//            OrderToVo ol = orderService.findByOrderSn(orderSn);
//            StringBuilder stringBuffer = new StringBuilder();
//            BigDecimal bigDecimal;
//            if (ol != null) {
//                List<OrderGoods> goodsByOrderList = orderService.findByOrderId(ol.getOrderId());
//                for (OrderGoods orderHGoods : goodsByOrderList) {
//                    stringBuffer.append(orderHGoods.getGoodsName());
//                }
//                double orderAmount;
//                if (ol.getIsReserve() == 1) {
//                    //第一阶段支付了,取第二阶段的金额
//                    if (ol.getOneIsPay() == 1) {
//                        orderAmount = ol.getReserveTwoAmount();
//                    } else {
//                        orderAmount = ol.getReserveOneAmount();
//                    }
//
//                } else {
//                    orderAmount = ol.getOrderAmount();
//                }
//                if (orderAmount <= 0) {
//                    ossOrderService.addZeroPay(ol);
//                    httpResponse.getWriter().print("支付成功");
//                }
//                // 总金额
//                bigDecimal = new BigDecimal(orderAmount);
//            } else {
//                return;
//            }
//            alipayRequest.setReturnUrl(Alipayh5Contents.alipayh5backUrl + "?id=" + ol.getOrderId());
//            //在公共参数中设置回跳和通知地址
//            alipayRequest.setNotifyUrl(Alipayh5Contents.alipayh5frontUrl);
//            //填充业务参数
//            alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"" + orderSn + "\"," + "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":" + bigDecimal.doubleValue() + "," + "    \"subject\":\"德达康健商品\"," + "    \"body\":\"" + stringBuffer.toString() + "\"," + "    \"extend_params\":{" + "    \"sys_service_provider_id\":\"" + Alipayh5Contents.Pid + "\"" + "    }" + "  }");
//            //调用SDK生成表单
//            String form = alipayClient.pageExecute(alipayRequest).getBody();
//            httpResponse.setContentType("text/html;charset=" + "utf-8");
//            //直接将完整的表单html输出到页面
//            httpResponse.getWriter().write(form);
//            httpResponse.getWriter().flush();
//            httpResponse.getWriter().close();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
    }



    /**
     * 获取购买服务商的价格
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
            @ApiImplicitParam(name="merchantCityId", value = "服务商购买的城市", required = true, paramType="query",dataType = "String"),
    })
    public Object findPoviderPrice(Long merchantCityId){
        log.info("获取购买服务商的价格，城市ID为：{}",merchantCityId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("price",userInfoService.findPoviderPrice(merchantCityId));
        return jsonObject;
    }

    /**
     * 更改服务商邀请人ID
     * @param userId
     * @return
     */
    @ApiImplicitParams({
        @ApiImplicitParam(name="userId", value = "邀请人UserId", required = true, paramType="query",dataType = "String"),
        @ApiImplicitParam(name="type", value = "类型（1-邀请购买服务商、2-邀请购买商品）", required = true, paramType="query",dataType = "int"),
        @ApiImplicitParam(name="goodsId", value = "商品ID（type为2的时候必传）", required = true, paramType="query",dataType = "int")
    })
    @ApiOperation(notes = "", value = "更改邀请人ID")
    @PostMapping("/updatePoviderInvitation")
    public void updatePoviderInvitation(String userId,Integer type,String goodsId) throws BusinessException {
        log.info("更改服务商邀请人ID：{}",userId);
        MemberLoginInfoModel model = RequestUtils.getMemberLoginInfo();
        if(type == 1){
            redisClient.stringSet(RedisEnum.DB_2.ordinal(),Constant.REDIS_INVITATION_MEMBER_POVIDER_CACHE+model.getUserId(),userId,0);
        }else if(type == 2){
            if(StringUtils.isBlank(goodsId)){
                throw new BusinessException(BusinessCode.PARAM_ERROR.getDescription());
            }
            redisClient.stringSet(RedisEnum.DB_2.ordinal(),Constant.REDIS_INVITATION_MEMBER_GOODSIDANDUSERID_CACHE+model.getUserId()+goodsId,userId,0);
        }
    }
}
