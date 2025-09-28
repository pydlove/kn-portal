package com.aiocloud.ruankao.portal.web.ruankao.service;

import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarDateTaskVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarDateVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarQuestionRelVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionDetailPageVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionPageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @description: ExamCalendarDateService.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 17:47
 */
public interface ExamCalendarDateService {

    /**
     * 创建日历日期内容
     * @param calendarDate 日历日期内容
     * @return 创建后的对象
     */
    RkExamCalendarDateVO createCalendarDate(RkExamCalendarDateVO calendarDate);

    /**
     * 根据ID获取日历日期内容
     * @param id ID
     * @return 日历日期内容
     */
    RkExamCalendarDateVO getCalendarDateById(Long id);

    /**
     * 更新日历日期内容
     * @param calendarDate 更新的内容
     * @return 更新后的对象
     */
    RkExamCalendarDateVO updateCalendarDate(RkExamCalendarDateVO calendarDate);

    /**
     * 删除日历日期内容
     * @param id ID
     * @return 是否删除成功
     */
    boolean deleteCalendarDate(Long id);

    /**
     * 分页查询日历日期内容
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param calendarDate 日期
     * @return 分页结果
     */
    Page<RkExamCalendarDateVO> pageCalendarDates(Long pageNum, Long pageSize, Date calendarDate);

    /**
     * 根据日期查询所有相关内容
     * @param calendarDate 日期
     * @return 相关内容列表
     */
    List<RkExamCalendarDateVO> listByCalendarDate(String calendarDate);

    /**
     * 根据日期范围查询相关内容
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 相关内容列表
     */
    List<RkExamCalendarDateVO> listByDateRange(Date startDate, Date endDate);

    /**
     * 保存日历日期关联的问题
     * @param calendarDateId 日历日期ID
     * @param relations 关联关系列表
     * @return 是否保存成功
     */
    boolean saveCalendarDateQuestions(Long calendarDateId, List<RkExamCalendarQuestionRelVO> relations);

    /**
     * 获取日历日期关联的问题
     *
     * @param calendarId 日历日期ID
     * @param pageNum
     * @param pageSize
     * @return 关联问题列表
     */
    Page<RkExamQuestionPageVO> getCalendarDateQuestions(Long calendarId, Long pageNum, Long pageSize);

    List<RkExamCalendarDateTaskVO> getTaskListByDateRange(String calendarDateStr);

    Page<RkExamQuestionDetailPageVO> getCalendarDateQuestionDetail(Long calendarId, Long pageNum, Long pageSize);

    List<String> getCalendarDateTaskCount(LocalDate startDate, LocalDate endDate);

    Boolean finishTask(Long calendarId);
}
