package com.aiocloud.onetable.console.web.sys.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @description: MessageDTO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 10:39
 */
@Data
public class MessageDTO {

    private List<Long> ids;

    private String content;

    /**
     * 0-默认消息
     */
    private Integer msgType;

    /**
     * 0-未读 1-已读
     */
    private Integer opStatus;
}
