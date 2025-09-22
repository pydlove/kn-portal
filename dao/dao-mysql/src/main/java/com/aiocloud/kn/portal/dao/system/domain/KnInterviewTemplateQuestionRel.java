package com.aiocloud.kn.portal.dao.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * @description: KnInterviewTempalteQuestionRel.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-20 22:18 
 */
@TableName(value ="kn_interview_template_question_rel")
@Data
public class KnInterviewTemplateQuestionRel {
    private Long id;

    private Long templateId;

    private Long questionId;

    private Integer orderNo;
}