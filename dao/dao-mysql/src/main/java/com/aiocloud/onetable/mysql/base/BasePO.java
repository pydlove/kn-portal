package com.aiocloud.onetable.mysql.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 *
 * @description: BasePO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-01-21 17:20 
 */
@Data
public class BasePO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;

    private Long createUid;

    private Long updateUid;

    public Date getUpdateTime() {
        return updateTime == null ? new Date() : updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag == null ? 0 : deleteFlag;
    }
}
