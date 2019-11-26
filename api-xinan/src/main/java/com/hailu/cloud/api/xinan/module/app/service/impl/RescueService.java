package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.RescueMapper;
import com.hailu.cloud.api.xinan.module.app.entity.Rescue;
import com.hailu.cloud.api.xinan.module.app.model.RescueVo;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 救助表Service
 * @Date: 18:14 2019/11/12 0012
 */
@Service
public class RescueService {

    @Resource
    private RescueMapper rescueMapper;

    @Autowired
    private RescueInfoService rescueInfoService;

    /**
     * 添加救助
     * @param rescue
     * @return
     */
    public int insertSelective(Rescue rescue){
        if (rescue == null){
            return 0;
        }
        return rescueMapper.insertSelective(rescue);
    }

    /**
     * 删除救助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(String numberId){
        return rescueMapper.deleteByPrimaryKey(numberId);
    }

    /**
     * 根据编号查询救助信息
     * @param numberId
     * @return
     */
    public Rescue findRescueById(String numberId){
        if (StringUtils.isBlank(numberId)){
            return null;
        }
        return rescueMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 查询救助列表
     * @return
     */
    public PageInfoModel findXaRescueList(Integer page, Integer size) throws BusinessException {
        Page pageSize =  PageHelper.startPage(page, size);
        List<RescueVo> xaRescueVos = rescueMapper.findXaRescueVo();
        if (xaRescueVos == null ){
            throw new BusinessException("未获取任何信息");
        }
        return new PageInfoModel(pageSize.getPages(),pageSize.getTotal(),xaRescueVos);
    }
    /**
     * 修改救助审核
     * @param numberId
     * @return
     */
    public void updateByPrimaryKeySelective(String numberId, String examine){
        Rescue rescue = new Rescue();
        rescue.setNumberId(numberId);
        rescue.setExamine(examine);
        rescueMapper.updateByPrimaryKeySelective(rescue);
    }

}
