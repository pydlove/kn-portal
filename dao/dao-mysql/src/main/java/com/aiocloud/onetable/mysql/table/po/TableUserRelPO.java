package com.aiocloud.onetable.mysql.table.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: TableUserRelPO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 14:39 
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "t_table_user_rel")
@Data
public class TableUserRelPO extends BasePO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 表名称
     */
    private Long tableId;
}