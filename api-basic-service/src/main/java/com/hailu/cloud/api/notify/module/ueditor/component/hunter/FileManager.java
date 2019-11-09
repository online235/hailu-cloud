package com.hailu.cloud.api.notify.module.ueditor.component.hunter;

import com.hailu.cloud.api.notify.module.ueditor.component.PathFormat;
import com.hailu.cloud.api.notify.module.ueditor.component.define.AppInfo;
import com.hailu.cloud.api.notify.module.ueditor.component.define.BaseState;
import com.hailu.cloud.api.notify.module.ueditor.component.define.MultiState;
import com.hailu.cloud.api.notify.module.ueditor.component.define.State;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


/**
 * @author Administrator
 */
public class FileManager {

    private String dir;
    private String rootPath;
    private String[] allowFiles;
    private int count;

    public FileManager(Map<String, Object> conf) {

        this.rootPath = (String) conf.get("rootPath");
        this.dir = this.rootPath + conf.get("dir");
        this.allowFiles = this.getAllowFiles(conf.get("allowFiles"));
        this.count = (Integer) conf.get("count");

    }

    public State listFile(int index) {

        File dir = new File(this.dir);
        State state;

        if (!dir.exists()) {
            return new BaseState(false, AppInfo.NOT_EXIST);
        }

        if (!dir.isDirectory()) {
            return new BaseState(false, AppInfo.NOT_DIRECTORY);
        }

        Collection<File> list = FileUtils.listFiles(dir, this.allowFiles, true);

        if (index < 0 || index > list.size()) {
            state = new MultiState(true);
        } else {
            Object[] fileList = Arrays.copyOfRange(list.toArray(), index, index + this.count);
            state = this.getState(fileList);
        }

        state.putInfo("start", index);
        state.putInfo("total", list.size());

        return state;

    }

    private State getState(Object[] files) {

        MultiState state = new MultiState(true);
        BaseState fileState;

        File file;

        for (Object obj : files) {
            if (obj == null) {
                break;
            }
            file = (File) obj;
            fileState = new BaseState(true);
            fileState.putInfo("url", PathFormat.format(this.getPath(file)));
            state.addState(fileState);
        }

        return state;

    }

    private String getPath(File file) {

        String path = file.getAbsolutePath();

        return path.replace(this.rootPath, "/");

    }

    private String[] getAllowFiles(Object fileExt) {

        String ext;

        if (fileExt == null) {
            return new String[0];
        }

        String[] exts = (String[]) fileExt;

        for (int i = 0, len = exts.length; i < len; i++) {

            ext = exts[i];
            exts[i] = ext.replace(".", "");

        }

        return exts;

    }

}
