package com.hailu.cloud.api.mall.module.sys.dao;

import com.hailu.cloud.api.mall.module.sys.entiy.SysAttribute;
import com.hailu.cloud.api.mall.module.sys.vo.SysAttributeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统属性表
 * Created by somebody on 2016/6/15.
 */
@Mapper
public interface SysAttributeMapper {
    /**
     * 根据key获取属性
     *
     * @return
     */
    SysAttributeVO getAttributeByKey(SysAttributeVO sysAttributeVO);

    String getDocoumentByCode(String code);

    SysAttributeVO findAttributeValue(String attributeKey);

    /**
     * 获取对应的东西
     *
     * @author HuangL
     * @email huangl96@163.com
     * @date 3:26 PM 9/7/2019
     */
    List<SysAttribute> findAttributes(String[] params);
}
