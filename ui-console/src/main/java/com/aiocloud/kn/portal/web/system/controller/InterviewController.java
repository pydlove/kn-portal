package com.aiocloud.kn.portal.web.system.controller;

import com.aiocloud.kn.portal.base.common.CommonResponse;
import com.aiocloud.kn.portal.web.system.service.InterviewQuestionsService;
import com.aiocloud.kn.portal.web.system.service.InterviewService;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewQuestionsVO;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewVO;
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

import java.util.List;

/**
 *
 * @description: InterviewController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 19:28
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @GetMapping("/questions")
    public CommonResponse<List<KnInterviewVO>> getInterviewQuestions(@RequestParam(required = true) Integer level) {
        return new CommonResponse<>(interviewService.getInterviewQuestions(level));
    }

}
