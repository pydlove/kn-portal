package com.aiocloud.onetable.console.base.exception;


import com.aiocloud.onetable.console.base.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @description: GlobalExceptionHandler.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-15 18:30 
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CommonResponse<String>> handleException(AccessDeniedException e) {
        var commonResponse = new CommonResponse<>(ErrorCode.UNAUTHORIZED, "no permission access");
        return new ResponseEntity<>(commonResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<CommonResponse<String>> handleException(BizException e) {
        var httpStatusCode = e.getClass().getAnnotation(ResponseStatus.class).code();
        var errorResponse = new CommonResponse<>(e.getErrorCode(), e.getDetails());
        return new ResponseEntity<>(errorResponse, httpStatusCode);
    }
}