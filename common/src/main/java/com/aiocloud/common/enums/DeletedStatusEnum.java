package com.aiocloud.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举类
 */
@Getter
public enum DeletedStatusEnum {

    /**
     * 未删除
     */
    NOT_DELETED(0, "未删除"),

    /**
     * 已删除
     */
    DELETED(1, "已删除");

    private final Integer code;
    private final String description;

    DeletedStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code获取枚举
     *
     * @param code 状态码
     * @return 对应的枚举值
     */
    public static DeletedStatusEnum fromCode(Integer code) {
        for (DeletedStatusEnum status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 判断是否为已删除状态
     *
     * @param code 状态码
     * @return 是否已删除
     */
    public static boolean isDeleted(Integer code) {
        return DELETED.getCode().equals(code);
    }

    /**
     * 判断是否为未删除状态
     *
     * @param code 状态码
     * @return 是否未删除
     */
    public static boolean isNotDeleted(Integer code) {
        return NOT_DELETED.getCode().equals(code);
    }
}

