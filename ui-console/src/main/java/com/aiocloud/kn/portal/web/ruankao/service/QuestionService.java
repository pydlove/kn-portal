package com.aiocloud.kn.portal.web.ruankao.service;

import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamCalendarQuestionRel;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionArticle;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay;
import com.aiocloud.kn.portal.web.ruankao.vo.RkExamQuestionDetailPageVO;
import com.aiocloud.kn.portal.web.ruankao.vo.RkExamQuestionPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface QuestionService {

    /**
     *  创建题目（通用）
     *
     * @since 1.0.0
     *
     * @param: question
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:56 
     */
    RkExamQuestion createQuestion(RkExamQuestion question);

    /**
     * 创建选择题
     *
     * @since 1.0.0
     *
     * @param: question
     * @param: choiceDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:56 
     */
    RkExamQuestion createChoiceQuestion(RkExamQuestion question, RkExamQuestionChoice choiceDetail);

    /**
     * 创建案例题
     *
     * @since 1.0.0
     *
     * @param: question
     * @param: caseDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:56 
     */
    RkExamQuestion createCaseQuestion(RkExamQuestion question, RkExamQuestionCase caseDetail);

    /**
     * 创建论文题
     *
     * @since 1.0.0
     *
     * @param: question
     * @param: essayDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57 
     */
    RkExamQuestion createEssayQuestion(RkExamQuestion question, RkExamQuestionEssay essayDetail);

    RkExamQuestion createArticleQuestion(RkExamQuestion question, RkExamQuestionArticle articleDetail);

    /**
     * 根据ID查询题目
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57 
     */
    RkExamQuestion getQuestionById(Long id);

    /**
     * 根据ID查询选择题详情
     *
     * @since 1.0.0
     *
     * @param: questionId
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestionChoice getChoiceQuestionDetail(Long questionId);

    /**
     * 根据ID查询案例题详情
     *
     * @since 1.0.0
     *
     * @param: questionId
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestionCase getCaseQuestionDetail(Long questionId);

    /**
     * 根据ID查询论文题详情
     *
     * @since 1.0.0
     *
     * @param: questionId
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestionEssay getEssayQuestionDetail(Long questionId);

    RkExamQuestionArticle getArticleQuestionDetail(Long questionId);

    /**
     * 更新题目
     *
     * @since 1.0.0
     *
     * @param: question
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestion updateQuestion(RkExamQuestion question);

    /**
     * 更新选择题详情
     *
     * @since 1.0.0
     *
     * @param: choiceDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestionChoice updateChoiceQuestionDetail(RkExamQuestionChoice choiceDetail);

    /**
     * 更新案例题详情
     *
     * @since 1.0.0
     *
     * @param: caseDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestionCase updateCaseQuestionDetail(RkExamQuestionCase caseDetail);

    /**
     * 更新论文题详情
     *
     * @since 1.0.0
     *
     * @param: essayDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    RkExamQuestionEssay updateEssayQuestionDetail(RkExamQuestionEssay essayDetail);

    RkExamQuestionArticle updateArticleQuestionDetail(RkExamQuestionArticle articleDetail);

    /**
     * 删除题目（逻辑删除）
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: boolean
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:57
     */
    boolean deleteQuestion(Long id);

    Page<RkExamQuestionPageVO> pageQuestions(Long pageNum, Long pageSize, String type, String title, Integer difficulty);

    Page<RkExamQuestionPageVO> pageQuestions(Long calendarId,Long pageNum, Long pageSize);


    Page<RkExamQuestionDetailPageVO> getQuestionDetailPage(Long calendarId, Long pageNum, Long pageSize);
}
