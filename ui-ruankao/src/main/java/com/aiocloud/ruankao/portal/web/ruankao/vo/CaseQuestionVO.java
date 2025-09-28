package com.aiocloud.ruankao.portal.web.ruankao.vo;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import lombok.Data;

/**
 *
 * @description: CaseQuestionRequest.java 
 * @copyright: @copyright (c) 2022 
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0 
 * @createTime: 2025-09-23 13:52
 */
@Data
public class CaseQuestionVO {
    private RkExamQuestion question;
    private RkExamQuestionCase caseDetail;
}
