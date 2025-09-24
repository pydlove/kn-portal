package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamQuestionEssay.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:45
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_exam_question_essay")
@Data
public class RkExamQuestionEssay extends BaseDomain {

    private Long questionId;

    private String requirement;

    private Integer wordLimitMin;

    private Integer wordLimitMax;

    private String referenceAnswer;

    private String scoringCriteria;
}