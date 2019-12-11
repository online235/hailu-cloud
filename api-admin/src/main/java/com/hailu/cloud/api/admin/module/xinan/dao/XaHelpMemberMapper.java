package com.hailu.cloud.api.admin.module.xinan.dao;


import com.hailu.cloud.api.admin.module.xinan.entity.XaHelpMember;
import com.hailu.cloud.api.admin.module.xinan.model.XaHelpMemberModel;

import java.util.List;

/**
 * @author zhangmugui
 */
public interface XaHelpMemberMapper {


    /**
     * 查询方法
     * @param parameter
     * @return
     */
    List<XaHelpMemberModel> findListByParameter(Object parameter);

    /**
     * 插入方法
     * @param xaHelpMember
     * @return
     */
    int insert(XaHelpMember xaHelpMember);

    /**
     * 更新方法
     * @param xaHelpMember
     * @return
     */
    int update(XaHelpMember xaHelpMember);


}
