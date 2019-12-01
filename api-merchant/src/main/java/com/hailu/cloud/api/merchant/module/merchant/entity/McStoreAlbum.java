package com.hailu.cloud.api.merchant.module.merchant.entity;


import lombok.Data;

import java.util.Date;

/**
 * @author zhangmugui
 */
@Data
public class McStoreAlbum {


    /**
     * 相册id
     */
    private Long id;


    /**
     *店铺id
     */
    private Long storeId;

    /**
     * 相册路径
     */
    private String albumUrl;


    /**
     * 创新时间
     */
    private Date createTime;


    /**
     * 更改时间
     */
    private Date updateTime;



}
