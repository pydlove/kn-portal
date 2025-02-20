package com.aiocloud.onetable.console.web.table.controller;


import com.aiocloud.onetable.console.base.common.CommonResponse;
import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import com.aiocloud.onetable.console.web.table.dto.BatchApplyDTO;
import com.aiocloud.onetable.console.web.table.service.ApplyService;
import com.aiocloud.onetable.console.web.table.vo.ApplyVO;
import com.aiocloud.onetable.console.web.test.dto.TestInfoDTO;
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
    public CommonResponse<Integer> submitApply(@RequestBody ApplyDTO applyDTO) {
        return new CommonResponse<>(applyService.submitApply(applyDTO));
    }

    @GetMapping("/list")
    public CommonResponse<PaginationResult<ApplyVO>>  getAllApplies(
            ApplyDTO applyDTO,
            PageRequest pageRequest
    ) {
        return new CommonResponse<>(applyService.getAllApplies(applyDTO, pageRequest));
    }

    @PostMapping("/update")
    public CommonResponse<Integer> updateApply(@RequestBody ApplyDTO applyDTO) {
        return new CommonResponse<>(applyService.updateApply(applyDTO));
    }

    @PostMapping("/batch-update")
    public CommonResponse<Integer> batchUpdateApply(@RequestBody BatchApplyDTO batchApplyDTO) {
        return new CommonResponse<>(applyService.batchUpdateApply(batchApplyDTO));
    }

}

