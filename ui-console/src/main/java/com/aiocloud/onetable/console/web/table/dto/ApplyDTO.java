package com.aiocloud.onetable.console.web.table.dto;

import lombok.Data;

/**
 *
 * @description: ApplyDTO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 17:36 
 */
@Data
public class ApplyDTO {

    private String username;
    private Long tableId;
    private String applyReason;
    private String purpose;
    private String applyNo;
}
