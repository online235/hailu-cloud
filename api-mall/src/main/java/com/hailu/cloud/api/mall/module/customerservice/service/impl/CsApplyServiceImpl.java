package com.hailu.cloud.api.mall.module.customerservice.service.impl;

import com.hailu.cloud.api.mall.module.customerservice.dao.CsApplyDao;
import com.hailu.cloud.api.mall.module.customerservice.dao.CsApplyProressDao;
import com.hailu.cloud.api.mall.module.customerservice.entity.CsApplyEntity;
import com.hailu.cloud.api.mall.module.customerservice.service.ICsApplyService;
import com.hailu.cloud.api.mall.module.customerservice.vo.*;
import com.hailu.cloud.api.mall.module.goods.dao.OrderMapper;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderInfo;
import com.hailu.cloud.api.mall.module.goods.entity.order.OrderToVo;
import com.hailu.cloud.api.mall.module.goods.tool.StringUtil;
import com.hailu.cloud.api.mall.module.goods.vo.AddressVo;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class CsApplyServiceImpl implements ICsApplyService {

    @Autowired
    private OrderMapper orderDao;

    @Autowired
    private CsApplyDao csApplyDao;


    @Autowired
    private CsApplyProressDao csApplyProressDao;

    /***
     * 返回该订单明细是否正在申请售后
     */
    @Override
    public CsApplyEntity getCsApply(Integer orderId, Integer orderGoodsId) {
        CsApplyEntity csApplyEntity = csApplyDao.getCsApply(orderId, orderGoodsId);
        return csApplyEntity;
    }

    /**
     * 获取可售后服务的订单列表
     */
    @Override
    public List<CSOrderListVo> getCSOrdersList(String userId, int page, int row) {
        return orderDao.getCSOrdersList(userId, page, row);
    }

    /**
     * @author 王必林
     * 根据订单id得到该订单下的所有商品
     */
    @Override
    public List<CSOrderGoods> findByOrderIdCS(int orderId) {

        return orderDao.findByOrderIdCS(orderId);
    }

    /**
     * 添加售后申请订单订单
     */
    @Override
    public int addCsApply(CsApplyEntity csApplyEntity) throws Exception {
        //添加服务订单号
        csApplyEntity.setCsNumber(getCsApplyNumber());
        CSOrderGoods cSOrderGoods = csApplyDao.getCSOrderGoods(csApplyEntity.getOrderGoodsId());
        Long l = System.currentTimeMillis();
        csApplyEntity.setGoodsId(cSOrderGoods.getGoodsId());
        csApplyEntity.setGoodsNum(cSOrderGoods.getGoodsNum());
        csApplyEntity.setTpState(0);
        csApplyEntity.setCreateName(cSOrderGoods.getBuyerId());
        csApplyEntity.setCreateDate(l);
        csApplyEntity.setUpdateName(cSOrderGoods.getBuyerId());
        csApplyEntity.setUpdateDate(l);
        int i = csApplyDao.addCsApply(csApplyEntity);
        if (i > 0) {
            CsApplyProgressVo csApplyProgressVo = new CsApplyProgressVo();
            csApplyProgressVo.setCsApplyId(csApplyEntity.getCsApplyId());
            csApplyProgressVo.setAuditContent("审核中 您的服务单已申请成功，待售后审核中");
            csApplyProgressVo.setDeleteStatus(0);
            csApplyProgressVo.setCreateDate(l);
            csApplyProgressVo.setCreateName(cSOrderGoods.getBuyerId());
            csApplyProgressVo.setUpdateName(cSOrderGoods.getBuyerId());
            csApplyProgressVo.setUpdateDate(l);
            csApplyProgressVo.setTpState(0);
            csApplyProressDao.addApplyProgress(csApplyProgressVo);
            //售后类型(0维修，1退货，2换货,3.整单退款)
            int csType = csApplyEntity.getCsType();
            /**********整单退款 由后台更改**************/
            if (csType == 1) {
                //修改订单 refund_state 退款状态:1是无退款,2是部分退款,3是全部退款  return_state ：退货状态:1是无退货,2是部分退货,3是全部退货
                int orderId = cSOrderGoods.getOrderId();
                int refundState = 2;
                int returnState = 2;
                csApplyDao.updateOrderRefund(refundState, returnState, orderId);
            }
        }
        return i;
    }

    /**
     * 获取提交售后的订单的明细
     */
    @Override
    public CsApplyGoods getCyApplyGoods(Integer orderGoodsId) {
        CSOrderGoods cSOrderGoods = csApplyDao.getCSOrderGoods(orderGoodsId);
        CsApplyGoods cyApplyGoods = new CsApplyGoods();
        OrderToVo orderToVo = csApplyDao.findByOrderId(cSOrderGoods.getOrderId());
        cyApplyGoods.setOrderSn(orderToVo.getOrderSn());
        cyApplyGoods.setOrderId(orderToVo.getOrderId());
        cyApplyGoods.setGoodsName(cSOrderGoods.getGoodsName());
        cyApplyGoods.setGoodsNum(cSOrderGoods.getGoodsNum());
        cyApplyGoods.setGoodsId(cSOrderGoods.getGoodsId());
        try {
            AddressVo shoppingAddressVo = orderDao.getAddressById(orderToVo.getAddressId());
            //收货人
            String person = shoppingAddressVo.getPerson();
            //联系方式
            String phone = shoppingAddressVo.getPhone();
            //详细地址
            String address = shoppingAddressVo.getAddress();
            cyApplyGoods.setConsignee(person);
            cyApplyGoods.setAddresseePhn(phone);
            cyApplyGoods.setReceivingAddress(address);
        } catch (Exception e) {
        }
        String goodsImges = cSOrderGoods.getGoodsImage();
        if (StringUtil.isNotEmpty(goodsImges) && !("http").equals(goodsImges.substring(0, 4))) {
            cyApplyGoods.setGoodsImage(Const.PRO_URL + goodsImges);
        }
        cyApplyGoods.setGoodsImage(goodsImges);
        //服务类型
        List<Integer> csTypes = new ArrayList<Integer>();
        //售后类型(0维修，1退货，2换货,3.整单退款)
        csTypes.add(0);
        //订单完成时间
        Long l = orderToVo.getAccomplishTime();
        //退货时间
        String returnsTime = csApplyDao.getAttributeValue("returns_time");
        //判断是否可已退货
        Long returns = Long.parseLong(returnsTime) * 86400000;
        long d = System.currentTimeMillis();
        if ((returns + l) >= d) {
            csTypes.add(1);
        }
        //换货时间
        String barterTime = csApplyDao.getAttributeValue("barter_time");
        //判断是否可已换货
        Long barter = Long.parseLong(barterTime) * 86400000;
        if ((barter + l) >= d) {
            csTypes.add(2);
        }

        cyApplyGoods.setCsTypes(csTypes);
        cyApplyGoods.setOrderGoodsId(orderGoodsId);
        //运费
        cyApplyGoods.setFreight(orderToVo.getFreight());
        //订单应付金额
        cyApplyGoods.setOrderAmount(orderToVo.getOrderAmount());
        return cyApplyGoods;
    }

    /**
     * 获取 售后记录列表
     */
    @Override
    public List<CsApplyListVo> getCsApplyList(String userId, Integer page, Integer row) {
        List<CsApplyEntity> list = csApplyDao.getCsApplyList(userId, page, row);
        List<CsApplyListVo> csList = new ArrayList<CsApplyListVo>();
        for (CsApplyEntity vo : list) {
            CsApplyListVo csApplyListVo = new CsApplyListVo();
            CSOrderGoods cSOrderGoods = csApplyDao.getCSOrderGoods(vo.getOrderGoodsId());
            csApplyListVo.setGoodsImage(cSOrderGoods.getGoodsImage());
            csApplyListVo.setGoodsName(cSOrderGoods.getGoodsName());
            csApplyListVo.setGoodsNum(cSOrderGoods.getGoodsNum());
            // 售后订单号
            csApplyListVo.setCsNumber(vo.getCsNumber());
            csApplyListVo.setTpState(vo.getTpState());
            //售后申请id主键
            csApplyListVo.setCsApplyId(vo.getCsApplyId());
            csApplyListVo.setCsType(vo.getCsType());
            csApplyListVo.setCreateDate(vo.getCreateDate());
            List<CsApplyProgressVo> csApplyProgressList = csApplyProressDao.findByApplyProgressId(vo.getCsApplyId());
            if (csApplyProgressList.size() > 0) {
                csApplyListVo.setAuditContent(csApplyProgressList.get(0).getAuditContent());
            }
            csList.add(csApplyListVo);
        }
        return csList;
    }

    /**
     * 获取售后明细
     */
    @Override
    public CsApplyVo csApplyById(Integer csApplyId) {
        CsApplyEntity csApplyEntity = csApplyDao.csApplyById(csApplyId);
        CsApplyVo csApplyVo = new CsApplyVo();
        //售后申请id主键
        csApplyVo.setCsApplyId(csApplyId);
        // 描述
        csApplyVo.setRemarks(csApplyEntity.getRemarks());
        // 售后类型(0维修，1退货，2换货,3.整单退款)
        csApplyVo.setCsType(csApplyEntity.getCsType());
        // 退款金额
        csApplyVo.setRefundAmount(csApplyEntity.getRefundAmount());
        //	审核留言
        csApplyVo.setReviewMessage(csApplyEntity.getReviewMessage());
        /**
         *
         * 维修流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4)，（5）取消申请，（6）商城拒绝 (7)商城拒绝完成 ;
         * 换货流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4),（5）取消申请，（6）商城拒绝;(7)商城拒绝完成 ;
         * 七天无理由退货: 提交申请(0)，商城审核(1)，收货(2)，退款(3)，完成(4) ，（5）取消申请，（6）商城拒绝）(7)商城拒绝完成 ;
         *
         */
        //审核 //售后进度
        csApplyVo.setTpState(csApplyEntity.getTpState());
        //  	0未删除1删除
        csApplyVo.setDeleteStatus(csApplyEntity.getDeleteStatus());
        //创建时间            	//申请时间
        csApplyVo.setCreateDate(csApplyEntity.getCreateDate());
        // 售后订单号
        csApplyVo.setCsNumber(csApplyEntity.getCsNumber());
        //用户Id    //服务单号
        csApplyVo.setUserId(csApplyEntity.getUserId());
        //差价原因          //差价原因
        csApplyVo.setCauseDifference(csApplyEntity.getCauseDifference());
        Integer orderId = csApplyEntity.getOrderId();
        OrderInfo orderInfo = orderDao.getOrderInfo(orderId);
        //订单号
        String orderSn = orderInfo.getOrderSn();
        Integer orderPayType = csApplyDao.shopOrderPayType(orderSn);
        String payType = "";
        if (orderPayType == null) {

        } else if (orderPayType == 1) {
            payType = "支付宝";
        } else if (orderPayType == 2) {
            payType = "微信";
        } else if (orderPayType == 3) {
            payType = "微信";
        }
        //支付方式
        csApplyVo.setPayType(payType);
        //private     String         payType;//1支付宝 ,2微信 ,3微信 	//支付方式
        //（5）取消申请，（6）商城拒绝）
        Integer tpStatue = csApplyVo.getTpState();
        if (tpStatue == 5) {
            List<CsApplyProgressVo> csApplyProgressList = csApplyProressDao.findByApplyProgressId2(csApplyId);
            csApplyVo.setCsApplyProgressList(csApplyProgressList);
            if (csApplyProgressList.size() > 0) {
                csApplyVo.setAuditContent(csApplyProgressList.get(0).getAuditContent());
            }

        } else if (tpStatue == 6) {
            List<CsApplyProgressVo> csApplyProgressList = csApplyProressDao.findByApplyProgressId3(csApplyId);
            csApplyVo.setCsApplyProgressList(csApplyProgressList);
            if (csApplyProgressList.size() > 0) {
                csApplyVo.setAuditContent(csApplyProgressList.get(0).getAuditContent());
            }
        } else {
            List<CsApplyProgressVo> csApplyProgressList = csApplyProressDao.findByApplyProgressId(csApplyId);
            csApplyVo.setCsApplyProgressList(csApplyProgressList);
            if (csApplyProgressList.size() > 0) {
                csApplyVo.setAuditContent(csApplyProgressList.get(0).getAuditContent());
            }
        }
        return csApplyVo;
    }

    /**
     * 更具订单获取获取售后明细
     */
    @Override
    public CsApplyVo csApplyByOrderId(String userId, Integer orderId) {
        List<CsApplyEntity> csApplyList = csApplyDao.csApplyByOrderId(userId, orderId);
        if (csApplyList.size() > 0) {
            CsApplyEntity csApplyEntity = csApplyList.get(0);
            CsApplyVo csApplyVo = new CsApplyVo();
            //售后申请id主键
            csApplyVo.setCsApplyId(csApplyEntity.getCsApplyId());
            // 描述原因
            csApplyVo.setRemarks(csApplyEntity.getCsReason());
            // 售后类型(0维修，1退货，2换货,3.整单退款)
            csApplyVo.setCsType(csApplyEntity.getCsType());
            // 退款金额   //退款金额
            csApplyVo.setRefundAmount(csApplyEntity.getRefundAmount());
            //	审核留言//审核留言
            csApplyVo.setReviewMessage(csApplyEntity.getReviewMessage());
            /**
             *
             * 维修流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4)，（5）取消申请，（6）商城拒绝 (7)商城拒绝完成 ;
             * 换货流程:提交申请(0)，商城审核(1)，收货(2)，处理中(3)，完成(4),（5）取消申请，（6）商城拒绝;(7)商城拒绝完成 ;
             * 七天无理由退货: 提交申请(0)，商城审核(1)，收货(2)，退款(3)，完成(4) ，（5）取消申请，（6）商城拒绝）(7)商城拒绝完成 ;
             *
             */
            //审核 //售后进度
            csApplyVo.setTpState(csApplyEntity.getTpState());
            //  	0未删除1删除
            csApplyVo.setDeleteStatus(csApplyEntity.getDeleteStatus());
            //创建时间            	//申请时间
            csApplyVo.setCreateDate(csApplyEntity.getCreateDate());
            // 售后订单号
            csApplyVo.setCsNumber(csApplyEntity.getCsNumber());
            //用户Id    //服务单号
            csApplyVo.setUserId(csApplyEntity.getUserId());
            //差价原因          //差价原因
            csApplyVo.setCauseDifference(csApplyEntity.getCauseDifference());

            //private     String         payType;//1支付宝 ,2微信 ,3微信 	//支付方式
            List<CsApplyProgressVo> csApplyProgressList = csApplyProressDao.findByApplyProgressId(csApplyEntity.getCsApplyId());
            csApplyVo.setCsApplyProgressList(csApplyProgressList);
            return csApplyVo;
        } else {
            return null;
        }


    }

    /**
     * 取消 订单
     */
    @Override
    public Boolean cancelCsApply(Integer csApplyId) throws BusinessException {
        CsApplyEntity csApplyEntity = csApplyDao.csApplyById(csApplyId);
        if (csApplyEntity.getTpState() == 3 || csApplyEntity.getTpState() == 4 || csApplyEntity.getTpState() == 6) {
            throw new BusinessException("该申请平台已处理，不可取消");
        }
        int refundState = 1;
        int returnState = 1;
        csApplyDao.updateOrderRefund(refundState, returnState, csApplyEntity.getOrderId());
        CsApplyProgressVo csApplyProgressVo = new CsApplyProgressVo();
        csApplyProgressVo.setCsApplyId(csApplyEntity.getCsApplyId());
        csApplyProgressVo.setAuditContent("您的已取消售后服务订单。");
        csApplyProgressVo.setDeleteStatus(0);
        Long l = System.currentTimeMillis();
        csApplyProgressVo.setCreateDate(l);
        csApplyProgressVo.setCreateName(csApplyEntity.getUserId());
        csApplyProgressVo.setUpdateName(csApplyEntity.getUserId());
        csApplyProgressVo.setUpdateDate(l);
        csApplyProgressVo.setTpState(5);
        csApplyProressDao.addApplyProgress(csApplyProgressVo);
        int i = csApplyDao.cancelCsApply(csApplyId);
        if (i == 0) {
            throw new BusinessException("取消售后申请失败");
        }
        return true;
    }

    /**
     * 添加退款订单
     */
    @Override
    public int orderRefund(CsApplyEntity csApplyEntity) throws Exception {
        int orderId = csApplyEntity.getOrderId();
        OrderToVo orderToVo = csApplyDao.findByOrderId(orderId);
        //添加服务订单号
        csApplyEntity.setCsNumber(getCsApplyNumber());
        csApplyEntity.setUserAmount(orderToVo.getOrderAmount());
        csApplyEntity.setTpState(0);
        csApplyEntity.setCreateName(csApplyEntity.getUserId());
        Long l = System.currentTimeMillis();
        csApplyEntity.setCreateDate(l);
        csApplyEntity.setUpdateName(csApplyEntity.getUserId());
        csApplyEntity.setUpdateDate(l);
        int i = csApplyDao.addCsApply(csApplyEntity);
        if (i > 0) {
            CsApplyProgressVo csApplyProgressVo = new CsApplyProgressVo();
            csApplyProgressVo.setCsApplyId(csApplyEntity.getCsApplyId());
            csApplyProgressVo.setAuditContent("您的服务单已申请成功，待售后审核中");
            csApplyProgressVo.setDeleteStatus(0);
            csApplyProgressVo.setCreateDate(l);
            csApplyProgressVo.setCreateName(csApplyEntity.getUserId());
            csApplyProgressVo.setUpdateName(csApplyEntity.getUserId());
            csApplyProgressVo.setUpdateDate(l);
            csApplyProgressVo.setTpState(0);
            csApplyProressDao.addApplyProgress(csApplyProgressVo);
            int csType = csApplyEntity.getCsType();
            if (csType == 3) {
                int refundState = 3;
                int returnState = 3;
                csApplyDao.updateOrderRefund(refundState, returnState, orderId);
            }
        }
        return i;
    }

    /**
     * 获取订单对象
     */
    @Override
    public OrderToVo findByOrderId(Integer orderId) {
        OrderToVo orderToVo = csApplyDao.findByOrderId(orderId);
        return orderToVo;
    }

    /**
     * 返回售后申请
     */
    @Override
    public CsApplyExpressVo getCsApplyCourierNumber(Integer csApplyId) {
        return csApplyDao.getCsApplyCourierNumber(csApplyId);
    }

    /**
     * 修改快递单号
     */
    @Override
    public Boolean udpateCsApplyCourierNumber(Integer csApplyId, String courierNumber, String courierCompany, String courierCode) {
        csApplyDao.udpateCsApplyCourierNumber(csApplyId, courierNumber, courierCompany, courierCode);
        return true;
    }

    /**
     * 获取所有的物流信息
     */
    @Override
    public List<ExpressVo> getCouriers() {
        return csApplyDao.getCouriers();
    }

    /**
     * 获取售后服务单号
     ***/
    public String getCsApplyNumber() {
        String returnStr = "";
        try {
            /*
             * 返回数据组装
             */
            String tempStr = csApplyDao.getCsApplyNumber();
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
            if (tempStr == null) {
                tempStr = "";
            }
            if (tempStr.length() > 0) {
                String code = tempStr;

                String d = code.substring(2, 10);// 年月日
                if (d.equals(yd)) {// 当天已有过编码
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
        //returnStr = "ZT" + returnStr; // 生产环境
        // 售后
        returnStr = "CS" + returnStr;
        return returnStr;
    }


}
