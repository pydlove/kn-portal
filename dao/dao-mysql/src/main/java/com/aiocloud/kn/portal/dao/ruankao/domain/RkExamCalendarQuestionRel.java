package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * @description: RkExamCalendarQuestionRel.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 18:56
 */
@TableName(value ="rk_exam_calendar_question_rel")
@Data
public class RkExamCalendarQuestionRel {

    private Long id;

    private Long questionId;

    private Long calendarId;

    private Integer sortOrder;
}