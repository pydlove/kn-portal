package com.aiocloud.onetable.console.base.common;

import com.aiocloud.onetable.console.base.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    public static final CommonResponse<Void> NO_CONTENT = new CommonResponse<>();

    private static final String SERVICE_NAME = "one-table";

    private static ErrorResponse<?> error;

    private T data;

    public CommonResponse(T value) {
        data = value;
    }

    public CommonResponse(ErrorCode errorCode, T value) {
        error = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMsg())
                .service(SERVICE_NAME)
                .detail(value)
                .build();
    }

    public CommonResponse(int errorCode, String message, T value) {
        error = ErrorResponse.builder()
                .code(errorCode)
                .message(message)
                .service(SERVICE_NAME)
                .detail(value)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(data);
    }

    @Data
    @Builder
    public static class ErrorResponse<T> {
        private String service;

        private int code;

        private String message;

        private T detail;
    }
}

