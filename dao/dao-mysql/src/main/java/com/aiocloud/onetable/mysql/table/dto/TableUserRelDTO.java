package com.aiocloud.onetable.mysql.table.dto;

import lombok.Data;

/**
 *
 * @description: TableUserRelDTO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-22 21:28 
 */
@Data
public class TableUserRelDTO {

    private String tableComment;
    private String tableName;
    private Long userId;
    private Long tableId;
}
