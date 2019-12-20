package com.hailu.cloud.api.xinan.module.app.dao;

import com.hailu.cloud.api.xinan.module.app.entity.XaHelpMember;
import com.hailu.cloud.api.xinan.module.app.model.XaHelpMemberModel;

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

    /**
     * 查询详情
     * @return
     */
    XaHelpMemberModel findXaHelpMemberModelById(Long id);


}
