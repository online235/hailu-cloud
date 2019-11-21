package com.hailu.cloud.api.mall.module.goods.vo;

import lombok.Data;

/**
 * 项目进展
 *
 * @author leiqi
 */
@Data
public class ProgressVo {
    private int id;
    private int goodsId;
    private String content;
    private Long time;

}
