package com.hailu.cloud.api.notify.module.ueditor.component.upload;

import com.hailu.cloud.api.notify.module.ueditor.component.define.AppInfo;
import com.hailu.cloud.api.notify.module.ueditor.component.define.BaseState;
import com.hailu.cloud.api.notify.module.ueditor.component.define.State;
import com.hailu.cloud.api.notify.module.upload.service.IFileStoreService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @author Administrator
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageManager {

    @Getter
    public static final int BUFFER_SIZE = 8192;

    @Getter
    @Setter
    private static String fileUrl;

    public static State saveBinaryFile(byte[] data, String path) {
        File file = new File(path);

        State state = valid(file);

        if (!state.isSuccess()) {
            return state;
        }

        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bos.write(data);
            bos.flush();
            bos.close();
        } catch (IOException ioe) {
            return new BaseState(false, AppInfo.IO_ERROR);
        }

        state = new BaseState(true, file.getAbsolutePath());
        state.putInfo("size", data.length);
        state.putInfo("title", file.getName());
        return state;
    }

    public static State saveFileByInputStream(
            InputStream is,
            String path,
            String picName,
            long fileSize,
            IFileStoreService storeService) {

        State state;

        try {
            // TODO 此处调用文件上传服务，并获取返回结果返回
            String savePath = storeService.saveFile(is, false, null, path, picName);

            //如果上传成功
            if (StringUtils.isNotBlank(savePath)) {
                state = new BaseState(true);
                state.putInfo("size", fileSize);
                //文件名填入此处
                state.putInfo("title", picName);
                //所属group填入此处
                state.putInfo("group", "");
                //文件访问的url填入此处
                state.putInfo("url", savePath);
            } else {
                state = new BaseState(false, 4);
            }
            return state;
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static State valid(File file) {
        File parentPath = file.getParentFile();

        if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
            return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
        }

        if (!parentPath.canWrite()) {
            return new BaseState(false, AppInfo.PERMISSION_DENIED);
        }

        return new BaseState(true);
    }
}
