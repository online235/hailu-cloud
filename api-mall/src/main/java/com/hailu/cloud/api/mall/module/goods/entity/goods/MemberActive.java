package com.hailu.cloud.api.mall.module.goods.entity.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class MemberActive implements Serializable {

    private static final long serialVersionUID = 1615467821089700131L;
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private String memberId;
}
