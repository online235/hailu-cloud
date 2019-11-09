package com.hailu.cloud.api.notify.module.ueditor.component.upload;

import com.hailu.cloud.api.notify.module.ueditor.component.PathFormat;
import com.hailu.cloud.api.notify.module.ueditor.component.define.AppInfo;
import com.hailu.cloud.api.notify.module.ueditor.component.define.BaseState;
import com.hailu.cloud.api.notify.module.ueditor.component.define.FileType;
import com.hailu.cloud.api.notify.module.ueditor.component.define.State;
import com.hailu.cloud.api.notify.module.upload.service.IFileStoreService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryUploader {

    public static final State save(
            HttpServletRequest request,
            Map<String, Object> conf,
            IFileStoreService storeService) {

        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("upfile");
            if (file == null) {
                return new BaseState(false, AppInfo.IO_ERROR);
            }

            String savePath = (String) conf.get("savePath");
            String localSavePathPrefix = (String) conf.get("localSavePathPrefix");
            String originFileName = file.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = (Long) conf.get("maxSize");
            long fileSize = file.getSize();
            if (fileSize > maxSize) {
                // 超出最大上传
                return new BaseState(false, AppInfo.MAX_SIZE);
            }

            String allowFiles = "allowFiles";
            if (!validType(suffix, (String[]) conf.get(allowFiles))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }
            savePath = PathFormat.parse(savePath, originFileName);
            localSavePathPrefix = localSavePathPrefix + savePath;
            String physicalPath = localSavePathPrefix;
            log.info("BinaryUploader physicalPath:{},savePath:{}", localSavePathPrefix, savePath);
            InputStream is = file.getInputStream();

            //在此处调用ftp的上传图片的方法将图片上传到文件服务器
            String path = physicalPath.substring(0, physicalPath.lastIndexOf("/"));
            String picName = physicalPath.substring(physicalPath.lastIndexOf("/") + 1);
            State storageState = StorageManager.saveFileByInputStream(
                    is,
                    path,
                    picName,
                    fileSize,
                    storeService);

            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (Exception e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        }
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
