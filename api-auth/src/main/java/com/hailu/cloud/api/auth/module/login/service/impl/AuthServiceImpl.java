package com.hailu.cloud.api.auth.module.login.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hailu.cloud.api.auth.module.login.dao.AdminMapper;
import com.hailu.cloud.api.auth.module.login.dao.MemberMapper;
import com.hailu.cloud.api.auth.module.login.dao.MerchantMapper;
import com.hailu.cloud.api.auth.module.login.service.IAuthService;
import com.hailu.cloud.api.auth.module.login.service.ILoginCallback;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.enums.LoginTypeEnum;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.exception.RefreshTokenExpiredException;
import com.hailu.cloud.common.function.RFunction;
import com.hailu.cloud.common.model.auth.AdminLoginInfoModel;
import com.hailu.cloud.common.model.auth.AuthInfo;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.merchant.StoreInformationModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.security.AuthInfoParseTool;
import com.hailu.cloud.common.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author zhijie
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private RedisStandAloneClient redisClient;

    /**
     * 心安会员账号/商城会员账号
     */
    @Resource
    private MemberMapper memberMapper;

    /**
     * 商户账号
     */
    @Resource
    private MerchantMapper merchantMapper;

    /**
     * 管理员账号查询
     */
    @Resource
    private AdminMapper adminMapper;

    /**
     * 是否启用全局万能验证码
     */
    @Value("${dev.enable.global-veri-code:false}")
    private boolean enableGlobalVeriCode;

    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;

    @Override
    public String refreshAccessToken(String refreshToken) throws RefreshTokenExpiredException, BusinessException {
        // 验证token是否有效
        DecodedJWT decodedJwt = JwtUtil.verifierToken(refreshToken);
        if (decodedJwt == null) {
            throw new BusinessException("无效的refreshToken");
        }
        // 根据token获取redis value
        String token = decodedJwt.getClaim(Constant.JWT_TOKEN).asString();
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + token;
        String redisUserInfoJsonValue = redisClient.stringGet(refreshTokenRedisKey);
        if (StringUtils.isBlank(redisUserInfoJsonValue)) {
            throw new RefreshTokenExpiredException("RefreshToken已失效，请重新登录");
        }
        // 判断token是否过期
        AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, decodedJwt);
        Date current = new Date();
        Date expire = DateUtil.date(authInfo.getRefreshTokenExpire());
        if (DateUtil.compare(expire, current) < 0) {
            throw new RefreshTokenExpiredException("RefreshToken已失效，请重新登录");
        }
        // 更新token
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        redisClient.deleteKey(Constant.REDIS_KEY_AUTH_INFO + authInfo.getAccessToken());
        String accessToken = IdUtil.simpleUUID();
        int loginType = decodedJwt.getClaim(Constant.JWT_LOGIN_TYPE).asInt();
        authInfo.setAccessToken(JwtUtil.createToken(accessToken, loginType));
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());
        AuthInfoParseTool.updateAuthInfoToken(authInfo, authInfo.getAccessToken(), null);

        // 存储到redis,并设置有效期
        String authJson = JSON.toJSONString(authInfo);
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        redisClient.stringSet(accessTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        redisClient.stringSet(refreshTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_SEVEN_DAYS);
        return authInfo.getAccessToken();
    }

    @Override
    public Object vericodeLogin(Integer loginType, String phone, String code) throws BusinessException {
        if (!enableGlobalVeriCode) {
            String vericodeRedisKey = Constant.REDIS_KEY_VERIFICATION_CODE + phone + loginType;
            String redisCode = redisClient.stringGet(vericodeRedisKey);
            if (!code.equals(redisCode)) {
                throw new BusinessException("验证码不正确或已过期");
            }
            // 删除redis里的验证码
            redisClient.deleteKey(vericodeRedisKey);
        }
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.of(loginType);
        switch (loginTypeEnum) {
            case ADMIN:
                throw new BusinessException("该账号 " + phone + " 暂时不支持验证码登录");
            case MERCHANT:
                return loginHandle(loginType, phone, merchantUserVericodeLoginCallback());
            case XINAN_AND_MALL:
                return loginHandle(loginType, phone, memberVericodeLoginCallback());
            default:
                break;
        }
        throw new BusinessException("该用户 " + phone + " 被禁止使用验证密码登录");
    }

    @Override
    public Object login(Integer loginType, String account, String pwd) throws BusinessException {
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.of(loginType);
        switch (loginTypeEnum) {
            case ADMIN:
                return loginHandle(loginType, account, adminLoginCallback(pwd));
            case MERCHANT:
                return loginHandle(loginType, account, merchantLoginCallback(pwd));
            case XINAN_AND_MALL:
                return loginHandle(loginType, account, xinanLoginCallback(pwd));
            default:
                break;
        }
        throw new BusinessException("该用户 " + account + " 被禁止使用账号密码登录");
    }

    @Override
    public void logout(String refreshToken) throws BusinessException {
        DecodedJWT decodedJwt = JwtUtil.verifierToken(refreshToken);
        if (decodedJwt == null) {
            throw new BusinessException("无效的refreshToken");
        }
        String token = decodedJwt.getClaim(Constant.JWT_TOKEN).asString();
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + token;
        String redisUserInfoJsonValue = redisClient.stringGet(refreshTokenRedisKey);
        if (StringUtils.isBlank(redisUserInfoJsonValue)) {
            return;
        }

        AuthInfo authInfo = AuthInfoParseTool.parse(redisUserInfoJsonValue, decodedJwt);
        if (authInfo == null) {
            return;
        }
        // 清理redis缓存
        String redisToken = JwtUtil.extractToken(authInfo.getAccessToken(), Constant.JWT_TOKEN);
        if (redisToken == null) {
            return;
        }
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + redisToken;
        redisClient.deleteKey(accessTokenRedisKey, refreshTokenRedisKey);
    }

    private Object loginHandle(Integer loginType, String account, ILoginCallback callback) throws BusinessException {
        boolean exists = callback.exists(account);
        if (!exists) {
            throw new BusinessException("账号不存在");
        }
        if (!callback.isEnable()) {
            throw new BusinessException("该账号已被注销");
        }
        // 添加一些其他校验
        callback.extendValidate();
        String userId = callback.getUserId();
        return generateAuthInfo(loginType, userId, callback::handle, callback::beforeReturnExcludeField);
    }

    // region 登录处理

    /**
     * 生成token相关信息
     *
     * @param userId   用户ID
     * @param callback
     * @return
     * @throws BusinessException
     */
    private Object generateAuthInfo(Integer loginType, String userId, BiFunction<String, String, Object> callback, RFunction excludeField) throws BusinessException {
        // 生成认证信息存储于redis, accessToken有效期2小时， refreshToken有效期7天
        Date current = new Date();
        AuthInfo authInfo = new AuthInfo();
        authInfo.setUserId(userId);
        authInfo.setLoginType(loginType);

        String accessToken = IdUtil.simpleUUID();
        Date accessTokenExpire = DateUtil.offset(current, DateField.HOUR_OF_DAY, 2);
        authInfo.setAccessToken(JwtUtil.createToken(accessToken, loginType));
        authInfo.setAccessTokenExpire(accessTokenExpire.getTime());

        String refreshToken = IdUtil.simpleUUID();
        Date refreshTokenExpire = DateUtil.offset(current, DateField.DAY_OF_MONTH, 7);
        authInfo.setRefreshToken(JwtUtil.createToken(refreshToken, loginType));
        authInfo.setRefreshTokenExpire(refreshTokenExpire.getTime());

        Object userInfo = callback.apply(authInfo.getAccessToken(), authInfo.getRefreshToken());
        authInfo.setUserInfo(userInfo);

        // 存储到redis,并设置有效期
        String refreshTokenRedisKey = Constant.REDIS_KEY_REFRESH_TOKEN_STORE + refreshToken;
        String accessTokenRedisKey = Constant.REDIS_KEY_AUTH_INFO + accessToken;
        String authJson = JSON.toJSONString(authInfo);
        redisClient.stringSet(accessTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_TWO_HOUR);
        redisClient.stringSet(refreshTokenRedisKey, authJson, Constant.REDIS_EXPIRE_OF_SEVEN_DAYS);
        return excludeField.apply();
    }

    // region 验证码登录回调处理

    private ILoginCallback merchantUserVericodeLoginCallback() {
        return new AbstractLoginCallback() {

            private MerchantUserLoginInfoModel loginInfoModel;

            @Override
            public boolean exists(String account) {
                loginInfoModel = merchantMapper.findUserByPhone(account);
                boolean exists = false;
                if (loginInfoModel != null) {
                    exists = true;
                    List<StoreInformationModel> storeList = merchantMapper.findUserStore(Long.valueOf(loginInfoModel.getNumberid()));
                    loginInfoModel.setStores(storeList);
                }
                return exists;
            }

            @Override
            public String getUserId() {
                return String.valueOf(loginInfoModel.getNumberid());
            }

            @Override
            public Object handle(String accessToken, String refreshToken) {
                loginInfoModel.setAccessToken(accessToken);
                loginInfoModel.setRefreshToken(refreshToken);
                return loginInfoModel;
            }

            @Override
            public Object beforeReturnExcludeField() {
                return loginInfoModel;
            }

        };
    }

    private ILoginCallback memberVericodeLoginCallback() {
        return new AbstractLoginCallback() {

            private MemberLoginInfoModel loginInfoModel;

            @Override
            public boolean exists(String account) {
                loginInfoModel = memberMapper.findMember(account);
                return loginInfoModel != null;
            }

            @Override
            public String getUserId() {
                return loginInfoModel.getUserId();
            }

            @Override
            public Object handle(String accessToken, String refreshToken) {
                loginInfoModel.setAccessToken(accessToken);
                loginInfoModel.setRefreshToken(refreshToken);
                loginInfoModel.setHasPwd(StringUtils.isNotBlank(loginInfoModel.getPwd()));
                return loginInfoModel;
            }

            @Override
            public Object beforeReturnExcludeField() {
                return loginInfoModel;
            }

        };
    }

    // endregion

    // region 账号密码登录回调处理

    private ILoginCallback adminLoginCallback(String pwd) {
        return new AbstractLoginCallback() {

            private AdminLoginInfoModel loginInfoModel;

            @Override
            public boolean exists(String account) {
                loginInfoModel = adminMapper.searchAccount(account);
                return loginInfoModel != null;
            }

            @Override
            public String getUserId() {
                return loginInfoModel.getId().toString();
            }

            @Override
            public void extendValidate() throws BusinessException {
                String pwdMd5 = SecureUtil.sha256(pwd + "&key=" + signKey);
                if (!pwdMd5.equals(loginInfoModel.getPwd())) {
                    throw new BusinessException("登录密码不正确");
                }
            }

            @Override
            public boolean isEnable() {
                return loginInfoModel.getEnableStatus() == 1;
            }

            @Override
            public Object handle(String accessToken, String refreshToken) {
                loginInfoModel.setAccessToken(accessToken);
                loginInfoModel.setRefreshToken(refreshToken);
                // 转换成树型结构
                List<SysMenuModel> menus = adminMapper.findAccountMenu(loginInfoModel.getId());
                Map<Long, SysMenuModel> mapping = new HashMap<>(menus.size());
                menus.forEach(menu -> mapping.put(menu.getId(), menu));
                menus.stream()
                        // 过滤父类菜单
                        .filter(menu -> menu.getParentId() != null && menu.getParentId() != 0)
                        // 只保留菜单和按钮类型的数据
                        .filter(menu -> menu.getMenuType() != 2)
                        .forEach(menu -> {
                            if (!mapping.containsKey(menu.getParentId())) {
                                return;
                            }
                            SysMenuModel parent = mapping.get(menu.getParentId());
                            parent.getChildren().add(menu);
                        });

                // 生成前端路由列表
                List<SysMenuModel> routes = menus.stream()
                        .filter(menu -> menu.getParentId() == 0)
                        .collect(Collectors.toList());
                loginInfoModel.setMenus(routes);

                // 生成允许访问的api列表
                List<String> apis = menus.stream()
                        .filter(menu -> menu.getMenuType() == 2)
                        .filter(menu -> StringUtils.isNotBlank(menu.getApi()))
                        .map(SysMenuModel::getApi)
                        .distinct()
                        .collect(Collectors.toList());
                loginInfoModel.setApis(apis);

                return loginInfoModel;
            }

            @Override
            public Object beforeReturnExcludeField() {
                loginInfoModel.setPwd(null);
                loginInfoModel.setEnableStatus(null);
                return loginInfoModel;
            }

        };
    }

    private ILoginCallback merchantLoginCallback(String pwd) {
        return new AbstractLoginCallback() {

            private MerchantUserLoginInfoModel loginInfoModel;

            @Override
            public boolean exists(String account) {
                loginInfoModel = merchantMapper.findUserByAccount(account);
                boolean exists = false;
                if (loginInfoModel != null) {
                    exists = true;
                    List<StoreInformationModel> storeList = merchantMapper.findUserStore(Long.valueOf(loginInfoModel.getNumberid()));
                    loginInfoModel.setStores(storeList);
                }
                return exists;
            }

            @Override
            public String getUserId() {
                return String.valueOf(loginInfoModel.getNumberid());
            }

            @Override
            public void extendValidate() throws BusinessException {
                String pwdMd5 = SecureUtil.sha256(pwd + "&key=" + signKey);
                if (!pwdMd5.equals(loginInfoModel.getLandingpassword())) {
                    // 判断旧加密方式
                    String oldPwdMd5 = DigestUtils.md5Hex(DigestUtils.sha1("passwd=" + pwd + "&key=79AA9270FD5D4B28A02E616C61B7D7AB"));
                    if (!oldPwdMd5.equals(loginInfoModel.getLandingpassword())) {
                        throw new BusinessException("登录密码不正确");
                    }
                }
            }

            @Override
            public Object handle(String accessToken, String refreshToken) {
                loginInfoModel.setAccessToken(accessToken);
                loginInfoModel.setRefreshToken(refreshToken);
                return loginInfoModel;
            }

            @Override
            public Object beforeReturnExcludeField() {
                loginInfoModel.setLandingpassword(null);
                return loginInfoModel;
            }

        };
    }

    private ILoginCallback xinanLoginCallback(String pwd) {
        return new AbstractLoginCallback() {

            private MemberLoginInfoModel loginInfoModel;

            @Override
            public boolean exists(String account) {
                loginInfoModel = memberMapper.findMember(account);
                boolean exists = false;
                if (loginInfoModel != null) {
                    exists = true;
                }
                return exists;
            }

            @Override
            public String getUserId() {
                return String.valueOf(loginInfoModel.getUserId());
            }

            @Override
            public void extendValidate() throws BusinessException {
                String pwdMd5 = SecureUtil.sha256(pwd + "&key=" + signKey);
                if (!pwdMd5.equals(loginInfoModel.getPwd())) {
                    // 判断旧加密方式
                    String oldPwdMd5 = DigestUtils.md5Hex(DigestUtils.sha1("passwd=" + pwd + "&key=79AA9270FD5D4B28A02E616C61B7D7AB"));
                    if (!oldPwdMd5.equals(loginInfoModel.getPwd())) {
                        throw new BusinessException("登录密码不正确");
                    }
                }
            }

            @Override
            public Object handle(String accessToken, String refreshToken) {
                loginInfoModel.setAccessToken(accessToken);
                loginInfoModel.setRefreshToken(refreshToken);
                loginInfoModel.setHasPwd(StringUtils.isNotBlank(loginInfoModel.getPwd()));
                return loginInfoModel;
            }

            @Override
            public Object beforeReturnExcludeField() {
                loginInfoModel.setPwd(null);
                return loginInfoModel;
            }

        };
    }


    // endregion

    // endregion

}
