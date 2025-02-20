package com.aiocloud.onetable.mysql.table.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @auther ybin
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_column_info")
@Data
public class ColumnInfoPO extends BasePO {

    @TableField("id")
    private Long id;

    @TableField("table_name")
    private String tableName;

    @TableField("column_name")
    private String columnName;

    @TableField("column_type")
    private String columnType;

    @TableField("column_comment")
    private String columnComment;

    @TableField("delete_flag")
    private Integer deleteFlag;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("create_uid")
    private Long createUid;

    @TableField("update_uid")
    private Long updateUid;
}