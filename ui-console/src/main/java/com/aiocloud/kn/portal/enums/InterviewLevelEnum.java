package com.aiocloud.kn.portal.enums;

import lombok.Getter;

/**
 *
 * @description: InterviewLevelEnum.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-21 9:10
 */
@Getter
public enum InterviewLevelEnum {
    FRESH_GRADUATE("应届生", 4),
    JUNIOR("初级", 1),
    MIDDLE("中级", 2),
    SENIOR("高级", 3);

    private final String levelName;
    private final int levelCode;

    InterviewLevelEnum(String levelName, int levelCode) {
        this.levelName = levelName;
        this.levelCode = levelCode;
    }

    public static int getLevelCodeByLevelName(String levelName) {
        for (InterviewLevelEnum level : InterviewLevelEnum.values()) {
            if (level.levelName.equals(levelName)) {
                return level.levelCode;
            }
        }
        throw new IllegalArgumentException("未知的面试等级: " + levelName);
    }

}
