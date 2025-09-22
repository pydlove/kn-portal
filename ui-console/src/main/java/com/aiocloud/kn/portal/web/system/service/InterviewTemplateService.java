package com.aiocloud.kn.portal.web.system.service;

import com.aiocloud.kn.portal.web.system.vo.IdNameVO;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewTemplateVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 *
 * @description: InterviewTemplateService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 20:04
 */
public interface InterviewTemplateService {

    Page<KnInterviewTemplateVO> getInterviewTemplatePage(String templateName, Integer pageNum, Integer pageSize);

    KnInterviewTemplateVO create(KnInterviewTemplateVO interviewTemplateVO);

    KnInterviewTemplateVO update(Long id, KnInterviewTemplateVO interviewTemplateVO);

    Long delete(Long id);

    List<IdNameVO> getAllTemplates();

}
