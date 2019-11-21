package com.hailu.cloud.api.mall.module.customerservice.entity;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class CsResonEntity {
    private Integer csReasonId;
    private Integer reasonType;
    private String reason;

    private Integer deleteStatus;
    private Integer createDate;
    private String createName;
    private Integer updateDate;
    private String updateName;
}
