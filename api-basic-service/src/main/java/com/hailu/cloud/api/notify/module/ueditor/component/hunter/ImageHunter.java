package com.hailu.cloud.api.notify.module.ueditor.component.hunter;

import com.hailu.cloud.api.notify.module.ueditor.component.PathFormat;
import com.hailu.cloud.api.notify.module.ueditor.component.define.*;
import com.hailu.cloud.api.notify.module.ueditor.component.upload.StorageManager;
import com.hailu.cloud.api.notify.module.upload.service.IFileStoreService;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 图片抓取器
 *
 * @author Administrator
 */
public class ImageHunter {

    private String filename;
    private String savePath;
    private List<String> allowTypes;
    private long maxSize;
    private String localSavePathPrefix;

    private List<String> filters;

    public ImageHunter(Map<String, Object> conf) {

        this.filename = (String) conf.get("filename");
        this.savePath = (String) conf.get("savePath");
        this.maxSize = (Long) conf.get("maxSize");
        this.allowTypes = Arrays.asList((String[]) conf.get("allowFiles"));
        this.filters = Arrays.asList((String[]) conf.get("filter"));
        this.localSavePathPrefix = (String) conf.get("localSavePathPrefix");

    }

    public State capture(String[] list, IFileStoreService storeService) {

        MultiState state = new MultiState(true);

        for (String source : list) {
            state.addState(captureRemoteData(source, storeService));
        }

        return state;

    }

    public State captureRemoteData(String urlStr, IFileStoreService storeService) {

        HttpURLConnection connection;
        URL url;
        String suffix;

        try {
            url = new URL(urlStr);

            if (!validHost(url.getHost())) {
                return new BaseState(false, AppInfo.PREVENT_HOST);
            }

            connection = (HttpURLConnection) url.openConnection();

            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(true);

            if (!validContentState(connection.getResponseCode())) {
                return new BaseState(false, AppInfo.CONNECTION_ERROR);
            }

            suffix = MimeType.getSuffix(connection.getContentType());

            if (!validFileType(suffix)) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            if (!validFileSize(connection.getContentLength())) {
                return new BaseState(false, AppInfo.MAX_SIZE);
            }

            String savePath = this.getPath(this.savePath, this.filename, suffix);
            String physicalPath = this.localSavePathPrefix + savePath;
            String path = physicalPath.substring(0, physicalPath.lastIndexOf("/"));
            String picName = physicalPath.substring(physicalPath.lastIndexOf("/") + 1);

            State state = StorageManager.saveFileByInputStream(connection.getInputStream(), path, picName, connection.getContentLength(), storeService);

            if (state.isSuccess()) {
                state.putInfo("url", null);
                state.putInfo("source", urlStr);
            }

            return state;

        } catch (Exception e) {
            return new BaseState(false, AppInfo.REMOTE_FAIL);
        }

    }

    private String getPath(String savePath, String filename, String suffix) {

        return PathFormat.parse(savePath + suffix, filename);

    }

    private boolean validHost(String hostname) {

        return !filters.contains(hostname);

    }

    private boolean validContentState(int code) {

        return HttpURLConnection.HTTP_OK == code;

    }

    private boolean validFileType(String type) {

        return this.allowTypes.contains(type);

    }

    private boolean validFileSize(int size) {
        return size < this.maxSize;
    }

}
