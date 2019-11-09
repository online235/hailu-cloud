package com.hailu.cloud.api.notify.module.ueditor.component.upload;


import com.hailu.cloud.api.notify.module.ueditor.component.define.State;
import com.hailu.cloud.api.notify.module.upload.service.IFileStoreService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author Administrator
 */
public class Uploader {
    private HttpServletRequest request;
    private Map<String, Object> conf;

    public Uploader(HttpServletRequest request, Map<String, Object> conf) {
        this.request = request;
        this.conf = conf;
    }

    public final State doExec(IFileStoreService storeService) {
        String filedName = (String) this.conf.get("fieldName");
        State state;

        String isBase64 = "true";
        String isBase64Key = "isBase64";
        if (isBase64.equals(this.conf.get(isBase64Key))) {
            state = Base64Uploader.save(this.request.getParameter(filedName),
                    this.conf);
        } else {
            state = BinaryUploader.save(this.request, this.conf, storeService);
        }

        return state;
    }
}
