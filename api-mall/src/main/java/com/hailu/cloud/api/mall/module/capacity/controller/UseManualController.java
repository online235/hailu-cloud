package com.hailu.cloud.api.mall.module.capacity.controller;


import com.hailu.cloud.api.mall.module.capacity.service.IUseManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

/**
 * @author xuzhijie
 */
@RestController
@RequestMapping(value = "/api/capacity")
public class UseManualController {

    @Autowired
    private IUseManualService useManualService;

    /**
     * 获取搜索热词
     *
     * @return
     */
    @GetMapping("/getCapacityHotWord")
    public Map<String, Object> getCapacityHotWord() {
        return useManualService.getCapacityHotWord();
    }
}
