package com.hailu.cloud.api.mall.module.customerservice.controller;

import com.hailu.cloud.api.mall.module.customerservice.service.ICsReasonService;
import com.hailu.cloud.api.mall.module.customerservice.vo.CsReasonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("api/mall")
public class CsReasonController {

    @Autowired
    ICsReasonService csReasonService;

    /**
     * 查看 可以售后的退货原因
     *
     * @param reasonType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCsReasons", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCsReasons(
            @RequestParam(value = "reasonType", required = true) Integer reasonType) throws Exception {

        List<CsReasonVo> csReasons = csReasonService.findByCsReasonType(reasonType);

        Map<String, Object> data = new HashMap<>();
        data.put("csReasons", csReasons);
        return data;
    }

    /**
     * 查看 可以售后的退货原因
     *
     * @param reasonId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findCsReason", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findCsReason(
            @RequestParam(value = "reasonId", required = true) Integer reasonId) throws Exception {
        CsReasonVo csReasons = csReasonService.findCsReason(reasonId);
        Map<String, Object> data = new HashMap<>();
        data.put("csReasons", csReasons);
        return data;
    }
}
