package com.hailu.cloud.api.mall.module.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.hailu.cloud.api.mall.constant.Constant;
import com.hailu.cloud.api.mall.module.common.enums.BusinessCode;
import com.hailu.cloud.api.mall.module.goods.tool.PictureUploadUtil;
import com.hailu.cloud.api.mall.module.sys.dao.SysAttributeMapper;
import com.hailu.cloud.api.mall.module.sys.vo.SysAttributeVO;
import com.hailu.cloud.api.mall.module.user.dao.UserInfoMapper;
import com.hailu.cloud.api.mall.module.user.entity.UserInfo;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.module.user.vo.RealNameVo;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.*;

/**
 * @author 刘柱栋
 * @Description 用户信息服务
 * @date 2016/6/9 22:02
 * @copyright Jelly.Liu. All rights reserved. Mail to liuzhudong57@gmail.com
 * @since v1.0
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Value("${redis.key.user.login.token}")
    private String tokenKey;
    @Value("${redis.key.user.login.token-times}")
    private int tokenKeyTimes;

    @Value("${redis.key.user.registerSmsCode.token.times}")
    private int registerSmsCodeTimes;
    @Value("${user.passwd.sign.key}")
    private String signKey;
    @Resource
    private UserInfoMapper userInfoDao;
    @Autowired
    private RedisStandAloneClient redisTemplate;
    @Resource
    private SysAttributeMapper sysAttributeDao;
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    @Resource
    private BasicFeignClient smsFeightClient;


    @Override
    public boolean valiLogin(String userId, String token){
        String key = tokenKey + userId;
        String redisToken = redisTemplate.stringGet(key);
        if (token.equals(redisToken)) {
            redisTemplate.stringSet(key, token, tokenKeyTimes * Constant.HOUR);
            return true;
        }

        return false;
    }

    /**
     * 密码加密
     *
     * @param account
     * @param loginPwd
     * @return
     * @throws Exception
     */
    String passwdSign(String account, String loginPwd){
        return DigestUtils.md5Hex(DigestUtils.sha1("passwd=" + loginPwd + "&key=" + signKey));
    }



    // 登录
    @Override
    public UserInfoVo userLogin(String account, String loginPwd, String cid, String source, int systemType) throws Exception {
        // 用户不存在
        if (userInfoDao.verifyAccountState(account) == null) {
            throw new BusinessException(BusinessCode.USER_NOT_EXISTS.getDescription());
        }
        UserInfoVo user = userInfoDao.userLoginQuery(account, passwdSign(account, loginPwd));
        if (user != null) {
            if (user.getNickName().matches(REGEX_MOBILE)) {
                String str = user.getNickName();
                user.setNickName(str.substring(0, 3) + "****" + str.substring(7, user.getNickName().length()));
            }
            if (user.getUserName().matches(REGEX_MOBILE)) {
                String str = user.getUserName();
                user.setUserName(str.substring(0, 3) + "****" + str.substring(7, user.getUserName().length()));
            }
            if (StringUtils.isNotBlank(cid)) {
                user.setCid(cid);
                if (systemType == 1) {
                    systemType = 2;
                }
                user.setSystemType(systemType);
                userInfoDao.updateUserCid(user);
            }
        }
        if (user == null) {
            throw new BusinessException(BusinessCode.LOGIN_PWD_ERROR.getDescription());
        }
        // 将邀请人id 转为名字
        UserInfoVo u = userInfoDao.getUser(user.getBeInviteUser());
        if (u != null) {
            user.setBeInviteUser(u.getUserName());
        }
        // 先删除之前的token 如果没有就不删除 保存token到redis
        redisTemplate.deleteKey(tokenKey + user.getUserId());
        String token = UUID.randomUUID().toString();
        redisTemplate.stringSet(tokenKey + user.getUserId(), token, tokenKeyTimes * Constant.HOUR);
        user.setToken(token);
        if (user != null) {
            if (user.getUserIcon() != null) {
                if (user.getUserIcon().indexOf("http") == -1) {
                    user.setUserIcon(Const.PRO_URL + user.getUserIcon());
                }
            } else {
                SysAttributeVO sysAttributeVO = new SysAttributeVO();
                sysAttributeVO.setAttributeKey("userDefaultIcon");
                user.setUserIcon(Const.PRO_URL + sysAttributeDao.getAttributeByKey(sysAttributeVO).getAttributeValue());
            }
        }
        if (user.getPayPassword() == null) {//设置密码状态
            user.setUpdatePwState(0);//设置
        } else {
            user.setUpdatePwState(1);//修改
        }
        if (StringUtils.isNotEmpty(user.getUnionid())) {
            user.setIsBindWeChat(1);
        } else {
            user.setIsBindWeChat(0);
        }
        return user;
    }

    // 登出
    @Override
    public Boolean logout(String userId, String token) throws Exception {
        String redisToken = redisTemplate.stringGet(tokenKey + userId);
        // 是否存在token 该token 是否和前台传的相同
        if (!token.equals(redisToken)) {
            throw new BusinessException(BusinessCode.USER_NOT_LOGIN.getDescription());
        }
        redisTemplate.deleteKey(tokenKey + userId);
        return true;
    }

    // 帐号是否可注册 true 表示可注册 (该用户不存在)
    @Override
    public Boolean verifyAccountState(String account) throws Exception {
        return userInfoDao.verifyAccountState(account) == null;
    }

    /**
     * 注册
     *
     * @param account
     * @param loginPwd
     * @param openId
     * @param state
     * @return
     */
    @Override
    public UserInfoVo register(String account, String loginPwd, String beInviteCode, String unionid,
                               String userIcon, String nickName, String memberSex, String cid, Integer systemType, String sourceRegistration, String openId, String state) throws Exception {

        String userId = UUID.randomUUID().toString();
        loginPwd = passwdSign(account, loginPwd);
        SysAttributeVO sysAttributeVO = new SysAttributeVO();
        sysAttributeVO.setAttributeKey("userDefaultIcon");
        UserInfo userinfo = new UserInfo();
        userinfo.setUserId(userId);
        userinfo.setSourceRegistration(sourceRegistration);

        if (memberSex != null) {
            userinfo.setMemberSex(memberSex);
        }
        if (StringUtils.isNotBlank(cid)) {
            userinfo.setCid(cid);
            if (systemType == 1) {
                systemType = 2;
            }
            userinfo.setSystemType(systemType);

        }
        // 设置系统默认头像
        if (userIcon != null) {
            if (userIcon.length() > 5) {
                String str = userIcon.substring(0, 5);
                if (str.indexOf("s") == -1) {
                    userIcon = "https" + userIcon.substring(4, userIcon.length());
                }
            }
            String img = PictureUploadUtil.saveToService(userIcon, "img");
            userinfo.setUserIcon(img);
        }
        userinfo.setLoginName(account);
        userinfo.setLoginPasswd(loginPwd);
        userinfo.setUserMobile(account);
        userinfo.setUserName(account);
        if (StringUtils.isNotEmpty(unionid)) {
            userinfo.setUnionid(unionid);
            userinfo.setOpenId(openId);
            userinfo.setWxState(state);
        }
        if (nickName != null) {
            userinfo.setNickName(nickName);
        } else {
            userinfo.setNickName(account);
        }
        userinfo.setCreateDate(System.currentTimeMillis());
        userInfoDao.addMember(userinfo);// 注册完成
        UserInfoVo vo = new UserInfoVo();
        vo.setUserId(userId);
        vo.setUserIcon(userinfo.getUserIcon());
        vo.setUserName(account);
        vo.setUserMobile(account);
        vo.setCreateTime(userinfo.getCreateTime());
        vo.setNickName(userinfo.getNickName());
        vo.setNickName(userinfo.getNickName());
        vo.setUnionid(userinfo.getUnionid());
        vo.setSex(memberSex);
        vo.setSourceRegistration(sourceRegistration);
        // 保存token到redis
        String token = UUID.randomUUID().toString();
        redisTemplate.stringSet(tokenKey + userId, token, tokenKeyTimes * Constant.HOUR);
        vo.setToken(token);
        return vo;
    }

    /**
     * 验证短信验证码
     *
     * @param account
     * @param smsCode
     * @return
     */
    @Override
    public UserInfoVo verifySmsCode(String account, String smsCode) throws Exception {
        UserInfoVo result = new UserInfoVo();
        String redisSmsCode = redisTemplate.stringGet(tokenKey + account);
        if (StringUtils.isBlank(redisSmsCode)) {
            // 验证码已过期
            throw new BusinessException(BusinessCode.CODE_EXPIRE.getDescription());
        }
        if (!redisSmsCode.equals(smsCode)) {
            // 验证码输入错误
            throw new BusinessException(BusinessCode.CODE_ERROR.getDescription());
        }
        // 验证成功，删除token
        redisTemplate.deleteKey(tokenKey + account);
        return result;
    }

    /**
     * 发送注册短信
     *
     * @param account
     * @return
     */
    @Override
    public Boolean sendRegisterSmsCode(String account) throws Exception {
        String smsCode = RandomUtil.randomNumbers(6);
        smsFeightClient.send(account, smsCode);
        return true;
    }

    /**
     * 发送忘记密码短信验证
     *
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public Boolean sendForgetPwdSmsCode(String account) throws Exception {
        // 判断是否已经注册
        if (null == userInfoDao.verifyAccountState(account)) {
            // 未注册
            throw new BusinessException(BusinessCode.USER_NOT_EXISTS.getDescription());
        }
        String smsCode = RandomUtil.randomNumbers(6);
        // 发送短信
        smsFeightClient.send(account, smsCode);
        return true;
    }

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean updateUserInfo(UserInfoVo userInfoVo) throws Exception {
        Boolean result = false;

        UserInfoVo user = userInfoDao.getUser(userInfoVo.getUserId());
        if (user == null) {
            throw new BusinessException(BusinessCode.USER_NOT_EXISTS.getDescription());
        }

        boolean isUpdate = false;
        if (user.getUserIcon() == null && StringUtils.isNoneBlank(userInfoVo.getUserIcon())) {
            String imgPath = PictureUploadUtil.uploadPicture("img", userInfoVo.getUserIcon());
            user.setUserIcon(imgPath);
            isUpdate = true;
        } else if (user.getUserIcon() != null && !user.getUserIcon().equals(userInfoVo.getUserIcon())) {
            //String imgPath = PictureUploadUtil.uploadPicture("img", userInfoVo.getUserIcon());
            user.setUserIcon(userInfoVo.getUserIcon());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getUserName())) {
            user.setUserName(userInfoVo.getUserName());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getNickName())) {
            user.setNickName(userInfoVo.getNickName());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getUserMobile())) {
            user.setUserMobile(userInfoVo.getUserMobile());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getQq())) {
            user.setQq(userInfoVo.getQq());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getWechat())) {
            user.setWechat(userInfoVo.getWechat());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getEmail())) {
            user.setEmail(userInfoVo.getEmail());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getProfession())) {
            user.setProfession(userInfoVo.getProfession());
            isUpdate = true;
        }

        if (StringUtils.isNotBlank(userInfoVo.getBirthday())) {
            user.setBirthday(userInfoVo.getBirthday());
            isUpdate = true;
        }
        if (StringUtils.isNotBlank(userInfoVo.getSex())) {
            user.setSex(userInfoVo.getSex());
            isUpdate = true;
        }
        if (StringUtils.isNotBlank(userInfoVo.getWechat())) {
            user.setWechat(userInfoVo.getWechat());
            isUpdate = true;
        }
        if (isUpdate) {
            userInfoDao.updateUserInfo(user);
            return true;
        }

        return result;
    }

    /**
     * 修改密码
     *
     * @param loginName
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    public Boolean updatePwd(String userId, String loginName, String oldPwd, String newPwd)
            throws Exception {
        Boolean result = true;
        // 原密码是否正确
        UserInfoVo user = userInfoDao.userLoginQuery(loginName, passwdSign(loginName, oldPwd));
        if (null == user) {
            throw new BusinessException(BusinessCode.OLD_LOGIN_PWD_ERROR.getDescription());
        }
        // 修改密码
        newPwd = passwdSign(loginName, newPwd);

        UserInfo userinfo = new UserInfo();
        userinfo.setLoginPasswd(newPwd);
        userinfo.setLoginName(loginName);
        int backResult = userInfoDao.updatePwd(userinfo);
        if (1 != backResult) {
            throw new BusinessException(BusinessCode.DATABASE_FAILURE.getDescription());
        }
        // 修改密码成功,将原来的token删除
        redisTemplate.deleteKey(tokenKey + userId);
        return result;
    }

    /**
     * 忘记密码
     *
     * @param
     * @param newPwd
     * @return
     * @throws Exception
     */
    @Override
    public Boolean forgetPwd(String memberMobile, String newPwd) {
        Boolean result = true;
        // 修改密码
        try {
            newPwd = passwdSign(memberMobile, newPwd);
            UserInfo userinfo = new UserInfo();
            userinfo.setLoginPasswd(newPwd);
            userinfo.setLoginName(memberMobile);
            int backResult = userInfoDao.updatePwd(userinfo);
            if (1 != backResult) {
                throw new BusinessException(BusinessCode.DATABASE_FAILURE.getDescription());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }


    @Override
    public UserInfo saveWeChatUset(String userId) {
        UserInfo byIdFindUser = userInfoDao.byIdFindUser(userId);
        if (byIdFindUser != null) {
            return byIdFindUser;

        } else {
            SysAttributeVO sysAttributeVO = new SysAttributeVO();
            sysAttributeVO.setAttributeKey("userDefaultIcon");
            UserInfo userinfo = new UserInfo();
            userinfo.setUserId(userId);
            userinfo.setCreateTime(System.currentTimeMillis());
            userInfoDao.saveWeChatUser(userinfo);
            return userinfo;
        }
    }

    @Override
    public void realName(RealNameVo realNameVo) throws Exception {
        realNameVo.setIsSub(1);
        realNameVo.setAuditTime(System.currentTimeMillis());
        userInfoDao.realName(realNameVo);
    }

    @Override
    public UserInfoVo userInfoQueryByUserId(String userId) throws Exception {
        return userInfoDao.userInfoQueryByUserId(userId);
    }

    @Override
    public UserInfoVo userInfoQueryByUnionid(String unionid) throws Exception {
        return userInfoDao.userInfoQueryByUnionid(unionid);
    }

    @Override
    public void upUserInfoByPhone(UserInfoVo userInfoVo) throws Exception {
        userInfoDao.upUserInfoByPhone(userInfoVo);
    }

    @Override
    public int findUnionid(String unionid) {
        return userInfoDao.findUnionid(unionid);
    }

    @Override
    public RealNameVo getRealName(String userId) throws Exception {
        return userInfoDao.getRealName(userId);
    }

    @Override
    public void setPayPwd(String userId, String payPwd, int type) throws Exception {

        try {
            if (verifyPwdIsNum(payPwd).equals(false) || payPwd.length() != 6) {
                throw new BusinessException("密码只能是6位纯数字");
            }
            Map<String, String> map = generate(payPwd, null);
            payPwd = (String) map.get("pwd");
            userInfoDao.updatePayPwd(userId, payPwd);
            UserInfoVo userInfo = userInfoDao.getUser(userId);
            if (type == 1) {
                userInfoDao.addRecord(userId, (String) map.get("salt"));
                throw new BusinessException("设置密码成功");
            }
            if (type == 2) {
                userInfoDao.updateRecord(userId, (String) map.get("salt"));
                throw new BusinessException("重置密码成功");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("你已经设置过支付密码");
        }
    }


    public static Map<String, String> generate(String password, String salt) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99909999)).append(r.nextInt(99998999));
        char letter = (char) (int) (Math.random() * 26 + 97);
        sb.append(letter);
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        if (salt != null && salt.length() > 0) {

        } else {
            salt = sb.toString();
        }
        password = md5Hex(password + salt);
        char[] hls = new char[48];
        for (int i = 0; i < 48; i += 3) {
            hls[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            hls[i + 1] = c;
            hls[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        Map<String, String> map = new HashMap<>();
        map.put("pwd", new String(hls));
        map.put("salt", salt);
        return map;
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updatePayPwd(String userId, String payPwd, String newPayPwd) throws Exception {
        Object rd = null;

        if (verifyPwdIsNum(newPayPwd).equals(false) || newPayPwd.length() != 6) {
            throw new BusinessException("密码只能是6位纯数字");
        }
        String salt = userInfoDao.getRecordTo(userId);
        Map<String, String> map = generate(payPwd, salt);
        payPwd = (String) map.get("pwd");
        if (userInfoDao.getPayPwd(userId).equals(payPwd)) {
            Map<String, String> map1 = generate(newPayPwd, null);
            newPayPwd = (String) map1.get("pwd");
            userInfoDao.updatePayPwd(userId, newPayPwd);
            UserInfoVo userInfo = userInfoDao.getUser(userId);
            userInfoDao.updateRecord(userId, (String) map1.get("salt"));
        } else {
            throw new BusinessException("原密码错误");
        }
    }


    @Override
    public Boolean verifyIsSetPwd(String account, String smsCode, String name, String cardId) throws BusinessException {
        RealNameVo realNameVo;
        try {
            realNameVo = userInfoDao.getRealNameByAccount(account, null);
            if (realNameVo != null) {
                if (!name.equals(realNameVo.getUserName())) {
                    throw new BusinessException("姓名跟实名认证不一致");
                } else if (!cardId.equals(realNameVo.getIdCard())) {
                    throw new BusinessException("身份证号跟实名认证不一致");
                } else {
                    return true;
                }
            }
            throw new BusinessException("实名认证在审核OR未通过");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("未知错误请联系客服");
        }
    }


    @Override
    public Boolean verifyIsAlterPwd(String userId, String payPwd, String name, String cardId) throws BusinessException {
        try {
            RealNameVo realNameVo = userInfoDao.getRealNameByAccount(null, userId);
            if (realNameVo != null) {
                if (!name.equals(realNameVo.getUserName())) {
                    throw new BusinessException("姓名跟实名认证不一致");
                }
                if (!cardId.equals(realNameVo.getIdCard())) {
                    throw new BusinessException("身份证号跟实名认证不一致");
                }

                String salt = userInfoDao.getRecordTo(userId);
                Map<String, String> map = generate(payPwd, salt);
                payPwd = (String) map.get("pwd");
                if (userInfoDao.getPayPwd(userId).equals(payPwd)) {
                    return true;
                } else {
                    throw new BusinessException("原密码错误");
                }
            }
            throw new BusinessException("实名认证在审核OR未通过");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("未知错误请联系客服");
        }

    }

    /**
     * 验证String中是否是纯数字
     *
     * @param obj
     * @return
     */
    public Boolean verifyPwdIsNum(String obj) {
        boolean isNum = obj.matches("[0-9]+");
        return isNum;
    }



    @Override
    public Boolean WXunbundle(String phone) {
        int rows = userInfoDao.WXunbundle(phone);
        if (rows > 0) {
            return true;
        }
        return false;
    }


    /**
     * @Author WangTao
     * @Date 18:01 2018/3/10 0010
     * @param: TODO 说明:验证是否可以修改手机号码
     **/
    @Override
    public Integer verifyAccountIfUpdate(String phone, String msg) {
        Integer b = 0;
        try {
            Integer i = userInfoDao.verifyAccountState(phone);

            if (i == null) {
                b = 1;//该用户不是系统用户
                return b;
            }
            verifySmsCode(phone, msg);
            return b;
        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }
        return 3;
    }

    @Override
    public Integer updateUserPhone(String phone, String msg, String userId) {
        Integer b = 0;
        try {
            UserInfoVo responseData = verifySmsCode(phone, msg);

            Integer i = userInfoDao.verifyAccountState(phone);
            if (i != null) {
                b = 1;//该用户已存在
                return b;
            }
            int rows = userInfoDao.updateUserPhone(phone, userId);

            if (rows == 0) {
                return 3;//信息不正确
            }
            return b;
        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }


        return 3;
    }


    @Override
    @Transactional
    public Boolean updateWeCartBind(String unionid, String userId, String openId, String state) throws BusinessException {
        if (userInfoDao.findUnionid(unionid) != 0) {
            throw new BusinessException("该微信已经绑定过");
        }
        try {
            userInfoDao.updateWeCartBind(unionid, userId, openId, state);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("该微信号已被别的账号绑定,请先解绑其他账号。");
        }
    }


    /*
     * APP扫码登入
     */
    @Override
    public Boolean loginCode(String loginCode, String userId) throws BusinessException {
        String loginCodeVal = redisTemplate.stringGet("loginCode" + loginCode);
        if (loginCodeVal == null) {
            throw new BusinessException("二维码已失效");
        } else {
            loginCodeVal = "1," + userId;
            redisTemplate.stringSet("loginCode" + loginCode, loginCodeVal, tokenKeyTimes * Constant.HOUR);
        }
        return true;
    }

    @Override
    public Boolean sendAmendPwdSmsCode(String account, String userId, String name, String cardId) throws BusinessException {
        Boolean result = false;

        RealNameVo realNameVo;
        try {
            realNameVo = userInfoDao.getRealNameByAccount(account, userId);

            if (realNameVo != null) {
                if (!name.equals(realNameVo.getUserName())) {
                    throw new BusinessException("姓名跟实名认证不一致");
                } else if (!cardId.equals(realNameVo.getIdCard())) {
                    throw new BusinessException("身份证号跟实名认证不一致");
                }
                String smsCode = RandomUtil.randomNumbers(6);
                // 发送短信
                smsFeightClient.send(account, smsCode);
                // 成功
                redisTemplate.stringSet(tokenKey + account, smsCode, registerSmsCodeTimes * Constant.MINUTES);
                return true;
            } else {
                throw new BusinessException("未找到该用户信息");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage(), e);
            throw new BusinessException("未知错误请联系客服");
        }
    }

    @Override
    public UserInfo getUserInfoVo(String account) {
        return userInfoDao.getUserInfoVo(account);
    }


    @Override
    public void updateAddBalance(double rechargeBalance, String userId) {
        userInfoDao.updateAddBalance(rechargeBalance, userId);
    }

    //分享页面注册
    @Override
    public UserInfoVo shareRegister(String account, String loginPwd, String QRcode, String unionid, String inviteCode,
                                    String userIcon, String nickName, String memberSex, String cid, Integer systemType, String sourceRegistration) throws Exception {
        String userId = UUID.randomUUID().toString();
        loginPwd = passwdSign(account, loginPwd);
        SysAttributeVO sysAttributeVO = new SysAttributeVO();
        sysAttributeVO.setAttributeKey("userDefaultIcon");
        UserInfo userinfo = new UserInfo();
        userinfo.setUserId(userId);
        userinfo.setSourceRegistration(sourceRegistration);
        if (memberSex != null) {
            userinfo.setMemberSex(memberSex);
        }
        if (StringUtils.isNotBlank(cid)) {
            userinfo.setCid(cid);
            if (systemType == 1) {
                systemType = 2;
            }
            userinfo.setSystemType(systemType);

        }

        // 设置系统默认头像
        if (userIcon != null) {
            if (userIcon.length() > 5) {
                String str = userIcon.substring(0, 5);
                if (str.indexOf("s") == -1) {
                    userIcon = "https" + userIcon.substring(4, userIcon.length());
                }
            }
            String img = PictureUploadUtil.saveToService(userIcon, "img");
            userinfo.setUserIcon(img);
        }
        userinfo.setBeInviteUser(QRcode);
        userinfo.setLoginName(account);
        userinfo.setLoginPasswd(loginPwd);
        userinfo.setUserMobile(account);
        userinfo.setUserName(account);
        if (userinfo != null) {
            userinfo.setUnionid(unionid);
        }
        if (nickName != null) {
            userinfo.setNickName(nickName);
        } else {
            userinfo.setNickName(account);
        }
        userinfo.setCreateTime(System.currentTimeMillis());
        userinfo.setCreateDate(System.currentTimeMillis());
        // 注册完成
        userInfoDao.addMember(userinfo);
        UserInfoVo vo = new UserInfoVo();
        vo.setUserId(userId);
        vo.setUserIcon(userinfo.getUserIcon());
        vo.setUserName(account);
        vo.setUserMobile(account);
        vo.setCreateTime(userinfo.getCreateTime());
        vo.setNickName(userinfo.getNickName());
        vo.setInviteCode(inviteCode);
        vo.setNickName(userinfo.getNickName());
        vo.setUnionid(userinfo.getUnionid());
        vo.setSex(memberSex);
        vo.setSourceRegistration(sourceRegistration);
        // 保存token到redis
        String token = UUID.randomUUID().toString();
        redisTemplate.stringSet(tokenKey + userId, token, tokenKeyTimes * Constant.HOUR);
        vo.setToken(token);
        return vo;
    }

    @Override
    public UserInfoVo userVqLogin(String account, String smsCode, String cid, Integer systemType) throws Exception {
        UserInfoVo result = new UserInfoVo();
        // 用户不存在
        if (userInfoDao.verifyAccountState(account) == null) {
            throw new BusinessException(BusinessCode.USER_NOT_EXISTS.getDescription());
        }
        UserInfoVo user = userInfoDao.findByAccount(account);
        if (user != null) {
            if (StringUtils.isNotBlank(cid)) {
                user.setCid(cid);
                if (systemType == 1) {
                    systemType = 2;
                }
                user.setSystemType(systemType);
                userInfoDao.updateUserCid(user);
            }
        }
        // 将邀请人id 转为名字
		/*UserInfoVo u = userInfoDao.getUser(user.getBeInviteUser());
		if (u != null) {
			user.setBeInviteUser(u.getUserName());
		}*/
        // 先删除之前的token 如果没有就不删除 保存token到redis
        redisTemplate.deleteKey(tokenKey + user.getUserId());
        String token = UUID.randomUUID().toString();
        redisTemplate.stringSet(tokenKey + user.getUserId(), token, tokenKeyTimes * Constant.HOUR);
        user.setToken(token);
        if (user != null) {
            if (user.getUserIcon() != null && user.getUserIcon().indexOf("http") == -1) {
                user.setUserIcon(Const.PRO_URL + user.getUserIcon());
            } else {
                SysAttributeVO sysAttributeVO = new SysAttributeVO();
                sysAttributeVO.setAttributeKey("userDefaultIcon");
                user.setUserIcon(Const.PRO_URL + sysAttributeDao.getAttributeByKey(sysAttributeVO).getAttributeValue());
            }
        }
        if (user.getPayPassword() == null) {//设置密码状态
            user.setUpdatePwState(0);//设置
        } else {
            user.setUpdatePwState(1);//修改
        }
        if (StringUtils.isNotEmpty(user.getUnionid())) {
            user.setIsBindWeChat(1);
        } else {
            user.setIsBindWeChat(0);
        }
        return user;
    }



    @Override
    public void editHlMember(String userId, int status, String memberCard, Date timeOut) {
        userInfoDao.editHlMember(userId, status, memberCard, timeOut);
    }

    @Override
    public int findPoviderPrice(Long merchantCityId) {
        //根据城市ID获取服务商的数量
        int sumPovider = userInfoDao.countPoviderNum(merchantCityId);
        if (sumPovider >= 200) {
            return 30000;
        } else {
            return 8888;
        }
    }


    @Override
    public UserInfo findById(String userId) {
        UserInfo userInfo = userInfoDao.byIdFindUser(userId);
        if(userInfo != null){
            return userInfo;
        }
        return null;
    }

    @Override
    public void editMerchantTypeAndSuperiorMember(String userId,int merchantType,  String superiorMember,Long cityId) {
        userInfoDao.editMerchantTypeAndSuperiorMember(userId,merchantType,superiorMember,cityId);
    }
}
