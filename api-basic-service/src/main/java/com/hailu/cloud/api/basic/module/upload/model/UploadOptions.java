package com.hailu.cloud.api.basic.module.upload.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author xuzhijie
 */
@Data
@Builder
public class UploadOptions {

    // region 图片压缩参数

    /**
     * true开启图片压缩(只有图片为jpg时才会压缩)
     */
    private Boolean isImageCompress;

    /**
     * 压缩比例，只有启用图片压缩后该参数才生效， 范围0 - 1, 如：0.5， 0.8
     */
    private Double compressQuality;

    // endregion

    // region 图片裁切参数

    /**
     * 是否裁切图片
     */
    private boolean isCut;

    // endregion

    // region 上传参数

    /**
     * 文件保存相对路径
     */
    private String path;

    /**
     * 保存名称
     */
    private String picName;

    /**
     * 业务代码
     */
    private String businessCode;

    // endregion

}
