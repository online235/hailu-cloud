package com.hailu.cloud.api.mall.module.goods.dao;

import com.hailu.cloud.api.mall.module.customerservice.vo.CSOrderGoods;
import com.hailu.cloud.api.mall.module.customerservice.vo.CSOrderListVo;
import com.hailu.cloud.api.mall.module.goods.entity.goods.GoodsCompl;
import com.hailu.cloud.api.mall.module.goods.entity.order.*;
import com.hailu.cloud.api.mall.module.goods.vo.*;
import com.hailu.cloud.api.mall.module.goods.vo.vm.OrderGoodsVm;
import com.hailu.cloud.api.mall.module.payment.vo.OrderPay;
import com.hailu.cloud.api.mall.module.payment.vo.OrderToPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 订单
 */
@Mapper
public interface OrderMapper {

    /**
     * 获取省市县
     *
     * @return
     * @throws Exception
     */
    List<RegionVo> regionList(int pid) throws Exception;

    /**
     * 获取购物车
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<ShoppingCartVo> shoppingList(String userId) throws Exception;

    /**
     * 查找购物车商品价格
     *
     * @param goodsIds 商品ID列表
     * @param specIds  规格ID列表
     * @return
     * @throws Exception
     */
    List<GoodsSpec> shoppingListPrice(
            @Param("goodsIds") Set<Integer> goodsIds,
            @Param("specIds") Set<Integer> specIds) throws Exception;

    /**
     * 查询购物车是否有相同规格商品
     *
     * @return
     * @throws Exception
     */
    ShoppingCartVo getShoppingCartByVo(ShoppingCartVo shoppingCartVo) throws Exception;

    /**
     * 加购物车
     */
    void addShoppingCart(ShoppingCartVo shoppingCartVo);

    /**
     * 更新购物车
     *
     * @param shoppingCartVo
     * @throws Exception
     */
    void updateShoppingCart(ShoppingCartVo shoppingCartVo) throws Exception;

    /**
     * @param shoppingCartVo
     * @return
     * @throws Exception
     */
    int getShoppingCartCount(ShoppingCartVo shoppingCartVo) throws Exception;

    /**
     * 根据id获取购物车
     *
     * @return
     * @throws Exception
     */
    ShoppingCartVo getShoppingCartById(int cartId) throws Exception;

    /**
     * 删除购物车商品
     *
     * @throws Exception
     */
    void delShoppingCart(int cartId) throws Exception;

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
    void addShoppingAddress(ShoppingAddressVo shoppingAddressVo) throws Exception;

    /**
     * 修改收货地址
     *
     * @param shoppingAddressVo
     * @throws Exception
     */
    void updataShoppingAddress(ShoppingAddressVo shoppingAddressVo);

    /**
     * 删除收货地址
     *
     * @param id
     * @throws Exception
     */
    void delShoppingAddress(int id) throws Exception;

    /**
     * 查找默认地址
     *
     * @param userId
     * @return
     * @throws Exception
     */
    ShoppingAddressVo shoppingAddressVo(String userId);

    /**
     * 查找收货地址
     *
     * @param id
     * @return
     * @throws Exception
     */
    ShoppingAddressVo shoppingAddressVoById(int id) throws Exception;

    /**
     * 生成订单
     *
     * @param order
     * @throws Exception
     */
    void addOrder(OrderToVo order);

    /**
     * 根据订单编号查询订单
     *
     * @param orderNumber
     * @return
     * @throws Exception
     */
    OrderVo orderVo(String orderNumber) throws Exception;

    /**
     * 添加订单明细
     *
     * @param orderDetail
     * @throws Exception
     */
    void addOrderDetail(OrderDetailVo orderDetail) throws Exception;


    /**
     * 添加订单状态记录
     *
     * @param orderStatus
     * @throws Exception
     */
    void addOrderStatus(OrderStatusVo orderStatus) throws Exception;

    /**
     * 根据订单编号获取订单明细
     *
     * @param orderDetail
     * @return
     * @throws Exception
     */
    List<OrderDetailVo> getOrderDetails(OrderDetailVo orderDetail) throws Exception;

    RuleStrVo getRuleStr(int id) throws Exception;

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
     * 查询新品预定人数
     *
     * @param goodsId
     * @return
     * @throws Exception
     */
    int getOrderDetailsCount(int goodsId) throws Exception;

    /**
     * @param orderDetailVo
     * @return
     * @throws Exception
     */
    OrderDetailVo getOrderDetailById(OrderDetailVo orderDetailVo) throws Exception;

    /**
     * 获取订单数
     *
     * @param goodsId
     * @return
     * @throws Exception
     */
    int getGooodsByCount(int goodsId) throws Exception;

    /**
     * @param userId
     * @author 黄亮
     * 更改所有的地址状态
     */
    void updateAddress(String userId);

    /**
     * @param og
     * @author 黄亮
     * 添加订单中的商品
     */
    void addOrderGoods(OrderGoods og);

    /**
     * @param oi
     * @author 黄亮
     * 添加订单发票
     */
    void addOrderInvoiceH(OrderInvoiceHVo oi);

    /**
     * @param goodsId
     * @param startTime
     * @param endTime
     * @param userId
     * @param actId
     * @return
     * @author 黄亮
     * 得到商品购买数量
     */
    Integer getBuyNum(
            @Param("goodsId") int goodsId,
            @Param("specId") int specId,
            @Param("startTime") long startTime,
            @Param("endTime") long endTime,
            @Param("userId") String userId,
            @Param("actId") int actId);

    /**
     * @param orderId
     * @param orderStatus
     * @param time
     * @author 黄亮
     * 更新订单状态
     */
    void updateOrderStatus(
            @Param("orderId") int orderId,
            @Param("orderStatus") Integer orderStatus,
            @Param("time") long time,
            @Param("integral") Integer integral) throws Exception;

    /**
     * @param userId
     * @param orderStatus
     * @param page
     * @param row
     * @return
     * @author 黄亮
     * 根据用户id和订单状态得到订单列表
     */
    List<OrderListVo> getOrdersList(
            @Param("userId") String userId,
            @Param("orderStatus") Integer orderStatus,
            @Param("evaluateState") Integer evaluateState,
            @Param("page") int page,
            @Param("row") int row);

    /**
     * @param orderId
     * @return
     * @author 黄亮
     * 根据订单id得到订单中的商品列表
     */
    List<OrderGoods> findByOrderId(int orderId);

    /**
     * @param orderId
     * @return
     * @author 黄亮
     * 得到订单详情
     */
    OrderInfo getOrderInfo(Integer orderId);

    /**
     * @param orderId
     * @return
     * @author 黄亮
     * 得到订单发票
     */
    OrderInvoiceHVo getOrderInvoiceH(Integer orderId);

    /**
     * @param addressId
     * @return
     * @author 黄亮
     * 得到收货地址
     */
    AddressVo getAddressById(Integer addressId);

    /**
     * @param orderSn
     * @return
     * @author 黄亮
     * 根据订单编号的得到订单明细
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
     * 更改訂單支付狀態
     */
    void updateOrderByPay(OrderToPay otp);

    /**
     * @param goodsId
     * @return
     * @author 黄亮
     * 根据订单商品id得到商品下的所有赠品
     */
    List<GoodsCompl> getGoodsCompl(int goodsId);

    /**
     * @param userId
     * @param os
     * @return
     * @author 黄亮
     * 根据用户id和订单状态得到对应的条数
     */
    Integer findByUserIdAndOs(@Param("userId") String userId, @Param("os") int os);

    /**
     * 根据用户id查询到对应状态的条数
     * @param userId
     * @return
     */
    List<Map<String,Object>> findCountStatusByUserId(@Param("userId") String userId);



    /**
     * @param userId
     * @param os
     * @return
     * @author 黄亮
     * 根据用户id和订单状态得到对应的未评价数
     */
    Integer findByUserIdAndEvaluate(@Param("userId") String userId, @Param("os") int os);

    /**
     * @param orderId
     * @author 黄亮
     * 根据订单id改变订单状态
     */
    void updateOrderState(int orderId);

    /**
     * @param orderId
     * @author 黄亮
     * 更新订单评价
     */
    void updatEvaluate(int orderId);

    /**
     * @param addressId
     * @return
     * @author 黄亮
     * 得到收货地址详细信息
     */
    ShoppingAddressVo findAddressInfoById(int addressId);

    /**
     * @param orderId
     * @param couponId
     * @param couponPrice
     * @param newOrderAmount
     * @param newDisAmount
     * @AUTHOR HUANGL
     * 更新订单优惠卷金额
     */
    void updateOrderAmount(
            @Param("orderId") int orderId,
            @Param("couponId") Integer couponId,
            @Param("couponPrice") Double couponPrice,
            @Param("newOrderAmount") BigDecimal newOrderAmount,
            @Param("newDisAmount") BigDecimal newDisAmount);

    /**
     * @param orderId
     * @param newOrderAmount
     * @AUTHOR HUANGL
     * 更新订单金额
     */
    void updateActOrderAmount(
            @Param("orderId") Integer orderId,
            @Param("newOrderAmount") BigDecimal newOrderAmount);


    /**
     * @param userId
     * @param page
     * @param row
     * @return
     * @author 王必林
     * 根据用户id得到可以售后服务订单列表
     */
    List<CSOrderListVo> getCSOrdersList(
            @Param("userId") String userId,
            @Param("page") int page,
            @Param("row") int row);


    /**
     * @param orderId
     * @return
     * @author 王必林
     * 根据订单id得到订单中的商品列表
     */
    List<CSOrderGoods> findByOrderIdCS(@Param("orderId") int orderId);

    /**
     * @param orderNumber
     * @return
     * @AUTHOR HUANGL
     * 得到快递
     */
    Map<String, Object> getExpress(String orderNumber);

    /**
     * 添加訂單收貨地址
     *
     * @AUTHOR HUANGL
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
     * 得到該訂單支付條數
     *
     * @param out_trade_no
     * @return
     */
    int getOrderPayCount(String out_trade_no);

    /**
     * 得到订单支付金额
     *
     * @param orderSn
     * @return
     */
    List<OrderPay> findOrderPayByOrderSn(String orderSn);

    /**
     * 得到购物车的角标
     *
     * @param userId
     * @return
     * @Author huangl
     */
    Map<String, Object> getCartGoodsNum(String userId);

    /**
     * 批量添加到购物车
     *
     * @param orderGoods
     * @Author huangl
     */
    void addListToCart(List<OrderGoods> orderGoods);

    /**
     * 清除失效宝贝
     *
     * @param userId
     */
    void deleteFailureUId(String userId);

    /**
     * @Author HuangL
     * @Description
     * @Date 2018-11-01_18:19
     */
    void deleteShoppingCarts(@Param("split") String[] split);

    /**
     * 根据购物车ids获取购物车数据
     *
     * @Author HuangL
     * @Email huangl96@163.com
     * @Date 9:59 2019/6/18
     */
    List<CartVo> getShoppingCartByIds(String[] split);

    List<OrderGoodsVm> findOrderVmByOrderId(@Param("orderId") Integer orderId);

}
