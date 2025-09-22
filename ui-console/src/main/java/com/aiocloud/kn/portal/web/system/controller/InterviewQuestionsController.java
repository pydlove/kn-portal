package com.aiocloud.kn.portal.web.system.controller;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.web.system.service.InterviewQuestionsService;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewQuestionsVO;
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
 * @description: InterviewQuestionsController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:28
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class InterviewQuestionsController {

    private final InterviewQuestionsService interviewQuestionsService;

    @GetMapping("/page")
    public CommonResponse<Page<KnInterviewQuestionsVO>> getInterviewQuestionsPage(
            @RequestParam(required = false) String questionText,
            @RequestParam(required = false) String difficultyLevel,
            @RequestParam(required = false) String categoryName,
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(
                interviewQuestionsService.getInterviewQuestionsPage(questionText, difficultyLevel, categoryName, pageNum, pageSize));
        }

    @PostMapping("/create")
    public CommonResponse<KnInterviewQuestionsVO> create(@RequestBody KnInterviewQuestionsVO interviewQuestionsVO) {
        return new CommonResponse<>(interviewQuestionsService.create(interviewQuestionsVO));
    }

    @PutMapping("/update/{id}")
    public CommonResponse<KnInterviewQuestionsVO> update(@PathVariable Long id, @RequestBody KnInterviewQuestionsVO interviewQuestionsVO) {
        return new CommonResponse<>(interviewQuestionsService.update(id, interviewQuestionsVO));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Long> delete(@PathVariable Long id) {
        return new CommonResponse<>(interviewQuestionsService.delete(id));
    }
}
