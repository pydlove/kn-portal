package com.aiocloud.ruankao.portal.web.ruankao.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiocloud.kn.portal.dao.ruankao.domain.RkExamCalendarQuestionRel;
import com.aiocloud.kn.portal.dao.ruankao.mapper.RkExamCalendarQuestionRelMapper;
import com.aiocloud.ruankao.portal.web.ruankao.service.ExamCalendarQuestionRelService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.RkExamCalendarQuestionRelVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @description: ExamCalendarQuestionRelServiceImpl.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-23 18:59
 */
@RequiredArgsConstructor
@Service
public class ExamCalendarQuestionRelServiceImpl implements ExamCalendarQuestionRelService {

    private final RkExamCalendarQuestionRelMapper rkExamCalendarQuestionRelMapper;

    @Override
    @Transactional
    public boolean batchSaveRelations(Long calendarId, List<RkExamCalendarQuestionRelVO> relations) {

        // 先删除原有的关联关系
        rkExamCalendarQuestionRelMapper.deleteByCalendarDateId(calendarId);

        // 批量插入新的关联关系
        for (RkExamCalendarQuestionRelVO relation : relations) {
            relation.setCalendarId(calendarId);
        }

        List<RkExamCalendarQuestionRel> rkExamCalendarQuestionRels = BeanUtil.copyToList(relations, RkExamCalendarQuestionRel.class);
        rkExamCalendarQuestionRelMapper.insertBatch(rkExamCalendarQuestionRels);

        return true;
    }

    @Override
    public Page<RkExamCalendarQuestionRelVO> pageByCalendarDateId(Long calendarId, Long pageNum, Long pageSize) {

        Page<RkExamCalendarQuestionRel> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<RkExamCalendarQuestionRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RkExamCalendarQuestionRel::getCalendarId, calendarId);
        Page<RkExamCalendarQuestionRel> relPage = rkExamCalendarQuestionRelMapper.selectPage(page, queryWrapper);

        List<RkExamCalendarQuestionRel> records = relPage.getRecords();
        List<RkExamCalendarQuestionRelVO> voList = BeanUtil.copyToList(records, RkExamCalendarQuestionRelVO.class);

        Page<RkExamCalendarQuestionRelVO> voPage = new Page<>(pageNum, pageSize, relPage.getTotal());
        voPage.setRecords(voList);

        return voPage;
    }

    @Override
    public boolean deleteByCalendarDateId(Long calendarDateId) {
        return rkExamCalendarQuestionRelMapper.deleteByCalendarDateId(calendarDateId) >= 0;
    }
}
