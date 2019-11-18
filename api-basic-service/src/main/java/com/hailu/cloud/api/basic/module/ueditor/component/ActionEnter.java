package com.hailu.cloud.api.basic.module.ueditor.component;

import com.alibaba.fastjson.JSONException;
import com.hailu.cloud.api.basic.module.ueditor.component.define.ActionMap;
import com.hailu.cloud.api.basic.module.ueditor.component.define.AppInfo;
import com.hailu.cloud.api.basic.module.ueditor.component.define.BaseState;
import com.hailu.cloud.api.basic.module.ueditor.component.define.State;
import com.hailu.cloud.api.basic.module.ueditor.component.hunter.FileManager;
import com.hailu.cloud.api.basic.module.ueditor.component.hunter.ImageHunter;
import com.hailu.cloud.api.basic.module.ueditor.component.upload.Uploader;
import com.hailu.cloud.api.basic.module.upload.service.IFileStoreService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;


/**
 * @author Administrator
 */
public class ActionEnter {

    private HttpServletRequest request;

    private String actionType;

    private ConfigManager configManager;

    public ActionEnter(HttpServletRequest request, String rootPath, String serverPrefix) {

        this.request = request;
        this.actionType = request.getParameter("action");
        String contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(rootPath, contextPath, request.getRequestURI(), serverPrefix);
    }

    public String exec(IFileStoreService storeService) throws JSONException {

        String callbackName = this.request.getParameter("callback");

        if (callbackName != null) {

            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJsonString();
            }

            return this.invoke(storeService);

        } else {
            return this.invoke(storeService);
        }

    }

    public String invoke(IFileStoreService storeService) throws JSONException {

        if (actionType == null || !ActionMap.MAPPING.containsKey(actionType)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJsonString();
        }

        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJsonString();
        }

        State state = null;

        int actionCode = ActionMap.getType(this.actionType);

        Map<String, Object> conf;

        switch (actionCode) {

            case ActionMap.CONFIG:
                return this.configManager.getAllConfig().toString();

            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                conf = this.configManager.getConfig(actionCode);
                state = new Uploader(request, conf).doExec(storeService);
                break;

            case ActionMap.CATCH_IMAGE:
                conf = configManager.getConfig(actionCode);
                String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
                state = new ImageHunter(conf).capture(list, storeService);
                break;

            case ActionMap.LIST_IMAGE:
            case ActionMap.LIST_FILE:
                conf = configManager.getConfig(actionCode);
                int start = this.getStartIndex();
                state = new FileManager(conf).listFile(start);
                break;
            default:
                break;
        }

        return Objects.requireNonNull(state).toJsonString();

    }

    public int getStartIndex() {

        String start = this.request.getParameter("start");

        try {
            return Integer.parseInt(start);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * callback参数验证
     */
    public boolean validCallbackName(String name) {

        String reg = "^[a-zA-Z_]+[\\w0-9_]*$";
        if (name.matches(reg)) {
            return true;
        }

        return false;

    }

}