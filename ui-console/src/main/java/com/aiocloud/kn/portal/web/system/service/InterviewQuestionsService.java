package com.aiocloud.kn.portal.web.system.service;

import com.aiocloud.kn.portal.web.system.vo.KnInterviewQuestionsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * @description: InterviewQuestionsService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:34
 */
public interface InterviewQuestionsService {

    Page<KnInterviewQuestionsVO> getInterviewQuestionsPage(String questionText, String difficultyLevel, String categoryName, Integer pageNum, Integer pageSize);

    KnInterviewQuestionsVO create(KnInterviewQuestionsVO interviewQuestionsVO);

    KnInterviewQuestionsVO update(Long id, KnInterviewQuestionsVO interviewQuestionsVO);

    Long delete(Long id);
}
