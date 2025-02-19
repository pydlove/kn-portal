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
@TableName("t_dictionary_conf")
@Data
public class DictionaryConfPO extends BasePO {

    @TableField("id")
    private Long id;

    @TableField("word_name")
    private String wordName;

    @TableField("word_nature")
    private String wordNature;

    @TableField("word_usege")
    private Integer wordUsege;

    @TableField("word_desc")
    private Date wordDesc;

    @TableField("delete_flag")
    private Date deleteFlag;

    @TableField("update_time")
    private Date updateTime;

    @TableField("create_uid")
    private Long createUid;

    @TableField("update_uid")
    private Long updateUid;

}