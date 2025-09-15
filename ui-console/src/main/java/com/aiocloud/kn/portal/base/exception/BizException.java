package com.aiocloud.kn.portal.base.exception;

import lombok.Getter;

/**
 *
 * @description: BizException.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-01-21 15:07 
 */
@Getter
public abstract class BizException extends RuntimeException {
    
    private final ErrorCode errorCode;

    private String details;

    protected BizException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    protected BizException(ErrorCode errorCode, String details) {
        this(errorCode);
        this.details = details;
    }
}
