package com.hailu.cloud.api.xinan.module.app.model;

import com.hailu.cloud.api.xinan.module.app.entity.Rescue;
import lombok.Data;

@Data
public class RescueVo extends Rescue {

    /**
     * 图片路径
     */
    private String picture;
}
