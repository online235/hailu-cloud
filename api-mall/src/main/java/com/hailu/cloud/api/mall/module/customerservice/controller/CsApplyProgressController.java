package com.hailu.cloud.api.mall.module.customerservice.controller;

import com.hailu.cloud.api.mall.module.customerservice.service.ICsApplyProgressService;
import com.hailu.cloud.api.mall.module.customerservice.vo.CsApplyProgressVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("api/mall")
public class CsApplyProgressController {

    @Autowired
    private ICsApplyProgressService csApplyProgressService;

    /**
     * 查看 可以售后的退货原因
     */
    @RequestMapping(value = "/getCsApplyProgresss", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCsApplyProgresss(
            @RequestParam(value = "csApplyId", required = true) Integer csApplyId) throws Exception {

        List<CsApplyProgressVo> csReasons = csApplyProgressService.findByApplyProgressId(csApplyId);

        Map<String, Object> data = new HashMap<>();
        data.put("csReasons", csReasons);
        return data;
    }


    /**
     * @param
     * @return
     * @throws Exception
     * @author 黄亮
     * 添加问题或回答问题
     */
    @RequestMapping(value = "/addApplyProgress", method = RequestMethod.POST)
    @ResponseBody
    public void addApplyProgress(
            @RequestParam(value = "csApplyId", required = true) int csApplyId,
            @RequestParam(value = "auditContent", required = true) String auditContent,
            @RequestParam(value = "createName", required = true) String createName,
            @RequestParam(value = "updateName", required = true) String updateName) throws Exception {

        CsApplyProgressVo vo = new CsApplyProgressVo();
        vo.setCsApplyId(csApplyId);
        vo.setAuditContent(auditContent);
        vo.setDeleteStatus(0);
        vo.setCreateDate(System.currentTimeMillis());
        vo.setCreateName(createName);
        vo.setUpdateDate(System.currentTimeMillis());
        vo.setUpdateName(updateName);
        csApplyProgressService.addApplyProgress(vo);
    }
}
