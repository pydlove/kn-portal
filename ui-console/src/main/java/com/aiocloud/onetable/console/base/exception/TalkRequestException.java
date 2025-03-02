package com.aiocloud.onetable.console.base.exception;

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
public class TalkRequestException extends Exception {

    public TalkRequestException(String errorMessage) {
        super(errorMessage);
    }

}
