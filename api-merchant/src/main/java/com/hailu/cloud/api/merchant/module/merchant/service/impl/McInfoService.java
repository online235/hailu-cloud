package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.hailu.cloud.api.merchant.module.lifecircle.dao.McUserMapper;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.McUser;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.McEntryinFormationService;
import com.hailu.cloud.api.merchant.module.lifecircle.service.impl.McUserService;
import com.hailu.cloud.api.merchant.module.merchant.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.UuidFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author: QiuFeng:WANG
 * @Description:
 * @Date: 19:10 2019/11/3 0003
 */
@Service
public class McInfoService {

    @Resource
    private McUserMapper mcUserMapper;

    @Autowired
    private McUserService mcUserService;

    @Autowired
    private McEntryinFormationService mcEntryinFormationService;

    @Resource
    private McEntryInformationMapper mcEntryinFormationMapper;

    @Autowired
    private UuidFeignClient uuidFeignClient;

    /**
     * 密码加密的key
     */
    @Value("${user.passwd.sign.key}")
    private String signKey;

    /**
     * 商家注册以及入驻
     * @return
     */
    public void addMcUserAndEntry(McEntryInformation mcEntryinFormation, String landingAccount, String landingPassword, String moli) throws BusinessException {
        McUser mcUser = new McUser();
        mcUser.setLandingAccount(landingPassword);
        mcUser.setPhone(moli);
        boolean user = false;
        //判断账号是否存在
        user = mcUserService.exists(mcUser.getLandingAccount());
        if (user) {
            throw new BusinessException("账号以存在");
        }

        //判断手机号码是否绑定
        user = mcUserService.isBind(mcUser.getPhone());
        if (user) {
            throw new BusinessException("手机号码以绑定");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成登陆token
//        String accessToken = TokenProccessor.getInstance().makeToken();
        //生成涮新token
//        String refreshToken = TokenProccessor.getInstance().makeToken();
        //生成随机ID
        String numberId = String.valueOf(uuidFeignClient.uuid());
        //密码加密
        String password = SecureUtil.sha256(landingAccount + "&key=" + signKey);
        mcUser.setNumberId(numberId);
        mcUser.setLandingPassword(password);
        mcUser.setNetworkName(landingPassword);
        mcUser.setAccountType(Mceunm.DEPARTMENT_STORE_SHOPPING.getKey());
        mcUser.setCreatedat(time);
        mcUser.setUpdatedat(time);
        //添加商户
        int mcUserResult = mcUserMapper.insertSelective(mcUser);
        if (mcUserResult > 0) {
            if (mcEntryinFormation == null) {
                throw new BusinessException("数据状态不正确");
            }
            boolean boo = mcEntryinFormationService.selectMcEntryinFormationById(numberId);
            if (boo){
                throw new BusinessException("信息已存在");
            }
            //生成随机ID
            String mcnumberid = String.valueOf(uuidFeignClient.uuid());
            mcEntryinFormation.setNumberId(mcnumberid);
            mcEntryinFormation.setCreatedat(time);
            mcEntryinFormation.setUpdatedat(time);
            mcEntryinFormation.setToExamine(String.valueOf(Mceunm.IN_AUDIT.getKey()));
            mcEntryinFormation.setMcNumberId(numberId);
            int result = mcEntryinFormationMapper.insertSelective(mcEntryinFormation);
            if (result == 0) {
                mcUserService.delUser(numberId);
            }
        }
    }
}
