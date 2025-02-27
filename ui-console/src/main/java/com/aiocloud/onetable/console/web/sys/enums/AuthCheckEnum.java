package com.aiocloud.onetable.console.web.sys.enums;

import lombok.Getter;

/**
 *
 * @description: AuthCheckEnum.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-02-21 10:45 
 */
@Getter
public enum AuthCheckEnum {
    NO_PERMISSION_NOT_APPLY (0, "没有权限未提交申请"),
    HAS_PERMISSION(1, "有权限"),
    NO_PERMISSION_APPLIED(2, "没有权限已提交申请"),
    ;

    private final int code;
    private final String message;

    AuthCheckEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static AuthCheckEnum fromCode(int code) {

        for (AuthCheckEnum status : AuthCheckEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
