package com.hailu.cloud.api.mall.module.goods.controller;


import com.hailu.cloud.api.mall.module.goods.service.IconService;
import com.hailu.cloud.api.mall.module.goods.vo.IconVo;
import com.hailu.cloud.api.mall.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城首页功能图标
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/")
public class IconController {

    @Autowired
    private IconService iconService;

    @GetMapping("api/mall/iconList")
    public Map<String, Object> iconList() throws Exception {
        List<IconVo> iconList = iconService.iconList(2);
        for (IconVo iconVo : iconList) {
            iconVo.setIconPath(Const.PRO_URL + iconVo.getIconPath());
        }
        Map<String, Object> data = new HashMap<>();
        // 商品分类
        data.put("iconList", iconList);
        return data;
    }

}
