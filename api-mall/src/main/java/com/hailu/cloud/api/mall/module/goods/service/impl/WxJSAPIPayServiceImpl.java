package com.hailu.cloud.api.mall.module.goods.service.impl;


import com.hailu.cloud.api.mall.module.goods.service.IWxPayService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Administrator
 */
public class WxJSAPIPayServiceImpl implements IWxPayService {

    @Override
    public Map<String, String> pay(String orderid, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean payNotify(String orderNumber) {
        // TODO Auto-generated method stub
        return false;
    }

}
