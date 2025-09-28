package com.aiocloud.kn.portal.web.system.controller;

import com.aiocloud.common.base.common.CommonResponse;
import com.aiocloud.kn.portal.web.system.service.InterviewTemplateService;
import com.aiocloud.kn.portal.web.system.vo.IdNameVO;
import com.aiocloud.kn.portal.web.system.vo.KnInterviewTemplateVO;
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
 * @description: InterviewTemplateController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-20 20:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/template")
public class InterviewTemplateController {

    private final InterviewTemplateService interviewTemplateService;

    @GetMapping("/page")
    public CommonResponse<Page<KnInterviewTemplateVO>> getInterviewTemplatePage(
            @RequestParam(required = false) String templateName,
            @RequestParam() Integer pageNum,
            @RequestParam() Integer pageSize
    ) {
        return new CommonResponse<>(interviewTemplateService.getInterviewTemplatePage(templateName, pageNum, pageSize));
    }

    @PostMapping("/create")
    public CommonResponse<KnInterviewTemplateVO> create(@RequestBody KnInterviewTemplateVO InterviewTemplateVO) {
        return new CommonResponse<>(interviewTemplateService.create(InterviewTemplateVO));
    }

    @PutMapping("/update/{id}")
    public CommonResponse<KnInterviewTemplateVO> update(@PathVariable Long id, @RequestBody KnInterviewTemplateVO InterviewTemplateVO) {
        return new CommonResponse<>(interviewTemplateService.update(id, InterviewTemplateVO));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse<Long> delete(@PathVariable Long id) {
        return new CommonResponse<>(interviewTemplateService.delete(id));
    }

    @GetMapping("/all")
    public CommonResponse<List<IdNameVO>> getAllTemplates() {
        return new CommonResponse<>(interviewTemplateService.getAllTemplates());
    }
}
