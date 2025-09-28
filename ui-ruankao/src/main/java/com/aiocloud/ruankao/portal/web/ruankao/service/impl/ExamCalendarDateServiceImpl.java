package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiocloud.common.base.exception.BadRequestException;
import com.aiocloud.common.base.exception.BizException;
import com.aiocloud.common.base.exception.ErrorCode;
import com.aiocloud.common.enums.DeletedStatusEnum;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamCalendarDate;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkUserCalendarRel;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamCalendarDateMapper;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkUserCalendarRelMapper;
import com.aiocloud.ruankao.portal.web.ruankao.service.ExamCalendarDateService;
import com.aiocloud.ruankao.portal.web.ruankao.service.ExamCalendarQuestionRelService;
import com.aiocloud.ruankao.portal.web.ruankao.service.QuestionService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarDateTaskVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarDateVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarQuestionRelVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionDetailPageVO;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamQuestionPageVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 软考日历日期服务实现
 * @description: ExamCalendarDateServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 17:47
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExamCalendarDateServiceImpl implements ExamCalendarDateService {

    private final RkExamCalendarDateMapper rkExamCalendarDateMapper;
    private final ExamCalendarQuestionRelService examCalendarQuestionRelService;
    private final QuestionService questionService;
    private final RkUserCalendarRelMapper rkUserCalendarRelMapper;

    @Override
    @Transactional
    public RkExamCalendarDateVO createCalendarDate(RkExamCalendarDateVO calendarDate) {
        rkExamCalendarDateMapper.insert(calendarDate);
        return calendarDate;
    }

    @Override
    public RkExamCalendarDateVO getCalendarDateById(Long id) {
        return BeanUtil.copyProperties(rkExamCalendarDateMapper.selectByPrimaryKey(id), RkExamCalendarDateVO.class);
    }

    @Override
    @Transactional
    public RkExamCalendarDateVO updateCalendarDate(RkExamCalendarDateVO calendarDate) {
        rkExamCalendarDateMapper.updateByPrimaryKeySelective(calendarDate);
        return calendarDate;
    }

    @Override
    @Transactional
    public boolean deleteCalendarDate(Long id) {
        // 逻辑删除
        RkExamCalendarDate calendarDate = rkExamCalendarDateMapper.selectByPrimaryKey(id);
        if (calendarDate == null) {
            return false;
        }

        calendarDate.setDeletedStatus(1);
        rkExamCalendarDateMapper.updateByPrimaryKeySelective(calendarDate);
        return true;
    }

    @Override
    public Page<RkExamCalendarDateVO> pageCalendarDates(Long pageNum, Long pageSize, Date calendarDate) {

        Page<RkExamCalendarDate> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<RkExamCalendarDate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(calendarDate != null, RkExamCalendarDate::getCalendarDate, calendarDate);
        queryWrapper.eq(RkExamCalendarDate::getDeletedStatus, 0);
        queryWrapper.orderByAsc(RkExamCalendarDate::getSortOrder);

        Page<RkExamCalendarDate> domainPage = rkExamCalendarDateMapper.selectPage(page, queryWrapper);
        Page<RkExamCalendarDateVO> voPage = new Page<>(
                domainPage.getCurrent(),
                domainPage.getSize(),
                domainPage.getTotal());

        voPage.setRecords(BeanUtil.copyToList(domainPage.getRecords(), RkExamCalendarDateVO.class));
        return voPage;
    }

    @Override
    public List<RkExamCalendarDateVO> listByCalendarDate(String calendarDate) {

        QueryWrapper<RkExamCalendarDate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("calendar_date", calendarDate);
        queryWrapper.eq("deleted_status", DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByAsc("sort_order");

        List<RkExamCalendarDate> rkExamCalendarDates = rkExamCalendarDateMapper.selectList(queryWrapper);
        List<RkExamCalendarDateVO> calendarDateVOList = BeanUtil.copyToList(rkExamCalendarDates, RkExamCalendarDateVO.class);
        for (RkExamCalendarDateVO rkExamCalendarDate : calendarDateVOList) {
            Page<RkExamQuestionPageVO> associatedQuestions = questionService.pageQuestions(rkExamCalendarDate.getId(), 1L, 10L);
            rkExamCalendarDate.setAssociatedQuestions(associatedQuestions);
        }

        return calendarDateVOList;
    }

    @Override
    public List<RkExamCalendarDateVO> listByDateRange(Date startDate, Date endDate) {

        LambdaQueryWrapper<RkExamCalendarDate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(RkExamCalendarDate::getCalendarDate, startDate);
        queryWrapper.le(RkExamCalendarDate::getCalendarDate, endDate);
        queryWrapper.eq(RkExamCalendarDate::getDeletedStatus, DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByAsc(RkExamCalendarDate::getCalendarDate)
                .orderByAsc(RkExamCalendarDate::getSortOrder);

        List<RkExamCalendarDate> rkExamCalendarDates = rkExamCalendarDateMapper.selectList(queryWrapper);

        return BeanUtil.copyToList(rkExamCalendarDates, RkExamCalendarDateVO.class);
    }

    @Override
    public List<RkExamCalendarDateTaskVO> getTaskListByDateRange(String calendarDateStr) {

        // 获取当前用户ID
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Long userId = (Long) request.getAttribute("userId");

        LambdaQueryWrapper<RkExamCalendarDate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RkExamCalendarDate::getCalendarDate, calendarDateStr);
        queryWrapper.eq(RkExamCalendarDate::getDeletedStatus, DeletedStatusEnum.NOT_DELETED.getCode());
        queryWrapper.orderByAsc(RkExamCalendarDate::getSortOrder);

        List<RkExamCalendarDate> rkExamCalendarDates = rkExamCalendarDateMapper.selectList(queryWrapper);
        List<RkExamCalendarDateTaskVO> taskVOList = BeanUtil.copyToList(rkExamCalendarDates, RkExamCalendarDateTaskVO.class);

        // 查询用户已完成的任务
        if (userId != null && !taskVOList.isEmpty()) {
            List<Long> calendarIds = taskVOList.stream()
                    .map(RkExamCalendarDateTaskVO::getId)
                    .collect(Collectors.toList());

            LambdaQueryWrapper<RkUserCalendarRel> relQueryWrapper = new LambdaQueryWrapper<>();
            relQueryWrapper.eq(RkUserCalendarRel::getUserId, userId);
            relQueryWrapper.in(RkUserCalendarRel::getCalendarId, calendarIds);

            List<RkUserCalendarRel> finishedTasks = rkUserCalendarRelMapper.selectList(relQueryWrapper);
            List<Long> finishedCalendarIds = finishedTasks.stream()
                    .map(RkUserCalendarRel::getCalendarId)
                    .toList();

            // 设置完成状态
            taskVOList.forEach(task -> {
                task.setIsFinished(finishedCalendarIds.contains(task.getId()));
            });
        }

        return taskVOList;
    }


    @Override
    @Transactional
    public boolean saveCalendarDateQuestions(Long calendarDateId, List<RkExamCalendarQuestionRelVO> relations) {
        return examCalendarQuestionRelService.batchSaveRelations(calendarDateId, relations);
    }

    @Override
    public Page<RkExamQuestionPageVO> getCalendarDateQuestions(Long calendarId, Long pageNum, Long pageSize) {
        return questionService.pageQuestions(calendarId, pageNum, pageSize);
    }

    @Override
    public Page<RkExamQuestionDetailPageVO> getCalendarDateQuestionDetail(Long calendarId, Long pageNum, Long pageSize) {
        return questionService.getQuestionDetailPage(calendarId, pageNum, pageSize);
    }

    @Override
    public List<String> getCalendarDateTaskCount(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> dates = rkExamCalendarDateMapper.selectTaskDates(startDate, endDate);

        return dates.stream()
                .map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Boolean finishTask(Long calendarId) {

        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            Long userId = (Long) request.getAttribute("userId");

            RkUserCalendarRel rkUserCalendarRel = new RkUserCalendarRel();
            rkUserCalendarRel.setCalendarId(calendarId);
            rkUserCalendarRel.setUserId(userId);
            rkUserCalendarRelMapper.insertSelective(rkUserCalendarRel);

            return true;
        } catch (Exception ex) {
            log.error("finish task error, cause by:", ex);
            throw new BadRequestException(ErrorCode.FINISH_TASK);
        }
    }
}
