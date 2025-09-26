package com.aiocloud.kn.portal.dao.ruankao.domain;

import com.aiocloud.kn.portal.dao.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamQuestionArticle.java
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-24 16:42 
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="rk_exam_question_article")
@Data
public class RkExamQuestionArticle extends BaseDomain {

    private Long questionId;

    private String title;

    private String content;
}