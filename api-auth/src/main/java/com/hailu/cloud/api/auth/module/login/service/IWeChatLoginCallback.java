package com.hailu.cloud.api.auth.module.login.service;

import com.hailu.cloud.api.auth.module.login.model.WeChatUserInfoResponse;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.auth.WeChatAuthResponse;

/**
 * 登录验证回调处理
 */
public interface IWeChatLoginCallback {

    /**
     * 根据微信unionId查询是否绑定平台账号
     *
     * @param unionId
     * @return
     */
    boolean exists(String unionId);

    /**
     * 获取用户ID
     *
     * @return
     */
    String getUserId();

    /**
     * 该账号是否启用
     *
     * @return
     */
    boolean isEnable();

    /**
     * 其他校验
     *
     * @return
     * @throws BusinessException
     */
    void extendValidate() throws BusinessException;

    /**
     * 将生成的token保存到用户信息返回给客户端
     *
     * @param accessToken
     * @param refreshToken
     * @param weChatLoginInfoModel
     * @param weChatUserInfoModel
     * @return
     */
    Object handle(
            String accessToken,
            String refreshToken,
            WeChatAuthResponse weChatLoginInfoModel,
            WeChatUserInfoResponse weChatUserInfoModel);

    /**
     * 返回前排除一些字段
     *
     * @return
     */
    Object beforeReturnExcludeField();
}