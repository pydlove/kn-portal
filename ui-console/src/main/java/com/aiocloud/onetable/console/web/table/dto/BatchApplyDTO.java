package com.aiocloud.onetable.console.web.table.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @description: BatchApplyDTO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 17:36 
 */
@Data
public class BatchApplyDTO {

    private List<Long> ids;
    private Integer applyStatus;
}
