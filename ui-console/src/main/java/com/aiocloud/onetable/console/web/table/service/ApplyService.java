package com.aiocloud.onetable.console.web.table.service;

import com.aiocloud.onetable.console.base.common.PageRequest;
import com.aiocloud.onetable.console.base.common.PaginationResult;
import com.aiocloud.onetable.console.web.table.dto.ApplyDTO;
import com.aiocloud.onetable.console.web.table.dto.BatchApplyDTO;
import com.aiocloud.onetable.console.web.table.vo.ApplyVO;

/**
 *
 * @description: ApplyService.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 14:51 
 */
public interface ApplyService {

    int submitApply(ApplyDTO applyDTO);

    PaginationResult<ApplyVO> getAllApplies(ApplyDTO applyDTO, PageRequest pageRequest);

    Integer updateApply(ApplyDTO applyDTO);

    Integer batchUpdateApply(BatchApplyDTO batchApplyDTO);

    boolean checkSubmitApply(Long tableId, Long currentUserId);
}