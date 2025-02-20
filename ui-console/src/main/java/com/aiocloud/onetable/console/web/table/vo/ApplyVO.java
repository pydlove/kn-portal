package com.aiocloud.onetable.console.web.table.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    private Long tableId;
    private String tableName;
    private String applyReason;
    private String purpose;
    private String applyNo;
    private Integer applyStatus;
    private String chApplyStatus;
    private String applyResult;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
