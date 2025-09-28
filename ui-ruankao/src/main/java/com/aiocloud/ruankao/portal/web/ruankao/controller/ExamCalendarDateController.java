package com.aiocloud.ruankao.portal.web.ruankao.controller;

import com.aiocloud.common.base.common.CommonResponse;
import com.aiocloud.ruankao.portal.web.ruankao.service.ExamCalendarDateService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarDateTaskVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarDateVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarQuestionRelVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionDetailPageVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 软考日历日期控制器
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calendar-date")
public class ExamCalendarDateController {

    private final ExamCalendarDateService examCalendarDateService;

    /**
     * 创建日历日期内容
     */
    @PostMapping("/create")
    public CommonResponse<RkExamCalendarDateVO> createCalendarDate(@RequestBody RkExamCalendarDateVO calendarDate) {
        RkExamCalendarDateVO CommonResponse = examCalendarDateService.createCalendarDate(calendarDate);
        return new CommonResponse<>(CommonResponse);
    }

    /**
     * 根据ID获取日历日期内容
     */
    @GetMapping("/{id}")
    public CommonResponse<RkExamCalendarDateVO> getCalendarDateById(@PathVariable Long id) {
        RkExamCalendarDateVO CommonResponse = examCalendarDateService.getCalendarDateById(id);
        return new CommonResponse<>(CommonResponse);
    }

    /**
     * 更新日历日期内容
     */
    @PostMapping("/update")
    public CommonResponse<RkExamCalendarDateVO> updateCalendarDate(@RequestBody RkExamCalendarDateVO calendarDate) {
        RkExamCalendarDateVO CommonResponse = examCalendarDateService.updateCalendarDate(calendarDate);
        return new CommonResponse<>(CommonResponse);
    }

    /**
     * 删除日历日期内容
     */
    @DeleteMapping("/{id}")
    public CommonResponse<Boolean> deleteCalendarDate(@PathVariable Long id) {
        boolean CommonResponse = examCalendarDateService.deleteCalendarDate(id);
        return new CommonResponse<>(CommonResponse);
    }

    /**
     * 分页查询日历日期内容
     */
    @GetMapping("/page")
    public CommonResponse<Page<RkExamCalendarDateVO>> pageCalendarDates(
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "10") Long pageSize,
            @RequestParam(required = false) Date calendarDate) {
        Page<RkExamCalendarDateVO> CommonResponse = examCalendarDateService.pageCalendarDates(pageNum, pageSize, calendarDate);
        return new CommonResponse<>(CommonResponse);
    }

    /**
     * 根据日期查询所有相关内容
     */
    @GetMapping("/list-by-date")
    public CommonResponse<List<RkExamCalendarDateVO>> listByCalendarDate(
            @RequestParam(required = false) String calendarDateStr
    ) {
        List<RkExamCalendarDateVO> CommonResponse = examCalendarDateService.listByCalendarDate(calendarDateStr);
        return new CommonResponse<>(CommonResponse);
    }

    @GetMapping("/task/count")
    public CommonResponse<List<String>> getCalendarDateTaskCount(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return new CommonResponse<>(examCalendarDateService.getCalendarDateTaskCount(LocalDate.parse(startDate), LocalDate.parse(endDate)));
    }

    /**
     * 根据日期范围查询相关内容
     */
    @GetMapping("/list-by-range")
    public CommonResponse<List<RkExamCalendarDateVO>> listByDateRange(
            @RequestParam Date startDate,
            @RequestParam Date endDate
    ) {
        List<RkExamCalendarDateVO> CommonResponse = examCalendarDateService.listByDateRange(startDate, endDate);
        return new CommonResponse<>(CommonResponse);
    }

    /**
     * 保存日历日期关联的问题
     */
    @PostMapping("/{id}/questions")
    public CommonResponse<Boolean> saveCalendarDateQuestions(
            @PathVariable Long id,
            @RequestBody List<RkExamCalendarQuestionRelVO> relations) {
        boolean result = examCalendarDateService.saveCalendarDateQuestions(id, relations);
        return new CommonResponse<>(result);
    }

    /**
     * 获取日历日期关联的问题
     */
    @GetMapping("/{calendarId}/questions")
    public CommonResponse<Page<RkExamQuestionPageVO>> getCalendarDateQuestions(
            @PathVariable Long calendarId,
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "10") Long pageSize
    ) {
        return new CommonResponse<>(examCalendarDateService.getCalendarDateQuestions(calendarId, pageNum, pageSize));
    }

    @GetMapping("/{calendarId}/detail")
    public CommonResponse<Page<RkExamQuestionDetailPageVO>> getCalendarDateQuestionDetail(
            @PathVariable Long calendarId,
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "1") Long pageSize
    ) {
        return new CommonResponse<>(examCalendarDateService.getCalendarDateQuestionDetail(calendarId, pageNum, pageSize));
    }

    @GetMapping("/task/list-by-date")
    public CommonResponse<List<RkExamCalendarDateTaskVO>> getTaskListByDateRange(
            @RequestParam() String calendarDateStr
    ) {
        List<RkExamCalendarDateTaskVO> CommonResponse = examCalendarDateService.getTaskListByDateRange(calendarDateStr);
        return new CommonResponse<>(CommonResponse);
    }

    @GetMapping("/task/finish")
    public CommonResponse<Boolean> finishTask(
            @RequestParam() Long calendarId
    ) {
        return new CommonResponse<>(examCalendarDateService.finishTask(calendarId));
    }
}
