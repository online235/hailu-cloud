package com.hailu.cloud.api.mall.module.customerservice.dao;

import com.hailu.cloud.api.mall.module.customerservice.vo.CsApplyProgressVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CsApplyProressDao {
    /**
     * 通售后申请ID  获取正在进行的售后的进度
     *
     * @return
     */
    List<CsApplyProgressVo> findByApplyProgressId(int csApplyId);

    /**
     * 通售后申请ID  获取正在进行的售后的进度 用户取消
     *
     * @return
     */
    List<CsApplyProgressVo> findByApplyProgressId2(int csApplyId);

    /**
     * 通售后申请ID  获取正在进行的售后的进度 平台拒绝
     *
     * @return
     */
    List<CsApplyProgressVo> findByApplyProgressId3(int csApplyId);

    /**
     * 添加售后进度
     *
     * @param csApplyProgressVo
     */
    void addApplyProgress(CsApplyProgressVo csApplyProgressVo);


    /**
     * 通售后申请ID  获取正在进行的售后的进度
     *
     * @return
     */
    CsApplyProgressVo findApplyStatusProgress(
            @Param("csApplyId") Integer csApplyId,
            @Param("tpState") Integer tpState
    );

}
