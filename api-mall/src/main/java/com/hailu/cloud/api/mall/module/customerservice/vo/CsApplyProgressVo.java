package com.hailu.cloud.api.mall.module.customerservice.vo;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class CsApplyProgressVo {
    private Integer csApplyProgressId;
    private Integer csApplyId;
    private String auditContent;

    /**
     * 0未删除1删除
     */
    private Integer deleteStatus;
    private long createDate;
    private String createName;
    private long updateDate;
    private String updateName;
    private Integer tpState;

}
