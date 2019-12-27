package com.hailu.cloud.api.mall.module.user.controller;

import com.hailu.cloud.api.mall.module.user.service.IUserFeedbackService;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.module.user.vo.UserFeedbackVO;
import com.hailu.cloud.common.entity.member.ShopMember;
import com.hailu.cloud.common.model.auth.MemberLoginInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserFeedbackService userFeedbackService;

    /**
     * 用户信息更新
     *
     * @param userIcon
     * @param nickName
     * @param sex
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "upUserInfo", method = RequestMethod.POST)
    public void updateUserInfo(
            @RequestParam(name = "userIcon", required = false) String userIcon,
            @RequestParam(name = "nickName", required = false) String nickName,
            @RequestParam(name = "sex", required = false) String sex) throws Exception {

        userInfoService.updateUserInfo(userIcon, nickName, sex);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "getUserInfoVo", method = RequestMethod.POST)
    public MemberLoginInfoModel userInfo() {
        return RequestUtils.getMemberLoginInfo();
    }

    /**
     * 用户反馈
     *
     * @param userFeedbackVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addFeedback", method = RequestMethod.POST)
    public Boolean addFeedback(@ModelAttribute UserFeedbackVO userFeedbackVO) throws Exception {
        if (userFeedbackVO.getUserId() != null) {
            ShopMember userInfo = userInfoService.userInfoQueryByUserId(userFeedbackVO.getUserId());
            if (userInfo != null) {
                userFeedbackVO.setEmail(userInfo.getMemberMobile());
            }
        }
        return userFeedbackService.addFeedback(userFeedbackVO);
    }

    /**
     * userId 就是openId
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "save/weChatUser", method = RequestMethod.POST)
    public ShopMember saveWeChatUser(@RequestParam(value = "userId", required = true) String userId) {
        return userInfoService.saveWeChatUset(userId);
    }

    /**
     * 根据UserId获取信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/findById")
    public ShopMember findById(@RequestParam(value = "userId") String userId) {
        return userInfoService.findById(userId);
    }

    /**
     * 将用户更改成服务商
     *
     * @param userId
     * @param merchantType
     * @param superiorMember
     * @param cityId
     */
    @PostMapping("/editMerchantTypeAndSuperiorMember")
    public void editMerchantTypeAndSuperiorMember(
            @RequestParam("userId") String userId,
            @RequestParam("merchantType") int merchantType,
            @RequestParam("superiorMember") String superiorMember,
            @RequestParam("cityId") Long cityId
    ) {
        userInfoService.editMerchantTypeAndSuperiorMember(userId, merchantType, superiorMember, cityId);
    }

    /**
     * 获取购买服务商价格
     *
     * @param chooseCityId
     * @return
     */
    @GetMapping("/findPoviderPrice")
    public int findPoviderPrice(@RequestParam("chooseCityId") Long chooseCityId) {
        return userInfoService.findPoviderPrice(chooseCityId);
    }
}
