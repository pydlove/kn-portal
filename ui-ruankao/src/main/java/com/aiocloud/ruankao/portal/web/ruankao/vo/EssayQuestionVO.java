package com.aiocloud.ruankao.portal.web.ruankao.vo;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay;
import lombok.Data;

/**
 *
 * @description: EssayQuestionRequest.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:53
 */
@Data
public class EssayQuestionVO {
    private RkExamQuestion question;
    private RkExamQuestionEssay essayDetail;
}
