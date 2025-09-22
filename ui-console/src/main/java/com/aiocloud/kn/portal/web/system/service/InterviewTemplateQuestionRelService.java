package com.aiocloud.kn.portal.web.system.service;

import com.aiocloud.kn.portal.web.system.vo.KnInterviewTemplateQuestionRelVO;

import java.util.List;

/**
 *
 * @description: InterviewTemplateQuestionRelService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 22:25
 */
public interface InterviewTemplateQuestionRelService {

    KnInterviewTemplateQuestionRelVO create(KnInterviewTemplateQuestionRelVO interviewTemplateQuestionRelVO);

    List<KnInterviewTemplateQuestionRelVO> batchCreate(List<KnInterviewTemplateQuestionRelVO> interviewTemplateQuestionRelVOs);

    List<KnInterviewTemplateQuestionRelVO> getByTemplateId(Long templateId);

    Long delete(Long id);

    Integer batchDelete(List<Long> ids);
}
