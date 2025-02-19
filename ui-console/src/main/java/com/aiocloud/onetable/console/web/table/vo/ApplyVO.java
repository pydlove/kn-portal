package com.aiocloud.onetable.console.web.table.vo;

import lombok.Data;

/**
 *
 * @description: ApplyVO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 17:37 
 */
@Data
public class ApplyVO {

    private Long id;
    private Long userId;
    private String username;
    private String tableId;
    private String tableName;
    private String applyReason;
    private String purpose;
    private String applyNo;
    private Integer applyStatus;
    private String applyResult;
}
