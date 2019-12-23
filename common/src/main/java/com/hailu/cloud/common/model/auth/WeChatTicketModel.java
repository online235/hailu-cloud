package com.hailu.cloud.common.model.auth;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 调用微信JS接口的临时票据信息
 *
 * @author xuzhijie
 */
@Data
public class WeChatTicketModel {

    /**
     * 过期时间， 单位秒
     */
    @JSONField(name = "expires_in")
    private Long expiresIn;

    /**
     * 过期时间， 单位秒
     */
    private String ticket;

    @JSONField(name = "errcode")
    private String errCode;

    @JSONField(name = "errmsg")
    private String errMsg;

}
