package com.hailu.cloud.common.model.basic;

import com.hailu.cloud.common.model.BaseEntity;
import lombok.Data;

/**
 * @author 190726
 */
@Data
public class VersionManagement extends BaseEntity {

    /**
     * 版本号
     */
    private String version;

    /**
     * 构建号
     */
    private String build;

    /**
     * 标题
     */
    private String title;

    /**
     * 版本内容
     */
    private String content;

    /**
     * 更新标识
     */
    private String mandatory;

    /**
     * 1-强制更新、2-非强制更新、3-无需更新
     */
    private Integer renewal;

    /**
     * 下载跳转URl
     */
    private String url;


    /**
     * 设备类型（1-IOS、2-安卓）
     */
    private Integer type;

    /**
     * 状态（1-正常、2-删除）
     */
    private Integer status;



}
