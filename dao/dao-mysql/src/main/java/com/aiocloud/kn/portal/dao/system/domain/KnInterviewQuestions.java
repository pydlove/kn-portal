package com.aiocloud.kn.portal.dao.system.domain;

import java.util.Date;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: KnInterviewQuestions.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:26
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="kn_interview_questions")
@Data
public class KnInterviewQuestions extends BaseDomain {

    private String questionTitle;

    private String questionText;

    private String difficultyLevel;

    private String answerHint;

    private String referenceAnswer;

    private String categoryName;
}