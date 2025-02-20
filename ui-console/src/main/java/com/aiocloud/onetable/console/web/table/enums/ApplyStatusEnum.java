package com.aiocloud.onetable.console.web.table.enums;


import lombok.Getter;

/**
 *
 * @description: ApplyStatusEnum.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-02-20 19:34 
 */
@Getter
public enum ApplyStatusEnum {
    PENDING(0, "待审批"),
    REJECTED(1, "审批通过"),
    APPROVED(2, "审批拒绝");

    private final int code;
    private final String description;

    ApplyStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ApplyStatusEnum fromCode(int code) {

        for (ApplyStatusEnum status : ApplyStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
