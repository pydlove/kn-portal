package com.aiocloud.onetable.console.web.table.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @description: TableInfoVO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-22 22:14 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableInfoVO {

    private Long tableId;
    private String tableName;
    private String tableComment;
}
