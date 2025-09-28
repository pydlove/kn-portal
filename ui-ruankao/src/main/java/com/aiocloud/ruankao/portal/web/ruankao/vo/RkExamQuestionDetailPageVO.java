package com.aiocloud.ruankao.portal.web.ruankao.vo;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionArticle;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay;
import com.aiocloud.kn.portal.dao.ruankao.dto.RkExamQuestionDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @description: RkExamQuestionPageVO.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 14:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RkExamQuestionDetailPageVO extends RkExamQuestionDTO {

    private RkExamQuestionEssay questionEssay;
    private RkExamQuestionChoice questionChoice;
    private RkExamQuestionCase questionCase;
    private RkExamQuestionArticle questionArticle;
}
