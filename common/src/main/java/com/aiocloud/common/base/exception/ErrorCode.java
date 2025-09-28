package com.aiocloud.common.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(1000, "内部服务器错误"),
    USER_OR_PASSWORD_ERROR(1001, "用户或者密码有误"),
    INVALID_REFRESH_TOKEN(1002, "Refresh token 无效"),
    TOKEN_EXCEPTION(1003, "token 异常"),
    TOKEN_HAS_EXPIRED(1004, "token 已过期"),

    /**
     * unauthorized
     */
    UNAUTHORIZED(1005, "没有权限"),
    PARAMETER_ERROR(1006, "参数错误"),
    UNRECOGNIZED(1007, "很抱歉，未能识别您的需求。"),
    NOTFOUNDTABLE(1008, "查不到表信息"),
    NOTFOUNDCOLUMN(1009, "查不到列信息"),
    USER_EXIST(2001, "用户已经存在"),

    FINISH_TASK(3001, "完成任务失败"),
    ;

    private final int code;

    private final String msg;
}
