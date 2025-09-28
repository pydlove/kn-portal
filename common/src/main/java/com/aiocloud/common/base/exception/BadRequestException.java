package com.aiocloud.common.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @description: BadRequestException.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 15:10 
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BizException {

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(ErrorCode errorCode, String details) {
        super(errorCode, details);
    }
}
