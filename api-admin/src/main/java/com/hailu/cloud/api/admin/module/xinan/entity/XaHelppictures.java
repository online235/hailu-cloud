package com.hailu.cloud.api.admin.module.xinan.entity;
import lombok.Data;
import java.util.Date;

/**
 * null
 *
 * @author zhijie
 */
@Data
public class XaHelppictures {

    /**
     * 编号
     */
    private Long numberId;

    /**
     * 图片路径
     */
    private String picture;

    /**
     * 图片名称
     */
    private String pictureName;

    /**
     * 互助表ID
     */
    private Long mutualaId;

    /**
     * 图片类型1-病历','2-互助者图片;3-视频
     */
    private Integer pictureType;

    /**
     * 创建时间
     */
    private Date dateTime;

}
