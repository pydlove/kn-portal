package com.aiocloud.kn.portal.web.ruankao.controller;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionArticle;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay;
import com.aiocloud.kn.portal.web.ruankao.service.QuestionService;
import com.aiocloud.kn.portal.web.ruankao.vo.ArticleQuestionVO;
import com.aiocloud.kn.portal.web.ruankao.vo.CaseQuestionVO;
import com.aiocloud.kn.portal.web.ruankao.vo.ChoiceQuestionVO;
import com.aiocloud.kn.portal.web.ruankao.vo.EssayQuestionVO;
import com.aiocloud.kn.portal.web.ruankao.vo.RkExamQuestionPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @description: QuestionController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 13:51
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 创建选择题
     *
     * @since 1.0.0
     *
     * @param: request
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @PostMapping("/choice")
    public CommonResponse<RkExamQuestion> createChoiceQuestion(@RequestBody ChoiceQuestionVO request) {
        return new CommonResponse<>(questionService.createChoiceQuestion(request.getQuestion(), request.getChoiceDetail()));
    }

    /**
     * 创建案例题
     *
     * @since 1.0.0
     *
     * @param: request
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @PostMapping("/case")
    public CommonResponse<RkExamQuestion> createCaseQuestion(@RequestBody CaseQuestionVO request) {
        return new CommonResponse<>(questionService.createCaseQuestion(request.getQuestion(), request.getCaseDetail()));
    }

    /**
     * 创建论文题
     *
     * @since 1.0.0
     *
     * @param: request
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @PostMapping("/essay")
    public CommonResponse<RkExamQuestion> createEssayQuestion(@RequestBody EssayQuestionVO request) {
        return new CommonResponse<>(questionService.createEssayQuestion(request.getQuestion(), request.getEssayDetail()));
    }

    @PostMapping("/article")
    public CommonResponse<RkExamQuestion> createArticleQuestion(@RequestBody ArticleQuestionVO request) {
        return new CommonResponse<>(questionService.createArticleQuestion(request.getQuestion(), request.getArticleDetail()));
    }


    /**
     * 查询题目
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @GetMapping("/{id}")
    public CommonResponse<RkExamQuestion> getQuestion(@PathVariable Long id) {
        return new CommonResponse<>(questionService.getQuestionById(id));
    }

    /**
     * 查询选择题详情
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @GetMapping("/{id}/choice")
    public CommonResponse<RkExamQuestionChoice> getChoiceDetail(@PathVariable Long id) {
        return new CommonResponse<>(questionService.getChoiceQuestionDetail(id));
    }

    /**
     * 查询案例题详情
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @GetMapping("/{id}/case")
    public CommonResponse<RkExamQuestionCase> getCaseDetail(@PathVariable Long id) {
        return new CommonResponse<>(questionService.getCaseQuestionDetail(id));
    }

    /**
     * 查询论文题详情
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @GetMapping("/{id}/essay")
    public CommonResponse<RkExamQuestionEssay> getEssayDetail(@PathVariable Long id) {
        return new CommonResponse<>(questionService.getEssayQuestionDetail(id));
    }

    @GetMapping("/{id}/article")
    public CommonResponse<RkExamQuestionArticle> getArticleDetail(@PathVariable Long id) {
        return new CommonResponse<>(questionService.getArticleQuestionDetail(id));
    }

    /**
     * 更新题目
     *
     * @since 1.0.0
     *
     * @param: id
     * @param: question
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestion
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @PutMapping("/{id}")
    public CommonResponse<RkExamQuestion> updateQuestion(@PathVariable Long id, @RequestBody RkExamQuestion question) {
        question.setId(id);
        return new CommonResponse<>(questionService.updateQuestion(question));
    }

    /**
     * 更新选择题详情
     *
     * @since 1.0.0
     *
     * @param: id
     * @param: choiceDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionChoice
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @PutMapping("/{id}/choice")
    public CommonResponse<RkExamQuestionChoice> updateChoiceDetail(@PathVariable Long id, @RequestBody RkExamQuestionChoice choiceDetail) {
        choiceDetail.setQuestionId(id);
        return new CommonResponse<>(questionService.updateChoiceQuestionDetail(choiceDetail));
    }

    /**
     * 更新案例题详情
     *
     * @since 1.0.0
     *
     * @param: id
     * @param: caseDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionCase
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:58
     */
    @PutMapping("/{id}/case")
    public CommonResponse<RkExamQuestionCase> updateCaseDetail(@PathVariable Long id, @RequestBody RkExamQuestionCase caseDetail) {
        caseDetail.setQuestionId(id);
        return new CommonResponse<>(questionService.updateCaseQuestionDetail(caseDetail));
    }

    /**
     * 更新论文题详情
     *
     * @since 1.0.0
     *
     * @param: id
     * @param: essayDetail
     * @return: com.aiocloud.kn.portal.dao.ruankao.domain.RkExamQuestionEssay
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:59
     */
    @PutMapping("/{id}/essay")
    public CommonResponse<RkExamQuestionEssay> updateEssayDetail(@PathVariable Long id, @RequestBody RkExamQuestionEssay essayDetail) {
        essayDetail.setQuestionId(id);
        return new CommonResponse<>(questionService.updateEssayQuestionDetail(essayDetail));
    }

    @PutMapping("/{id}/article")
    public CommonResponse<RkExamQuestionArticle> updateArticleDetail(@PathVariable Long id, @RequestBody RkExamQuestionArticle articleDetail) {
        articleDetail.setQuestionId(id);
        return new CommonResponse<>(questionService.updateArticleQuestionDetail(articleDetail));
    }


    /**
     * 删除题目
     *
     * @since 1.0.0
     *
     * @param: id
     * @return: boolean
     * @author: panyong
     * @version: 1.0.0
     * @createTime: 2025-09-23 13:59
     */
    @DeleteMapping("/{id}")
    public CommonResponse<Boolean> deleteQuestion(@PathVariable Long id) {
        return new CommonResponse<>(questionService.deleteQuestion(id));
    }
    @GetMapping("/page")
    public CommonResponse<Page<RkExamQuestionPageVO>> pageQuestions(
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer difficulty) {

        return new CommonResponse<>(questionService.pageQuestions(pageNum, pageSize, type, title, difficulty));
    }

}
