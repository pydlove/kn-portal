package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamQuestionCase.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:43
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_exam_question_case")
@Data
public class RkExamQuestionCase extends BaseDomain {

    private Long questionId;

    private String background;

    private String requirement;

    private String referenceAnswer;
}