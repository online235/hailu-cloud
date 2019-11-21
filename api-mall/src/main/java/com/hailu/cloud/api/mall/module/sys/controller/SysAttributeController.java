package com.hailu.cloud.api.mall.module.sys.controller;

import com.hailu.cloud.api.mall.module.sys.service.ISysAttributeService;
import com.hailu.cloud.api.mall.module.sys.vo.SysAttributeVO;
import com.hailu.cloud.api.mall.util.BaseRequest;
import com.hailu.cloud.api.mall.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统属性
 */
@Slf4j
@Controller
@RequestMapping(value = "api/sys/")
public class SysAttributeController {

    @Resource
    private ISysAttributeService sysAttributeService;

    /**
     * 根据key  获取系统属性
     *
     * @param key
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "attribute")
    @ResponseBody
    public SysAttributeVO info(@RequestParam String key,
                                             @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("根据key获取属性uptoken|param={}", baseInfo);
        SysAttributeVO sysAttributeVO2 = new SysAttributeVO();
        SysAttributeVO sysAttributeVO = new SysAttributeVO();
        if ("-1".equals(key)) {
            sysAttributeVO2.setAttributeKey("payType");
        } else {
            sysAttributeVO.setAttributeKey(key);
            return sysAttributeService.getAttributeByKey(sysAttributeVO);
        }

        return sysAttributeService.getAttributeByKey(sysAttributeVO2);

    }

    /**
     * 根据key  获取系统属性
     *
     * @param key
     * @param baseInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getSystemImg")
    @ResponseBody
    public SysAttributeVO getSystemImg(@RequestParam String key,
                                                     @ModelAttribute BaseRequest baseInfo) throws Exception {
        log.info("根据key获取属性uptoken|param={}", baseInfo);
        SysAttributeVO sysAttributeVO2 = new SysAttributeVO();
        SysAttributeVO sysAttributeVO = new SysAttributeVO();
        if ("-1".equals(key)) {
            sysAttributeVO2.setAttributeKey("payType");
        } else {
            sysAttributeVO.setAttributeKey(key);
            SysAttributeVO sysAttributeVO1 = sysAttributeService.getAttributeByKey(sysAttributeVO);
            sysAttributeVO1.setAttributeValue(Const.PRO_URL + sysAttributeVO1.getAttributeValue());//加图片链接
            return sysAttributeVO1;
        }

        return sysAttributeService.getAttributeByKey(sysAttributeVO2);

    }

    /**
     * 根据key  获取系统属性
     *
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDocoumentByCode")
    @ResponseBody
    public String getDocoumentByCode(String code) {
        return sysAttributeService.getDocoumentByCode(code);
    }

    /**
     * 根据download_background得到attributeValue
     */
    @RequestMapping(value = "findByDownloadBackground")
    @ResponseBody
    public SysAttributeVO findByDownloadBackground() {
        return sysAttributeService.findAttributeValue("download_background");
    }

    /**
     * 根据freight_rule得到attributeValue
     */
    @RequestMapping(value = "findAttributeValue")
    @ResponseBody
    public SysAttributeVO findAttributeValue() {
        return sysAttributeService.findAttributeValue("freight_rule");
    }

    /**
     * 获取版本号和版本地址
     */
    @RequestMapping(value = "findVersionNumber", method = RequestMethod.POST)
    @ResponseBody
    public SysAttributeVO findVersionNumber(@RequestParam(value = "type", required = false) String type) throws Exception {
        String param = StringUtils.isNotBlank(type) && "ios".equals(type) ? "ios_version_number" : "version_number";
        return sysAttributeService.findAttributeValue(param);
    }

}
