package com.aiocloud.kn.portal.dao.base.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 *
 * @description: BaseDomain.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-07-28 17:33
 */
@Data
public class BaseDomain {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private Long createUid;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUid;

    @TableField(fill = FieldFill.INSERT)
    private Integer deletedStatus;
}
