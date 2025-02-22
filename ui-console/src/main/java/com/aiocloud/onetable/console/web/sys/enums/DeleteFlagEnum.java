package com.aiocloud.onetable.console.web.sys.enums;

import lombok.Getter;

/**
 *
 * @description: DeleteFlagEnum.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 10:45 
 */
@Getter
public enum DeleteFlagEnum {
    UNDELETE(0, "未删除"),
    DELETED(1, "已删除"),
    ;

    private final int code;
    private final String message;

    DeleteFlagEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DeleteFlagEnum fromCode(int code) {

        for (DeleteFlagEnum status : DeleteFlagEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
