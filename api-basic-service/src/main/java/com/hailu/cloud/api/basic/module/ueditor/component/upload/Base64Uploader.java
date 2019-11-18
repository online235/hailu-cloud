package com.hailu.cloud.api.basic.module.ueditor.component.upload;

import com.hailu.cloud.api.basic.module.ueditor.component.PathFormat;
import com.hailu.cloud.api.basic.module.ueditor.component.define.AppInfo;
import com.hailu.cloud.api.basic.module.ueditor.component.define.BaseState;
import com.hailu.cloud.api.basic.module.ueditor.component.define.FileType;
import com.hailu.cloud.api.basic.module.ueditor.component.define.State;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
public final class Base64Uploader {

    public static State save(String content, Map<String, Object> conf) {

        byte[] data = decode(content);

        long maxSize = (Long) conf.get("maxSize");

        if (!validSize(data, maxSize)) {
            return new BaseState(false, AppInfo.MAX_SIZE);
        }

        String suffix = FileType.getSuffix("JPG");

        String savePath = PathFormat.parse((String) conf.get("savePath"),
                (String) conf.get("filename"));
        String localSavePathPrefix = PathFormat.parse((String) conf.get("localSavePathPrefix"),
                (String) conf.get("filename"));
        savePath = savePath + suffix;
        localSavePathPrefix = localSavePathPrefix + suffix;
        String physicalPath = localSavePathPrefix;
        log.info("Base64Uploader physicalPath:{},savePath:{}", localSavePathPrefix, savePath);
        State storageState = StorageManager.saveBinaryFile(data, physicalPath);

        if (storageState.isSuccess()) {
            storageState.putInfo("url", PathFormat.format(savePath));
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }

        return storageState;
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(StringUtils.getBytesUtf8(content));
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }

}