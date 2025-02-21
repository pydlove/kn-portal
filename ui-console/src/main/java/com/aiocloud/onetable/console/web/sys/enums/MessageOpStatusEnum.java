package com.aiocloud.onetable.console.web.sys.enums;

import lombok.Getter;

/**
 *
 * @description: OpStatusEnum.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 10:45 
 */
@Getter
public enum MessageOpStatusEnum {
    UNREAD(0, "已读"),
    READ(1, "未读");

    private final int code;
    private final String message;

    MessageOpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static MessageOpStatusEnum fromCode(int code) {

        for (MessageOpStatusEnum status : MessageOpStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
