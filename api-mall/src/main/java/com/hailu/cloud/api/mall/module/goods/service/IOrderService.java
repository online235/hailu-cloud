package com.hailu.cloud.api.mall.module.goods.service;

import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import com.hailu.cloud.api.mall.module.goods.entity.order.*;
import com.hailu.cloud.api.mall.module.goods.vo.FreightVo;
import com.hailu.cloud.api.mall.module.goods.vo.RegionVo;
import com.hailu.cloud.api.mall.module.sys.vo.OrderPay;
import com.hailu.cloud.api.mall.module.sys.vo.OrderToPay;
import com.hailu.cloud.common.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @author Administrator
 */
public interface IOrderService {
    /**
     * 获取省市县
     *
     * @return
     * @throws Exception
     */
    List<RegionVo> regionList(int pid) throws Exception;

    /**
     * 加入购物车
     *
     * @param shoppingCartVo
     * @throws Exception
     */
    void addShoppingCart(ShoppingCartVo shoppingCartVo) throws Exception;

    /**
     * 获取购物车
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<ShoppingCartVo> shoppingList(String userId) throws Exception;

    /**
     * 根据id获取购物车
     *
     * @param cartId
     * @return
     * @throws Exception
     */
    ShoppingCartVo getShoppingCartById(int cartId) throws Exception;

    /**
     * 查询购物车是否有相同规格商品
     *
     * @return
     * @throws Exception
     */
    ShoppingCartVo getShoppingCartByVo(ShoppingCartVo shoppingCartVo) throws Exception;

    /**
     * 更新购物车
     */
    void updateShoppingCart(ShoppingCartVo shoppingCartVo) throws Exception;

    /**
     * 收货地址列表
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<ShoppingAddressVo> shoppingAddressVoList(String userId) throws Exception;

    /**
     * 添加收货地址
     *
     * @param shoppingAddressVo
     * @throws Exception
     */
    Map<String, Object> addShoppingAddress(ShoppingAddressVo shoppingAddressVo);

    /**
     * 修改收货地址
     *
     * @param shoppingAddressVo
     * @throws Exception
     */
    Map<String, Object> updataShoppingAddress(ShoppingAddressVo shoppingAddressVo);

    /**
     * 删除收货地址
     *
     * @param id
     * @throws Exception
     */
    void delShoppingAddress(int id) throws Exception;

    /**
     * 删除订单 (逻辑删除)
     *
     * @throws Exception
     */
    void deleteOrder(int orderId) throws Exception;

    /**
     * 申请记录列表
     *
     * @param orderServiceVo
     * @return
     * @throws Exception
     */
    List<OrderServiceVo> getOrderServices(OrderServiceVo orderServiceVo) throws Exception;

    /**
     * 提交申请
     *
     * @param orderServiceVo
     * @throws Exception
     */
    void addOrderService(OrderServiceVo orderServiceVo) throws Exception;

    /**
     * 取消申请
     *
     * @param orderServiceVo
     * @throws Exception
     */
    void updateOrderService(OrderServiceVo orderServiceVo) throws Exception;

    /**
     * 更新明细
     *
     * @param orderDetailVo
     * @throws Exception
     */
    void upOrderDetail(OrderDetailVo orderDetailVo) throws Exception;

    /**
     * 查询运费
     *
     * @param freightVo
     * @return
     * @throws Exception
     */
    FreightVo getFreight(FreightVo freightVo) throws Exception;

    /**
     * @param userId
     * @param goodsId
     * @param goodsSpecId
     * @param goodsNum
     * @param cartIds
     * @param isReserve
     * @param isLimitTime
     * @param isActivity
     * @return
     * @author 黄亮
     * 得到订单的价格 , 判断是否通过订单验证
     */
    Map<String, Object> getOrderPrice(String userId, Integer goodsId, Integer goodsSpecId, Integer goodsNum,
                                                    String cartIds, Integer isActivity, Integer isLimitTime, Integer isReserve, Integer type) throws Exception;

    /**
     * @param orderId
     * @param orderStatus
     * @return
     * @author 黄亮
     * 更改订单状态
     */
    Map<String, Object> updateOrderStatus(int orderId, Integer orderStatus) throws Exception;

    /**
     * @param userId
     * @param orderStatus
     * @param row
     * @param page
     * @return
     * @author 黄亮
     * 得到订单列表
     */
    List<OrderListVo> getOrdersList(String userId, Integer orderStatus, Integer evaluateState, int page, int row);

    /**
     * @param orderId
     * @return
     * @author 黄亮
     * 根据订单id得到订单中的商品
     */
    List<OrderGoods> findByOrderId(int orderId);

    /**
     * @param orderId
     * @return
     * @author 黄亮
     * 得到订单详情
     */
    Map<String, Object> getOrderInfo(Integer orderId) throws BusinessException;

    /**
     * @param orderSn
     * @return
     * @author 黄亮
     * 根据订单编号得到订单明细
     */
    OrderToVo findByOrderSn(String orderSn);

    /**
     * @param op
     * @author 黄亮
     * 添加訂單詳情
     */
    void addOrderPay(OrderPay op);

    /**
     * @param otp
     * @author 黄亮
     * 更改订单状态
     */
    void updateOrderByPay(OrderToPay otp);

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     * 根据订单商品id得到该商品下的所有赠品
     */
    List<GoodsCompl> getGoodsCompl(int goodsId);

    /**
     * @param userId
     * @return
     * @author 黄亮
     * 根据用户id得到该用户的所有订单状态对应条数
     */
    Map<String, Object> getOrderCount(String userId);

    /**
     * @param orderId
     * @return
     * @author 黄亮
     * 订单支付验证
     */
    Map<String, Object> orderVerification(int orderId);

    /**
     * @param orderId
     * @author 黄亮
     * 更新订单评论状态
     */
    void updatEvaluate(int orderId);

    /**
     * @param addressId
     * @return
     * @author 黄亮
     * 根据收货地址id得到收货地址详情
     */
    Map<String, Object> findAddressInfoById(int addressId);

    /**
     * @param orderNumber
     * @return
     * @AUTHOR HUANGL
     * 得到运费
     */
    Map<String, Object> getExpress(String orderNumber);

    void deleteCarts(String cartIds);

    /**
     * @param sa
     * @AUTHOR HUANGL
     * 添加订单收货地址
     */
    void addOrderAddress(ShoppingAddressVo sa);

    /**
     * 得到订单完成时间
     *
     * @param orderId
     * @return
     */
    long getOrderAccomplishTime(int orderId);

    /**
     * 得到是否有支付
     *
     * @param out_trade_no
     * @return
     */
    int getOrderPayCount(String out_trade_no);

    /**
     * 再来一单加入到购物车
     *
     * @param orderId
     * @return
     */
    Boolean addRecourToCart(int orderId) throws BusinessException;

    /**
     * 得到购物车中的角标
     *
     * @param userId
     * @return
     * @Author huangl
     */
    Map<String, Object> getCartGoodsNum(String userId);

    void deleteFailure(String userId);


    /**
     * 根据购物车ids 获取购物车
     *
     * @Author HuangL
     * @Email huangl96@163.com
     * @Date 9:58 2019/6/18
     */
    List<CartVo> getShoppingCartByIds(String[] split);

    /**
     * 添加直接下单
     *
     * @param orderParam 订单参数
     * @return 返回结果
     */
    Map<String, Object> addBuyOrder(OrderParam orderParam);

    /**
     * 添加购物车下单
     *
     * @param orderParam 订单参数
     * @return 返回结果
     */
    Map<String, Object> addCartOrder(OrderParam orderParam);


}
