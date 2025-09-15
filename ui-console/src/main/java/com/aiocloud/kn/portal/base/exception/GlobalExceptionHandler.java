package com.aiocloud.kn.portal.base.exception;


import com.aiocloud.kn.portal.base.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    @ExceptionHandler(BizException.class)
    public ResponseEntity<CommonResponse<String>> handleException(BizException e) {
        var httpStatusCode = e.getClass().getAnnotation(ResponseStatus.class).code();
        var errorResponse = new CommonResponse<>(e.getErrorCode(), e.getDetails());
        errorResponse.setData(e.getErrorCode().getMsg());
        return new ResponseEntity<>(errorResponse, httpStatusCode);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CommonResponse<String>> handleException(BadRequestException e) {
        var httpStatusCode = e.getClass().getAnnotation(ResponseStatus.class).code();
        ErrorCode errorCode = e.getErrorCode();
        var errorResponse = new CommonResponse<>(errorCode, errorCode.getMsg());
        errorResponse.setData(errorCode.getMsg());
        return new ResponseEntity<>(errorResponse, httpStatusCode);
    }
}