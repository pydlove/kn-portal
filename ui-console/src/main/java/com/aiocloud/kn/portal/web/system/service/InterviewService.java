package com.aiocloud.kn.portal.web.system.service;

import com.aiocloud.kn.portal.web.system.vo.KnInterviewQuestionsVO;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 *
 * @description: InterviewService.java`
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-21 9:03
 */
public interface InterviewService {

    List<KnInterviewVO> getInterviewQuestions(Integer level);
}
