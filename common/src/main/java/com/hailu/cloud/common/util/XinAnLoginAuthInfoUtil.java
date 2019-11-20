package com.hailu.cloud.common.util;

import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;

import javax.servlet.http.HttpServletRequest;

public class XinAnLoginAuthInfoUtil {

    public static MemberLoginInfoModel LoginInfo(HttpServletRequest request){
        AuthInfo authInfo = (AuthInfo) request.getAttribute(Constant.REQUEST_ATTRIBUTE_CURRENT_USER);
        if (authInfo.getLoginType() == 0) {
             return  (MemberLoginInfoModel) authInfo.getUserInfo();
        }
        return null;
    }
}
