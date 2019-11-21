package com.hailu.cloud.api.mall.module.sys.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author HuangL
 * @Email huangl96@163.com
 * @Description
 * @Date 2019/2/16 10:22
 */
@Data
public class DownloadStat implements Serializable {
    private Long id;
    private Long createTime;
    private Date downloadDate;
    private String ip;

}
