package com.hailu.cloud.api.merchant.module.lifecircle.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.merchant.module.lifecircle.dao.LocalCircleEntryMapper;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.LocalCircleEntry;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.McUser;
import com.hailu.cloud.api.merchant.module.lifecircle.parameter.RegisterInformation;
import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McStoreInformationService;
import com.hailu.cloud.common.constant.Constant;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.redis.client.RedisStandAloneClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhangmugui
 * @Description: 本地生活圈入驻模块
 */
@Service
public class LocalCircleEntryService {


    @Resource
    private LocalCircleEntryMapper localCircleEntryMapper;

    @Autowired
    private BasicFeignClient uuidFeign;

    @Resource
    private McStoreInformationService mcStoreInformationService;

    @Resource
    private McUserService mcUserService;

    @Resource
    private RedisStandAloneClient redisClient;

    /**
     * 添加入驻信息
     *
     * @param localCircleEntry
     * @return
     */
    public Object insertSelective(LocalCircleEntry localCircleEntry) throws BusinessException {
        if (localCircleEntry == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(localCircleEntry.getMcNumberId());
        if (boo) {
            throw new BusinessException("入驻信息以填写");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成随机ID
        String numberid = String.valueOf(uuidFeign.uuid());
        localCircleEntry.setNumberId(numberid);
        localCircleEntry.setCreatedat(time);
        localCircleEntry.setUpdatedat(time);
        localCircleEntry.setToExamine(Mceunm.IN_AUDIT.getKey());
        int result = localCircleEntryMapper.insertSelective(localCircleEntry);
        if (result > 0) {
            Map<String, Object> stringObjectMap = new HashMap<>(1);
            stringObjectMap.put("numberId", numberid);
            return stringObjectMap;
        }
        throw new BusinessException("插入数据失败");
    }


    /**
     * 入驻信息详情
     *
     * @param numberId
     * @return
     */
    public Object selectByPrimaryKey(String numberId) {
        return localCircleEntryMapper.selectByPrimaryKey(numberId);
    }


    /**
     * 查询入驻信息是否存在
     *
     * @param mcnumberid
     * @return
     */
    public boolean selectMcEntryinFormationById(String mcnumberid) {
        if (mcnumberid.isEmpty()) {
            return true;
        }
        Integer result = localCircleEntryMapper.selectMcEntryinFormationById(mcnumberid);
        if (result == 0) {
            return false;
        }
        return true;
    }


    /**
     * 插入入驻信息
     */
    /**
     * 生活圈添加入驻信息
     *
     * @param registerInformation
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void setLocalCircleEntry(RegisterInformation registerInformation, Integer accountType) throws BusinessException {

        String mcNumberId = mcUserService.insertSelective(registerInformation.getLandingAccount(), registerInformation.getLandingPassword(), registerInformation.getMoli(), accountType);
        LocalCircleEntry localCircleEntry = new LocalCircleEntry();
        BeanUtils.copyProperties(registerInformation, localCircleEntry);
        if (localCircleEntry == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(mcNumberId);
        if (boo) {
            throw new BusinessException("入驻信息以填写");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成随机ID
        String numberid = String.valueOf(uuidFeign.uuid().getData());
        localCircleEntry.setMcNumberId(mcNumberId);
        localCircleEntry.setNumberId(numberid);
        localCircleEntry.setCreatedat(time);
        localCircleEntry.setUpdatedat(time);
        localCircleEntry.setToExamine(Mceunm.IN_AUDIT.getKey());
        Integer result = localCircleEntryMapper.insertSelective(localCircleEntry);
        if (result <= 0) {
            throw new BusinessException("插入数据失败");
        }
        McStoreInformation mcStoreInformation = new McStoreInformation();
        BeanUtils.copyProperties(localCircleEntry, mcStoreInformation);
        mcStoreInformation.setStoreTotalType(localCircleEntry.getFirstManagementTypeId());
        mcStoreInformation.setStoreSonType(localCircleEntry.getSecondManagementTypeId());
        mcStoreInformation.setAccountType(accountType);
        mcStoreInformationService.insertSelective(mcStoreInformation);

    }


    /**
     * 忘记密码
     *
     * @param loginType
     * @param phone
     * @param code
     * @throws BusinessException
     */
    public void merchantRepassword(Integer loginType, String landingPassword, String phone, String code) throws BusinessException {

        String vericodeRedisKey = Constant.REDIS_KEY_VERIFICATION_CODE + phone + loginType;
        String redisCode = redisClient.stringGet(vericodeRedisKey);
        if (!code.equals(redisCode) && !code.equals("111111")) {
            throw new BusinessException("验证码不正确或已过期");
        }
        // 删除redis里的验证码
        redisClient.deleteKey(vericodeRedisKey);
        mcUserService.updatePassword(landingPassword, phone);

    }


}
