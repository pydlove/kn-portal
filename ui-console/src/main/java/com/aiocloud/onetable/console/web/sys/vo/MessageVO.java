package com.aiocloud.onetable.console.web.sys.vo;

import lombok.Data;

/**
 *
 * @description: MessageVO.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-21 10:39 
 */
@Data
public class MessageVO {

    private Long id;
    private String content;
    private Integer msgType;
    private Integer opStatus;
    private String chOpStatus;
}
