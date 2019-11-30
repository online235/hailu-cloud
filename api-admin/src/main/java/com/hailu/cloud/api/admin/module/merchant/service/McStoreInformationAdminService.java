package com.hailu.cloud.api.admin.module.merchant.service;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.merchant.dao.McStoreInformationAdminMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.LocalCircleEntry;
import com.hailu.cloud.api.admin.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.admin.module.merchant.parmeter.LocalCircleListParameter;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McStoreInformationListParameter;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.auth.MerchantUserLoginInfoModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @Author: zhangmugui
 * @Description: 店铺详情模块
 */
@Service
public class McStoreInformationAdminService {



    @Resource
    private McStoreInformationAdminMapper mcStoreInformationAdminMapper;

    @Autowired
    private BasicFeignClient uuidFeign;



    /**
     * 查询入驻信息是否存在
     * @param mcNumberId
     * @return
     */
    public boolean selectMcEntryinFormationById(String mcNumberId){
        if (mcNumberId.isEmpty()){
            return true;
        }
        int result = mcStoreInformationAdminMapper.selectMcEntryinFormationById(mcNumberId);
        if (result == 0) {
            return false;
        }
        return true;
    }


    /**
     * 查询店铺信息列表
     */
    public Object selectLocalCircleEntryList(McStoreInformationListParameter mcStoreInformationListParameter) {

        Page pageData = PageHelper.startPage(mcStoreInformationListParameter.getPageNum(), mcStoreInformationListParameter.getPageSize());
        List<McStoreInformation> result = mcStoreInformationAdminMapper.selectMcStoreInformationList(mcStoreInformationListParameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }



    /**
     * 查看店铺信息详情
     * @return
     */
    public McStoreInformation findMcStoreInformation(Long id){

        return mcStoreInformationAdminMapper.findMcStoreInformation(id);
    }

    /**
     * 更改店铺审核状态 审核中-1'',''审核通过-2'',''审核不通过-3
     */
    public void updateMcStoreInformationStatus(Long id,Integer toExamine){

        if(toExamine == 2){

        }
        McStoreInformation mcStoreInformation = new McStoreInformation();
        mcStoreInformation.setToExamine(toExamine);
        mcStoreInformation.setId(id);
        mcStoreInformation.setUpdatedat(new Date());
        mcStoreInformationAdminMapper.updateByPrimaryKeySelective(mcStoreInformation);
    }


    /**
     * 更改店铺信息
     */
    public void updateMcStoreInformation(McStoreInformation mcStoreInformation){

        mcStoreInformationAdminMapper.updateByPrimaryKeySelective(mcStoreInformation);
    }


    /**
     * 删除店铺
     */
    public void deleMcStoreInformation(Long id){
        mcStoreInformationAdminMapper.deleteByPrimaryKey(id);
    }



}
