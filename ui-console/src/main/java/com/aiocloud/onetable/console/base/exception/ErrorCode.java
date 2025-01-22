package com.aiocloud.onetable.console.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_OR_PASSWORD_ERROR(1001, "用户或者密码有误"),
    INVALID_REFRESH_TOKEN(1002, "Refresh token 无效"),
    TOKEN_EXCEPTION(1003, "token 异常"),
    TOKEN_HAS_EXPIRED(1004, "token 已过期"),
    INTERNAL_SERVER_ERROR(1000, "内部服务器错误"),

    ;


    private final int code;

    private final String msg;
}
