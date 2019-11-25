package com.hailu.cloud.api.merchant.module.lifecircle.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.merchant.module.lifecircle.dao.LocalCircleEntryMapper;
import com.hailu.cloud.api.merchant.module.lifecircle.entity.LocalCircleEntry;
import com.hailu.cloud.api.merchant.module.merchant.dao.McStoreInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.api.merchant.module.merchant.service.impl.McStoreInformationService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * @author zhangmugui
 * @Description: 本地生活圈入驻模块
 *
 */
@Service
public class LocalCircleEntryService {


    @Resource
    private LocalCircleEntryMapper localCircleEntryMapper;

    @Autowired
    private BasicFeignClient uuidFeign;

    @Resource
    private McStoreInformationService mcStoreInformationService;

    /**
     * 添加入驻信息
     * @param localCircleEntry
     * @return
     */
    public Object insertSelective(LocalCircleEntry localCircleEntry) throws BusinessException {
        if (localCircleEntry == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(localCircleEntry.getMcNumberId());
        if (boo){
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
            stringObjectMap.put("numberId",numberid);
            return stringObjectMap;
        }
        throw new BusinessException("插入数据失败");
    }


    /**
     * 查询入驻审核是否通过
     * @param memberid
     * @return
     */
    public int selectByMcnumberidAndToexamine(String memberid){
        if (memberid.isEmpty()){
            return -1;
        }
        for (int i = 1; i <= 3; i++) {
            int result = localCircleEntryMapper.selectByMcnumberidAndToexamine(memberid,i);
            if (i == 1 && result > 0 ){
                return 1;
            }else if(i == 2 && result > 0){
                return 0;
            }else if (i == 3 && result > 0){
                return 2;
            }
        }
        return -1;
    }

    /**
     * 商家后台审核列表
     * @return
     */
    public Object localCircleEntryList(String shopname, String phone,Integer page, Integer szie){
        PageHelper.startPage(page, szie);
        PageInfo pageInfo = new PageInfo(localCircleEntryMapper.selectMcEntryinFormationList(shopname,phone));
        return pageInfo;
    }

    /**
     * 入驻信息详情
     * @param numberId
     * @return
     */
    public Object selectByPrimaryKey(String numberId){
        return localCircleEntryMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 更改审核状态
     * @param numberId
     * @param toExamine
     * @return
     */
    public void updateToExamineByNumberId(String numberId, Integer toExamine){
        LocalCircleEntry localCircleEntry = new LocalCircleEntry();
        localCircleEntry.setNumberId(numberId);
        localCircleEntry.setToExamine(toExamine);
        localCircleEntry.setUpdatedat(System.currentTimeMillis());
        localCircleEntry.setMcNumberId(null);
        localCircleEntryMapper.updateByPrimaryKeySelective(localCircleEntry);
    }

    /**
     * 更改审核信息
     * @param localCircleEntry
     * @return
     */
    public void updateMcEntryInformation(LocalCircleEntry localCircleEntry){
        localCircleEntry.setUpdatedat(System.currentTimeMillis());
        localCircleEntry.setToExamine(1);
        localCircleEntry.setNumberId(null);
        localCircleEntryMapper.updateByPrimaryKeySelective(localCircleEntry);
    }



    /**
     * 删除信息
     * @param numberId
     * @return
     */
    public void deleteByPrimaryKey(String numberId){
        int result = localCircleEntryMapper.deleteByPrimaryKey(numberId);
    }

    /**
     * 查询入驻信息是否存在
     * @param mcnumberid
     * @return
     */
    public boolean selectMcEntryinFormationById(String mcnumberid){
        if (mcnumberid.isEmpty()){
            return true;
        }
        int result = localCircleEntryMapper.selectMcEntryinFormationById(mcnumberid);
        if (result == 0) {
            return false;
        }
        return true;
    }


    /**
     * 更新店铺入驻资质
     */
    public void updateSelective(LocalCircleEntry localCircleEntry) throws BusinessException {
        if (localCircleEntry == null) {
            throw new BusinessException("信息为空");
        }
        localCircleEntryMapper.updateByPrimaryKeySelective(localCircleEntry);
    }

    /**
     * 插入入驻信息
     */
    /**
     * 添加入驻信息
     * @param localCircleEntry
     * @param userNumberId
     * @return
     */
    public void setLocalCircleEntry(LocalCircleEntry localCircleEntry,String userNumberId) throws BusinessException {

        if (localCircleEntry == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(localCircleEntry.getMcNumberId());
        if (boo){
            throw new BusinessException("入驻信息以填写");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成随机ID
        String numberid = String.valueOf(uuidFeign.uuid());
        localCircleEntry.setMcNumberId(userNumberId);
        localCircleEntry.setNumberId(numberid);
        localCircleEntry.setCreatedat(time);
        localCircleEntry.setUpdatedat(time);
        localCircleEntry.setToExamine(Mceunm.IN_AUDIT.getKey());
        int result = localCircleEntryMapper.insertSelective(localCircleEntry);
        if (result <= 0) {
            throw new BusinessException("插入数据失败");
        }
        McStoreInformation mcStoreInformation = new McStoreInformation();
        BeanUtils.copyProperties(localCircleEntry, mcStoreInformation);
        mcStoreInformationService.insertSelective(mcStoreInformation);

    }


}
