package com.aiocloud.kn.portal.web.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiocloud.kn.portal.dao.system.domain.KnInterviewTemplate;
import com.aiocloud.kn.portal.dao.system.dto.KnInterviewQuestionsDTO;
import com.aiocloud.kn.portal.dao.system.mapper.KnInterviewQuestionsMapper;
import com.aiocloud.kn.portal.dao.system.mapper.KnInterviewTemplateMapper;
import com.aiocloud.kn.portal.web.system.service.InterviewService;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @description: InterviewServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-21 9:03
 */
@RequiredArgsConstructor
@Service
public class InterviewServiceImpl implements InterviewService {

    private final KnInterviewQuestionsMapper interviewQuestionsMapper;
    private final KnInterviewTemplateMapper interviewTemplateMapper;

    @Override
    public List<KnInterviewVO> getInterviewQuestions(Integer level) {

        List<KnInterviewTemplate> templates = interviewTemplateMapper.selectByLevel(level);

        if (templates == null || templates.isEmpty()) {
            return new ArrayList<>();
        }

        KnInterviewTemplate randomTemplate = templates.get(new Random().nextInt(templates.size()));

        // 4. 查询所选模板相关的问题
        List<KnInterviewQuestionsDTO> questions = interviewQuestionsMapper.selectByTemplateId(randomTemplate.getId());
        return BeanUtil.copyToList(questions, KnInterviewVO.class);
    }

}
