package com.hailu.cloud.api.mall.module.user.controller;

import com.hailu.cloud.api.mall.module.common.enums.BusinessCode;
import com.hailu.cloud.api.mall.module.goods.tool.PictureUploadUtil;
import com.hailu.cloud.api.mall.module.user.entity.UserInfo;
import com.hailu.cloud.api.mall.module.user.service.IUserFeedbackService;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.module.user.service.IUserSignService;
import com.hailu.cloud.api.mall.module.user.vo.RealNameVo;
import com.hailu.cloud.api.mall.module.user.vo.UserFeedbackVO;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.api.mall.module.user.vo.UserSignVO;
import com.hailu.cloud.api.mall.util.BaseRequest;
import com.hailu.cloud.api.mall.util.Const;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class UserController  {

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IUserFeedbackService userFeedbackService;
    @Autowired
    private IUserSignService userSignService;

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 用户信息更新
     *
     * @param userInfoVo
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "upUserInfo", method = RequestMethod.POST)
    public Boolean updateUserInfo(@ModelAttribute UserInfoVo userInfoVo,
                                                @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("用户信息更新|userInfoVo={}|param={}", userInfoVo, baseInfo);
        return userInfoService.updateUserInfo(userInfoVo);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "getUserInfoVo", method = RequestMethod.POST)
    public UserInfoVo login(
            @RequestParam(value = "userId", required = true) String userId
    ) throws Exception {
        log.info("获取用户信息|userId={}", userId);
        UserInfoVo userInfo = userInfoService.userInfoQueryByUserId(userId);
        if (userInfo != null) {
            if (userInfo.getNickName().matches(REGEX_MOBILE)) {
                String str = userInfo.getNickName();
                userInfo.setNickName(str.substring(0, 3) + "****" + str.substring(7, userInfo.getNickName().length()));
            }
            if (StringUtils.isNotBlank(userInfo.getUserName()) && userInfo.getUserName().matches(REGEX_MOBILE)) {
                String str = userInfo.getUserName();
                userInfo.setUserName(str.substring(0, 3) + "****" + str.substring(7, userInfo.getUserName().length()));
            }
            if (userInfo.getPayPassword() == null) {//设置密码状态
                userInfo.setUpdatePwState(0);//设置
            } else {
                userInfo.setUpdatePwState(1);//修改
            }
            if (StringUtils.isNotEmpty(userInfo.getUnionid())) {
                userInfo.setIsBindWeChat(1);//绑定了
            }
            if (userInfo.getUserIcon() != null && userInfo.getUserIcon().length() > 0) {
                if (userInfo.getUserIcon().indexOf("http") == -1) {
                    userInfo.setUserIcon(Const.PRO_URL + userInfo.getUserIcon());
                }

            }

        }
        return userInfo;
    }

    @RequestMapping(value = "realName", method = RequestMethod.POST)
    public void realName(@RequestParam(value = "userId", required = true) String userId,
                                                      @RequestParam(value = "userName", required = false) String userName,
                                                      @RequestParam(value = "idcard", required = false) String idcard,
                                                      @RequestParam(value = "idcardImgx", required = false) String idcardImgx,
                                                      @RequestParam(value = "idcardImgy", required = false) String idcardImgy) throws Exception {
        log.info("用户實名認證|userId={}|userName={}|idcard={}|idcardImgx={}|idcardImgy={}", userId, userName, idcard, idcardImgx, idcardImgy);
        RealNameVo realNameVo = new RealNameVo();
        if (StringUtils.isNoneBlank(userId)) {
            realNameVo.setUserId(userId);
        }
        if (StringUtils.isNoneBlank(userName)) {
            realNameVo.setUserName(userName);
        }
        if (StringUtils.isNoneBlank(idcard)) {
            realNameVo.setIdCard(idcard);
        }
        if (StringUtils.isNoneBlank(idcardImgx)) {
            idcardImgx = PictureUploadUtil.uploadPicture("img", idcardImgx);
            realNameVo.setIdcardImgx(idcardImgx);
        }
        if (StringUtils.isNoneBlank(idcardImgy)) {
            idcardImgy = PictureUploadUtil.uploadPicture("img", idcardImgy);
            realNameVo.setIdcardImgy(idcardImgy);
        }

        userInfoService.realName(realNameVo);
    }

    @RequestMapping(value = "getRealName", method = RequestMethod.GET)
    public Map<String, Object> getRealName(@RequestParam(value = "userId", required = true) String userId
    ) throws Exception {
        log.info("用户实名认证信息|userId={}", userId);
        RealNameVo realNameVo = userInfoService.getRealName(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("realNameVo", realNameVo);
        return data;
    }

    /**
     * 修改用户密码
     *
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    public Boolean updatePwd(@RequestParam String userId,
                                           @RequestParam String loginName,
                                           @RequestParam String oldPwd,
                                           @RequestParam String newPwd,
                                           @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("用户修改密码|userId={}|oldPwd={}|newPwd={}|param={}", userId, loginName, oldPwd, newPwd, baseInfo);
        return userInfoService.updatePwd(userId, loginName, oldPwd, newPwd);
    }

    /**
     * 用户反馈
     *
     * @param userFeedbackVO
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addFeedback", method = RequestMethod.POST)
    public Boolean addFeedback(@ModelAttribute UserFeedbackVO userFeedbackVO,
                                             @ModelAttribute BaseRequest baseInfo) throws Exception {

        log.info("用户提问反馈|userFeedbackVO={}|param={}", userFeedbackVO, baseInfo);
        if (userFeedbackVO.getUserId() != null) {
            UserInfoVo userInfo = userInfoService.userInfoQueryByUserId(userFeedbackVO.getUserId());
            if (userInfo != null) {
                userFeedbackVO.setEmail(userInfo.getUserMobile());
            }
        }
        return userFeedbackService.addFeedback(userFeedbackVO);
    }

    /**
     * 签到
     *
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public Map<String, Object> sign(@ModelAttribute UserSignVO userSignVO,
                                                  @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("签到|userSignVO|param={}", userSignVO, baseInfo);
        Map<String, String> map = new HashMap<>();
        boolean isSing = userSignService.addSign(userSignVO);
        if (!isSing) {
            throw new BusinessException(BusinessCode.USER_ALREADY_SIGN.getDescription());
        }
        UserInfoVo userInfo = userInfoService.userInfoQueryByUserId(userSignVO.getUserId());
        return new HashMap<String, Object>() {{
            put("sign", isSing);
            put("integral", userInfo.getIntegral());
            put("getIntegral", (userInfo.getIntegral()));
        }};
    }

    @RequestMapping(value = "getSign", method = RequestMethod.GET)
    public UserSignVO getSign(@ModelAttribute UserSignVO userSignVO,
                                            @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("签到|userSignVO|param={}", userSignVO, baseInfo);

        UserInfoVo userInfo = userInfoService.userInfoQueryByUserId(userSignVO.getUserId());
        UserSignVO userSignVo = userSignService.getLastDaySign(userSignVO);
        Boolean flag = userSignService.isCanSign(userSignVO);
        if (userSignVo != null) {
            userSignVo.setIntegral(userInfo.getIntegral());
            userSignVo.setFlag(flag);
        }
        return userSignVo;
    }

    /**
     * 查询今天是否可以签到
     * true 表示可以签到 (即今天还未签到)
     *
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "isCanSign", method = RequestMethod.POST)
    public Boolean isCanSign(@ModelAttribute UserSignVO userSignVO,
                                           @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("签到|userSignVO|param={}", userSignVO, baseInfo);

        boolean flag = userSignService.isCanSign(userSignVO);
        if (!flag) {
            throw new BusinessException(BusinessCode.USER_ALREADY_SIGN.getDescription());
        }
        return flag;
    }

    /**
     * userId 就是openId
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "save/weChatUser", method = RequestMethod.POST)
    public UserInfo saveWeChatUser(@RequestParam(value = "userId", required = true) String userId) {
        return userInfoService.saveWeChatUset(userId);
    }

    /**
     * 设置 第一次支付密码
     *
     * @param userId
     * @param payPwd
     * @return
     * @throws Exception
     * @Author huangl
     */
    @RequestMapping(value = "setPayPwd", method = RequestMethod.POST)
    public void setPayPwd(@RequestParam(value = "userId", required = true) String userId,
                                          @RequestParam(value = "payPwd", required = true) String payPwd, @RequestParam("type") int type) throws Exception {
        log.info("设置支付密码", "");
       userInfoService.setPayPwd(userId, payPwd, type);
    }

    @RequestMapping(value = "verifyIsAlterPwd", method = RequestMethod.POST)
    public Boolean verifyIsAlterPwd(@RequestParam(value = "userId", required = true) String userId, //用户id
                                                  @RequestParam(value = "payPwd", required = true) String payPwd, //原密码
                                                  @RequestParam(value = "name", required = true) String name,  //姓名
                                                  @RequestParam(value = "cardId", required = true) String cardId //身份证号码
    ) throws BusinessException {
        return userInfoService.verifyIsAlterPwd(userId, payPwd, name, cardId);
    }

    /**
     * 修改 支付密码
     *
     * @param userId
     * @param payPwd
     * @return
     * @throws Exception
     * @Author huangl
     */
    @RequestMapping(value = "updatePayPwd", method = RequestMethod.POST)
    public void updatePayPwd(@RequestParam(value = "userId", required = true) String userId,
                                             @RequestParam(value = "payPwd", required = true) String payPwd,
                                             @RequestParam(value = "newPayPwd", required = true) String newPayPwd) throws Exception {
        log.info("修改支付密码", "");
        userInfoService.updatePayPwd(userId, payPwd, newPayPwd);
    }

    /**
     * 发送忘记支付密码验证码
     * @Author huangl
     * @param account
     * @return
     * @throws Exception
     * 并不需要这个重复的接口 , 就注释掉了
     */
    /*@RequestMapping(value = "sendForgetPayPwdSmsCode", method = RequestMethod.POST)
	 
	public ResponseData<Boolean> sendForgetPayPwdSmsCode(@RequestParam(value="account",required=true) String account) throws Exception {
		log.info("发送短信 |account={}", account);
		return userInfoService.sendForgetPwdSmsCode(account);
	}*/

    /**
     * 第一次设置支付密码,验证是否可以设置密码
     *
     * @param account
     * @return
     * @throws Exception
     * @Author huangl
     */
    @RequestMapping(value = "verifyIsSetPwd", method = RequestMethod.POST)
    public Boolean verifyIsSetPwd(@RequestParam(value = "account", required = true) String account, //手机号码
                                                @RequestParam(value = "smsCode", required = true) String smsCode, //短信验证码
                                                @RequestParam(value = "name", required = true) String name,  //姓名
                                                @RequestParam(value = "cardId", required = true) String cardId //身份证号码
    ) throws Exception {
        log.info("忘记密码实名信息验证 |account={}", account);
        UserInfoVo verifyCode = userInfoService.verifySmsCode(account, smsCode);
        return userInfoService.verifyIsSetPwd(account, smsCode, name, cardId);
    }



    /**
     * @Author WangTao
     * @Date 18:01 2018/3/10 0010
     * @param: TODO 说明:验证是否可以修改手机号码
     **/
    @RequestMapping(value = "/verifyAccountIfUpdate", method = RequestMethod.POST)
     
    public Integer verifyAccountIfUpdate(@RequestParam String phone, @RequestParam String msg) {
        try {
            Integer state = userInfoService.verifyAccountIfUpdate(phone, msg);
            return state;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @Author WangTao
     * @Date 18:02 2018/3/10 0010
     * @param: TODO 说明:修改用户手机号
     **/
    @RequestMapping(value = "/updateUserPhone", method = RequestMethod.POST)
    public Integer updateUserPhone(@RequestParam String phone, @RequestParam String msg, @RequestParam String userId) {
        return userInfoService.updateUserPhone(phone, msg, userId);
    }

    /**
     * 根据UserId获取信息
     * @param userId
     * @return
     */
    @GetMapping("/findById")
    public UserInfo findById(@RequestParam(value = "userId")String userId){
        return userInfoService.findById(userId);
    }

    /**
     * 将用户更改成服务商
     * @param userId
     * @param merchantType
     * @param superiorMember
     * @param cityId
     */
    @PostMapping("/editMerchantTypeAndSuperiorMember")
    public void editMerchantTypeAndSuperiorMember(
            @RequestParam("userId") String userId,
            @RequestParam("merchantType") int merchantType,
            @RequestParam("superiorMember")String superiorMember,
            @RequestParam("cityId")Long cityId
    ){
        userInfoService.editMerchantTypeAndSuperiorMember(userId,merchantType,superiorMember,cityId);
    }

    /**
     * 获取购买服务商价格
     * @param chooseCityId
     * @return
     */
    @GetMapping("/findPoviderPrice")
    public int findPoviderPrice(@RequestParam("chooseCityId")Long chooseCityId){
        return userInfoService.findPoviderPrice(chooseCityId);
    }
}
