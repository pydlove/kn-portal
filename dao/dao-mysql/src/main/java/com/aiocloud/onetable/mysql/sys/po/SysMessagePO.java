package com.aiocloud.onetable.mysql.sys.po;

import com.aiocloud.onetable.mysql.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 *
 * @description: SysMessagePO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 10:30 
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_message")
@Data
public class SysMessagePO extends BasePO {

    private String content;

    /**
     * 0-默认消息
     */
    private Integer msgType;

    /**
     * 0-未读 1-已读
     */
    private Integer opStatus;

    private Long toUserId;

}