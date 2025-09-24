package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamQuestionChoice.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:44
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_exam_question_choice")
@Data
public class RkExamQuestionChoice extends BaseDomain {

    private Long questionId;

    private Integer choiceType;

    private String options;

    private String correctAnswers;

    private String analysis;
}