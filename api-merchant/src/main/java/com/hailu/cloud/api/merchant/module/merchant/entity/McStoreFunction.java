package com.hailu.cloud.api.merchant.module.merchant.entity;
import lombok.Data;
import java.util.Date;

/**
 * 店铺功能表
 *
 * @author zhijie
 */
@Data
public class McStoreFunction {

    /**
     * 店铺功能表id
     */
    private Long id;

    /**
     * 店铺表id
     */
    private Long storeId;

    /**
     * 是否预定：1、开启预定；2、关闭预定
     */
    private Integer reserveStatus;

    /**
     * 是否开启自动接单：1-开启自动接单；2-关闭自动接单
     */
    private Integer automaticStatus;

    /**
     * 相同时段最高接单数
     */
    private Integer maxOrderNum;

    /**
     * 预约保留时间（单位：分钟）
     */
    private Integer appointmentSaveTime;

    /**
     * 创建时间
     */
    private Date dateTime;

    /**
     * 更新时间
     */
    private Date updateDateTime;

}
