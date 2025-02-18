package com.aiocloud.onetable.console.web.table.controller;


import com.aiocloud.onetable.console.web.table.service.ApplyService;
import com.aiocloud.onetable.mysql.table.po.ApplyPO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description: ApplyController.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 14:50 
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/submit")
    public int submitApply(@RequestBody ApplyPO applyPO) {
        return applyService.submitApply(applyPO);
    }

    @GetMapping("/list")
    public List<ApplyPO> getAllApplies() {
        return applyService.getAllApplies();
    }
}

