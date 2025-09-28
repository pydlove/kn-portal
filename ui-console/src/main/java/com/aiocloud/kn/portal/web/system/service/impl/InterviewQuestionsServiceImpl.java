package com.aiocloud.kn.portal.web.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.common.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.dao.system.domain.KnInterviewQuestions;
import com.aiocloud.kn.portal.dao.system.mapper.KnInterviewQuestionsMapper;
import com.aiocloud.kn.portal.dao.system.mapper.KnInterviewTemplateMapper;
import com.aiocloud.kn.portal.web.system.service.InterviewQuestionsService;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewQuestionsVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @description: InterviewQuestionsServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:29
 */
@RequiredArgsConstructor
@Service
public class InterviewQuestionsServiceImpl implements InterviewQuestionsService {

    private final KnInterviewQuestionsMapper knInterviewQuestionsMapper;
    private final KnInterviewTemplateMapper knInterviewTemplateMapper;

    @Override
    public Page<KnInterviewQuestionsVO> getInterviewQuestionsPage(String questionText, String difficultyLevel, String categoryName, Integer pageNum, Integer pageSize) {

        Page<KnInterviewQuestions> page = new Page<>(pageNum, pageSize);

        QueryWrapper<KnInterviewQuestions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());

        if (StrUtil.isNotBlank(questionText)) {
            queryWrapper.like("question_text", questionText);
        }

        if (StrUtil.isNotBlank(difficultyLevel)) {
            queryWrapper.like("difficulty_level", difficultyLevel);
        }

        if (StrUtil.isNotBlank(categoryName)) {
            queryWrapper.like("category_name", categoryName);
        }

        queryWrapper.orderByDesc("create_time");

        Page<KnInterviewQuestions> questionsPage = knInterviewQuestionsMapper.selectPage(page, queryWrapper);

        List<KnInterviewQuestionsVO> records = questionsPage.getRecords().stream()
                .map(questions -> BeanUtil.copyProperties(questions, KnInterviewQuestionsVO.class))
                .collect(Collectors.toList());

        Page<KnInterviewQuestionsVO> voPage = new Page<>(pageNum, pageSize, questionsPage.getTotal());
        voPage.setRecords(records);

        return voPage;
    }

    @Override
    public KnInterviewQuestionsVO create(KnInterviewQuestionsVO interviewQuestionsVO) {

        KnInterviewQuestions knInterviewQuestions = new KnInterviewQuestions();
        knInterviewQuestions.setQuestionTitle(interviewQuestionsVO.getQuestionTitle());
        knInterviewQuestions.setQuestionText(interviewQuestionsVO.getQuestionText());
        knInterviewQuestions.setDifficultyLevel(interviewQuestionsVO.getDifficultyLevel());
        knInterviewQuestions.setReferenceAnswer(interviewQuestionsVO.getReferenceAnswer());
        knInterviewQuestions.setAnswerHint(interviewQuestionsVO.getAnswerHint());
        knInterviewQuestions.setCategoryName(interviewQuestionsVO.getCategoryName());

        int result = knInterviewQuestionsMapper.insert(knInterviewQuestions);
        if (result > 0) {
            return BeanUtil.copyProperties(knInterviewQuestions, KnInterviewQuestionsVO.class);
        }

        return null;
    }

    @Override
    public KnInterviewQuestionsVO update(Long id, KnInterviewQuestionsVO interviewQuestionsVO) {

        KnInterviewQuestions knInterviewQuestions = knInterviewQuestionsMapper.selectById(id);
        if (knInterviewQuestions == null) {
            return null;
        }

        knInterviewQuestions.setQuestionTitle(interviewQuestionsVO.getQuestionTitle());
        knInterviewQuestions.setQuestionText(interviewQuestionsVO.getQuestionText());
        knInterviewQuestions.setDifficultyLevel(interviewQuestionsVO.getDifficultyLevel());
        knInterviewQuestions.setReferenceAnswer(interviewQuestionsVO.getReferenceAnswer());
        knInterviewQuestions.setAnswerHint(interviewQuestionsVO.getAnswerHint());
        knInterviewQuestions.setCategoryName(interviewQuestionsVO.getCategoryName());

        int result = knInterviewQuestionsMapper.updateById(knInterviewQuestions);
        if (result > 0) {
            return BeanUtil.copyProperties(knInterviewQuestions, KnInterviewQuestionsVO.class);
        }

        return null;
    }

    @Override
    public Long delete(Long id) {

        KnInterviewQuestions knInterviewQuestions = knInterviewQuestionsMapper.selectByPrimaryKey(id);
        knInterviewQuestions.setDeletedStatus(DeletedStatusEnum.DELETED.getCode());

        knInterviewQuestionsMapper.updateByPrimaryKeySelective(knInterviewQuestions);

        return knInterviewQuestions.getId();
    }
}
