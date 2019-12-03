package com.hailu.cloud.api.mall.module.multiindustry.entity;

import java.io.Serializable;
import java.util.Date;

public class StoreAlbum implements Serializable {
    /**
     * 相册id
     */
    private Long id;

    /**
     * 店铺id
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

    /**
     * mc_store_album
     */
    private static final long serialVersionUID = 1L;

    /**
     * 相册id
     * @return id 相册id
     */
    public Long getId() {
        return id;
    }

    /**
     * 相册id
     * @param id 相册id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 店铺id
     * @return store_id 店铺id
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * 店铺id
     * @param storeId 店铺id
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * 相册路径
     * @return album_url 相册路径
     */
    public String getAlbumUrl() {
        return albumUrl;
    }

    /**
     * 相册路径
     * @param albumUrl 相册路径
     */
    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl == null ? null : albumUrl.trim();
    }

    /**
     * 创新时间
     * @return create_time 创新时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创新时间
     * @param createTime 创新时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更改时间
     * @return update_time 更改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更改时间
     * @param updateTime 更改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}