package com.hailu.cloud.api.merchant.module.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hailu.cloud.api.merchant.module.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.eunms.Mceunm;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.UuidFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户入驻模块
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class McEntryinFormationService {

    @Resource
    private McEntryInformationMapper mcEntryinFormationMapper;

    @Autowired
    private UuidFeignClient uuidFeign;

    /**
     * 添加入驻信息
     * @param mcEntryinFormation
     * @return
     */
    public Object insertSelective(McEntryInformation mcEntryinFormation) throws BusinessException {
        if (mcEntryinFormation == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(mcEntryinFormation.getMcNumberId());
        if (boo){
            throw new BusinessException("入驻信息以填写");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成随机ID
        String numberid = String.valueOf(uuidFeign.uuid());
        mcEntryinFormation.setNumberId(numberid);
        mcEntryinFormation.setCreatedat(time);
        mcEntryinFormation.setUpdatedat(time);
        mcEntryinFormation.setToExamine(String.valueOf(Mceunm.IN_AUDIT.getKey()));
        int result = mcEntryinFormationMapper.insertSelective(mcEntryinFormation);
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
            int result = mcEntryinFormationMapper.selectByMcnumberidAndToexamine(memberid,i);
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
    public Object selectMcEntryinFormationList(String shopname, String phone,Integer page, Integer szie){
        PageHelper.startPage(page, szie);
        PageInfo pageInfo = new PageInfo(mcEntryinFormationMapper.selectMcEntryinFormationList(shopname,phone));
        return pageInfo;
    }

    /**
     * 入驻信息详情
     * @param numberId
     * @return
     */
    public Object selectByPrimaryKey(String numberId){
        return mcEntryinFormationMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 更改审核状态
     * @param numberId
     * @param toExamine
     * @return
     */
    public void updateToExamineByNumberId(String numberId, String toExamine){
        McEntryInformation mcEntryinFormation = new McEntryInformation();
        mcEntryinFormation.setNumberId(numberId);
        mcEntryinFormation.setToExamine(toExamine);
        mcEntryinFormation.setUpdatedat(System.currentTimeMillis());
        mcEntryinFormation.setMcNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }

    /**
     * 更改审核信息
     * @param mcEntryinFormation
     * @return
     */
    public void updateMcEntryInformation(McEntryInformation mcEntryinFormation){
        mcEntryinFormation.setUpdatedat(System.currentTimeMillis());
        mcEntryinFormation.setToExamine("1");
        mcEntryinFormation.setNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }



    /**
     * 删除信息
     * @param numberId
     * @return
     */
    public void deleteByPrimaryKey(String numberId){
        int result = mcEntryinFormationMapper.deleteByPrimaryKey(numberId);
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
        int result = mcEntryinFormationMapper.selectMcEntryinFormationById(mcnumberid);
        if (result == 0) {
            return false;
        }
        return true;
    }


    /**
     * 更新店铺入驻资质
     */
    public void updateSelective(McEntryInformation mcEntryinFormation) throws BusinessException {
        if (mcEntryinFormation == null) {
            throw new BusinessException("信息为空");
        }
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }


}
