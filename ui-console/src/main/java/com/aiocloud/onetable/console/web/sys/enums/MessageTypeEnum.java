package com.aiocloud.onetable.console.web.sys.enums;

import lombok.Getter;

/**
 *
 * @description: MessageTypeEnum.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 10:45 
 */
@Getter
public enum MessageTypeEnum {
    DEFAULT(0, "默认消息"),
    APPLY(1, "审批消息"),
    ;

    private final int code;
    private final String message;

    MessageTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static MessageTypeEnum fromCode(int code) {

        for (MessageTypeEnum status : MessageTypeEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
