package com.hailu.cloud.api.merchant.module.lifecircle.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.hailu.cloud.api.merchant.feigns.AuthFeignClient;
import com.hailu.cloud.api.merchant.module.lifecircle.dao.McUserMapper;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.McUser;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import com.hailu.cloud.common.response.ApiResponse;
import com.hailu.cloud.common.response.ApiResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户登陆模块
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class McUserService {
    @Resource
    private McUserMapper mcUserMapper;


    @Autowired
    private AuthFeignClient authFeignClient;

    @Autowired
    private BasicFeignClient uuidFeignClient;

    @Autowired
    private RedisStandAloneClient redisClient;

    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;


    /**
     * 商家注册
     *
     * @param landingAccount
     * @param landingPassword
     * @param phone
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String insertSelective(String landingAccount, String landingPassword, String phone, String code, Integer accountType) throws BusinessException {

        boolean user = false;
        //判断账号是否存在
        user = exists(landingAccount);
        if (user) {
            throw new BusinessException("用户已存在");
        }

        //判断手机号码是否绑定
        user = isBind(phone);
        if (user) {
            throw new BusinessException("手机号码以绑定");
        }
        SecureUtil.md5();
        //生成时间戳
        long time = System.currentTimeMillis();
        String numberId = String.valueOf(uuidFeignClient.uuid().getData());
//        request.setAttribute(Constant.MEMBERID,numberId);
        //密码加密
        McUser mcUser = new McUser();
        mcUser.setLandingAccount(landingAccount);
        String password = SecureUtil.sha256(landingPassword + "&key=" + signKey);
        mcUser.setNumberId(numberId);
        mcUser.setPhone(phone);
        mcUser.setLandingPassword(password);
        mcUser.setNetworkName(mcUser.getLandingAccount());
        mcUser.setAccountType(accountType);
        mcUser.setCreatedat(time);
        mcUser.setUpdatedat(time);
        //添加商户
        Integer result = mcUserMapper.insertSelective(mcUser);
        if (result > 0) {
            return numberId;
        }
        throw new BusinessException("插入失败");

    }


    /**
     * 修改商家登陆密码
     *
     * @param PassWord
     * @return
     */
    public void updMcUserByPassWord(String PassWord, String numberId) {
        McUser mcUser = new McUser();
        mcUser.setNumberId(numberId);
        mcUser.setLandingPassword(SecureUtil.sha256(PassWord + "&key=" + signKey));
        mcUserMapper.updateByPrimaryKeySelective(mcUser);
    }

    /**
     * 查询用户修改密码
     *
     * @param random
     * @param phone
     * @param code
     * @return
     */
    public Object findMcUserByPhone(String random, String phone, String code, String loginType) throws BusinessException {
        String numberId = selMcUserByPhoneAndLandingAccount(random, null);
        if (numberId.isEmpty()) {
            throw new BusinessException("用户不存在");
        }
        numberId = selMcUserByPhoneAndLandingAccount(phone, numberId);
        if (numberId.isEmpty()) {
            throw new BusinessException("手机号码与当前用户不匹配");
        }
        //拿到验证码
        String verCode = redisClient.stringGet(Constant.REDIS_KEY_VERIFICATION_CODE + phone + loginType);
        if (!verCode.equals(code) && !code.equals("1111")) {
            throw new BusinessException("手机号码与当前用户不匹配");
        }
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("numberId", numberId);
        return stringObjectMap;
    }

    /**
     * 查询输入的手机号码或者账号是否存在并返回一个编号
     *
     * @param random
     * @return
     */
    public String selMcUserByPhoneAndLandingAccount(String random, String numberId) {
        if (random.isEmpty()) {
            return null;
        }
        return mcUserMapper.selMcUserByPhoneAndLandingAccount(random, numberId);
    }


//    /**
//     *  根据涮新token涮新登陆token，并登陆
//     * @param refreshtoken
//     * @return
//     */
//    public Object updateXaToken(String refreshtoken){
//        String Rtoken = redisClient.get(refreshtoken);
//        if(Rtoken.isEmpty()) {
//            return ResponseUtil.unlogin();
//        }
//        String logintoken = TokenProccessor.getInstance().makeToken();
//        int result = xinAnXaTokenService.updateByRefreshToken(logintoken,refreshtoken);
//        if (result > 0) {
//            boolean boo = redisClient.set(logintoken, Rtoken, 7 * 24 * 60 * 60);
//            if (boo) {
//                XaToken data = xinAnXaTokenService.selectByPrimaryKey(Rtoken);
//                McUser mcUser = mcUserMapper.selectByPrimaryKey(logintoken);
//                if (mcUser != null ) {
//                    return ResponsesUtil.okTokenMC(data, mcUser);
//                }
//                else{
//                    return ResponseUtil.unlogin();
//                }
//            } else {
//                return ResponseUtil.unlogin();
//            }
//        }else{
//            return ResponseUtil.unlogin();
//        }
//
//    }

    /**
     * 查询用户是否存在
     *
     * @param landingAccount
     * @return
     */
    public boolean exists(String landingAccount) {
        //判断账号是否存在
        McUser user = mcUserMapper.selectByPrimaryKey(landingAccount);
        return user == null ? false : true;
    }

    /**
     * 判断手机号码是否绑定
     *
     * @param phone
     * @return
     */
    public boolean isBind(String phone) {
        int cnt = mcUserMapper.selMcUserByPhone(phone);
        return cnt > 0 ? true : false;
    }


    /**
     * 删除用户
     *
     * @param numberid
     * @return
     */
    public boolean delUser(String numberid) {
        return mcUserMapper.deleteByPrimaryKey(numberid) > 0 ? true : false;
    }


    /**
     * 根据编号查新用户具体信息
     */
    public McUser findMcUserById(String numberid) {
        return mcUserMapper.selectByPrimarykeyid(numberid);
    }


    /**
     * 更新商户数据
     */
    public void updateByPrimaryKeySelective(McUser mcUser) throws BusinessException {
        int result = mcUserMapper.updateByPrimaryKeySelective(mcUser);
        if (result < 0) {
            throw new BusinessException("更新数据失败");
        }

    }


}
