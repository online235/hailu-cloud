package com.hailu.cloud.api.notify.module.ueditor.controller;

import com.hailu.cloud.api.notify.module.ueditor.component.ActionEnter;
import com.hailu.cloud.api.notify.module.upload.service.IFileStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @Author xuzhijie
 * @Date 2019/10/25 16:06
 */
@Api(tags = "百度富文本编辑器接口")
@RestController
@RequestMapping("/ueditor")
public class UeditorController {

    @Autowired
    private IFileStoreService storeService;

    @Value("${static.server.prefix}")
    private String serverPrefix;

    /**
     * 文件上传处理
     *
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @ApiOperation(value = "文件上传处理")
    @RequestMapping(value = "/exec", method = {RequestMethod.GET, RequestMethod.POST})
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getServletContext().getRealPath("/");
        return new ActionEnter(request, rootPath, serverPrefix).exec(storeService);
    }

}
