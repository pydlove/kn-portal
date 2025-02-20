package com.aiocloud.onetable.mysql.table.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 *
 * @description: ApplyPO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-18 17:51 
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_apply")
@Data
public class ApplyPO extends BasePO {

    private Long userId;

    private String username;

    private String tableId;

    private String applyReason;

    private String purpose;
    
    private String applyNo;

    private Integer applyStatus;

    private String applyResult;

}