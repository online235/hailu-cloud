package com.hailu.cloud.api.mall.module.user.controller;

import com.hailu.cloud.api.mall.module.common.enums.BusinessCode;
import com.hailu.cloud.api.mall.module.sys.service.ISysAttributeService;
import com.hailu.cloud.api.mall.module.sys.vo.SysAttributeVO;
import com.hailu.cloud.api.mall.module.user.service.IUserInfoService;
import com.hailu.cloud.api.mall.module.user.vo.UserInfoVo;
import com.hailu.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/api/userLb")
public class UserLbController  {

    @Autowired
    private IUserInfoService userInfoService;

    @Resource
    private ISysAttributeService sysAttributeService;


    /**
     * 查询用户是否领取礼包
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/isLqlb", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> isLqlb(
            @RequestParam(value = "userId", required = true) String userId
    ) throws Exception {
        log.info("查询用户是否领取礼包|userId={}", userId);
        UserInfoVo userInfo = userInfoService.userInfoQueryByUserId(userId);
        //判定用户是否存在
        Map<String, String> resultMap = new HashMap<>();
        if (userInfo == null) {
            throw new BusinessException(BusinessCode.USER_NOT_EXISTS.getDescription());
        }
        Integer isLqlb = userInfo.getIsLqlb();
        if (isLqlb != null) {
            if (isLqlb.equals(0)) {//用户未领取礼包
                resultMap.put("flag", "1");
                SysAttributeVO sysAttribute = new SysAttributeVO();
//				sysAttribute.setAttributeKey("new_gift");
                sysAttribute = sysAttributeService.findAttributeValue("new_gift");
                resultMap.put("image", sysAttribute.getAttributeValue());
                return resultMap;

            } else if (isLqlb.equals(1)) {//用户已领取礼包
                resultMap.put("flag", "2");
                resultMap.put("", "");
                return resultMap;
            }
        }
        resultMap.put("flag", "2");
        resultMap.put("image", "没有新人礼包");
        return resultMap;
    }

}