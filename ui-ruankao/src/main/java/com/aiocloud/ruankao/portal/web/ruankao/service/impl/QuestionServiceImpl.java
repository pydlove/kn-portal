package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionArticle;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay;
import com.aiocloud.kn.portal.dao.ruankao.dto.RkExamQuestionDTO;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamQuestionArticleMapper;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamQuestionCaseMapper;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamQuestionChoiceMapper;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamQuestionEssayMapper;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamQuestionMapper;
import com.aiocloud.ruankao.portal.web.ruankao.enums.QuestionTypeEnum;
import com.aiocloud.ruankao.portal.web.ruankao.service.QuestionService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionDetailPageVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionPageVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @description: QuestionServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:56
 */
@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final RkExamQuestionMapper rkExamQuestionMapper;
    private final RkExamQuestionChoiceMapper rkExamQuestionChoiceMapper;
    private final RkExamQuestionCaseMapper rkExamQuestionCaseMapper;
    private final RkExamQuestionEssayMapper rkExamQuestionEssayMapper;
    private final RkExamQuestionArticleMapper rkExamQuestionArticleMapper;

    @Override
    @Transactional
    public RkExamQuestion createQuestion(RkExamQuestion question) {
        rkExamQuestionMapper.insertSelective(question);
        return question;
    }

    @Override
    @Transactional
    public RkExamQuestion createChoiceQuestion(RkExamQuestion question, RkExamQuestionChoice choiceDetail) {
        // 先创建题目主表
        question.setType(QuestionTypeEnum.CHOICE.getCode());
        rkExamQuestionMapper.insert(question);

        // 再创建选择题详情
        choiceDetail.setQuestionId(question.getId());
        rkExamQuestionChoiceMapper.insert(choiceDetail);

        return question;
    }

    @Override
    @Transactional
    public RkExamQuestion createCaseQuestion(RkExamQuestion question, RkExamQuestionCase caseDetail) {
        // 先创建题目主表
        question.setType(QuestionTypeEnum.CASE.getCode());
        rkExamQuestionMapper.insert(question);

        // 再创建案例题详情
        caseDetail.setQuestionId(question.getId());
        rkExamQuestionCaseMapper.insert(caseDetail);

        return question;
    }

    @Override
    @Transactional
    public RkExamQuestion createEssayQuestion(RkExamQuestion question, RkExamQuestionEssay essayDetail) {
        // 先创建题目主表
        question.setType(QuestionTypeEnum.ESSAY.getCode());
        rkExamQuestionMapper.insert(question);

        // 再创建论文题详情
        essayDetail.setQuestionId(question.getId());
        rkExamQuestionEssayMapper.insert(essayDetail);

        return question;
    }

    @Override
    public RkExamQuestion createArticleQuestion(RkExamQuestion question, RkExamQuestionArticle articleDetail) {

        question.setType(QuestionTypeEnum.ARTICLE.getCode());
        rkExamQuestionMapper.insert(question);

        articleDetail.setQuestionId(question.getId());
        rkExamQuestionArticleMapper.insert(articleDetail);

        return question;
    }

    @Override
    public RkExamQuestion getQuestionById(Long id) {
        return rkExamQuestionMapper.selectByPrimaryKey(id);
    }

    @Override
    public RkExamQuestionChoice getChoiceQuestionDetail(Long questionId) {
        // 可以通过 question_id 字段查询
        // 这里需要自定义查询方法
        return rkExamQuestionChoiceMapper.selectByQuestionId(questionId);
    }

    @Override
    public RkExamQuestionCase getCaseQuestionDetail(Long questionId) {
        return rkExamQuestionCaseMapper.selectByQuestionId(questionId);
    }

    @Override
    public RkExamQuestionEssay getEssayQuestionDetail(Long questionId) {
        return rkExamQuestionEssayMapper.selectByQuestionId(questionId);
    }

    @Override
    public RkExamQuestionArticle getArticleQuestionDetail(Long questionId) {
        return rkExamQuestionArticleMapper.selectByQuestionId(questionId);
    }

    @Override
    public RkExamQuestion updateQuestion(RkExamQuestion question) {
        rkExamQuestionMapper.updateByPrimaryKeySelective(question);
        return question;
    }

    @Override
    public RkExamQuestionChoice updateChoiceQuestionDetail(RkExamQuestionChoice choiceDetail) {
        rkExamQuestionChoiceMapper.updateByPrimaryKeySelective(choiceDetail);
        return choiceDetail;
    }

    @Override
    public RkExamQuestionCase updateCaseQuestionDetail(RkExamQuestionCase caseDetail) {
        rkExamQuestionCaseMapper.updateByPrimaryKeySelective(caseDetail);
        return caseDetail;
    }

    @Override
    public RkExamQuestionEssay updateEssayQuestionDetail(RkExamQuestionEssay essayDetail) {
        rkExamQuestionEssayMapper.updateByPrimaryKeySelective(essayDetail);
        return essayDetail;
    }

    @Override
    public RkExamQuestionArticle updateArticleQuestionDetail(RkExamQuestionArticle articleDetail) {
        rkExamQuestionArticleMapper.updateByPrimaryKeySelective(articleDetail);
        return articleDetail;
    }

    @Override
    @Transactional
    public boolean deleteQuestion(Long id) {
        RkExamQuestion question = rkExamQuestionMapper.selectByPrimaryKey(id);
        if (question == null) {
            return false;
        }

        // 逻辑删除主表
        question.setDeletedStatus(1);
        rkExamQuestionMapper.updateByPrimaryKeySelective(question);

        // 根据题型删除对应的详情表

        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(question.getType());
        switch (questionTypeEnum) {
            case CHOICE:
                RkExamQuestionChoice choice = rkExamQuestionChoiceMapper.selectByQuestionId(id);
                if (choice != null) {
                    choice.setDeletedStatus(1);
                    rkExamQuestionChoiceMapper.updateByPrimaryKeySelective(choice);
                }
                break;
            case CASE:
                RkExamQuestionCase caseDetail = rkExamQuestionCaseMapper.selectByQuestionId(id);
                if (caseDetail != null) {
                    caseDetail.setDeletedStatus(1);
                    rkExamQuestionCaseMapper.updateByPrimaryKeySelective(caseDetail);
                }
                break;
            case ESSAY:
                RkExamQuestionEssay essay = rkExamQuestionEssayMapper.selectByQuestionId(id);
                if (essay != null) {
                    essay.setDeletedStatus(1);
                    rkExamQuestionEssayMapper.updateByPrimaryKeySelective(essay);
                }
                break;
        }

        return true;
    }

    @Override
    public Page<RkExamQuestionPageVO> pageQuestions(Long pageNum, Long pageSize, String type, String title, Integer difficulty) {
        return selectByPageQuestions(pageNum, pageSize, type, title, difficulty);
    }

    @Override
    public Page<RkExamQuestionPageVO> pageQuestions(Long calendarId, Long pageNum, Long pageSize) {
        Page<RkExamQuestionDTO> page = new Page<>(pageNum, pageSize);
        Page<RkExamQuestionDTO> dtoPage = rkExamQuestionMapper.selectPageByCalendarId(page, calendarId, null, null, null);

        Page<RkExamQuestionPageVO> voPage = new Page<>(pageNum, pageSize, dtoPage.getTotal());
        voPage.setRecords(BeanUtil.copyToList(dtoPage.getRecords(), RkExamQuestionPageVO.class));
        return voPage;
    }

    @Override
    public Page<RkExamQuestionDetailPageVO> getQuestionDetailPage(Long calendarId, Long pageNum, Long pageSize) {

        Page<RkExamQuestionDTO> page = new Page<>(pageNum, pageSize);
        Page<RkExamQuestionDTO> dtoPage = rkExamQuestionMapper.selectPageByCalendarId(page, calendarId, null, null, null);
        List<RkExamQuestionDTO> records = Optional.ofNullable(dtoPage.getRecords()).orElse(new ArrayList<>());
        List<RkExamQuestionDetailPageVO> questionDetailPageVOList = BeanUtil.copyToList(records, RkExamQuestionDetailPageVO.class);
        for (RkExamQuestionDetailPageVO questionDetail : questionDetailPageVOList) {

            QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(questionDetail.getType());
            switch (questionTypeEnum) {
                case CHOICE:
                    RkExamQuestionChoice choice = rkExamQuestionChoiceMapper.selectByQuestionId(questionDetail.getId());
                    questionDetail.setQuestionChoice(choice);
                    break;
                case CASE:
                    RkExamQuestionCase caseDetail = rkExamQuestionCaseMapper.selectByQuestionId(questionDetail.getId());
                    questionDetail.setQuestionCase(caseDetail);
                    break;
                case ESSAY:
                    RkExamQuestionEssay essay = rkExamQuestionEssayMapper.selectByQuestionId(questionDetail.getId());
                    questionDetail.setQuestionEssay(essay);
                    break;
                case ARTICLE:
                    RkExamQuestionArticle article = rkExamQuestionArticleMapper.selectByQuestionId(questionDetail.getId());
                    questionDetail.setQuestionArticle(article);
            }
        }

        Page<RkExamQuestionDetailPageVO> voPage = new Page<>(pageNum, pageSize, dtoPage.getTotal());
        voPage.setRecords(questionDetailPageVOList);
        return voPage;
    }

    public Page<RkExamQuestionPageVO> selectByPageQuestions(
            Long pageNum,
            Long pageSize,
            String type,
            String title,
            Integer difficulty
    ) {

        Page<RkExamQuestion> page = new Page<>(pageNum, pageSize);


        LambdaQueryWrapper<RkExamQuestion> queryWrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(type)) {
            queryWrapper.eq(RkExamQuestion::getType, type);
        }

        if (StrUtil.isNotBlank(title)) {
            queryWrapper.like(RkExamQuestion::getTitle, title);
        }

        if (difficulty != null) {
            queryWrapper.eq(RkExamQuestion::getDifficulty, difficulty);
        }

        queryWrapper.eq(RkExamQuestion::getDeletedStatus, 0);
        queryWrapper.orderByDesc(RkExamQuestion::getCreateTime);

        Page<RkExamQuestion> selectPage = rkExamQuestionMapper.selectPage(page, queryWrapper);

        List<RkExamQuestion> records = selectPage.getRecords();

        Page<RkExamQuestionPageVO> voPage = new Page<>(pageNum, pageSize, selectPage.getTotal());
        voPage.setRecords(BeanUtil.copyToList(records, RkExamQuestionPageVO.class));

        return voPage;
    }
}
