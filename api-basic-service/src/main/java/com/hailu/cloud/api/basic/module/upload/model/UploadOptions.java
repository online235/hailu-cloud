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
    private boolean isImageCompress;

    /**
     * 压缩比例，只有启用图片压缩后该参数才生效， 范围0 - 1, 如：0.5， 0.8
     */
    private Double compressQuality;

    // endregion

    // region 图片裁剪参数

    /**
     * 是否裁剪图片, 注意，裁切会从最左上角0，0位置开始裁切
     */
    private boolean isCut;

    /**
     * 裁剪宽度
     */
    private int cutWidth;

    /**
     * 裁剪高度
     */
    private int cutHeight;

    // endregion

    // region 图片绽放参数

    /**
     * 是否缩放图片
     */
    private boolean isZoom;

    /**
     * 图片绽放宽度
     */
    private int zoomWidth;

    /**
     * 图片绽放高度
     */
    private int zoomHeight;

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
