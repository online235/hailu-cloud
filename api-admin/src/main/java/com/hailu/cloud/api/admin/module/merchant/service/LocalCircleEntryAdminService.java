package com.hailu.cloud.api.admin.module.merchant.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.admin.module.merchant.dao.LocalCircleEntryAdminMapper;
import com.hailu.cloud.api.admin.module.merchant.entity.LocalCircleEntry;
import com.hailu.cloud.api.admin.module.merchant.parmeter.LocalCircleListParameter;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * @author zhangmugui
 * @Description: 本地生活圈入驻模块
 *
 */
@Service
public class LocalCircleEntryAdminService {


    @Resource
    private LocalCircleEntryAdminMapper localCircleEntryAdminMapper;

    @Autowired
    private BasicFeignClient uuidFeign;



    /**
     * 查询生活圈入驻信息列表
     */
    public PageInfoModel<List<LocalCircleEntry>> selectLocalCircleEntryList(LocalCircleListParameter localCircleListParameter) {

        Page pageData = PageHelper.startPage(localCircleListParameter.getPageNum(), localCircleListParameter.getPageSize());
        List<LocalCircleEntry> result = localCircleEntryAdminMapper.selectMcEntryinFormationList(localCircleListParameter);
        return new PageInfoModel<>(pageData.getPages(), pageData.getTotal(), result);
    }


    /**
     * 入驻信息详情
     * @param numberId
     * @return
     */
    public LocalCircleEntry selectByPrimaryKey(String numberId){
        return localCircleEntryAdminMapper.selectByPrimaryKey(numberId);
    }


    /**
     * 更改审核状态
     * @param numberId
     * @param toExamine
     */
    public void updateLocalCircleEntryStatus(String numberId,Integer toExamine){

        LocalCircleEntry localCircleEntry = new LocalCircleEntry();
        localCircleEntry.setToExamine(toExamine);
        localCircleEntry.setNumberId(numberId);
        localCircleEntry.setUpdatedat(System.currentTimeMillis());
        localCircleEntryAdminMapper.updateByPrimaryKeySelective(localCircleEntry);
    }

    /**
     * 更新入驻信息
     */
    public void updateLocalCircleEntry(LocalCircleEntry localCircleEntry ){

        localCircleEntryAdminMapper.updateByPrimaryKeySelective(localCircleEntry);
    }


    /**
     * 删除入驻信息
     */
    public void deleteByPrimaryKey(String numberId){
        localCircleEntryAdminMapper.deleteByPrimaryKey(numberId);
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
        Integer result = localCircleEntryAdminMapper.selectMcEntryinFormationById(mcnumberid);
        if (result == 0) {
            return false;
        }
        return true;
    }



}
