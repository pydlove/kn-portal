package com.aiocloud.kn.portal.web.system.controller;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.web.system.service.InterviewTemplateQuestionRelService;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewQuestionsVO;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewTemplateQuestionRelVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @description: InterviewQuestionsController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:28
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/qt_rel")
public class InterviewTemplateQuestionRelController {

    private final InterviewTemplateQuestionRelService interviewTemplateQuestionRelService;

    @PostMapping("/create")
    public CommonResponse<KnInterviewTemplateQuestionRelVO> create(@RequestBody KnInterviewTemplateQuestionRelVO interviewTemplateQuestionRelVO) {
        return new CommonResponse<>(interviewTemplateQuestionRelService.create(interviewTemplateQuestionRelVO));
    }

    @PostMapping("/batchCreate")
    public CommonResponse<List<KnInterviewTemplateQuestionRelVO>> batchCreate(@RequestBody List<KnInterviewTemplateQuestionRelVO> interviewTemplateQuestionRelVOs) {
        return new CommonResponse<>(interviewTemplateQuestionRelService.batchCreate(interviewTemplateQuestionRelVOs));
    }

    @GetMapping("/template/{templateId}")
    public CommonResponse<List<KnInterviewTemplateQuestionRelVO>> getByTemplateId(@PathVariable Long templateId) {
        return new CommonResponse<>(interviewTemplateQuestionRelService.getByTemplateId(templateId));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Long> delete(@PathVariable Long id) {
        return new CommonResponse<>(interviewTemplateQuestionRelService.delete(id));
    }

    @PostMapping("/batchDelete")
    public CommonResponse<Integer> batchDelete(@RequestBody List<Long> ids) {
        return new CommonResponse<>(interviewTemplateQuestionRelService.batchDelete(ids));
    }
}
