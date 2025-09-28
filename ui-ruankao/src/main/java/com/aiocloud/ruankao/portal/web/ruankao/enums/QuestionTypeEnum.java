package com.aiocloud.ruankao.portal.web.ruankao.enums;

import lombok.Getter;

/**
 *
 * @description: QuestionTypeEnum.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-23 17:36 
 */
@Getter
public enum QuestionTypeEnum {
    CHOICE("CHOICE", "选择题"),
    CASE("CASE", "案例题"),
    ESSAY("ESSAY", "论文题"),
    ARTICLE("ARTICLE", "文章");

    private final String code;
    private final String description;

    QuestionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static QuestionTypeEnum fromCode(String code) {

        for (QuestionTypeEnum type : QuestionTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Unknown question type: " + code);
    }
}
