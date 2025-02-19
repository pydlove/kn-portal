package com.aiocloud.onetable.mysql.table.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @description: TableInfoPO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-19 21:59 
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_table_info")
@Data
public class TableInfoPO extends BasePO {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表说明
     */
    private String tableComment;

}